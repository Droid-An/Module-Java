package com.backlog;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class LibraryService {
    private final DataAccessLayer dataAccessLayer;
    private final PresentationLayer presentationLayer;

    public LibraryService(DataAccessLayer dataAccessLayer, PresentationLayer presentationLayer) {
        this.dataAccessLayer = dataAccessLayer;
        this.presentationLayer = presentationLayer;
    }

    public void saveBook(Book book) {
        if (checkDuplicated(book)) {
            presentationLayer.printSuccess("Book is already in the db");
        } else {
            dataAccessLayer.saveBook(book);
            presentationLayer.printSuccess("Book " + book.title() + " saved successfully");
        }
    }

    public boolean checkDuplicated(Book book) {
        return dataAccessLayer.checkBookExists(book);
    }

    public void borrowBook(Book book, User user) {
        if (dataAccessLayer.availableToBorrow(book)) {
            dataAccessLayer.borrow(book, user, LocalDate.now(), LocalDate.now().plusMonths(1));
            presentationLayer.printSuccess("Book " + book.title() + " has been borrowed by " + user.name());
        } else {
            presentationLayer.printSuccess("Book " + book.title() + " is already borrowed");
        }
    }

    public void returnBook(Book book) {
        if (canBeReturned(book)) {
            LocalDate returnBy = dataAccessLayer.getReturnByDate(book);
            if (returnBy.isBefore(LocalDate.now())) {
                int fine = calculateFine(returnBy);
                presentationLayer.printSuccess("User fined £" + fine);
            }
            dataAccessLayer.returnBook(book, LocalDate.now());
            presentationLayer.printSuccess("Book " + book.title() + " is returned successfully");
        }
    }

    private boolean canBeReturned(Book book) {
        if (!dataAccessLayer.checkBookExists(book)) {
            presentationLayer.printSuccess("There's no such book in the Database");
            return false;
        }
        if (dataAccessLayer.availableToBorrow(book)) {
            presentationLayer.printSuccess("This book is not currently borrowed");
            return false;
        }
        return true;
    }

    private int calculateFine(LocalDate returnBy) {
        return Math.toIntExact(ChronoUnit.DAYS.between(returnBy, LocalDate.now()));
    }

    public void listBorrowed(User user) {
        List<Book> books = dataAccessLayer.listBorrowed(user);
        if (books.isEmpty()) {
            presentationLayer.printSuccess("No books borrowed");
        } else {
            presentationLayer.printSuccess("Books borrowed by " + user.name() + ":");
            for (Book book : books) {
                presentationLayer.printSuccess(book.title() + " by " + book.author());
            }
        }
    }

    //deduplicate some checks if possible
    public void canBeBorrowed(String title) {
        if (!dataAccessLayer.checkBookExists(title)) {
            presentationLayer.printSuccess("No such books exist");
            return;
        }
        if (dataAccessLayer.availableToBorrow(title)) {
            presentationLayer.printSuccess("Book with title " + title + " can be borrowed");
        } else if (!dataAccessLayer.availableToBorrow(title)) {
            presentationLayer.printSuccess("Book with title " + title + " is borrowed by another user");
        }
    }
}

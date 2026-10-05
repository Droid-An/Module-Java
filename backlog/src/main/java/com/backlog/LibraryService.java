package com.backlog;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class LibraryService {

    public static void main(String[] args) {
        LibraryService libraryService = new LibraryService();
        PresentationLayer presentationLayer = new ConsolePrint();
        SQLliteDatabase db = new SQLliteDatabase("jdbc:sqlite:sample.db");
        db.initSchema();
        DataAccessLayer dataAccess = new DataAccess(db);

        Book book1 = new Book("fjhkdgf", "jgfknjkf");
        User user = new User("bobr");
        User user1 = new User("grogr");
        libraryService.saveBook(book1, dataAccess, presentationLayer);
        libraryService.borrowBook(book1, user, dataAccess, presentationLayer);
        libraryService.borrowBook(book1, user1, dataAccess, presentationLayer);
        libraryService.returnBook(book1, dataAccess, presentationLayer);
        libraryService.borrowBook(book1, user1, dataAccess, presentationLayer);

    }

    public void saveBook(Book book, DataAccessLayer dataAccessLayer, PresentationLayer presentationLayer) {
        if (checkDuplicated(book, dataAccessLayer)) {
            presentationLayer.printSuccess("Book is already in the db");
        } else {
            dataAccessLayer.saveBook(book);
            presentationLayer.printSuccess("Book " + book.getTitle() + " saved successfully");
        }
    }

    public boolean checkDuplicated(Book book, DataAccessLayer dataAccessLayer) {
        return dataAccessLayer.checkBookExists(book);
    }

    public void borrowBook(Book book, User user, DataAccessLayer dataAccessLayer, PresentationLayer presentationLayer) {
        if (dataAccessLayer.availableToBorrow(book)) {
            System.out.println("available to borrow");
            dataAccessLayer.borrow(book, user, LocalDate.now(), LocalDate.now().plusMonths(1));
            presentationLayer.printSuccess("Book " + book.getTitle() + " borrowed");
        } else {
            presentationLayer.printSuccess("Book " + book.getTitle() + " is already borrowed");
        }
    }

    public void returnBook(Book book, DataAccessLayer dataAccessLayer, PresentationLayer presentationLayer) {
        if (canBeReturned(book, dataAccessLayer, presentationLayer)) {
            LocalDate returnBy = dataAccessLayer.getReturnByDate(book);
            if (returnBy.isBefore(LocalDate.now())) {
                int fine = calculateFine(returnBy);
                presentationLayer.printSuccess("User fined £" + fine);
            }
            dataAccessLayer.returnBook(book, LocalDate.now());
            presentationLayer.printSuccess("Book " + book.getTitle() + " is returned successfully");
        }
    }

    private boolean canBeReturned(Book book, DataAccessLayer dataAccessLayer, PresentationLayer presentationLayer) {
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

    }

    public boolean isAvailableToBorrow(String title) {
        return false;
    }
}

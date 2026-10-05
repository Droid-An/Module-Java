package com.backlog;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class LibraryService {

    public static void main(String[] args) {
        LibraryService libraryService = new LibraryService();
        PresentationLayer presentationLayer = new ConsolePrint();
        SQLliteDatabase db = new SQLliteDatabase("jdbc:sqlite:sample.db");
        db.initSchema();
        DataAccessLayer dataAccess = new DataAccess(db);
        Book book1 = new Book("Java", "Ada Lovelace");
        Book book2 = new Book("Python", "Linus Torvalds");

//        create users with the same name as in schema
        User user1 = new User("bobr");
        User user2 = new User("grogr");

        //books can be saved
        libraryService.saveBook(book1, dataAccess, presentationLayer);
        libraryService.saveBook(book2, dataAccess, presentationLayer);

        //Duplicates can't be saved
        libraryService.saveBook(book1, dataAccess, presentationLayer);

        //books can be borrowed
        libraryService.borrowBook(book1, user1, dataAccess, presentationLayer);
        libraryService.borrowBook(book2, user1, dataAccess, presentationLayer);

        //Borrowed books can't be borrowed by somebody else
        libraryService.borrowBook(book1, user2, dataAccess, presentationLayer);

        //books can be returned
        libraryService.returnBook(book1, dataAccess, presentationLayer);

        //returned book can be borrowed again by others
        libraryService.borrowBook(book1, user2, dataAccess, presentationLayer);

        //list books borrowed by user (only active ones)
        libraryService.listBorrowed(user1, dataAccess, presentationLayer);

        //search book by title and show if book is available to order
        libraryService.canBeBorrowed("Java", dataAccess, presentationLayer);

        libraryService.returnBook(book2, dataAccess, presentationLayer);
        //search book by title and show if book is available to order
        libraryService.canBeBorrowed("Python", dataAccess, presentationLayer);

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
            dataAccessLayer.borrow(book, user, LocalDate.now(), LocalDate.now().plusMonths(1));
            presentationLayer.printSuccess("Book " + book.getTitle() + " has been borrowed by " + user.name());
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

    public void listBorrowed(User user, DataAccessLayer dataAccessLayer, PresentationLayer presentationLayer) {
        List<Book> books = dataAccessLayer.listBorrowed(user);
        if (books.isEmpty()) {
            presentationLayer.printSuccess("No books borrowed");
        } else {
            presentationLayer.printSuccess("Books borrowed by " + user.name() + ":");
            for (Book book : books) {
                presentationLayer.printSuccess(book.getTitle() + " by " + book.getAuthor());
            }
        }
    }

    //deduplicate some checks if possible
    public void canBeBorrowed(String title, DataAccessLayer dataAccessLayer, PresentationLayer presentationLayer) {
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

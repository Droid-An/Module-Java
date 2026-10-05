package com.backlog;

public class Demo {
    public static void main(String[] args) {

        PresentationLayer presentationLayer = new ConsolePrint();
        SQLliteDatabase db = new SQLliteDatabase("jdbc:sqlite:sample.db");
        db.initSchema();
        DataAccessLayer dataAccess = new DataAccess(db);

        LibraryService libraryService = new LibraryService(dataAccess, presentationLayer);

        Book book1 = new Book("Java", "Ada Lovelace");
        Book book2 = new Book("Python", "Linus Torvalds");

//        create users with the same name as in schema
        User user1 = new User("bobr");
        User user2 = new User("grogr");

        //books can be saved
        libraryService.saveBook(book1);
        libraryService.saveBook(book2);

        //Duplicates can't be saved
        libraryService.saveBook(book1);

        //books can be borrowed
        libraryService.borrowBook(book1, user1);
        libraryService.borrowBook(book2, user1);

        //Borrowed books can't be borrowed by somebody else
        libraryService.borrowBook(book1, user2);

        //books can be returned
        libraryService.returnBook(book1);

        //returned book can be borrowed again by others
        libraryService.borrowBook(book1, user2);

        //list books borrowed by user (only active ones)
        libraryService.listBorrowed(user1);

        //search book by title and show if book is available to order
        libraryService.canBeBorrowed("Java");

        libraryService.returnBook(book2);
        //search book by title and show if book is available to order
        libraryService.canBeBorrowed("Python");

    }
}

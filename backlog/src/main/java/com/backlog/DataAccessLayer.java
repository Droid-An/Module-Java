package com.backlog;

import java.time.LocalDate;
import java.util.List;

public interface DataAccessLayer {
    void saveBook(Book book);

    boolean checkBookExists(Book book);

    boolean availableToBorrow(Book book);

    void borrow(Book book, User user, LocalDate borrowDate, LocalDate returnByDate);

    void returnBook(Book book, LocalDate returnDate);

    List<Book> listBorrowed(User user);

    LocalDate getReturnByDate(Book book);
}

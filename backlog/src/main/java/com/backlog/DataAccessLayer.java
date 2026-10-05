package com.backlog;

import java.time.LocalDate;

public interface DataAccessLayer {
    void saveBook(Book book);

    boolean checkBookExists(Book book);

    boolean availableToBorrow(Book book);

    void borrow(Book book, User user, LocalDate borrowDate, LocalDate returnByDate);

    void returnBook(Book book, LocalDate returnDate);

    LocalDate getReturnByDate(Book book);
}

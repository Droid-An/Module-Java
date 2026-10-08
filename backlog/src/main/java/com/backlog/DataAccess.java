package com.backlog;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * SQLite-backed implementation of {@link DataAccessLayer}.
 *
 * <p>Each method opens its own connection through {@link SQLliteDatabase},
 * runs one statement and closes everything again. Dates are stored as ISO-8601
 * text ({@code yyyy-MM-dd})
 *
 * <p>A book is identified by its (author, title) pair. A borrow is "open"
 * while its {@code returned_date} column is {@code null}.
 */
public class DataAccess implements DataAccessLayer {

    private final SQLliteDatabase db;

    /**
     * Creates a data access object that uses the given database.
     */
    public DataAccess(SQLliteDatabase db) {
        this.db = db;
    }

    /**
     * Inserts a new book.
     *
     * @param book the book to save
     */
    @Override
    public void saveBook(Book book) {
        String sql = "insert into books (author, title) values (?, ?)";
        try (Connection c = db.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, book.author());
            ps.setString(2, book.title());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Checks whether a book with this exact author and title exists.
     *
     * @param book the book to look for
     * @return {@code true} if a matching row exists
     */
    @Override
    public boolean checkBookExists(Book book) {
        String sql = "select * from books where author = (?) and title = (?)";
        try (Connection c = db.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, book.author());
            ps.setString(2, book.title());
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Checks whether any book with this title exists, whatever its author.
     *
     * @param title the title to look for
     * @return {@code true} if at least one book has this title
     */
    @Override
    public boolean checkBookExists(String title) {
        String sql = "select * from books where title = (?)";
        try (Connection c = db.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, title);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Checks whether a book can be borrowed right now.
     *
     * <p>Only the most recent borrow of the book is considered: the book is
     * available if that borrow has a return date, or if the book was never borrowed.
     *
     * @param book the book to check
     * @return {@code true} if the book is on the shelf, {@code false} if it is currently borrowed
     */
    @Override
    public boolean availableToBorrow(Book book) {
        String sql = "select returned_date from borrows where book_id = (select id from books where author = (?) and title = (?))" +
                " order by id desc limit 1";
        try (Connection c = db.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, book.author());
            ps.setString(2, book.title());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String value = rs.getString("returned_date");
                    return value != null;
                }
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Checks whether a book can be borrowed right now, identifying it by title only.
     *
     * <p>Titles are considered to be unique for simplicity
     *
     * @param title the book's title
     * @return {@code true} if the book is on the shelf or was never borrowed
     */
    @Override
    public boolean availableToBorrow(String title) {
        String sql = "select returned_date from borrows where book_id = (select id from books where title = (?))" +
                " order by id desc limit 1";
        try (Connection c = db.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, title);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String value = rs.getString("returned_date");
                    return value != null;
                }
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Records that a user has borrowed a book.
     *
     * <p>The book and user are looked up by author/title and by name.
     *
     * @param book         the book being borrowed
     * @param user         the borrower
     * @param borrowDate   the day the book was taken out
     * @param returnByDate the day the book is due back
     */
    @Override
    public void borrow(Book book, User user, LocalDate borrowDate, LocalDate returnByDate) {
        String sql = """
                insert into borrows (book_id, user_id, borrow_date, return_by_date)
                values (
                    (select id from books where author = ? and title = ?),
                    (select id from users where name = ?),
                    ?, ?)
                """;
        try (Connection c = db.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, book.author());
            ps.setString(2, book.title());
            ps.setString(3, user.name());
            ps.setString(4, borrowDate.toString());
            ps.setString(5, returnByDate.toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Marks the open borrow of a book as returned.
     *
     * @param book       the book being returned
     * @param returnDate the day it was returned
     */
    @Override
    public void returnBook(Book book, LocalDate returnDate) {
        String sql = "update borrows set returned_date = (?) where book_id = ((select id from books where author = ? and title = ?)) and returned_date is null";
        try (Connection c = db.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, returnDate.toString());
            ps.setString(2, book.author());
            ps.setString(3, book.title());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Lists the books a user currently has on loan (borrows that are not yet returned).
     *
     * @param user the borrower
     * @return the borrowed books in no guaranteed order; an empty list if there are none
     */
    @Override
    public List<Book> listBorrowed(User user) {
        String sql = "select books.title, books.author from borrows join books on book_id = books.id where user_id = ((select id from users where name = ?)) and returned_date is null";
        List<Book> borrowedBooks = new ArrayList<>();
        try (Connection c = db.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, user.name());
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    borrowedBooks.add(new Book(rs.getString("title"), rs.getString("author")));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return borrowedBooks;
    }


    /**
     * Returns the due date of the book's current (open) borrow.
     *
     * @param book the borrowed book
     * @return the date the book must be returned by
     * @throws IllegalStateException if the book is not currently borrowed
     * @throws RuntimeException      wrapping any {@link SQLException}
     */
    @Override
    public LocalDate getReturnByDate(Book book) {
        String sql = "select return_by_date from borrows where book_id = ((select id from books where author = ? and title = ?)) and returned_date is null limit 1";
        try (Connection c = db.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, book.author());
            ps.setString(2, book.title());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return LocalDate.parse(rs.getString("return_by_date"));
                }
                throw new IllegalStateException("Book is not currently borrowed: " + book.title());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}

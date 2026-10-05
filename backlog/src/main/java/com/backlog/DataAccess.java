package com.backlog;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DataAccess implements DataAccessLayer {

    private final SQLliteDatabase db;

    public DataAccess(SQLliteDatabase db) {
        this.db = db;
    }

    @Override
    public void saveBook(Book book) {
        String sql = "insert into books (author, title) values (?, ?)";
        try (Connection c = db.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, book.getAuthor());
            ps.setString(2, book.getTitle());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean checkBookExists(Book book) {
        String sql = "select * from books where author = (?) and title = (?)";
        try (Connection c = db.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, book.getAuthor());
            ps.setString(2, book.getTitle());
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean checkBookExists(String title) {
        String sql = "select * from books where title = (?)";
        try (Connection c = db.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, title);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean availableToBorrow(Book book) {
        String sql = "select returned_date from borrows where book_id = (select id from books where author = (?) and title = (?))" +
                " order by id desc limit 1";
        try (Connection c = db.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, book.getAuthor());
            ps.setString(2, book.getTitle());
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
            ps.setString(1, book.getAuthor());
            ps.setString(2, book.getTitle());
            ps.setString(3, user.name());
            ps.setString(4, borrowDate.toString());
            ps.setString(5, returnByDate.toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void returnBook(Book book, LocalDate returnDate) {
        String sql = "update borrows set returned_date = (?) where book_id = ((select id from books where author = ? and title = ?)) and returned_date is null";
        try (Connection c = db.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, returnDate.toString());
            ps.setString(2, book.getAuthor());
            ps.setString(3, book.getTitle());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

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

    @Override
    public LocalDate getReturnByDate(Book book) {
        String sql = "select return_by_date from borrows where book_id = ((select id from books where author = ? and title = ?)) and returned_date is null limit 1";
        try (Connection c = db.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, book.getAuthor());
            ps.setString(2, book.getTitle());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return LocalDate.parse(rs.getString("return_by_date"));
                }
                throw new IllegalStateException("Book is not currently borrowed: " + book.getTitle());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}

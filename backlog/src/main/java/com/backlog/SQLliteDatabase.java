package com.backlog;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLliteDatabase {
    private final String url;

    public SQLliteDatabase(String url) {
        this.url = url;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url);
    }

    public void initSchema() {
        try (Connection c = getConnection(); Statement statement = c.createStatement()) {
            statement.execute("""
                    create table if not exists users (
                        id integer primary key autoincrement,
                        name text not null unique )""");
            statement.execute("""
                    create table if not exists books (
                        id integer primary key autoincrement,
                        author text not null,
                        title text not null, constraint unique_books unique (author,title))""");
            statement.execute("""
                    create table if not exists borrows (
                            id integer primary key autoincrement,
                        book_id integer not null references books(id),
                        user_id integer not null references users(id),
                        borrow_date text not null,
                        return_by_date text not null,
                        returned_date text)""");
            statement.executeUpdate("insert or ignore into users (name) values ('grogr')");
            statement.executeUpdate("insert or ignore into users (name )values ('bobr')");
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
    }
}
package com.backlog;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Instantiate a SQlite db connection, add columns and populate users
 */
public class SQLliteDatabase {
    private final String url;

    /**
     * Set up a url to connect to db
     * For local db use "jdbc:sqlite:sample.db"
     *
     * @param url
     */
    public SQLliteDatabase(String url) {
        this.url = url;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url);
    }

    /**
     * Set up db schema and populate two users named "grogr" and "bobr"
     */
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
package com.backlog;

public class Book {
    private int id;
    private final String title;
    private final String author;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }


    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }
}

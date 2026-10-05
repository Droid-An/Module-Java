package com.backlog;

public class Book {
    private final String title;
    private final String author;
    private int id;

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

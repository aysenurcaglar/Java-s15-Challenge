package com.workintech.library;

import java.util.ArrayList;
import java.util.List;

public class Author implements Person {
    private String name;
    private List<Book> books = new ArrayList<>();

    public Author(String name) {
        this.name = name;
    }

    public void addBook(Book book) {
        books.add(book);
    }

    @Override
    public String getName() {
        return name;
    }
}

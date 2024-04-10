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

    public void showBooks() {
        if (books.isEmpty()) {
            System.out.println("This author has no books in this library");
        } else {
            System.out.println("Books by " + name + ":");
            for (Book book : books) {
                System.out.println(" - " + book.getTitle());
            }
        }
    }
}

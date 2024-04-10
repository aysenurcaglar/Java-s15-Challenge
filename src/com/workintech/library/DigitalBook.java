package com.workintech.library;

public class DigitalBook extends Book {
    BookStatus status;

    public DigitalBook(String title, Author author, double price) {
        super(title, author, price);
        this.status = BookStatus.AVAILABLE;
    }

    @Override
    public void display() {
        System.out.println("Digital Book Details:");
        System.out.println("Book ID: " + getBookID());
        System.out.println("Title: " + getTitle());
        System.out.println("Author: " + getAuthor().getName());
        System.out.println("Price: $" + getPrice());
    }
}

package com.workintech.library;

public class DigitalBook extends Book {

    public DigitalBook(String title, Author author, double price) {
        super(title, author, price);
        this.setStatus(BookStatus.AVAILABLE);
    }

    @Override
    public void display() {
        System.out.println("Book ID: " + getBookID());
        System.out.println("Title: " + getTitle());
        System.out.println("Author: " + getAuthor().getName());
        System.out.println("Price: $" + getPrice());
        System.out.println("Type: Digital Book");
        System.out.println("Status: " + getStatus());
        System.out.println("———————————————");
    }
}

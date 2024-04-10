package com.workintech.library;

import java.util.Date;

public class PrintBook extends Book {
    public PrintBook(String title, Author author, double price, BookStatus status) {
        super(title, author, price, status);
    }

    public PrintBook(String title, Author author, double price, BookStatus status, Date dateOfBorrowing, String borrowerID) {
        super(title, author, price, status, dateOfBorrowing, borrowerID);
    }

    @Override
    public void display() {
        System.out.println("Print Book Details:");
        System.out.println("Book ID: " + getBookID());
        System.out.println("Title: " + getTitle());
        System.out.println("Author: " + getAuthor().getName());
        System.out.println("Price: $" + getPrice());
    }
}

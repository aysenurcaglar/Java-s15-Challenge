package com.workintech.library;

import java.time.LocalDate;
import java.util.Date;

public class PrintBook extends Book {
    public PrintBook(String title, Author author, double price, BookStatus status) {
        super(title, author, price, status);
    }

    public PrintBook(String title, Author author, double price, BookStatus status, LocalDate dateOfBorrowing, String borrowerID) {
        super(title, author, price, status, dateOfBorrowing, borrowerID);
    }

    @Override
    public void display() {
        System.out.println("Book ID: " + getBookID());
        System.out.println("Title: " + getTitle());
        System.out.println("Author: " + getAuthor().getName());
        System.out.println("Price: $" + getPrice());
        System.out.println("Type: Print Book");
        System.out.println("Status: " + getStatus());
        System.out.println("———————————————");
    }
}

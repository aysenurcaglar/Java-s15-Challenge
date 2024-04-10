package com.workintech.library;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public abstract class Book {
    private static int defBookID = 1000;
    private String bookID;
    private String title;
    private Author author;
    private double price;
    private BookStatus status;
    private Date dateOfBorrowing;
    private String borrowerID;

    public Book(String title, Author author, double price, BookStatus status) {
        this.bookID = generateBookID();
        this.title = title;
        this.author = author;
        this.price = price;
        this.status = status;
    }

    public Book(String title, Author author, double price, BookStatus status, Date dateOfBorrowing, String borrowerID) {
        this.bookID = generateBookID();
        this.title = title;
        this.author = author;
        this.price = price;
        this.status = status;
        this.dateOfBorrowing = dateOfBorrowing;
        this.borrowerID = borrowerID;
    }

    public Book(String title, Author author, double price) {
        this.bookID = generateBookID();
        this.title = title;
        this.author = author;
        this.price = price;
    }

    private String generateBookID() {
        return "B" + Book.defBookID++;
    }

    public String getBookID() {
        return bookID;
    }

    public String getTitle() {
        return title;
    }

    public Author getAuthor() {
        return author;
    }

    public double getPrice() {
        return price;
    }

    public BookStatus getStatus() {
        return status;
    }

    public Date getDateOfBorrowing() {
        return dateOfBorrowing;
    }

    public String getBorrowerID() {
        return borrowerID;
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setStatus(BookStatus status) {
        this.status = status;
    }

    public void setDateOfBorrowing(Date dateOfBorrowing) {
        this.dateOfBorrowing = dateOfBorrowing;
    }

    public void setBorrowerID(String borrowerID) {
        this.borrowerID = borrowerID;
    }

    public abstract void display();

}

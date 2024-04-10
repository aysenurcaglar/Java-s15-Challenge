package com.workintech.library;

public interface Library {
    void newPrintBook(String title, String authorName, double price, BookStatus status);
    void newDigitalBook(String title, String authorName, double price);
    void showBook(String bookID);
    void searchByAuthor(String authorName);
    void searchByTitle(String title);
    boolean authenticate(String memberID, String password);
}

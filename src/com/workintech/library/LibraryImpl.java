package com.workintech.library;

import java.util.HashMap;
import java.util.Map;

public class LibraryImpl implements Library {
    protected Map<String, Book> books = new HashMap<>();
    protected Map<String, Reader> members = new HashMap<>();
    protected Map<String, String> memberCredentials = new HashMap<>();


    @Override
    public void newPrintBook(String title, String authorName, double price, BookStatus status) {
        Author author = new Author(authorName);
        Book book = new PrintBook(title, author, price, status);
        books.put(book.getBookID(), book);
    }

    @Override
    public void newDigitalBook(String title, String authorName, double price) {
        Author author = new Author(authorName);
        Book book = new DigitalBook(title, author, price);
        books.put(book.getBookID(), book);
    }

    @Override
    public void showBook(String bookID) {
        Book book = books.get(bookID);
        if (book != null) {
            book.display();
        } else {
            System.out.println("Book not found.");
        }
    }

    @Override
    public void searchByAuthor(String authorName) {
        boolean found = false;
        for (Book book : books.values()) {
            if (book.getAuthor().getName().equalsIgnoreCase(authorName)) {
                book.display();
                found = true;
            }
        }
        if (!found) {
            System.out.println("No books found for author: " + authorName);
        }
    }

    @Override
    public void searchByTitle(String title) {
        boolean found = false;
        for (Book book : books.values()) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                book.display();
                found = true;
            }
        }
        if (!found) {
            System.out.println("No books found with the title: " + title);
        }
    }

    public Map<String, Book> getBooks() {
        return books;
    }

    public void setBooks(Map<String, Book> books) {
        this.books = books;
    }

    public Map<String, Reader> getMembers() {
        return members;
    }

    public void setMembers(Map<String, Reader> members) {
        this.members = members;
    }

    public Map<String, String> getMemberCredentials() {
        return memberCredentials;
    }

    public void setMemberCredentials(Map<String, String> memberCredentials) {
        this.memberCredentials = memberCredentials;
    }

    @Override
    public boolean authenticate(String memberID, String password) {
        if (memberCredentials.containsKey(memberID)) {
            return memberCredentials.get(memberID).equals(password);
        }
        return false;
    }
}

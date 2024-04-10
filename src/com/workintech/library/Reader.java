package com.workintech.library;

import java.util.Date;

public class Reader implements Person {
    private static int defMemberID = 1000; // Static variable to generate member IDs
    private String memberID;
    private String name;
    private MemberType type;
    private Date dateOfMembership;
    private int noOfBooksIssued;
    private int maxBookLimit;

    public Reader(String name, MemberType type) {
        this.memberID = generateMemberID();
        this.name = name;
        this.type = type;
        this.dateOfMembership = new Date(); // Set to current date
        this.noOfBooksIssued = 0;
        this.maxBookLimit = 5;
    }

    private String generateMemberID() {
        return "M" + Reader.defMemberID++;
    }

    public boolean login(String password, Library library) {
        if (library.authenticate(memberID, password)) {
            return true;
        } else {
            return false;
        }
    }

    public void borrowBook(String bookID, LibraryImpl library) {
        if (noOfBooksIssued >= maxBookLimit) {
            System.out.println("You have reached the maximum borrowing limit.");
            return;
        }

        // bu limit bug'ını çöz

        // Check if book is available
        Book book = library.books.get(bookID);
        if (book == null) {
            System.out.println("Book not found.");
            return;
        }

        if (book.getStatus() == BookStatus.BORROWED) {
            System.out.println("This book is already borrowed by someone else.");
            return;
        }

        // Update book status and borrower information
        book.setStatus(BookStatus.BORROWED);
        book.setDateOfBorrowing(new Date());
        book.setBorrowerID(this.memberID);
        noOfBooksIssued++;
        maxBookLimit--;
        System.out.println("Book borrowed successfully!");
    }

    public void returnBook(String bookID, LibraryImpl library) {
        // Check if book exists
        Book book = library.books.get(bookID);
        if (book == null) {
            System.out.println("Book not found.");
            return;
        }

        // Check if the member has borrowed the book
        if (!memberID.equals(book.getBorrowerID())) {
            System.out.println("You have not borrowed this book.");
            return;
        }

        // Update book status and borrower information
        book.setStatus(BookStatus.AVAILABLE);
        book.setBorrowerID(null);
        book.setDateOfBorrowing(null);
        noOfBooksIssued--;
        maxBookLimit++;
        System.out.println("Book returned successfully!");
    }

    public void showMembershipInfo() {
        System.out.println("Name: " + this.name);
        System.out.println("Member ID: " + this.memberID);
        System.out.println("Member Type: " + this.type);
        System.out.println("Number of Books Borrowed: " + this.noOfBooksIssued);
        System.out.println("Maximum Borrowing Limit: " + this.maxBookLimit);
        System.out.println("Membership Since: " + this.dateOfMembership);
    }


    @Override
    public String getName() {
        return name;
    }

    public String getMemberID() {
        return memberID;
    }

    public void setMemberID(String memberID) {
        this.memberID = memberID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MemberType getType() {
        return type;
    }

    public void setType(MemberType type) {
        this.type = type;
    }

    public Date getDateOfMembership() {
        return dateOfMembership;
    }

    public void setDateOfMembership(Date dateOfMembership) {
        this.dateOfMembership = dateOfMembership;
    }

    public int getNoOfBooksIssued() {
        return noOfBooksIssued;
    }

    public void setNoOfBooksIssued(int noOfBooksIssued) {
        this.noOfBooksIssued = noOfBooksIssued;
    }

    public int getMaxBookLimit() {
        return maxBookLimit;
    }

    public void setMaxBookLimit(int maxBookLimit) {
        this.maxBookLimit = maxBookLimit;
    }
}

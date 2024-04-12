package com.workintech.library;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Reader implements Person {
    private static int defMemberID = 1000; // Static variable to generate member IDs
    private String memberID;
    private String name;
    private String password;
    private MemberType type;
    private LocalDate dateOfMembership;
    private int noOfBooksIssued;
    private int maxBookLimit;

    public Reader(String name, MemberType type, String password) {
        this.memberID = generateMemberID();
        this.name = name;
        this.password = password;
        this.type = type;
        this.dateOfMembership = LocalDate.now(); // Set to current date
        this.noOfBooksIssued = 0;
        this.maxBookLimit = 5;
    }

    private String generateMemberID() {
        return "M" + Reader.defMemberID++;
    }

    public boolean login(Library library) {
        return library.authenticate(memberID, password);
    }

    public void borrowBook(String bookID, LibraryImpl library) {

        Book book = library.books.get(bookID);

        int remainingLimit = maxBookLimit - noOfBooksIssued;

        if (remainingLimit <= 0) {
            System.out.println("You have reached the maximum borrowing limit.");
        } else {
            if (book == null) {
                System.out.println("Book not found.");
            } else if (book.getStatus() == BookStatus.BORROWED) {
                System.out.println("This book is already borrowed by someone else.");
            } else {
                book.setStatus(BookStatus.BORROWED);
                book.setDateOfBorrowing(LocalDate.now());
                book.setBorrowerID(this.memberID);
                noOfBooksIssued++;
                System.out.println(book.getTitle() + " borrowed successfully!");
            }
        }
    }

    public void returnBook(String bookID, LibraryImpl library) {

        Book book = library.books.get(bookID);

        if (book == null) {
            System.out.println("Book not found.");
        } else if (!memberID.equals(book.getBorrowerID())) {
            System.out.println("You have not borrowed " + book.getTitle() + ".");
        } else {
            book.setStatus(BookStatus.AVAILABLE);
            book.setBorrowerID(null);
            book.setDateOfBorrowing(null);
            noOfBooksIssued--;
            System.out.println(book.getTitle() + " returned successfully!");
        }
    }

    public void showMembershipInfo() {
        int remainingLimit = maxBookLimit - noOfBooksIssued;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MM yyyy");
        String formattedDate = dateOfMembership.format(formatter);

        System.out.println("Name: " + this.name);
        System.out.println("Member ID: " + this.memberID);
        System.out.println("Member Type: " + this.type);
        System.out.println("Number of Books Borrowed: " + this.noOfBooksIssued);
        System.out.println("Maximum Borrowing Limit: " + remainingLimit);
        System.out.println("Membership Since: " + formattedDate);
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public MemberType getType() {
        return type;
    }

    public void setType(MemberType type) {
        this.type = type;
    }

    public LocalDate getDateOfMembership() {
        return dateOfMembership;
    }

    public void setDateOfMembership(LocalDate dateOfMembership) {
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

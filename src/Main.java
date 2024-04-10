import com.workintech.library.BookStatus;
import com.workintech.library.LibraryImpl;
import com.workintech.library.MemberType;
import com.workintech.library.Reader;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LibraryImpl library = new LibraryImpl();

        library.newPrintBook("The Lord of the Rings", "J.R.R. Tolkien", 19.95, BookStatus.AVAILABLE);
        library.newPrintBook("Pride and Prejudice", "Jane Austen", 15.50, BookStatus.AVAILABLE);
        library.newDigitalBook("Foundation", "Isaac Asimov", 12.99);
        library.newPrintBook("The Hitchhiker's Guide to the Galaxy", "Douglas Adams", 9.99, BookStatus.BORROWED);
        library.newDigitalBook("1984", "George Orwell", 10.99);
        library.newPrintBook("To Kill a Mockingbird", "Harper Lee", 14.50, BookStatus.BORROWED);
        library.newPrintBook("The Book Thief", "Markus Zusak", 16.95, BookStatus.AVAILABLE);
        library.newPrintBook("The Great Gatsby", "F. Scott Fitzgerald", 13.50, BookStatus.BORROWED);
        library.newPrintBook("Dune", "Frank Herbert", 22.50, BookStatus.AVAILABLE);
        library.newPrintBook("The Handmaid's Tale", "Margaret Atwood", 14.99, BookStatus.AVAILABLE);
        library.newDigitalBook("Tales from Earthsea", "Ursula K. Le Guin", 9.99);
        library.newDigitalBook("On the Genealogy of Morality", "Friedrich Nietzsche", 10.99);
        library.newDigitalBook("Ways of Seeing", "John Berger", 12.99);


        boolean running = true;
        Reader currentReader = null;

        while (running) {
            if (currentReader == null) {
                System.out.println("1. Register"); // Registration now comes first
                System.out.println("2. Login");
                System.out.println("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine();

                if (choice == 1) {
                    // Registration Process
                    System.out.println("Enter your name: ");
                    String name = scanner.nextLine();

                    System.out.println("Choose your member type:");
                    for (int i = 0; i < MemberType.values().length; i++) {
                        System.out.println((i + 1) + ". " + MemberType.values()[i]);
                    }
                    int typeChoice = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    MemberType type = MemberType.values()[typeChoice - 1];

                    System.out.println("Enter your password: ");
                    String newPassword = scanner.nextLine();

                    // Loop to ensure unique member ID generation
                    Reader newReader = null; // Initialize outside the loop
                    do {
                        if (newReader != null) {
                            System.out.println("Member ID already exists. Generating a new one...");
                        }
                        newReader = new Reader(name, type);
                    } while (library.getMembers().containsKey(newReader.getMemberID()));

                    library.getMembers().put(newReader.getMemberID(), newReader);
                    library.getMemberCredentials().put(newReader.getMemberID(), newPassword);

                    System.out.println("Registration successful! Your Member ID is " + newReader.getMemberID());
                    System.out.println("Please keep this information for future logins.");

                } else if (choice == 2) {
                    System.out.println("Enter Member ID: ");
                    String memberID = scanner.nextLine();
                    System.out.println("Enter Password: ");
                    String password = scanner.nextLine();

                    if (!library.authenticate(memberID, password)) { // Use library to authenticate
                        System.out.println("Invalid credentials. Please try again.");
                        currentReader = null; // Reset on failed login
                        continue;
                    } else {
                        currentReader = library.getMembers().get(memberID); // Get reader on successful login
                    }

                } else {
                    System.out.println("Invalid Choice");
                }
            } else {
                // Successful login
                System.out.println("Welcome " + currentReader.getType() + " " + currentReader.getName() + ".");
                // Display menu options
                System.out.println("1. Search Book by Title");
                System.out.println("2. Search Book by Author");
                System.out.println("3. Borrow Book");
                System.out.println("4. Return Book");
                System.out.println("5. Show Membership Information");
                System.out.println("6. Exit");
                System.out.println("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        // Search book by title
                        System.out.println("Enter Book Title: ");
                        String title = scanner.nextLine();
                        library.searchByTitle(title);
                        break;
                    case 2:
                        // Search book by author
                        System.out.println("Enter Author's Name: ");
                        String authorName = scanner.nextLine();
                        library.searchByAuthor(authorName);
                        break;
                    case 3:
                        // Borrow a book
                        System.out.println("Enter Book ID to borrow: ");
                        String bookID = scanner.nextLine();
                        currentReader.borrowBook(bookID, library);
                        break;
                    case 4:
                        // Return a book
                        System.out.println("Enter Book ID to return: ");
                        bookID = scanner.nextLine();
                        currentReader.returnBook(bookID, library);
                        break;
                    case 5:
                        // Show membership information
                        currentReader.showMembershipInfo();
                        break;
                    case 6:
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid choice!");
                }
            }
        }

        scanner.close();
    }
}
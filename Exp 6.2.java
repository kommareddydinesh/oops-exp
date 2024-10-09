<<< Exp 6.2 >>>

LIBRARY PROGRAM


package pkg1;
public class Book {
    private String title;
    private String author;
    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }
    public String getTitle() {
        return title;
    }
    public String getAuthor() {
        return author;
    }
}

// pkg1/Library.java
package pkg1;

public abstract class Library {
    public abstract void addBook(String title, String author);
}





// pkg2/Books.java
package pkg2;
import java.util.ArrayList;
import java.util.List;
import pkg1.Book;
import pkg1.Library;
public class Books extends Library {
    private List<Book> bookList;
    public Books() {
        this.bookList = new ArrayList<>();
    }

    @Override
    public void addBook(String title, String author) {
        bookList.add(new Book(title, author));
        System.out.println("Book '" + title + "' by " + author + " added successfully.");
    }

    public Book searchBook(String title) {
        for (Book book : bookList) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null;
    }

    public void listBooks() {
        if (bookList.isEmpty()) {
            System.out.println("No books available.");
        } else {
            System.out.println("Available Books:");
            for (Book book : bookList) {
                System.out.println("- " + book.getTitle() + " by " + book.getAuthor());
            }
        }
    }
}






// pkg3/Admin.java
package pkg3;
import pkg2.Books;
public class Admin {
    private Books books;
    public Admin() {
        this.books = new Books();
    }
    public void addBook(String title, String author) {
        books.addBook(title, author);
    }
    public Books getBooks() {
        return books;
    }
}


// pkg3/User.java
package pkg3;
import pkg1.Book;
import pkg2.Books;
public class User {
    private Books books;
    public User(Books books) {
        this.books = books;
    }
    public void searchBook(String title) {
        Book book = books.searchBook(title);
        if (book != null) {
            System.out.println("Found: " + book.getTitle() + " by " + book.getAuthor());
        } else {
            System.out.println("Book '" + title + "' not found.");
        }
    }

    public void listBooks() {
        books.listBooks();
    }
}





package Test;
import pkg3.Admin;
import pkg3.User;
import java.util.Scanner;
public class Test {
    public static void main(String[] args) {
        Admin admin = new Admin();
        User user = new User(admin.getBooks());
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\nLibrary Information System");
            System.out.println("1. Add Book");
            System.out.println("2. Search Book");
            System.out.println("3. List Books");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            switch (choice) {
                case 1:
                    System.out.print("Enter book title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter book author: ");
                    String author = scanner.nextLine();
                    admin.addBook(title, author);
                    break;
                case 2:
                    System.out.print("Enter book title to search: ");
                    title = scanner.nextLine();
                    user.searchBook(title);
                    break;
                case 3:
                    user.listBooks();
                    break;
                case 4:
                    System.out.println("Exiting the Library Information System.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 4);
        scanner.close();
    }
}


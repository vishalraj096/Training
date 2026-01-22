import java.util.*;

class Book {
    private final String title;
    private final String author;

    Book(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    @Override
    public String toString() {
        return "Book{" + "title='" + title + "'" + ", author='" + author + "'" + '}';
    }
}

class Library {
    private final String name;
    private final List<Book> books = new ArrayList<>();

    Library(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public boolean removeBook(Book book) {
        return books.remove(book);
    }

    public List<Book> getBooks() {
        return Collections.unmodifiableList(books);
    }

    public void printInventory() {
        System.out.println("Library '" + name + "' inventory:");
        if (books.isEmpty()) {
            System.out.println("  (no books)");
        } else {
            for (Book b : books) {
                System.out.println("  - " + b);
            }
        }
    }
}

public class LibraryBooksAggregation {
    public static void main(String[] args) {
        Book b1 = new Book("Clean Code", "Robert C. Martin");
        Book b2 = new Book("Effective Java", "Joshua Bloch");
        Book b3 = new Book("Design Patterns", "Gamma et al.");

        Library cityLibrary = new Library("City Library");
        Library campusLibrary = new Library("Campus Library");

        cityLibrary.addBook(b1);
        cityLibrary.addBook(b2);
        campusLibrary.addBook(b2);
        campusLibrary.addBook(b3);

        cityLibrary.printInventory();
        campusLibrary.printInventory();

        cityLibrary.removeBook(b1);
        System.out.println("\nRemoved '" + b1.getTitle() + "' from City Library. The book still exists: " + b1);
        cityLibrary.printInventory();
    }
}

import java.util.*;

abstract class LibraryItem {
    private int itemId;
    private String title;
    private String author;

    public LibraryItem(int itemId, String title, String author) {
        this.itemId = itemId;
        this.title = title;
        this.author = author;
    }
    public int getItemId() { return itemId; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public void setItemId(int itemId) { this.itemId = itemId; }
    public void setTitle(String title) { this.title = title; }
    public void setAuthor(String author) { this.author = author; }
    public abstract int getLoanDuration();
    public String getItemDetails() {
        return "ID: " + itemId + ", Title: " + title + ", Author: " + author;
    }
}

interface Reservable {
    void reserveItem(String borrower);
    boolean checkAvailability();
}

class Book extends LibraryItem implements Reservable {
    private boolean available = true;
    public Book(int itemId, String title, String author) {
        super(itemId, title, author);
    }
    @Override
    public int getLoanDuration() { return 21; }
    @Override
    public void reserveItem(String borrower) { available = false; }
    @Override
    public boolean checkAvailability() { return available; }
}

class Magazine extends LibraryItem implements Reservable {
    private boolean available = true;
    public Magazine(int itemId, String title, String author) {
        super(itemId, title, author);
    }
    @Override
    public int getLoanDuration() { return 7; }
    @Override
    public void reserveItem(String borrower) { available = false; }
    @Override
    public boolean checkAvailability() { return available; }
}

class DVD extends LibraryItem implements Reservable {
    private boolean available = true;
    public DVD(int itemId, String title, String author) {
        super(itemId, title, author);
    }
    @Override
    public int getLoanDuration() { return 14; }
    @Override
    public void reserveItem(String borrower) { available = false; }
    @Override
    public boolean checkAvailability() { return available; }
}

public class LibraryManagementSystem {
    public static void main(String[] args) {
        List<LibraryItem> items = new ArrayList<>();
        items.add(new Book(1, "Java Basics", "John Doe"));
        items.add(new Magazine(2, "Tech Today", "Jane Smith"));
        items.add(new DVD(3, "OOP Concepts", "Alice Brown"));
        for (LibraryItem item : items) {
            System.out.println(item.getItemDetails());
            System.out.println("Loan Duration: " + item.getLoanDuration() + " days");
            if (item instanceof Reservable reservable) {
                System.out.println("Available: " + reservable.checkAvailability());
            }
            System.out.println();
        }
    }
}

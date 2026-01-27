import java.util.*;

public class LibraryManagementSystem {
    static class Book {
        String title;
        String author;
        String genre;
        int id;
        boolean available;
        Book prev, next;

        Book(String title, String author, String genre, int id, boolean available) {
            this.title = title;
            this.author = author;
            this.genre = genre;
            this.id = id;
            this.available = available;
        }
    }

    static class DoublyList {
        Book head, tail;
        int count = 0;

        void addAtBeginning(Book b) {
            if (head == null) {
                head = tail = b;
                count++;
                return;
            }
            b.next = head;
            head.prev = b;
            head = b;
            count++;
        }

        void addAtEnd(Book b) {
            if (tail == null) {
                head = tail = b;
                count++;
                return;
            }
            tail.next = b;
            b.prev = tail;
            tail = b;
            count++;
        }

        void addAtPosition(Book b, int pos) {
            if (pos <= 1 || head == null) {
                addAtBeginning(b);
                return;
            }
            Book c = head;
            int i = 1;
            while (c.next != null && i < pos - 1) {
                c = c.next;
                i++;
            }
            b.next = c.next;
            b.prev = c;
            if (c.next != null)
                c.next.prev = b;
            else
                tail = b;
            c.next = b;
            count++;
        }

        boolean removeById(int id) {
            Book c = head;
            while (c != null && c.id != id)
                c = c.next;
            if (c == null)
                return false;
            if (c.prev != null)
                c.prev.next = c.next;
            else
                head = c.next;
            if (c.next != null)
                c.next.prev = c.prev;
            else
                tail = c.prev;
            c.next = c.prev = null;
            count--;
            return true;
        }

        List<Book> searchByTitle(String title) {
            List<Book> r = new ArrayList<>();
            for (Book c = head; c != null; c = c.next)
                if (c.title.equalsIgnoreCase(title))
                    r.add(c);
            return r;
        }

        List<Book> searchByAuthor(String author) {
            List<Book> r = new ArrayList<>();
            for (Book c = head; c != null; c = c.next)
                if (c.author.equalsIgnoreCase(author))
                    r.add(c);
            return r;
        }

        boolean updateAvailability(int id, boolean avail) {
            for (Book c = head; c != null; c = c.next)
                if (c.id == id) {
                    c.available = avail;
                    return true;
                }
            return false;
        }

        void displayForward() {
            if (head == null) {
                System.out.println("No books.");
                return;
            }
            for (Book c = head; c != null; c = c.next)
                print(c);
        }

        void displayReverse() {
            if (tail == null) {
                System.out.println("No books.");
                return;
            }
            for (Book c = tail; c != null; c = c.prev)
                print(c);
        }

        int count() {
            return count;
        }

        void print(Book b) {
            System.out.printf("ID:%d Title:%s Author:%s Genre:%s Available:%s%n", b.id, b.title, b.author, b.genre,
                    b.available ? "Yes" : "No");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        DoublyList list = new DoublyList();
        while (true) {
            System.out.println("\nLibrary Management (Doubly Linked List)");
            System.out.println("1. Add at beginning");
            System.out.println("2. Add at end");
            System.out.println("3. Add at position");
            System.out.println("4. Remove by Book ID");
            System.out.println("5. Search by Title");
            System.out.println("6. Search by Author");
            System.out.println("7. Update Availability");
            System.out.println("8. Display forward");
            System.out.println("9. Display reverse");
            System.out.println("10. Count books");
            System.out.println("0. Exit");
            System.out.print("Choose: ");
            int ch = safeInt(sc);
            if (ch == 0)
                break;
            switch (ch) {
                case 1 -> list.addAtBeginning(readBook(sc));
                case 2 -> list.addAtEnd(readBook(sc));
                case 3 -> {
                    System.out.print("Position: ");
                    int p = safeInt(sc);
                    list.addAtPosition(readBook(sc), p);
                }
                case 4 -> {
                    System.out.print("Book ID: ");
                    int id = safeInt(sc);
                    System.out.println(list.removeById(id) ? "Removed" : "Not found");
                }
                case 5 -> {
                    System.out.print("Title: ");
                    String t = sc.nextLine().trim();
                    printBooks(list.searchByTitle(t));
                }
                case 6 -> {
                    System.out.print("Author: ");
                    String a = sc.nextLine().trim();
                    printBooks(list.searchByAuthor(a));
                }
                case 7 -> {
                    System.out.print("Book ID: ");
                    int id = safeInt(sc);
                    System.out.print("Available (true/false): ");
                    boolean av = safeBoolean(sc);
                    System.out.println(list.updateAvailability(id, av) ? "Updated" : "Not found");
                }
                case 8 -> list.displayForward();
                case 9 -> list.displayReverse();
                case 10 -> System.out.println("Total books: " + list.count());
                default -> System.out.println("Invalid.");
            }
        }
        sc.close();
    }

    static Book readBook(Scanner sc) {
        System.out.print("Title: ");
        String title = sc.nextLine().trim();
        System.out.print("Author: ");
        String author = sc.nextLine().trim();
        System.out.print("Genre: ");
        String genre = sc.nextLine().trim();
        System.out.print("Book ID: ");
        int id = safeInt(sc);
        System.out.print("Available (true/false): ");
        boolean av = safeBoolean(sc);
        return new Book(title, author, genre, id, av);
    }

    static void printBooks(List<Book> list) {
        if (list.isEmpty())
            System.out.println("No results.");
        else
            for (Book b : list)
                System.out.printf("ID:%d Title:%s Author:%s Genre:%s Available:%s%n", b.id, b.title, b.author, b.genre,
                        b.available ? "Yes" : "No");
    }

    static int safeInt(Scanner sc) {
        while (!sc.hasNextInt()) {
            sc.nextLine();
            System.out.print("Enter integer: ");
        }
        int v = sc.nextInt();
        sc.nextLine();
        return v;
    }

    static boolean safeBoolean(Scanner sc) {
        while (!sc.hasNextBoolean()) {
            sc.nextLine();
            System.out.print("Enter true/false: ");
        }
        boolean v = sc.nextBoolean();
        sc.nextLine();
        return v;
    }
}

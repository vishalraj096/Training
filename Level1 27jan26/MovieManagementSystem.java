import java.util.*;

public class MovieManagementSystem {
    static class Movie {
        String title;
        String director;
        int year;
        double rating;
        Movie prev, next;

        Movie(String title, String director, int year, double rating) {
            this.title = title;
            this.director = director;
            this.year = year;
            this.rating = rating;
        }
    }

    static class DoublyList {
        Movie head, tail;

        boolean isEmpty() {
            return head == null;
        }

        void addAtBeginning(Movie m) {
            if (head == null) {
                head = tail = m;
                return;
            }
            m.next = head;
            head.prev = m;
            head = m;
        }

        void addAtEnd(Movie m) {
            if (tail == null) {
                head = tail = m;
                return;
            }
            tail.next = m;
            m.prev = tail;
            tail = m;
        }

        void addAtPosition(Movie m, int pos) {
            if (pos <= 1 || head == null) {
                addAtBeginning(m);
                return;
            }
            Movie cur = head;
            int i = 1;
            while (cur.next != null && i < pos - 1) {
                cur = cur.next;
                i++;
            }
            m.next = cur.next;
            m.prev = cur;
            if (cur.next != null)
                cur.next.prev = m;
            else
                tail = m;
            cur.next = m;
        }

        boolean removeByTitle(String title) {
            Movie cur = head;
            while (cur != null && !cur.title.equalsIgnoreCase(title))
                cur = cur.next;
            if (cur == null)
                return false;
            if (cur.prev != null)
                cur.prev.next = cur.next;
            else
                head = cur.next;
            if (cur.next != null)
                cur.next.prev = cur.prev;
            else
                tail = cur.prev;
            cur.next = cur.prev = null; // help GC
            return true;
        }

        List<Movie> searchByDirector(String director) {
            List<Movie> res = new ArrayList<>();
            for (Movie c = head; c != null; c = c.next)
                if (c.director.equalsIgnoreCase(director))
                    res.add(c);
            return res;
        }

        List<Movie> searchByRating(double rating) {
            List<Movie> res = new ArrayList<>();
            for (Movie c = head; c != null; c = c.next)
                if (Double.compare(c.rating, rating) == 0)
                    res.add(c);
            return res;
        }

        boolean updateRatingByTitle(String title, double rating) {
            for (Movie c = head; c != null; c = c.next)
                if (c.title.equalsIgnoreCase(title)) {
                    c.rating = rating;
                    return true;
                }
            return false;
        }

        void displayForward() {
            if (head == null) {
                System.out.println("No movies.");
                return;
            }
            for (Movie c = head; c != null; c = c.next)
                System.out.printf("%s | %s | %d | %.1f%n", c.title, c.director, c.year, c.rating);
        }

        void displayReverse() {
            if (tail == null) {
                System.out.println("No movies.");
                return;
            }
            for (Movie c = tail; c != null; c = c.prev)
                System.out.printf("%s | %s | %d | %.1f%n", c.title, c.director, c.year, c.rating);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        DoublyList list = new DoublyList();
        while (true) {
            System.out.println("\nMovie Management System (Doubly Linked List)");
            System.out.println("1. Add at beginning");
            System.out.println("2. Add at end");
            System.out.println("3. Add at position");
            System.out.println("4. Remove by Title");
            System.out.println("5. Search by Director");
            System.out.println("6. Search by Rating");
            System.out.println("7. Display forward");
            System.out.println("8. Display reverse");
            System.out.println("9. Update rating by Title");
            System.out.println("0. Exit");
            System.out.print("Choose: ");
            int ch = safeInt(sc);
            if (ch == 0)
                break;
            switch (ch) {
                case 1 -> list.addAtBeginning(readMovie(sc));
                case 2 -> list.addAtEnd(readMovie(sc));
                case 3 -> {
                    System.out.print("Position: ");
                    int p = safeInt(sc);
                    list.addAtPosition(readMovie(sc), p);
                }
                case 4 -> {
                    System.out.print("Title: ");
                    String t = sc.nextLine().trim();
                    System.out.println(list.removeByTitle(t) ? "Removed" : "Not found");
                }
                case 5 -> {
                    System.out.print("Director: ");
                    String d = sc.nextLine().trim();
                    List<Movie> r = list.searchByDirector(d);
                    printList(r);
                }
                case 6 -> {
                    System.out.print("Rating: ");
                    double r = safeDouble(sc);
                    List<Movie> rs = list.searchByRating(r);
                    printList(rs);
                }
                case 7 -> list.displayForward();
                case 8 -> list.displayReverse();
                case 9 -> {
                    System.out.print("Title: ");
                    String t = sc.nextLine().trim();
                    System.out.print("New rating: ");
                    double r = safeDouble(sc);
                    System.out.println(list.updateRatingByTitle(t, r) ? "Updated" : "Not found");
                }
                default -> System.out.println("Invalid.");
            }
        }
        sc.close();
    }

    static Movie readMovie(Scanner sc) {
        System.out.print("Title: ");
        String title = sc.nextLine().trim();
        System.out.print("Director: ");
        String director = sc.nextLine().trim();
        System.out.print("Year: ");
        int year = safeInt(sc);
        System.out.print("Rating (0-10): ");
        double rating = safeDouble(sc);
        return new Movie(title, director, year, rating);
    }

    static void printList(List<Movie> list) {
        if (list.isEmpty()) {
            System.out.println("No results.");
            return;
        }
        for (Movie c : list)
            System.out.printf("%s | %s | %d | %.1f%n", c.title, c.director, c.year, c.rating);
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

    static double safeDouble(Scanner sc) {
        while (!sc.hasNextDouble()) {
            sc.nextLine();
            System.out.print("Enter number: ");
        }
        double v = sc.nextDouble();
        sc.nextLine();
        return v;
    }
}

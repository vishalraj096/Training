import java.util.*;

public class TicketReservationSystem {
    static class Ticket {
        int ticketId;
        String customerName;
        String movieName;
        String seatNumber;
        String bookingTime;
        Ticket next;

        Ticket(int ticketId, String customerName, String movieName, String seatNumber, String bookingTime) {
            this.ticketId = ticketId;
            this.customerName = customerName;
            this.movieName = movieName;
            this.seatNumber = seatNumber;
            this.bookingTime = bookingTime;
        }
    }

    static class CircularList {
        Ticket head; // circular

        void addAtEnd(Ticket t) {
            if (head == null) {
                head = t;
                t.next = t;
                return;
            }
            Ticket last = head;
            while (last.next != head)
                last = last.next;
            last.next = t;
            t.next = head;
        }

        boolean removeById(int id) {
            if (head == null)
                return false;
            Ticket cur = head, prev = null;
            do {
                if (cur.ticketId == id) {
                    if (prev == null) { // head
                        if (cur.next == head) {
                            head = null;
                        } else {
                            Ticket last = head;
                            while (last.next != head)
                                last = last.next;
                            head = head.next;
                            last.next = head;
                        }
                    } else {
                        prev.next = cur.next;
                    }
                    cur.next = null;
                    return true;
                }
                prev = cur;
                cur = cur.next;
            } while (cur != head);
            return false;
        }

        int count() {
            if (head == null)
                return 0;
            int c = 0;
            Ticket cur = head;
            do {
                c++;
                cur = cur.next;
            } while (cur != head);
            return c;
        }

        void display() {
            if (head == null) {
                System.out.println("No tickets.");
                return;
            }
            Ticket cur = head;
            do {
                print(cur);
                cur = cur.next;
            } while (cur != head);
        }

        void print(Ticket t) {
            System.out.printf("ID:%d Customer:%s Movie:%s Seat:%s Time:%s%n", t.ticketId, t.customerName, t.movieName,
                    t.seatNumber, t.bookingTime);
        }

        List<Ticket> searchByCustomer(String name) {
            List<Ticket> r = new ArrayList<>();
            if (head == null)
                return r;
            Ticket c = head;
            do {
                if (c.customerName.equalsIgnoreCase(name))
                    r.add(c);
                c = c.next;
            } while (c != head);
            return r;
        }

        List<Ticket> searchByMovie(String name) {
            List<Ticket> r = new ArrayList<>();
            if (head == null)
                return r;
            Ticket c = head;
            do {
                if (c.movieName.equalsIgnoreCase(name))
                    r.add(c);
                c = c.next;
            } while (c != head);
            return r;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        CircularList list = new CircularList();
        while (true) {
            System.out.println("\nTicket Reservation System (Circular Linked List)");
            System.out.println("1. Add ticket at end");
            System.out.println("2. Remove ticket by ID");
            System.out.println("3. Display tickets");
            System.out.println("4. Search by Customer Name");
            System.out.println("5. Search by Movie Name");
            System.out.println("6. Count total tickets");
            System.out.println("0. Exit");
            System.out.print("Choose: ");
            int ch = safeInt(sc);
            if (ch == 0)
                break;
            switch (ch) {
                case 1 -> list.addAtEnd(readTicket(sc));
                case 2 -> {
                    System.out.print("Ticket ID: ");
                    int id = safeInt(sc);
                    System.out.println(list.removeById(id) ? "Removed" : "Not found");
                }
                case 3 -> list.display();
                case 4 -> {
                    System.out.print("Customer Name: ");
                    String n = sc.nextLine().trim();
                    List<Ticket> r = list.searchByCustomer(n);
                    if (r.isEmpty())
                        System.out.println("No results.");
                    else
                        for (Ticket t : r)
                            list.print(t);
                }
                case 5 -> {
                    System.out.print("Movie Name: ");
                    String n = sc.nextLine().trim();
                    List<Ticket> r = list.searchByMovie(n);
                    if (r.isEmpty())
                        System.out.println("No results.");
                    else
                        for (Ticket t : r)
                            list.print(t);
                }
                case 6 -> System.out.println("Total tickets: " + list.count());
                default -> System.out.println("Invalid.");
            }
        }
        sc.close();
    }

    static Ticket readTicket(Scanner sc) {
        System.out.print("Ticket ID: ");
        int id = safeInt(sc);
        System.out.print("Customer Name: ");
        String cn = sc.nextLine().trim();
        System.out.print("Movie Name: ");
        String mn = sc.nextLine().trim();
        System.out.print("Seat Number: ");
        String seat = sc.nextLine().trim();
        System.out.print("Booking Time: ");
        String time = sc.nextLine().trim();
        return new Ticket(id, cn, mn, seat, time);
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
}

import java.util.*;

public class InventoryManagementSystem {
    static class Item {
        String name;
        int id;
        int qty;
        double price;
        Item next;

        Item(String name, int id, int qty, double price) {
            this.name = name;
            this.id = id;
            this.qty = qty;
            this.price = price;
        }
    }

    static class SinglyList {
        Item head;

        void addAtBeginning(Item n) {
            n.next = head;
            head = n;
        }

        void addAtEnd(Item n) {
            if (head == null) {
                head = n;
                return;
            }
            Item c = head;
            while (c.next != null)
                c = c.next;
            c.next = n;
        }

        void addAtPosition(Item n, int pos) {
            if (pos <= 1 || head == null) {
                addAtBeginning(n);
                return;
            }
            Item c = head;
            int i = 1;
            while (c.next != null && i < pos - 1) {
                c = c.next;
                i++;
            }
            n.next = c.next;
            c.next = n;
        }

        boolean removeById(int id) {
            if (head == null)
                return false;
            if (head.id == id) {
                head = head.next;
                return true;
            }
            Item c = head;
            while (c.next != null && c.next.id != id)
                c = c.next;
            if (c.next == null)
                return false;
            c.next = c.next.next;
            return true;
        }

        Item findById(int id) {
            for (Item c = head; c != null; c = c.next)
                if (c.id == id)
                    return c;
            return null;
        }

        List<Item> findByName(String name) {
            List<Item> r = new ArrayList<>();
            for (Item c = head; c != null; c = c.next)
                if (c.name.equalsIgnoreCase(name))
                    r.add(c);
            return r;
        }

        boolean updateQty(int id, int qty) {
            Item it = findById(id);
            if (it == null)
                return false;
            it.qty = qty;
            return true;
        }

        double totalValue() {
            double s = 0;
            for (Item c = head; c != null; c = c.next)
                s += c.price * c.qty;
            return s;
        }

        void display() {
            if (head == null) {
                System.out.println("No items.");
                return;
            }
            for (Item c = head; c != null; c = c.next)
                System.out.printf("ID:%d Name:%s Qty:%d Price:%.2f Value:%.2f%n", c.id, c.name, c.qty, c.price,
                        c.qty * c.price);
        }

        void sort(boolean byName, boolean ascending) {
            head = mergeSort(head, byName, ascending);
        }

        Item mergeSort(Item h, boolean byName, boolean asc) {
            if (h == null || h.next == null)
                return h;
            Item mid = splitMid(h);
            Item right = mid.next;
            mid.next = null;
            Item leftSorted = mergeSort(h, byName, asc);
            Item rightSorted = mergeSort(right, byName, asc);
            return merge(leftSorted, rightSorted, byName, asc);
        }

        Item splitMid(Item h) {
            Item slow = h, fast = h.next;
            while (fast != null && fast.next != null) {
                slow = slow.next;
                fast = fast.next.next;
            }
            return slow;
        }

        int cmp(Item a, Item b, boolean byName) {
            if (byName)
                return a.name.compareToIgnoreCase(b.name);
            return Double.compare(a.price, b.price);
        }

        Item merge(Item a, Item b, boolean byName, boolean asc) {
            Item dummy = new Item("", 0, 0, 0);
            Item t = dummy;
            while (a != null && b != null) {
                int c = cmp(a, b, byName);
                if (asc ? c <= 0 : c >= 0) {
                    t.next = a;
                    a = a.next;
                } else {
                    t.next = b;
                    b = b.next;
                }
                t = t.next;
            }
            t.next = (a != null) ? a : b;
            return dummy.next;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        SinglyList list = new SinglyList();
        while (true) {
            System.out.println("\nInventory Management (Singly Linked List)");
            System.out.println("1. Add at beginning");
            System.out.println("2. Add at end");
            System.out.println("3. Add at position");
            System.out.println("4. Remove by Item ID");
            System.out.println("5. Update quantity by Item ID");
            System.out.println("6. Search by Item ID");
            System.out.println("7. Search by Item Name");
            System.out.println("8. Display all");
            System.out.println("9. Total inventory value");
            System.out.println("10. Sort by Name asc");
            System.out.println("11. Sort by Name desc");
            System.out.println("12. Sort by Price asc");
            System.out.println("13. Sort by Price desc");
            System.out.println("0. Exit");
            System.out.print("Choose: ");
            int ch = safeInt(sc);
            if (ch == 0)
                break;
            switch (ch) {
                case 1 -> list.addAtBeginning(readItem(sc));
                case 2 -> list.addAtEnd(readItem(sc));
                case 3 -> {
                    System.out.print("Position: ");
                    int p = safeInt(sc);
                    list.addAtPosition(readItem(sc), p);
                }
                case 4 -> {
                    System.out.print("Item ID: ");
                    int id = safeInt(sc);
                    System.out.println(list.removeById(id) ? "Removed" : "Not found");
                }
                case 5 -> {
                    System.out.print("Item ID: ");
                    int id = safeInt(sc);
                    System.out.print("New Qty: ");
                    int q = safeInt(sc);
                    System.out.println(list.updateQty(id, q) ? "Updated" : "Not found");
                }
                case 6 -> {
                    System.out.print("Item ID: ");
                    int id = safeInt(sc);
                    Item it = list.findById(id);
                    if (it == null)
                        System.out.println("Not found.");
                    else
                        System.out.printf("ID:%d Name:%s Qty:%d Price:%.2f%n", it.id, it.name, it.qty, it.price);
                }
                case 7 -> {
                    System.out.print("Item Name: ");
                    String n = sc.nextLine().trim();
                    List<Item> res = list.findByName(n);
                    if (res.isEmpty())
                        System.out.println("No results.");
                    else
                        for (Item it : res)
                            System.out.printf("ID:%d Name:%s Qty:%d Price:%.2f%n", it.id, it.name, it.qty, it.price);
                }
                case 8 -> list.display();
                case 9 -> System.out.printf("Total Value: %.2f%n", list.totalValue());
                case 10 -> {
                    list.sort(true, true);
                    System.out.println("Sorted by name asc.");
                }
                case 11 -> {
                    list.sort(true, false);
                    System.out.println("Sorted by name desc.");
                }
                case 12 -> {
                    list.sort(false, true);
                    System.out.println("Sorted by price asc.");
                }
                case 13 -> {
                    list.sort(false, false);
                    System.out.println("Sorted by price desc.");
                }
                default -> System.out.println("Invalid.");
            }
        }
        sc.close();
    }

    static Item readItem(Scanner sc) {
        System.out.print("Item Name: ");
        String name = sc.nextLine().trim();
        System.out.print("Item ID: ");
        int id = safeInt(sc);
        System.out.print("Quantity: ");
        int qty = safeInt(sc);
        System.out.print("Price: ");
        double price = safeDouble(sc);
        return new Item(name, id, qty, price);
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

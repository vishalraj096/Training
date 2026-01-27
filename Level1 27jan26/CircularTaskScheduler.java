import java.util.*;

public class CircularTaskScheduler {
    static class Task {
        int id;
        String name;
        int priority;
        String dueDate;
        Task next;

        Task(int id, String name, int priority, String dueDate) {
            this.id = id;
            this.name = name;
            this.priority = priority;
            this.dueDate = dueDate;
        }
    }

    static class CircularList {
        Task head; // circular; last.next -> head
        Task current;

        boolean isEmpty() {
            return head == null;
        }

        void addAtBeginning(Task t) {
            if (head == null) {
                head = t;
                t.next = t;
                current = head;
                return;
            }
            Task last = head;
            while (last.next != head)
                last = last.next;
            t.next = head;
            head = t;
            last.next = head;
        }

        void addAtEnd(Task t) {
            if (head == null) {
                head = t;
                t.next = t;
                current = head;
                return;
            }
            Task last = head;
            while (last.next != head)
                last = last.next;
            last.next = t;
            t.next = head;
        }

        void addAtPosition(Task t, int pos) {
            if (head == null || pos <= 1) {
                addAtBeginning(t);
                return;
            }
            Task cur = head;
            int i = 1;
            while (cur.next != head && i < pos - 1) {
                cur = cur.next;
                i++;
            }
            t.next = cur.next;
            cur.next = t;
        }

        boolean removeById(int id) {
            if (head == null)
                return false;
            Task cur = head, prev = null;
            do {
                if (cur.id == id) {
                    if (prev == null) { // removing head
                        if (cur.next == head) { // single node
                            head = current = null;
                        } else {
                            Task last = head;
                            while (last.next != head)
                                last = last.next;
                            head = head.next;
                            last.next = head;
                            if (current == cur)
                                current = head;
                        }
                    } else {
                        prev.next = cur.next;
                        if (current == cur)
                            current = cur.next;
                    }
                    cur.next = null;
                    return true;
                }
                prev = cur;
                cur = cur.next;
            } while (cur != head);
            return false;
        }

        Task searchByPriority(int priority) {
            if (head == null)
                return null;
            Task cur = head;
            do {
                if (cur.priority == priority)
                    return cur;
                cur = cur.next;
            } while (cur != head);
            return null;
        }

        void displayAll() {
            if (head == null) {
                System.out.println("No tasks.");
                return;
            }
            Task cur = head;
            do {
                System.out.printf("ID:%d Name:%s Priority:%d Due:%s%s%n", cur.id, cur.name, cur.priority, cur.dueDate,
                        (cur == current ? " <- current" : ""));
                cur = cur.next;
            } while (cur != head);
        }

        Task viewCurrentAndMoveNext() {
            if (current == null)
                return null;
            Task res = current;
            current = current.next;
            return res;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        CircularList list = new CircularList();
        while (true) {
            System.out.println("\nTask Scheduler (Circular Linked List)");
            System.out.println("1. Add at beginning");
            System.out.println("2. Add at end");
            System.out.println("3. Add at position");
            System.out.println("4. Remove by Task ID");
            System.out.println("5. View current and move next");
            System.out.println("6. Display all tasks");
            System.out.println("7. Search task by Priority");
            System.out.println("0. Exit");
            System.out.print("Choose: ");
            int ch = safeInt(sc);
            if (ch == 0)
                break;
            switch (ch) {
                case 1 -> list.addAtBeginning(readTask(sc));
                case 2 -> list.addAtEnd(readTask(sc));
                case 3 -> {
                    System.out.print("Position: ");
                    int p = safeInt(sc);
                    list.addAtPosition(readTask(sc), p);
                }
                case 4 -> {
                    System.out.print("Task ID: ");
                    int id = safeInt(sc);
                    System.out.println(list.removeById(id) ? "Removed" : "Not found");
                }
                case 5 -> {
                    Task t = list.viewCurrentAndMoveNext();
                    if (t == null)
                        System.out.println("No tasks.");
                    else
                        System.out.printf("Current: %d %s (Priority %d, Due %s)\n", t.id, t.name, t.priority,
                                t.dueDate);
                }
                case 6 -> list.displayAll();
                case 7 -> {
                    System.out.print("Priority: ");
                    int p = safeInt(sc);
                    Task t = list.searchByPriority(p);
                    if (t == null)
                        System.out.println("Not found.");
                    else
                        System.out.printf("Found -> %d %s (Due %s)\n", t.id, t.name, t.dueDate);
                }
                default -> System.out.println("Invalid.");
            }
        }
        sc.close();
    }

    static Task readTask(Scanner sc) {
        System.out.print("Task ID: ");
        int id = safeInt(sc);
        System.out.print("Task Name: ");
        String name = sc.nextLine().trim();
        System.out.print("Priority: ");
        int priority = safeInt(sc);
        System.out.print("Due Date: ");
        String due = sc.nextLine().trim();
        return new Task(id, name, priority, due);
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

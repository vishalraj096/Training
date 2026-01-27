import java.util.*;

public class TextEditorUndoRedo {
    static class State {
        String text;
        State prev, next;

        State(String text) {
            this.text = text;
        }
    }

    static class History {
        State head, tail, current;
        int maxSize = 10;
        int size = 0;

        void addState(String text) {
            // if we have forward history, truncate it
            if (current != null && current.next != null) {
                current.next.prev = null;
                current.next = null;
                tail = current;
                // recompute size
                size = count();
            }
            State s = new State(text);
            if (head == null) {
                head = tail = current = s;
                size = 1;
            } else {
                tail.next = s;
                s.prev = tail;
                tail = s;
                current = s;
                size++;
            }
            // cap to max size
            while (size > maxSize) {
                head = head.next;
                if (head != null)
                    head.prev = null;
                size--;
                if (current == null)
                    current = head;
            }
            if (head == null) {
                tail = null;
                current = null;
            }
        }

        boolean undo() {
            if (current == null || current.prev == null)
                return false;
            current = current.prev;
            return true;
        }

        boolean redo() {
            if (current == null || current.next == null)
                return false;
            current = current.next;
            return true;
        }

        String currentText() {
            return current == null ? "" : current.text;
        }

        int count() {
            int c = 0;
            for (State s = head; s != null; s = s.next)
                c++;
            return c;
        }

        void displayAll() {
            if (head == null) {
                System.out.println("No history.");
                return;
            }
            for (State s = head; s != null; s = s.next)
                System.out.println((s == current ? "* " : "  ") + s.text);
        }

        void setMaxSize(int k) {
            if (k < 1)
                return;
            maxSize = k;
            while (size > maxSize) {
                head = head.next;
                if (head != null)
                    head.prev = null;
                size--;
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        History history = new History();
        while (true) {
            System.out.println("\nText Editor Undo/Redo (Doubly Linked List)");
            System.out.println("1. Type/Add new state");
            System.out.println("2. Undo");
            System.out.println("3. Redo");
            System.out.println("4. Show current text");
            System.out.println("5. Show history");
            System.out.println("6. Set history size limit");
            System.out.println("0. Exit");
            System.out.print("Choose: ");
            int ch = safeInt(sc);
            if (ch == 0)
                break;
            switch (ch) {
                case 1 -> {
                    System.out.print("Enter text: ");
                    String t = sc.nextLine();
                    history.addState(t);
                }
                case 2 -> System.out.println(history.undo() ? "Undone." : "Cannot undo.");
                case 3 -> System.out.println(history.redo() ? "Redone." : "Cannot redo.");
                case 4 -> System.out.println("Current: " + history.currentText());
                case 5 -> history.displayAll();
                case 6 -> {
                    System.out.print("New limit: ");
                    int k = safeInt(sc);
                    history.setMaxSize(k);
                    System.out.println("Limit set.");
                }
                default -> System.out.println("Invalid.");
            }
        }
        sc.close();
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

import java.util.*;

public class StudentRecordManagement {
    static class Student {
        int roll;
        String name;
        int age;
        String grade;
        Student next;

        Student(int roll, String name, int age, String grade) {
            this.roll = roll;
            this.name = name;
            this.age = age;
            this.grade = grade;
        }
    }

    static class SinglyList {
        Student head;

        boolean isEmpty() {
            return head == null;
        }

        void addAtBeginning(Student s) {
            s.next = head;
            head = s;
        }

        void addAtEnd(Student s) {
            if (head == null) {
                head = s;
                return;
            }
            Student cur = head;
            while (cur.next != null)
                cur = cur.next;
            cur.next = s;
        }

        void addAtPosition(Student s, int pos) {
            if (pos <= 1 || head == null) {
                addAtBeginning(s);
                return;
            }
            Student cur = head;
            int i = 1;
            while (cur.next != null && i < pos - 1) {
                cur = cur.next;
                i++;
            }
            s.next = cur.next;
            cur.next = s;
        }

        boolean deleteByRoll(int roll) {
            if (head == null)
                return false;
            if (head.roll == roll) {
                head = head.next;
                return true;
            }
            Student cur = head;
            while (cur.next != null && cur.next.roll != roll)
                cur = cur.next;
            if (cur.next == null)
                return false;
            cur.next = cur.next.next;
            return true;
        }

        Student searchByRoll(int roll) {
            Student cur = head;
            while (cur != null) {
                if (cur.roll == roll)
                    return cur;
                cur = cur.next;
            }
            return null;
        }

        boolean updateGrade(int roll, String grade) {
            Student s = searchByRoll(roll);
            if (s == null)
                return false;
            s.grade = grade;
            return true;
        }

        void display() {
            if (head == null) {
                System.out.println("No records.");
                return;
            }
            Student cur = head;
            while (cur != null) {
                System.out.printf("Roll: %d, Name: %s, Age: %d, Grade: %s%n", cur.roll, cur.name, cur.age, cur.grade);
                cur = cur.next;
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        SinglyList list = new SinglyList();
        while (true) {
            System.out.println("\nStudent Record Management (Singly Linked List)");
            System.out.println("1. Add at beginning");
            System.out.println("2. Add at end");
            System.out.println("3. Add at position");
            System.out.println("4. Delete by Roll Number");
            System.out.println("5. Search by Roll Number");
            System.out.println("6. Display all");
            System.out.println("7. Update grade by Roll Number");
            System.out.println("0. Exit");
            System.out.print("Choose: ");
            int ch = safeInt(sc);
            if (ch == 0)
                break;
            switch (ch) {
                case 1 -> list.addAtBeginning(readStudent(sc));
                case 2 -> list.addAtEnd(readStudent(sc));
                case 3 -> {
                    System.out.print("Position (1-based): ");
                    int p = safeInt(sc);
                    list.addAtPosition(readStudent(sc), p);
                }
                case 4 -> {
                    System.out.print("Roll: ");
                    int r = safeInt(sc);
                    System.out.println(list.deleteByRoll(r) ? "Deleted." : "Not found.");
                }
                case 5 -> {
                    System.out.print("Roll: ");
                    int r = safeInt(sc);
                    Student s = list.searchByRoll(r);
                    if (s == null)
                        System.out.println("Not found.");
                    else
                        System.out.printf("Found -> Roll: %d, Name: %s, Age: %d, Grade: %s%n", s.roll, s.name, s.age,
                                s.grade);
                }
                case 6 -> list.display();
                case 7 -> {
                    System.out.print("Roll: ");
                    int r = safeInt(sc);
                    System.out.print("New Grade: ");
                    String g = sc.nextLine().trim();
                    System.out.println(list.updateGrade(r, g) ? "Updated." : "Not found.");
                }
                default -> System.out.println("Invalid.");
            }
        }
        sc.close();
    }

    static Student readStudent(Scanner sc) {
        System.out.print("Roll: ");
        int roll = safeInt(sc);
        System.out.print("Name: ");
        String name = sc.nextLine().trim();
        System.out.print("Age: ");
        int age = safeInt(sc);
        System.out.print("Grade: ");
        String grade = sc.nextLine().trim();
        return new Student(roll, name, age, grade);
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

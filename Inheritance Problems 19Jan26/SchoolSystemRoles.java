class Person {
    String name;

    Person(String name, int age) {
        this.name = name;
    }
}

class Teacher extends Person {
    String subject;

    Teacher(String name, int age, String subject) {
        super(name, age);
        this.subject = subject;
    }

    void displayRole() {
        System.out.println(name + " is a Teacher of " + subject);
    }
}

class Student extends Person {
    int grade;

    Student(String name, int age, int grade) {
        super(name, age);
        this.grade = grade;
    }

    void displayRole() {
        System.out.println(name + " is a Student in grade " + grade);
    }
}

class Staff extends Person {
    String position;

    Staff(String name, int age, String position) {
        super(name, age);
        this.position = position;
    }

    void displayRole() {
        System.out.println(name + " is a Staff member: " + position);
    }
}

public class SchoolSystemRoles {
    public static void main(String[] args) {
        Teacher t = new Teacher("Mr. Smith", 40, "Math");
        Student s = new Student("Alice", 15, 10);
        Staff st = new Staff("Bob", 35, "Librarian");
        t.displayRole();
        s.displayRole();
        st.displayRole();
    }
}

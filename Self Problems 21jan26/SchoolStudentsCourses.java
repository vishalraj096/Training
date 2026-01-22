import java.util.*;

class Course {
    private final String name;
    private final Set<Student> students = new HashSet<>();

    Course(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    void addStudent(Student s) {
        students.add(s);
    }

    public Set<Student> getStudents() {
        return Collections.unmodifiableSet(students);
    }

    @Override
    public String toString() {
        return name;
    }
}

class Student {
    private final String name;
    private final Set<Course> courses = new HashSet<>();

    Student(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void enroll(Course c) {
        courses.add(c);
        c.addStudent(this);
        System.out.println(name + " enrolled in " + c.getName());
    }

    public Set<Course> getCourses() {
        return Collections.unmodifiableSet(courses);
    }

    @Override
    public String toString() {
        return name;
    }
}

class School {
    private final String name;
    private final List<Student> students = new ArrayList<>();

    School(String name) {
        this.name = name;
    }

    public void addStudent(Student s) {
        students.add(s);
    }

    public List<Student> getStudents() {
        return Collections.unmodifiableList(students);
    }

    @Override
    public String toString() {
        return name;
    }
}

public class SchoolStudentsCourses {
    public static void main(String[] args) {
        School school = new School("Springfield High");
        Student john = new Student("John");
        Student jane = new Student("Jane");
        school.addStudent(john);
        school.addStudent(jane);

        Course math = new Course("Math");
        Course science = new Course("Science");

        john.enroll(math);
        john.enroll(science);
        jane.enroll(science);

        System.out.println("\nStudents and their courses:");
        for (Student s : school.getStudents()) {
            System.out.println("  " + s.getName() + " -> " + s.getCourses());
        }

        System.out.println("\nCourses and enrolled students:");
        for (Course c : List.of(math, science)) {
            System.out.println("  " + c.getName() + " -> " + c.getStudents());
        }
    }
}

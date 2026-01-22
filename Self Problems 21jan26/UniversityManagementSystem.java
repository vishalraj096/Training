import java.util.*;

class Course5 {
    private final String code;
    private final String title;
    private Professor5 professor;
    private final Set<Student5> students = new HashSet<>();

    Course5(String code, String title) {
        this.code = code;
        this.title = title;
    }

    public String getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }

    public Professor5 getProfessor() {
        return professor;
    }

    void assignProfessor(Professor5 p) {
        this.professor = p;
        System.out.println("Assigned " + p.getName() + " to course " + code);
    }

    void enroll(Student5 s) {
        students.add(s);
    }

    public Set<Student5> getStudents() {
        return Collections.unmodifiableSet(students);
    }

    @Override
    public String toString() {
        return code + " - " + title;
    }
}

class Student5 {
    private final String name;
    private final Set<Course5> courses = new HashSet<>();

    Student5(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void enrollCourse(Course5 c) {
        courses.add(c);
        c.enroll(this);
        System.out.println(name + " enrolled in " + c.getCode());
    }

    public Set<Course5> getCourses() {
        return Collections.unmodifiableSet(courses);
    }
}

class Professor5 {
    private final String name;

    Professor5(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void assignProfessor(Course5 c) {
        c.assignProfessor(this);
    }
}

public class UniversityManagementSystem {
    public static void main(String[] args) {
        Course5 ds = new Course5("CS201", "Data Structures");
        Course5 algo = new Course5("CS301", "Algorithms");

        Student5 sam = new Student5("Sam");
        Student5 ria = new Student5("Ria");
        Professor5 profKim = new Professor5("Prof. Kim");

        profKim.assignProfessor(ds);
        sam.enrollCourse(ds);
        ria.enrollCourse(ds);
        sam.enrollCourse(algo);

        System.out.println("\nCourse rosters:");
        for (Course5 c : List.of(ds, algo)) {
            System.out.println("  " + c + " taught by "
                    + (c.getProfessor() != null ? c.getProfessor().getName() : "TBA") + ": " + c.getStudents());
        }
    }
}

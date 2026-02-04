import java.util.*;

abstract class CourseType {
    private final String name;

    public CourseType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class ExamCourse extends CourseType {
    public ExamCourse(String name) {
        super(name);
    }
}

class AssignmentCourse extends CourseType {
    public AssignmentCourse(String name) {
        super(name);
    }
}

class ResearchCourse extends CourseType {
    public ResearchCourse(String name) {
        super(name);
    }
}

class Course<T extends CourseType> {
    private final String title;
    private final T type;

    public Course(String title, T type) {
        this.title = title;
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public T getType() {
        return type;
    }
}

class CourseUtils {
    public static void printTypes(List<? extends CourseType> types) {
        for (CourseType t : types)
            System.out.println(t.getName());
    }
}

public class UniversityCourseManagementSystem {
    public static void main(String[] args) {
        Course<ExamCourse> math = new Course<>("Mathematics", new ExamCourse("Exam-Based"));
        Course<AssignmentCourse> cs = new Course<>("Programming", new AssignmentCourse("Assignment-Based"));
        Course<ResearchCourse> ai = new Course<>("AI Research", new ResearchCourse("Research-Based"));
        List<CourseType> list = new ArrayList<>();
        list.add(math.getType());
        list.add(cs.getType());
        list.add(ai.getType());
        CourseUtils.printTypes(list);
    }
}
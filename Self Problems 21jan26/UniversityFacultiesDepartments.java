import java.util.*;

class Faculty {
    private final String name;

    Faculty(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}

class Department2 {
    private final String name;
    private final List<Faculty> assignedFaculty = new ArrayList<>();
    private final University university;

    Department2(String name, University university) {
        this.name = name;
        this.university = university;
    }

    public String getName() {
        return name;
    }

    void assignFaculty(Faculty f) {
        assignedFaculty.add(f);
    }

    public List<Faculty> getAssignedFaculty() {
        return Collections.unmodifiableList(assignedFaculty);
    }

    void clearAssignedFaculty() {
        assignedFaculty.clear();
    }
}

class University {
    private final String name;
    private final List<Department2> departments = new ArrayList<>();
    private boolean deleted = false;

    University(String name) {
        this.name = name;
    }

    public Department2 createDepartment(String name) {
        ensureAlive();
        Department2 d = new Department2(name, this);
        departments.add(d);
        return d;
    }

    public void deleteUniversity() {
        for (Department2 d : departments) {
            d.clearAssignedFaculty();
        }
        departments.clear();
        deleted = true;
        System.out.println("University '" + name + "' deleted with departments.");
    }

    public List<Department2> getDepartments() {
        return Collections.unmodifiableList(departments);
    }

    private void ensureAlive() {
        if (deleted)
            throw new IllegalStateException("University deleted");
    }
}

public class UniversityFacultiesDepartments {
    public static void main(String[] args) {
        University uni = new University("Metro University");
        Department2 cs = uni.createDepartment("Computer Science");
        Department2 mech = uni.createDepartment("Mechanical Engineering");

        Faculty profA = new Faculty("Prof. Adams");
        Faculty profB = new Faculty("Prof. Baker");

        cs.assignFaculty(profA);
        mech.assignFaculty(profB);

        System.out.println("Before deletion:");
        for (Department2 d : uni.getDepartments()) {
            System.out.println("  " + d.getName() + " -> " + d.getAssignedFaculty());
        }

        uni.deleteUniversity();
        System.out.println("Faculty still exist independently: " + List.of(profA, profB));
    }
}

import java.util.*;

abstract class JobRole {
    private final String name;

    public JobRole(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class SoftwareEngineer extends JobRole {
    public SoftwareEngineer() {
        super("Software Engineer");
    }
}

class DataScientist extends JobRole {
    public DataScientist() {
        super("Data Scientist");
    }
}

class ProductManager extends JobRole {
    public ProductManager() {
        super("Product Manager");
    }
}

class Resume<T extends JobRole> {
    private final T role;
    private final List<String> skills;

    public Resume(T role, List<String> skills) {
        this.role = role;
        this.skills = new ArrayList<>(skills);
    }

    public T getRole() {
        return role;
    }

    public List<String> getSkills() {
        return Collections.unmodifiableList(skills);
    }

    public int score() {
        return skills.size();
    }
}

class ScreeningPipeline {
    public static void handleRoles(List<? extends JobRole> roles) {
        for (JobRole r : roles)
            System.out.println(r.getName());
    }
}

public class ResumeScreeningSystem {
    public static void main(String[] args) {
        Resume<SoftwareEngineer> r1 = new Resume<>(new SoftwareEngineer(), Arrays.asList("Java", "DSA", "Spring"));
        Resume<DataScientist> r2 = new Resume<>(new DataScientist(), Arrays.asList("Python", "ML", "SQL"));
        Resume<ProductManager> r3 = new Resume<>(new ProductManager(), Arrays.asList("Roadmapping", "Metrics"));
        List<JobRole> roles = new ArrayList<>();
        roles.add(r1.getRole());
        roles.add(r2.getRole());
        roles.add(r3.getRole());
        ScreeningPipeline.handleRoles(roles);
        System.out.println(r1.score());
        System.out.println(r2.score());
        System.out.println(r3.score());
    }
}
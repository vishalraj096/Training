import java.util.*;

class Employee {
    private final String name;

    Employee(String name) {
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

class Department {
    private final String name;
    private final List<Employee> employees = new ArrayList<>();
    private final Company company;

    Department(String name, Company company) {
        this.name = name;
        this.company = company;
    }

    public String getName() {
        return name;
    }

    public void addEmployee(Employee e) {
        employees.add(e);
    }

    public List<Employee> getEmployees() {
        return Collections.unmodifiableList(employees);
    }

    void clearEmployees() {
        employees.clear();
    }
}

class Company {
    private final String name;
    private final List<Department> departments = new ArrayList<>();
    private boolean deleted = false;

    Company(String name) {
        this.name = name;
    }

    public Department createDepartment(String name) {
        ensureAlive();
        Department d = new Department(name, this);
        departments.add(d);
        return d;
    }

    public List<Department> getDepartments() {
        return Collections.unmodifiableList(departments);
    }

    public void deleteCompany() {
        for (Department d : departments) {
            d.clearEmployees();
        }
        departments.clear();
        deleted = true;
        System.out.println("Company '" + name + "' has been deleted along with its departments and employees.");
    }

    private void ensureAlive() {
        if (deleted)
            throw new IllegalStateException("Company has been deleted");
    }
}

public class CompanyDepartmentsComposition {
    public static void main(String[] args) {
        Company comp = new Company("TechWorks");
        Department eng = comp.createDepartment("Engineering");
        Department hr = comp.createDepartment("HR");

        eng.addEmployee(new Employee("Eve"));
        eng.addEmployee(new Employee("Bob"));
        hr.addEmployee(new Employee("Cara"));

        System.out.println("Before deletion:");
        for (Department d : comp.getDepartments()) {
            System.out.println("  " + d.getName() + " -> " + d.getEmployees());
        }

        comp.deleteCompany();

        System.out.println("\nAfter deletion:");
        try {
            comp.createDepartment("Sales");
        } catch (Exception ex) {
            System.out.println("  Cannot create department: " + ex.getMessage());
        }
    }
}

import java.util.*;
import java.util.stream.Collectors;

class Employee {
    private int id;
    private String name;
    private String department;
    private double salary;

    public Employee(int id, String name, String department, double salary) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public double getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", department='" + department + '\'' +
                ", salary=" + salary +
                '}';
    }
}

public class EmployeeDataProcessing {

    public static Map<String, Double> processEmployees(List<Employee> employees) {
        List<Employee> filteredSorted = employees.stream()
                .filter(e -> "Engineering".equalsIgnoreCase(e.getDepartment()) && e.getSalary() > 80000)
                .sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
                .collect(Collectors.toList());

        System.out.println("Filtered and sorted employees:");
        filteredSorted.forEach(System.out::println);

        return filteredSorted.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment,
                        Collectors.averagingDouble(Employee::getSalary)));
    }

    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1, "Alice", "Engineering", 90000));
        employees.add(new Employee(2, "Bob", "Engineering", 75000));
        employees.add(new Employee(3, "Charlie", "HR", 85000));
        employees.add(new Employee(4, "David", "Engineering", 120000));
        employees.add(new Employee(5, "Eve", "Sales", 95000));

        Map<String, Double> avgSalaryByDept = processEmployees(employees);

        System.out.println("\nAverage salary by department:");
        avgSalaryByDept.forEach((dept, avg) -> System.out.println(dept + " -> " + avg));
    }
}

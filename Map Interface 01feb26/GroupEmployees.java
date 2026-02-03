import java.util.*;

public class GroupEmployees {
    public static Map<String, List<Employee>> groupByDepartment(List<Employee> employees) {
        Map<String, List<Employee>> departmentMap = new HashMap<>();
        
        for (Employee employee : employees) {
            String department = employee.getDepartment();
            
            if (!departmentMap.containsKey(department)) {
                departmentMap.put(department, new ArrayList<>());
            }
            
            departmentMap.get(department).add(employee);
        }
        
        return departmentMap;
    }
    
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            List<Employee> employees = new ArrayList<>();
            
            System.out.print("Enter number of employees: ");
            int n = sc.nextInt();
            sc.nextLine();
            
            for (int i = 0; i < n; i++) {
                System.out.print("Enter employee name: ");
                String name = sc.nextLine();
                
                System.out.print("Enter department: ");
                String department = sc.nextLine();
                
                employees.add(new Employee(name, department));
            }
            
            Map<String, List<Employee>> departmentMap = groupByDepartment(employees);
            
            System.out.println("\nEmployees grouped by department:");
            for (Map.Entry<String, List<Employee>> entry : departmentMap.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
        }
    }
}

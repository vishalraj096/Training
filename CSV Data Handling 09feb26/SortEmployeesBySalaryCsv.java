import java.io.*;
import java.util.*;

public class SortEmployeesBySalaryCsv {

    private static class EmployeeRecord {
        String id;
        String name;
        String department;
        double salary;

        EmployeeRecord(String id, String name, String department, double salary) {
            this.id = id;
            this.name = name;
            this.department = department;
            this.salary = salary;
        }

        @Override
        public String toString() {
            return id + "," + name + "," + department + "," + salary;
        }
    }

    public static void main(String[] args) {
        String fileName = "employees.csv";
        List<EmployeeRecord> list = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String header = br.readLine();
            if (header == null) {
                System.out.println("Empty file");
                return;
            }

            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length < 4) {
                    continue;
                }
                String id = parts[0].trim();
                String name = parts[1].trim();
                String dept = parts[2].trim();
                double salary = Double.parseDouble(parts[3].trim());
                list.add(new EmployeeRecord(id, name, dept, salary));
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        list.stream()
                .sorted(Comparator.comparingDouble(e -> -e.salary))
                .limit(5)
                .forEach(System.out::println);
    }
}

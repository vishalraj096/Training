import java.io.*;
import java.util.Scanner;

public class SearchEmployeeInCsv {
    public static void main(String[] args) {
        String fileName = "employees.csv";
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter employee name to search: ");
        String searchName = scanner.nextLine().trim().toLowerCase();

        boolean found = false;

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
                String name = parts[1].trim();
                String department = parts[2].trim();
                String salary = parts[3].trim();

                if (name.toLowerCase().equals(searchName)) {
                    System.out.println("Department: " + department + ", Salary: " + salary);
                    found = true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (!found) {
            System.out.println("Employee not found.");
        }
    }
}

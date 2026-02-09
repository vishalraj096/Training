import java.io.*;
import java.util.*;

public class UpdateSalaryForITCsv {
    public static void main(String[] args) {
        String inputFile = "employees.csv";
        String outputFile = "employees_updated.csv";

        List<String> lines = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
            String header = br.readLine();
            if (header == null) {
                System.out.println("Empty file");
                return;
            }
            lines.add(header);

            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length < 4) {
                    lines.add(line);
                    continue;
                }
                String department = parts[2].trim();
                double salary = Double.parseDouble(parts[3].trim());
                if (department.equalsIgnoreCase("IT")) {
                    salary = salary * 1.10;
                    parts[3] = String.valueOf(salary);
                    line = String.join(",", parts);
                }
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile))) {
            for (String l : lines) {
                bw.write(l);
                bw.newLine();
            }
            System.out.println("Updated salaries written to " + outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

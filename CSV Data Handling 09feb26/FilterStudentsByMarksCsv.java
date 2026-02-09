import java.io.*;

public class FilterStudentsByMarksCsv {
    public static void main(String[] args) {
        String fileName = "students.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String header = br.readLine();
            if (header == null) {
                System.out.println("Empty file");
                return;
            }

            System.out.println("Students with marks > 80:");
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length < 4) {
                    continue;
                }
                double marks = Double.parseDouble(parts[3].trim());
                if (marks > 80) {
                    System.out.println(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

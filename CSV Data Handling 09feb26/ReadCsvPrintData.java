import java.io.*;

public class ReadCsvPrintData {
    public static void main(String[] args) {
        String fileName = "students.csv";

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
                String age = parts[2].trim();
                String marks = parts[3].trim();

                System.out.println("ID: " + id + ", Name: " + name + ", Age: " + age + ", Marks: " + marks);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

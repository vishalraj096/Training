import java.io.*;
import java.util.*;

public class DetectDuplicateIdsCsv {
    public static void main(String[] args) {
        String fileName = "students.csv";
        Set<String> seenIds = new HashSet<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String header = br.readLine();
            if (header == null) {
                System.out.println("Empty file");
                return;
            }

            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 0)
                    continue;
                String id = parts[0].trim();
                if (!seenIds.add(id)) {
                    System.out.println("Duplicate record: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

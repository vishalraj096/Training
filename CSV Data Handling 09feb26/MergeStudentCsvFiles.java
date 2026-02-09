import java.io.*;
import java.util.*;

public class MergeStudentCsvFiles {
    public static void main(String[] args) {
        String file1 = "students1.csv"; // ID,Name,Age
        String file2 = "students2.csv"; // ID,Marks,Grade
        String outputFile = "students_merged.csv";

        Map<String, String[]> map1 = new HashMap<>();
        Map<String, String[]> map2 = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(file1))) {
            String header = br.readLine();
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length < 3)
                    continue;
                map1.put(parts[0].trim(), new String[] { parts[1].trim(), parts[2].trim() });
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file2))) {
            String header = br.readLine();
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length < 3)
                    continue;
                map2.put(parts[0].trim(), new String[] { parts[1].trim(), parts[2].trim() });
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile))) {
            bw.write("ID,Name,Age,Marks,Grade");
            bw.newLine();

            for (String id : map1.keySet()) {
                String[] a = map1.get(id);
                String[] b = map2.get(id);
                if (b != null) {
                    String line = id + "," + a[0] + "," + a[1] + "," + b[0] + "," + b[1];
                    bw.write(line);
                    bw.newLine();
                }
            }

            System.out.println("Merged file written to " + outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

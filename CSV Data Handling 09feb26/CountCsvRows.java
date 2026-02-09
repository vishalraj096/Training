import java.io.*;

public class CountCsvRows {
    public static void main(String[] args) {
        String fileName = "students.csv";
        int count = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String header = br.readLine();
            if (header == null) {
                System.out.println("Empty file");
                return;
            }

            String line;
            while ((line = br.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    count++;
                }
            }

            System.out.println("Number of records (excluding header): " + count);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

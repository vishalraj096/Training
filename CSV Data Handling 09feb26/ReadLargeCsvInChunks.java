import java.io.*;

public class ReadLargeCsvInChunks {
    public static void main(String[] args) {
        String fileName = "largefile.csv";
        int chunkSize = 100;
        int totalCount = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String header = br.readLine();
            if (header == null) {
                System.out.println("Empty file");
                return;
            }

            String line;
            int currentCount = 0;
            while ((line = br.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    currentCount++;
                    totalCount++;
                }
                if (currentCount == chunkSize) {
                    System.out.println("Processed " + totalCount + " records so far...");
                    currentCount = 0;
                }
            }

            if (currentCount > 0) {
                System.out.println("Processed " + totalCount + " records in total.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

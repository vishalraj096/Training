import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileProcessor {

    public void processFiles(List<String> filePaths) {
        for (String path : filePaths) {
            System.out.println("Processing file: " + path);
            try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    // Simple processing: print the line
                    System.out.println(line);
                }
            } catch (IOException e) {
                System.err.println("Error reading file " + path + ": " + e.getMessage());
            }
            System.out.println("Finished processing: " + path);
            System.out.println("------------------------------");
        }
    }

    public static void main(String[] args) {
        FileProcessor processor = new FileProcessor();
        List<String> files = new ArrayList<>();

        if (args.length == 0) {
            // Demo file names, change as needed
            files.add("input1.txt");
            files.add("input2.txt");
        } else {
            for (String arg : args) {
                files.add(arg);
            }
        }

        processor.processFiles(files);
    }
}

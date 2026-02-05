import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MultiFileCombiner {

    public void combineFiles(String firstInput, String secondInput, String outputFile) {
        try (BufferedReader reader1 = new BufferedReader(new FileReader(firstInput));
                BufferedReader reader2 = new BufferedReader(new FileReader(secondInput));
                BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {

            String line;

            while ((line = reader1.readLine()) != null) {
                writer.write(line);
                writer.newLine();
            }

            while ((line = reader2.readLine()) != null) {
                writer.write(line);
                writer.newLine();
            }

            System.out.println("Combined content written to: " + outputFile);
        } catch (IOException e) {
            System.err.println("I/O error while combining files: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        MultiFileCombiner combiner = new MultiFileCombiner();

        String firstInput;
        String secondInput;
        String outputFile;

        if (args.length < 3) {
            firstInput = "file1.txt";
            secondInput = "file2.txt";
            outputFile = "combined.txt";
        } else {
            firstInput = args[0];
            secondInput = args[1];
            outputFile = args[2];
        }

        combiner.combineFiles(firstInput, secondInput, outputFile);
    }
}

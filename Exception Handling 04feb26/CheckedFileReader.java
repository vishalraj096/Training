import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CheckedFileReader {
    public static void main(String[] args) {
        String fileName = "data.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("File not found");
        }
    }
}

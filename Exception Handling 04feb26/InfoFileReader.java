import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class InfoFileReader {
    public static void main(String[] args) {
        String fileName = "info.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line = br.readLine();
            if (line != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading file");
        }
    }
}

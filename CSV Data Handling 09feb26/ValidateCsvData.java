import java.io.*;
import java.util.regex.Pattern;

public class ValidateCsvData {
    public static void main(String[] args) {
        String fileName = "contacts.csv";

        Pattern emailPattern = Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$");
        Pattern phonePattern = Pattern.compile("^[0-9]{10}$");

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String header = br.readLine();
            if (header == null) {
                System.out.println("Empty file");
                return;
            }

            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length < 3) {
                    System.out.println("Invalid row (missing columns): " + line);
                    continue;
                }
                String email = parts[1].trim();
                String phone = parts[2].trim();

                boolean emailValid = emailPattern.matcher(email).matches();
                boolean phoneValid = phonePattern.matcher(phone).matches();

                if (!emailValid || !phoneValid) {
                    System.out.println("Invalid row: " + line);
                    if (!emailValid) {
                        System.out.println("  -> Invalid email");
                    }
                    if (!phoneValid) {
                        System.out.println("  -> Invalid phone number");
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

import java.io.*;
import java.util.Base64;

public class EncryptDecryptCsv {

    public static void encryptCsv(String inputFile, String outputFile) {
        try (BufferedReader br = new BufferedReader(new FileReader(inputFile));
                BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile))) {

            String header = br.readLine();
            if (header == null) {
                System.out.println("Empty file");
                return;
            }
            bw.write(header);
            bw.newLine();

            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length < 4) {
                    bw.write(line);
                    bw.newLine();
                    continue;
                }
                String id = parts[0].trim();
                String name = parts[1].trim();
                String email = parts[2].trim();
                String salary = parts[3].trim();

                String encEmail = Base64.getEncoder().encodeToString(email.getBytes());
                String encSalary = Base64.getEncoder().encodeToString(salary.getBytes());

                String encLine = id + "," + name + "," + encEmail + "," + encSalary;
                bw.write(encLine);
                bw.newLine();
            }

            System.out.println("Encrypted data written to " + outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void decryptCsv(String inputFile, String outputFile) {
        try (BufferedReader br = new BufferedReader(new FileReader(inputFile));
                BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile))) {

            String header = br.readLine();
            if (header == null) {
                System.out.println("Empty file");
                return;
            }
            bw.write(header);
            bw.newLine();

            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length < 4) {
                    bw.write(line);
                    bw.newLine();
                    continue;
                }
                String id = parts[0].trim();
                String name = parts[1].trim();
                String emailEnc = parts[2].trim();
                String salaryEnc = parts[3].trim();

                String email = new String(Base64.getDecoder().decode(emailEnc));
                String salary = new String(Base64.getDecoder().decode(salaryEnc));

                String decLine = id + "," + name + "," + email + "," + salary;
                bw.write(decLine);
                bw.newLine();
            }

            System.out.println("Decrypted data written to " + outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String input = "sensitive_employees.csv"; // ID,Name,Email,Salary
        String encrypted = "sensitive_employees_encrypted.csv";
        String decrypted = "sensitive_employees_decrypted.csv";

        encryptCsv(input, encrypted);
        decryptCsv(encrypted, decrypted);
    }
}

import java.io.*;

public class WriteEmployeesToCsv {
    public static void main(String[] args) {
        String fileName = "employees.csv";

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            bw.write("ID,Name,Department,Salary");
            bw.newLine();

            bw.write("E001,John Smith,IT,50000");
            bw.newLine();
            bw.write("E002,Alice Brown,HR,45000");
            bw.newLine();
            bw.write("E003,Bob White,Finance,60000");
            bw.newLine();
            bw.write("E004,Charlie Green,IT,70000");
            bw.newLine();
            bw.write("E005,Eve Black,Marketing,48000");
            bw.newLine();

            System.out.println("employees.csv created.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

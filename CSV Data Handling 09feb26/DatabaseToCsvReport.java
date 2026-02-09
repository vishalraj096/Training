import java.io.*;
import java.sql.*;

public class DatabaseToCsvReport {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/testdb";
        String user = "root";
        String password = "password";
        String outputFile = "employees_from_db.csv";

        String query = "SELECT id, name, department, salary FROM employees";

        try (Connection conn = DriverManager.getConnection(url, user, password);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile))) {

            bw.write("Employee ID,Name,Department,Salary");
            bw.newLine();

            while (rs.next()) {
                String id = rs.getString("id");
                String name = rs.getString("name");
                String dept = rs.getString("department");
                double salary = rs.getDouble("salary");
                String line = id + "," + name + "," + dept + "," + salary;
                bw.write(line);
                bw.newLine();
            }

            System.out.println("Report written to " + outputFile);
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}

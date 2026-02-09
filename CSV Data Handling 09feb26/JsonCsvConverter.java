import java.io.*;

public class JsonCsvConverter {

    public static void jsonToCsv(String jsonFile, String csvFile) {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(jsonFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        String json = sb.toString().trim();
        if (json.startsWith("["))
            json = json.substring(1);
        if (json.endsWith("]"))
            json = json.substring(0, json.length() - 1);
        json = json.trim();

        String[] objects = json.split("\\},\\s*\\{");

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(csvFile))) {
            bw.write("id,name,age,marks");
            bw.newLine();

            for (String obj : objects) {
                String o = obj;
                if (!o.startsWith("{"))
                    o = "{" + o;
                if (!o.endsWith("}"))
                    o = o + "}";
                o = o.replaceAll("[{}]", "");

                String[] fields = o.split(",");
                String id = "";
                String name = "";
                String age = "";
                String marks = "";

                for (String f : fields) {
                    String[] kv = f.split(":");
                    if (kv.length < 2)
                        continue;
                    String key = kv[0].trim().replace("\"", "");
                    String value = kv[1].trim().replace("\"", "");
                    if (key.equals("id"))
                        id = value;
                    else if (key.equals("name"))
                        name = value;
                    else if (key.equals("age"))
                        age = value;
                    else if (key.equals("marks"))
                        marks = value;
                }

                bw.write(id + "," + name + "," + age + "," + marks);
                bw.newLine();
            }

            System.out.println("CSV written to " + csvFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void csvToJson(String csvFile, String jsonFile) {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String header = br.readLine();
            if (header == null) {
                System.out.println("Empty CSV file");
                return;
            }

            sb.append("[");
            String line;
            boolean first = true;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty())
                    continue;
                String[] parts = line.split(",");
                if (parts.length < 4)
                    continue;
                String id = parts[0].trim();
                String name = parts[1].trim();
                String age = parts[2].trim();
                String marks = parts[3].trim();

                if (!first) {
                    sb.append(",");
                }
                sb.append("{")
                        .append("\"id\":").append(id).append(",")
                        .append("\"name\":\"").append(name).append("\",")
                        .append("\"age\":").append(age).append(",")
                        .append("\"marks\":").append(marks)
                        .append("}");
                first = false;
            }
            sb.append("]");
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(jsonFile))) {
            bw.write(sb.toString());
            System.out.println("JSON written to " + jsonFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String jsonFile = "students.json";
        String csvFileFromJson = "students_from_json.csv";
        String csvFile = "students.csv";
        String jsonFileFromCsv = "students_from_csv.json";

        jsonToCsv(jsonFile, csvFileFromJson);
        csvToJson(csvFile, jsonFileFromCsv);
    }
}

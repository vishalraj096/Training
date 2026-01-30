import java.io.*;

public class ConsoleInputToFile {
    public static void main(String[] args) throws Exception {
        if (args.length < 1)
            return;
        String path = args[0];
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                FileWriter fw = new FileWriter(path)) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.equals("exit"))
                    break;
                fw.write(line);
                fw.write(System.lineSeparator());
            }
        }
    }
}

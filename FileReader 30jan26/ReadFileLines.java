import java.io.*;

public class ReadFileLines {
    public static void main(String[] args) throws Exception {
        if (args.length < 1)
            return;
        String path = args[0];
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null)
                System.out.println(line);
        }
    }
}

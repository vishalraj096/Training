import java.io.*;
import java.nio.charset.Charset;

public class ReadBinaryAsText {
    public static void main(String[] args) throws Exception {
        if (args.length < 1)
            return;
        String path = args[0];
        String cs = args.length > 1 ? args[1] : "UTF-8";
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream(path), Charset.forName(cs)))) {
            String line;
            while ((line = br.readLine()) != null)
                System.out.println(line);
        }
    }
}

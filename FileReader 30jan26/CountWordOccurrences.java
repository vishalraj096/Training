import java.io.*;

public class CountWordOccurrences {
    public static void main(String[] args) throws Exception {
        if (args.length < 2)
            return;
        String path = args[0];
        String word = args[1];
        int count = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\s+");
                for (String p : parts)
                    if (p.equals(word))
                        count++;
            }
        }
        System.out.println(count);
    }
}

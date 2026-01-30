import java.io.*;

public class CompareStringsAndReaders {
    public static long timeBuffer(int n) {
        long t1 = System.nanoTime();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < n; i++)
            sb.append("hello");
        String s = sb.toString();
        long t2 = System.nanoTime();
        return (t2 - t1) / 1_000_000;
    }

    public static long timeBuilder(int n) {
        long t1 = System.nanoTime();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++)
            sb.append("hello");
        String s = sb.toString();
        long t2 = System.nanoTime();
        return (t2 - t1) / 1_000_000;
    }

    public static int countWordsWithFileReader(String path) throws Exception {
        int count = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\s+");
                for (String p : parts)
                    if (p.length() > 0)
                        count++;
            }
        }
        return count;
    }

    public static int countWordsWithInputStreamReader(String path) throws Exception {
        int count = 0;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path)))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\s+");
                for (String p : parts)
                    if (p.length() > 0)
                        count++;
            }
        }
        return count;
    }

    public static void main(String[] args) throws Exception {
        if (args.length < 1)
            return;
        String path = args[0];
        int n = 1000000;
        long a = timeBuilder(n);
        long b = timeBuffer(n);
        long c1 = System.nanoTime();
        int wc1 = countWordsWithFileReader(path);
        long c2 = System.nanoTime();
        long frMs = (c2 - c1) / 1_000_000;
        long d1 = System.nanoTime();
        int wc2 = countWordsWithInputStreamReader(path);
        long d2 = System.nanoTime();
        long isrMs = (d2 - d1) / 1_000_000;
        System.out.println("StringBuilder=" + a + "ms StringBuffer=" + b + "ms");
        System.out.println("FileReader words=" + wc1 + " time=" + frMs + "ms");
        System.out.println("InputStreamReader words=" + wc2 + " time=" + isrMs + "ms");
    }
}

import java.io.*;

public class FileReadComparison {
    static long fileReaderTime(String path) throws Exception {
        long t1 = System.nanoTime();
        FileReader fr = new FileReader(path);
        char[] buf = new char[4096];
        while (true) {
            int r = fr.read(buf);
            if (r == -1)
                break;
        }
        fr.close();
        long t2 = System.nanoTime();
        return (t2 - t1) / 1_000_000;
    }

    static long inputStreamReaderTime(String path) throws Exception {
        long t1 = System.nanoTime();
        InputStreamReader isr = new InputStreamReader(new FileInputStream(path));
        char[] buf = new char[4096];
        while (true) {
            int r = isr.read(buf);
            if (r == -1)
                break;
        }
        isr.close();
        long t2 = System.nanoTime();
        return (t2 - t1) / 1_000_000;
    }

    public static void main(String[] args) throws Exception {
        if (args.length < 1) {
            System.out.println("Provide file path");
            return;
        }
        String p = args[0];
        long a = fileReaderTime(p);
        long b = inputStreamReaderTime(p);
        System.out.println("FileReader=" + a + "ms InputStreamReader=" + b + "ms");
    }
}

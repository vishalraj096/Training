import java.util.*;

public class SearchComparison {
    static int linear(int[] a, int t) {
        for (int i = 0; i < a.length; i++)
            if (a[i] == t)
                return i;
        return -1;
    }

    static int binary(int[] a, int t) {
        int l = 0, r = a.length - 1;
        while (l <= r) {
            int m = (l + r) / 2;
            if (a[m] == t)
                return m;
            if (a[m] < t)
                l = m + 1;
            else
                r = m - 1;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] sizes = { 1000, 10000, 1000000 };
        Random rd = new Random(1);
        for (int n : sizes) {
            int[] a = new int[n];
            for (int i = 0; i < n; i++)
                a[i] = rd.nextInt(n * 10);
            int t = a[n / 2];

            long x1 = System.nanoTime();
            linear(a, t);
            long x2 = System.nanoTime();
            long linearMs = (x2 - x1) / 1_000_000;

            int[] b = Arrays.copyOf(a, a.length);
            long y1 = System.nanoTime();
            Arrays.sort(b);
            long y2 = System.nanoTime();
            long sortMs = (y2 - y1) / 1_000_000;

            long z1 = System.nanoTime();
            binary(b, t);
            long z2 = System.nanoTime();
            long binaryMs = (z2 - z1) / 1_000_000;

            System.out.println("N=" + n + " Linear=" + linearMs + "ms Sort=" + sortMs + "ms Binary=" + binaryMs + "ms");
        }
    }
}

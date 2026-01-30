import java.util.*;

public class DataStructureSearchComparison {
    static int linear(int[] a, int t) {
        for (int i = 0; i < a.length; i++)
            if (a[i] == t)
                return i;
        return -1;
    }

    public static void main(String[] args) {
        int[] sizes = { 1000, 100000, 1000000 };
        Random rd = new Random(3);
        for (int n : sizes) {
            int[] a = new int[n];
            for (int i = 0; i < n; i++)
                a[i] = i;
            int t = rd.nextInt(n);

            Set<Integer> hs = new HashSet<>();
            Set<Integer> ts = new TreeSet<>();
            for (int v : a) {
                hs.add(v);
                ts.add(v);
            }

            long x1 = System.nanoTime();
            linear(a, t);
            long x2 = System.nanoTime();
            long arrayMs = (x2 - x1) / 1_000_000;

            long y1 = System.nanoTime();
            hs.contains(t);
            long y2 = System.nanoTime();
            long hashMs = (y2 - y1) / 1_000_000;

            long z1 = System.nanoTime();
            ts.contains(t);
            long z2 = System.nanoTime();
            long treeMs = (z2 - z1) / 1_000_000;

            System.out.println("N=" + n + " Array=" + arrayMs + "ms HashSet=" + hashMs + "ms TreeSet=" + treeMs + "ms");
        }
    }
}

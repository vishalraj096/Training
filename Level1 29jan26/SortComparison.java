import java.util.*;

public class SortComparison {
    static void bubbleSort(int[] a) {
        int n = a.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (a[j] > a[j + 1]) {
                    int t = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = t;
                }
            }
        }
    }

    static void mergeSort(int[] a) {
        int[] tmp = new int[a.length];
        merge(a, tmp, 0, a.length - 1);
    }

    static void merge(int[] a, int[] tmp, int l, int r) {
        if (l >= r)
            return;
        int m = (l + r) / 2;
        merge(a, tmp, l, m);
        merge(a, tmp, m + 1, r);
        int i = l, j = m + 1, k = l;
        while (i <= m && j <= r) {
            if (a[i] <= a[j])
                tmp[k++] = a[i++];
            else
                tmp[k++] = a[j++];
        }
        while (i <= m)
            tmp[k++] = a[i++];
        while (j <= r)
            tmp[k++] = a[j++];
        for (int x = l; x <= r; x++)
            a[x] = tmp[x];
    }

    static void quickSort(int[] a) {
        q(a, 0, a.length - 1);
    }

    static void q(int[] a, int l, int r) {
        int i = l, j = r;
        int p = a[(l + r) / 2];
        while (i <= j) {
            while (a[i] < p)
                i++;
            while (a[j] > p)
                j--;
            if (i <= j) {
                int t = a[i];
                a[i] = a[j];
                a[j] = t;
                i++;
                j--;
            }
        }
        if (l < j)
            q(a, l, j);
        if (i < r)
            q(a, i, r);
    }

    public static void main(String[] args) {
        int[] sizes = { 1000, 10000, 20000 };
        Random rd = new Random(2);
        for (int n : sizes) {
            int[] base = new int[n];
            for (int i = 0; i < n; i++)
                base[i] = rd.nextInt(n * 10);
            int[] a1 = Arrays.copyOf(base, base.length);
            int[] a2 = Arrays.copyOf(base, base.length);
            int[] a3 = Arrays.copyOf(base, base.length);

            long b1 = System.nanoTime();
            bubbleSort(a1);
            long b2 = System.nanoTime();
            long bubbleMs = (b2 - b1) / 1_000_000;

            long m1 = System.nanoTime();
            mergeSort(a2);
            long m2 = System.nanoTime();
            long mergeMs = (m2 - m1) / 1_000_000;

            long q1 = System.nanoTime();
            quickSort(a3);
            long q2 = System.nanoTime();
            long quickMs = (q2 - q1) / 1_000_000;

            System.out.println("N=" + n + " Bubble=" + bubbleMs + "ms Merge=" + mergeMs + "ms Quick=" + quickMs + "ms");
        }
    }
}

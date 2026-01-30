import java.util.*;

public class FirstMissingPositiveAndBinaryIndex {
    public static int firstMissingPositive(int[] a) {
        HashSet<Integer> set = new HashSet<>();
        for (int x : a)
            if (x > 0)
                set.add(x);
        int i = 1;
        while (true) {
            if (!set.contains(i))
                return i;
            i++;
        }
    }

    public static int binaryIndex(int[] a, int t) {
        Arrays.sort(a);
        int l = 0, r = a.length - 1;
        while (l <= r) {
            int m = l + (r - l) / 2;
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
        int[] a = { 3, 4, -1, 1 };
        int missing = firstMissingPositive(a);
        int idx = binaryIndex(new int[] { 1, 2, 3, 4, 5 }, 4);
        System.out.println(missing + " " + idx);
    }
}

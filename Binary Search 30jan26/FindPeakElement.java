public class FindPeakElement {
    public static int find(int[] a) {
        int l = 0, r = a.length - 1;
        while (l < r) {
            int m = l + (r - l) / 2;
            if (a[m] < a[m + 1])
                l = m + 1;
            else
                r = m;
        }
        return l;
    }

    public static void main(String[] args) {
        int[] a = { 1, 3, 5, 4, 2 };
        System.out.println(find(a));
    }
}

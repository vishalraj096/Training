public class FindRotationPoint {
    public static int find(int[] a) {
        int l = 0, r = a.length - 1;
        while (l < r) {
            int m = l + (r - l) / 2;
            if (a[m] > a[r])
                l = m + 1;
            else
                r = m;
        }
        return l;
    }

    public static void main(String[] args) {
        int[] a = { 4, 5, 6, 7, 0, 1, 2 };
        System.out.println(find(a));
    }
}

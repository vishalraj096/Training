public class FirstAndLastOccurrence {
    public static int first(int[] a, int t) {
        int l = 0, r = a.length - 1, ans = -1;
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (a[m] == t) {
                ans = m;
                r = m - 1;
            } else if (a[m] < t)
                l = m + 1;
            else
                r = m - 1;
        }
        return ans;
    }

    public static int last(int[] a, int t) {
        int l = 0, r = a.length - 1, ans = -1;
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (a[m] == t) {
                ans = m;
                l = m + 1;
            } else if (a[m] < t)
                l = m + 1;
            else
                r = m - 1;
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] a = { 1, 2, 2, 2, 3, 4 };
        int t = 2;
        System.out.println(first(a, t) + " " + last(a, t));
    }
}

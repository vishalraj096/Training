public class SearchInMatrix {
    public static boolean search(int[][] m, int t) {
        int rows = m.length, cols = m[0].length;
        int l = 0, r = rows * cols - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            int i = mid / cols;
            int j = mid % cols;
            int v = m[i][j];
            if (v == t)
                return true;
            if (v < t)
                l = mid + 1;
            else
                r = mid - 1;
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] m = {
                { 1, 3, 5, 7 },
                { 10, 11, 16, 20 },
                { 23, 30, 34, 60 }
        };
        int t = 16;
        System.out.println(search(m, t));
    }
}

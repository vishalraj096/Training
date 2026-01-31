public class QuickSortProductPrices {
    public static void sort(double[] a) {
        qs(a, 0, a.length - 1);
    }

    public static void qs(double[] a, int l, int r) {
        if (l >= r)
            return;
        double pivot = a[r];
        int i = l;
        for (int j = l; j < r; j++) {
            if (a[j] <= pivot) {
                double t = a[i];
                a[i] = a[j];
                a[j] = t;
                i++;
            }
        }
        double t = a[i];
        a[i] = a[r];
        a[r] = t;
        qs(a, l, i - 1);
        qs(a, i + 1, r);
    }

    public static void main(String[] args) {
        double[] prices = { 999.99, 249.99, 499.5, 149.0, 799.0, 299.99 };
        sort(prices);
        for (double x : prices)
            System.out.print(x + " ");
    }
}

public class MergeSortBookPrices {
    public static void sort(double[] a) {
        double[] aux = new double[a.length];
        sort(a, aux, 0, a.length - 1);
    }

    public static void sort(double[] a, double[] aux, int l, int r) {
        if (l >= r)
            return;
        int m = (l + r) / 2;
        sort(a, aux, l, m);
        sort(a, aux, m + 1, r);
        merge(a, aux, l, m, r);
    }

    public static void merge(double[] a, double[] aux, int l, int m, int r) {
        int i = l, j = m + 1, k = l;
        while (i <= m && j <= r) {
            if (a[i] <= a[j])
                aux[k++] = a[i++];
            else
                aux[k++] = a[j++];
        }
        while (i <= m)
            aux[k++] = a[i++];
        while (j <= r)
            aux[k++] = a[j++];
        for (i = l; i <= r; i++)
            a[i] = aux[i];
    }

    public static void main(String[] args) {
        double[] prices = { 399.0, 149.5, 299.0, 89.9, 499.0, 199.0 };
        sort(prices);
        for (double x : prices)
            System.out.print(x + " ");
    }
}

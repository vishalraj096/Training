public class InsertionSortEmployeeIDs {
    public static void sort(int[] a) {
        for (int i = 1; i < a.length; i++) {
            int key = a[i];
            int j = i - 1;
            while (j >= 0 && a[j] > key) {
                a[j + 1] = a[j];
                j--;
            }
            a[j + 1] = key;
        }
    }

    public static void main(String[] args) {
        int[] ids = { 1023, 501, 204, 987, 120, 765, 432 };
        sort(ids);
        for (int x : ids)
            System.out.print(x + " ");
    }
}

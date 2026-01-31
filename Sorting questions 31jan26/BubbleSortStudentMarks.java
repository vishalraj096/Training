public class BubbleSortStudentMarks {
    public static void sort(int[] a) {
        int n = a.length;
        boolean swapped = true;
        while (swapped) {
            swapped = false;
            for (int i = 1; i < n; i++) {
                if (a[i - 1] > a[i]) {
                    int t = a[i];
                    a[i] = a[i - 1];
                    a[i - 1] = t;
                    swapped = true;
                }
            }
            n--;
        }
    }

    public static void main(String[] args) {
        int[] marks = { 45, 78, 62, 89, 55, 71, 33 };
        sort(marks);
        for (int x : marks)
            System.out.print(x + " ");
    }
}

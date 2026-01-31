public class SelectionSortExamScores {
    public static void sort(int[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < a.length; j++) {
                if (a[j] < a[min])
                    min = j;
            }
            int t = a[i];
            a[i] = a[min];
            a[min] = t;
        }
    }

    public static void main(String[] args) {
        int[] scores = { 88, 75, 92, 64, 99, 73, 81 };
        sort(scores);
        for (int x : scores)
            System.out.print(x + " ");
    }
}

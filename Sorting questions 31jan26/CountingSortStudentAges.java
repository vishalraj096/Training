public class CountingSortStudentAges {
    public static int[] sort(int[] a) {
        int min = 10, max = 18;
        int[] count = new int[max - min + 1];
        for (int x : a)
            count[x - min]++;
        for (int i = 1; i < count.length; i++)
            count[i] += count[i - 1];
        int[] out = new int[a.length];
        for (int i = a.length - 1; i >= 0; i--) {
            int idx = a[i] - min;
            out[count[idx] - 1] = a[i];
            count[idx]--;
        }
        return out;
    }

    public static void main(String[] args) {
        int[] ages = { 12, 17, 15, 10, 18, 14, 13, 16, 12, 11 };
        int[] res = sort(ages);
        for (int x : res)
            System.out.print(x + " ");
    }
}

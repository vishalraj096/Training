public class SecondLargest {
    public static void main(String[] args) {
        int[] arr = { 4, 2, 5, 7, 3, 1, 6, 9 };
        int n = arr.length;
        int maxi1 = arr[0], maxi2 = Integer.MIN_VALUE;
        for (int i = 1; i < n; ++i) {
            if (arr[i] > maxi1) {
                maxi2 = maxi1;
                maxi2 = arr[i];
            } else if (arr[i] > maxi2 && arr[i] < maxi1) {
                maxi2 = arr[i];
            }
        }
        System.out.println(maxi2);
    }
}

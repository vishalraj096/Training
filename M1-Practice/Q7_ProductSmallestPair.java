import java.util.*;

public class Q7_ProductSmallestPair {
    public static int productSmallestPair(int sum, int[] arr) {
        if (arr == null || arr.length < 2) return -1;
        int first = Integer.MAX_VALUE;
        int second = Integer.MAX_VALUE;
        for (int x : arr) {
            if (x < first) {
                second = first;
                first = x;
            } else if (x < second) {
                second = x;
            }
        }
        if (first + second <= sum) return first * second;
        return 0;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int sum = sc.nextInt();
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = sc.nextInt();
        System.out.println(productSmallestPair(sum, arr));
    }
}

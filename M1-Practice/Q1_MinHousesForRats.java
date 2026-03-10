import java.util.*;

public class Q1_MinHousesForRats {
    public static int minHousesForRats(int r, int unit, int[] arr) {
        if (arr == null) return -1;
        int required = r * unit;
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            if (sum >= required) return i + 1;
        }
        return 0;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int r = sc.nextInt();
        int unit = sc.nextInt();
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = sc.nextInt();
        System.out.println(minHousesForRats(r, unit, arr));
    }
}

import java.util.*;

public class Q4_FindCount {
    public static int findCount(int[] arr, int length, int num, int diff) {
        int count = 0;
        for (int i = 0; i < length; i++) {
            if (Math.abs(arr[i] - num) <= diff) count++;
        }
        return count == 0 ? -1 : count;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int length = sc.nextInt();
        int[] arr = new int[length];
        for (int i = 0; i < length; i++) arr[i] = sc.nextInt();
        int num = sc.nextInt();
        int diff = sc.nextInt();
        System.out.println(findCount(arr, length, num, diff));
    }
}

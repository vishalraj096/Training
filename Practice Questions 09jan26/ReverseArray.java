import java.util.Arrays;

public class ReverseArray {
    public static void main(String[] args) {
        int[] arr = { 4, 2, 6, 8, 3, 2, 7 };
        int n = arr.length;
        int i = 0, j = n - 1;
        while (i < j) {
            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
            i++;
            j--;
        }
        System.out.println(Arrays.toString(arr));
    }
}

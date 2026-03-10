public class Ques10 {
    public static void main(String[] args) {
        int[] arr = { 5, 0, 7, 6 };
        int n = arr.length;
        int j = -1;
        for (int i = 0; i < n; ++i) {
            if (arr[i] == 0) {
                j = i;
                break;
            }
        }
        if (j != -1) {
            for (int i = j + 1; i < n; ++i) {
                if (arr[i] != 0) {
                    arr[j] = arr[i];
                    arr[i] = 0;
                    j++;
                }
            }
        }
        System.out.println(java.util.Arrays.toString(arr));
    }
}

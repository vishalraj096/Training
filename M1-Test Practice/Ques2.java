public class Ques2 {
    public static void main(String[] args) {
        int[] arr = { 1, 2, 3, 4, 3, 2, 1 };
        int n = arr.length;
        for (int i = 1; i < n - 1; ++i) {
            if (arr[i] > arr[i - 1] && arr[i] > arr[i + 1]) {
                System.out.println(arr[i]);
                break;
            }
        }
    }
}

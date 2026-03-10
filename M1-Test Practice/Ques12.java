public class Ques12 {
    public static void main(String[] args) {
        int[] arr = { 1, 2, 4, 6, 3, 7, 8 };
        int n = arr.length;
        int xor = 0;
        for (int i = 0; i < n; ++i) {
            xor ^= arr[i];
            xor ^= 1 + i;
        }
        xor ^= n + 1;
        System.out.println(xor);
    }
}

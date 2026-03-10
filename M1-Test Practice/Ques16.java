public class Ques16 {
    public static void main(String[] args) {
        int[] arr = { 1, -2, 3, -4, 5, 6 };
        int n = arr.length;
        int pos = 0;
        for (int i : arr) {
            if (i > 0) {
                pos++;
            }
        }
        int cnt = 0, mid = (1 + pos) / 2;
        for (int i : arr) {
            if (i > 0) {
                cnt++;
                if (cnt == mid) {
                    System.out.println(i);
                }
            }
        }
    }
}

public class Ques6 {
    public static void main(String[] args) {
        String s = "this is level 71";
        String[] arr = s.split(" ");
        int cnt = 0;
        for (int i = 0, n = arr.length; i < n; ++i) {
            if (isPalindrome(arr[i])) {
                cnt++;
            }
        }
        System.out.println(cnt);
    }

    public static boolean isPalindrome(String s) {
        int l = 0, r = s.length() - 1;
        while (l < r) {
            if (s.charAt(l++) != s.charAt(r--)) {
                return false;
            }
        }
        return true;
    }
}

import java.util.*;

public class Q17_PalindromeRange {
    private static boolean isPalindrome(int x) {
        int original = x;
        int rev = 0;
        while (x > 0) {
            rev = rev * 10 + (x % 10);
            x /= 10;
        }
        return original == rev;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int low = sc.nextInt();
        int high = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        for (int i = low; i <= high; i++) {
            if (isPalindrome(i)) {
                if (sb.length() > 0) sb.append(", ");
                sb.append(i);
            }
        }
        System.out.println(sb);
    }
}

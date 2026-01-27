import java.util.Scanner;

public class CheckPalindrome {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = n, rev = 0;
        while (n != 0) {
            int tmp = n % 10;
            rev = rev * 10 + tmp;
            n /= 10;
        }
        if (rev == m) {
            System.out.println("Palindrome");
        } else {
            System.out.println("Not Palindrome");
        }
        sc.close();
    }
}

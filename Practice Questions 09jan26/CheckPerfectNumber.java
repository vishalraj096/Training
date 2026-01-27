import java.util.Scanner;

public class CheckPerfectNumber {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        if (isPerfect(n)) {
            System.out.println("Perfect Number");
        } else {
            System.out.println("Not Perfect Number");
        }
        sc.close();
    }

    private static boolean isPerfect(int n) {
        if (n <= 1)
            return false;
        int sum = 1;
        for (int i = 2; i * i <= n; ++i) {
            if (n % i == 0) {
                sum += i;
                int d = n / i;
                if (d != i)
                    sum += d;
            }
        }
        return sum == n;
    }
}

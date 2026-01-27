import java.util.Scanner;

public class CheckArmstrong {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int digits = Integer.toString(n).length();
        int sum = 0, m = n;
        while (n != 0) {
            int tmp = n % 10;
            sum += Math.pow(tmp, digits);
            n /= 10;
        }
        if (m == sum) {
            System.out.println("Armstrong");
        } else {
            System.out.println("Not Armstrong");
        }
        sc.close();
    }
}

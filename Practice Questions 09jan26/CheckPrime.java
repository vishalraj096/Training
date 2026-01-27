import java.util.Scanner;

public class CheckPrime {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        boolean flg = true;
        for (int i = 2; i * i <= n; ++i) {
            if (n % i == 0) {
                flg = false;
                break;
            }
        }
        if (flg) {
            System.out.println("Is Prime");
        } else {
            System.out.println("Not Prime");
        }
        sc.close();
    }
}

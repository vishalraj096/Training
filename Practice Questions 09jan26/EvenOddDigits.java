import java.util.Scanner;

public class EvenOddDigits {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int even = 0, odd = 0;
        while (n != 0) {
            int tmp = n % 10;
            if ((tmp & 1) == 1) {
                odd++;
            } else {
                even++;
            }
        }
        System.out.println(even);
        System.out.println(odd);
        sc.close();
    }
}

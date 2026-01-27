import java.util.Scanner;

public class MissingNumberInArray {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long sum = 0;
        for (int i = 0; i < n - 1; ++i) {
            sum += sc.nextLong();
        }
        long expected = (long) n * (n + 1) / 2;
        System.out.println(expected - sum);
        sc.close();
    }
}

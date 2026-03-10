import java.util.*;

public class Q5_DifferenceOfSum {
    public static int differenceOfSum(int n, int m) {
        int div = 0;
        int nonDiv = 0;
        for (int i = 1; i <= m; i++) {
            if (i % n == 0) div += i;
            else nonDiv += i;
        }
        return nonDiv - div;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        System.out.println(differenceOfSum(n, m));
    }
}

import java.util.*;

public class Q13_MaxExponents {
    public static int maxExponents(int a, int b) {
        int bestNumber = a;
        int bestPower = powerOfTwo(a);
        for (int i = a + 1; i <= b; i++) {
            int p = powerOfTwo(i);
            if (p > bestPower) {
                bestPower = p;
                bestNumber = i;
            }
        }
        return bestNumber;
    }

    private static int powerOfTwo(int x) {
        int count = 0;
        while (x > 0 && x % 2 == 0) {
            count++;
            x /= 2;
        }
        return count;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();
        System.out.println(maxExponents(a, b));
    }
}

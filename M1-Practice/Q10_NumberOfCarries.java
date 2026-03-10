import java.util.*;

public class Q10_NumberOfCarries {
    public static int numberOfCarries(int num1, int num2) {
        int carries = 0;
        int carry = 0;
        while (num1 > 0 || num2 > 0) {
            int d1 = num1 % 10;
            int d2 = num2 % 10;
            int sum = d1 + d2 + carry;
            if (sum >= 10) {
                carries++;
                carry = 1;
            } else {
                carry = 0;
            }
            num1 /= 10;
            num2 /= 10;
        }
        return carries;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num1 = sc.nextInt();
        int num2 = sc.nextInt();
        System.out.println(numberOfCarries(num1, num2));
    }
}

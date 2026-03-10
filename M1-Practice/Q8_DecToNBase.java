import java.util.*;

public class Q8_DecToNBase {
    public static String decToNBase(int n, int num) {
        if (num == 0) return "0";
        String symbols = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder sb = new StringBuilder();
        int value = num;
        while (value > 0) {
            int rem = value % n;
            sb.append(symbols.charAt(rem));
            value /= n;
        }
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int num = sc.nextInt();
        System.out.println(decToNBase(n, num));
    }
}

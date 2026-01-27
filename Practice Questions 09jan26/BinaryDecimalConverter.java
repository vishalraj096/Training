import java.util.Scanner;

public class BinaryDecimalConverter {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();

        if (isBinary(s)) {
            long dec = 0;
            for (int i = 0; i < s.length(); ++i) {
                dec = dec * 2 + (s.charAt(i) - '0');
            }
            System.out.println(dec);
        } else {
            long n = Long.parseLong(s);
            if (n == 0) {
                System.out.println(0);
            } else {
                StringBuilder bin = new StringBuilder();
                while (n > 0) {
                    bin.append(n % 2);
                    n /= 2;
                }
                System.out.println(bin.reverse());
            }
        }
        sc.close();
    }

    private static boolean isBinary(String s) {
        if (s.length() == 0)
            return false;
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            if (c != '0' && c != '1')
                return false;
        }
        return true;
    }
}

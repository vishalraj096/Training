import java.util.Scanner;

public class SumEvenPositionDigits {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        int sum = 0;
        for (int i = 0; i < s.length(); ++i) {
            int pos = i + 1;
            char c = s.charAt(i);
            if (pos % 2 == 0 && c >= '0' && c <= '9') {
                sum += (c - '0');
            }
        }
        System.out.println(sum);
        sc.close();
    }
}

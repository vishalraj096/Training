import java.util.*;

public class Q12_OperationChoices {
    public static int operationChoices(int c, int a, int b) {
        if (c == 1) return a + b;
        if (c == 2) return a - b;
        if (c == 3) return a * b;
        if (c == 4) return a / b;
        return 0;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int c = sc.nextInt();
        int a = sc.nextInt();
        int b = sc.nextInt();
        System.out.println(operationChoices(c, a, b));
    }
}

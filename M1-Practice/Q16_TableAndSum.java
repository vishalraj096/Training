import java.util.*;

public class Q16_TableAndSum {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int sum = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= 10; i++) {
            int val = n * i;
            sum += val;
            sb.append(val);
            if (i < 10) sb.append(", ");
        }
        System.out.println(sb);
        System.out.println(sum);
    }
}

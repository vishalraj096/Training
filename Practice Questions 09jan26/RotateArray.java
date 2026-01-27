import java.util.Scanner;

public class RotateArray {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; ++i)
            a[i] = sc.nextInt();
        String dirStr = sc.next();
        char dir = dirStr.charAt(0); // 'L' or 'R'
        int k = sc.nextInt();
        if (n == 0) {
            System.out.println();
            sc.close();
            return;
        }
        k %= n;
        int[] res = new int[n];
        if (dir == 'L' || dir == 'l') {
            for (int i = 0; i < n; ++i)
                res[i] = a[(i + k) % n];
        } else {
            for (int i = 0; i < n; ++i)
                res[i] = a[(i + n - k) % n];
        }
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < n; ++i) {
            out.append(res[i]);
            if (i != n - 1)
                out.append(" ");
        }
        System.out.println(out);
        sc.close();
    }
}

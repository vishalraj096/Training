import java.util.Scanner;

public class PrintASCIIValues {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < s.length(); ++i) {
            out.append((int) s.charAt(i));
            if (i != s.length() - 1)
                out.append(" ");
        }
        System.out.println(out);
        sc.close();
    }
}

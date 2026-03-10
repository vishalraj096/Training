import java.util.*;

public class Q3_PasswordChecker {
    public static int passwordChecker(String str) {
        if (str == null || str.length() < 4) return 0;
        if (Character.isDigit(str.charAt(0))) return 0;
        boolean hasDigit = false;
        boolean hasUpper = false;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == ' ' || c == '/') return 0;
            if (Character.isDigit(c)) hasDigit = true;
            if (Character.isUpperCase(c)) hasUpper = true;
        }
        return (hasDigit && hasUpper) ? 1 : 0;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        System.out.println(passwordChecker(str));
    }
}

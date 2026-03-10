import java.util.*;

public class Q11_ReplaceCharacter {
    public static String replaceCharacter(String str, char ch1, char ch2) {
        if (str == null) return null;
        if (ch1 == ch2) return str;
        boolean hasCh1 = false;
        boolean hasCh2 = false;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ch1) hasCh1 = true;
            if (str.charAt(i) == ch2) hasCh2 = true;
        }
        if (!hasCh1 && !hasCh2) return str;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == ch1) sb.append(ch2);
            else if (c == ch2) sb.append(ch1);
            else sb.append(c);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        char ch1 = sc.next().charAt(0);
        char ch2 = sc.next().charAt(0);
        String ans = replaceCharacter(str, ch1, ch2);
        if (ans == null) System.out.println("null");
        else System.out.println(ans);
    }
}

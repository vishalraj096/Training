import java.util.*;

public class Q9_MoveHyphen {
    public static String moveHyphen(String str) {
        if (str == null) return null;
        StringBuilder hy = new StringBuilder();
        StringBuilder rest = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == '-') hy.append(c);
            else rest.append(c);
        }
        return hy.append(rest).toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        String ans = moveHyphen(str);
        if (ans == null) System.out.println("null");
        else System.out.println(ans);
    }
}

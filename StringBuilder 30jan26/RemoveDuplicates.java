import java.util.*;

public class RemoveDuplicates {
    public static String solve(String s) {
        StringBuilder sb = new StringBuilder();
        HashSet<Character> set = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!set.contains(c)) {
                sb.append(c);
                set.add(c);
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String s = args.length > 0 ? args[0] : "banana";
        System.out.println(solve(s));
    }
}

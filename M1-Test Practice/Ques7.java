import java.util.*;

public class Ques7 {
    public static void main(String[] args) {
        String str = "CsharpstarZ";
        StringBuilder res = new StringBuilder();
        Set<Character> s = new HashSet<>();
        for (int i = 0, n = str.length(); i < n; ++i) {
            char ch = str.charAt(i);
            if (!s.contains(ch)) {
                res.append(ch);
                s.add(ch);
            }
        }
        System.out.println(res.toString());
    }
}

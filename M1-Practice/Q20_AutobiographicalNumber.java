import java.util.*;

public class Q20_AutobiographicalNumber {
    public static int findAutoCount(String n) {
        if (n == null) return 0;
        int len = n.length();
        int[] freq = new int[10];
        for (int i = 0; i < len; i++) {
            int d = n.charAt(i) - '0';
            if (d < 0 || d > 9) return 0;
            freq[d]++;
        }
        for (int i = 0; i < len; i++) {
            int expected = n.charAt(i) - '0';
            int actual = i < 10 ? freq[i] : 0;
            if (expected != actual) return 0;
        }
        HashSet<Character> set = new HashSet<>();
        for (int i = 0; i < len; i++) set.add(n.charAt(i));
        return set.size();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String n = sc.next();
        System.out.println(findAutoCount(n));
    }
}

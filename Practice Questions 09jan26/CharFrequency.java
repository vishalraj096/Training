import java.util.*;

public class CharFrequency {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        Map<Character, Integer> mp = new HashMap<>();
        for (int i = 0, n = str.length(); i < n; ++i) {
            char ch = str.charAt(i);
            mp.put(ch, mp.getOrDefault(ch, 0) + 1);
        }
        for (Map.Entry<Character, Integer> m : mp.entrySet()) {
            System.out.println(m.getKey() + ": " + m.getValue());
        }
        sc.close();
    }
}

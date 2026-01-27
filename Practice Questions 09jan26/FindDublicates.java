import java.util.*;

public class FindDublicates {
    public static void main(String[] args) {
        int[] arr = { 2, 4, 7, 1, 9, 3, 8, 1, 5 };
        Map<Integer, Integer> mp = new HashMap<>();
        for (int i : arr) {
            mp.put(i, mp.getOrDefault(i, 0) + 1);
        }
        for (Map.Entry<Integer, Integer> m : mp.entrySet()) {
            if (m.getValue() > 1) {
                System.out.println(m.getKey());
            }
        }
    }
}

import java.util.*;

public class Ques8 {
    public static void main(String[] args) {
        int[] arr = { 11, 11, 11, 13, 13, 20 };
        int[] res = removeDublicate(arr);
        for (int i = 0, n = res.length; i < n; ++i) {
            System.out.print(res[i] + " ");
        }
    }

    public static int[] removeDublicate(int[] arr) {
        List<Integer> ls = new ArrayList<>();
        Set<Integer> s = new HashSet<>();
        for (int i = 0, n = arr.length; i < n; ++i) {
            if (!s.contains(arr[i])) {
                ls.add(arr[i]);
                s.add(arr[i]);
            }
        }
        return ls.stream().mapToInt(i -> i).toArray();
    }
}

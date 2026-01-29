import java.util.*;

public class PairWithGivenSum {
    public static int[] findPairIndices(int[] arr, int target) {
        Map<Integer, Integer> indexOf = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            int need = target - arr[i];
            if (indexOf.containsKey(need)) {
                return new int[] { indexOf.get(need), i };
            }
            indexOf.put(arr[i], i);
        }
        return null;
    }

    public static void main(String[] args) {
        int[] arr = { 2, 7, 11, 15 };
        int target = 9;
        int[] ans = findPairIndices(arr, target);
        System.out.println(Arrays.toString(ans));
    }
}

import java.util.*;

public class ZeroSumSubarrays {
    public static List<int[]> findZeroSumSubarrays(int[] arr) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        List<int[]> result = new ArrayList<>();
        int sum = 0;
        map.computeIfAbsent(0, k -> new ArrayList<>()).add(-1);
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            List<Integer> prev = map.get(sum);
            if (prev != null) {
                for (int startIdx : prev) {
                    result.add(new int[] { startIdx + 1, i });
                }
            }
            map.computeIfAbsent(sum, k -> new ArrayList<>()).add(i);
        }
        return result;
    }

    public static void main(String[] args) {
        int[] arr = { 3, 4, -7, 3, -1, 4, -2, 1 };
        List<int[]> res = findZeroSumSubarrays(arr);
        for (int[] r : res) {
            System.out.println("[" + r[0] + ", " + r[1] + "]");
        }
    }
}

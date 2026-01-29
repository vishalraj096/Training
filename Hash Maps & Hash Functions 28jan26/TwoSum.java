import java.util.*;

public class TwoSum {
    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> idx = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int need = target - nums[i];
            Integer j = idx.get(need);
            if (j != null)
                return new int[] { j, i };
            idx.put(nums[i], i);
        }
        return null;
    }

    public static void main(String[] args) {
        int[] nums = { 3, 2, 4 };
        int target = 6;
        System.out.println(Arrays.toString(twoSum(nums, target)));
    }
}

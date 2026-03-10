import java.util.*;

public class Q6_SecondOrderSum {
    public static int secondOrderElementSum(int[] arr) {
        if (arr == null || arr.length <= 3) return 0;
        ArrayList<Integer> even = new ArrayList<>();
        ArrayList<Integer> odd = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            if (i % 2 == 0) even.add(arr[i]);
            else odd.add(arr[i]);
        }
        if (even.size() < 2 || odd.size() < 2) return 0;
        Collections.sort(even);
        Collections.sort(odd);
        int secondLargestEven = even.get(even.size() - 2);
        int secondSmallestOdd = odd.get(1);
        return secondLargestEven + secondSmallestOdd;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = sc.nextInt();
        System.out.println(secondOrderElementSum(arr));
    }
}

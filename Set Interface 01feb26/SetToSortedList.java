import java.util.*;

public class SetToSortedList {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            Set<Integer> set = new HashSet<>();
            
            System.out.println("Enter number of elements:");
            int n = sc.nextInt();
            System.out.println("Enter elements:");
            for (int i = 0; i < n; i++) {
                set.add(sc.nextInt());
            }
            
            System.out.println("HashSet: " + set);
            
            List<Integer> sortedList = new ArrayList<>(set);
            Collections.sort(sortedList);
            
            System.out.println("Sorted List: " + sortedList);
        }
    }
}

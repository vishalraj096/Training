import java.util.*;

public class RemoveDuplicates {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("Enter number of elements:");
            int n = sc.nextInt();
            
            List<Integer> list = new ArrayList<>();
            
            System.out.println("Enter elements:");
            for (int i = 0; i < n; i++) {
                int num = sc.nextInt();
                list.add(num);
            }
            
            System.out.println("Original List: " + list);
            List<Integer> result = removeDuplicates(list);
            System.out.println("List after removing duplicates: " + result);
        }
    }
    
    public static List<Integer> removeDuplicates(List<Integer> list) {
        List<Integer> result = new ArrayList<>();
        Set<Integer> seen = new HashSet<>();
        
        for (Integer element : list) {
            if (!seen.contains(element)) {
                seen.add(element);
                result.add(element);
            }
        }
        
        return result;
    }
}

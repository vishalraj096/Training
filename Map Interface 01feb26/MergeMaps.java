import java.util.*;

public class MergeMaps {
    public static Map<String, Integer> mergeMaps(Map<String, Integer> map1, Map<String, Integer> map2) {
        Map<String, Integer> mergedMap = new HashMap<>(map1);
        
        for (Map.Entry<String, Integer> entry : map2.entrySet()) {
            String key = entry.getKey();
            int value = entry.getValue();
            
            mergedMap.put(key, mergedMap.getOrDefault(key, 0) + value);
        }
        
        return mergedMap;
    }
    
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            Map<String, Integer> map1 = new HashMap<>();
            Map<String, Integer> map2 = new HashMap<>();
            
            System.out.print("Enter number of entries for Map 1: ");
            int n1 = sc.nextInt();
            sc.nextLine();
            
            System.out.println("Enter entries for Map 1:");
            for (int i = 0; i < n1; i++) {
                System.out.print("Enter key: ");
                String key = sc.nextLine();
                
                System.out.print("Enter value: ");
                int value = sc.nextInt();
                sc.nextLine();
                
                map1.put(key, value);
            }
            
            System.out.print("\nEnter number of entries for Map 2: ");
            int n2 = sc.nextInt();
            sc.nextLine();
            
            System.out.println("Enter entries for Map 2:");
            for (int i = 0; i < n2; i++) {
                System.out.print("Enter key: ");
                String key = sc.nextLine();
                
                System.out.print("Enter value: ");
                int value = sc.nextInt();
                sc.nextLine();
                
                map2.put(key, value);
            }
            
            System.out.println("\nMap 1: " + map1);
            System.out.println("Map 2: " + map2);
            
            Map<String, Integer> mergedMap = mergeMaps(map1, map2);
            
            System.out.println("Merged Map: " + mergedMap);
        }
    }
}

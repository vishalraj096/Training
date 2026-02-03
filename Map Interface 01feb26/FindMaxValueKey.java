import java.util.*;

public class FindMaxValueKey {
    public static String findKeyWithMaxValue(Map<String, Integer> map) {
        if (map.isEmpty()) {
            return null;
        }
        
        String maxKey = null;
        int maxValue = Integer.MIN_VALUE;
        
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue() > maxValue) {
                maxValue = entry.getValue();
                maxKey = entry.getKey();
            }
        }
        
        return maxKey;
    }
    
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            Map<String, Integer> map = new HashMap<>();
            
            System.out.print("Enter number of entries: ");
            int n = sc.nextInt();
            sc.nextLine();
            
            System.out.println("Enter key-value pairs:");
            for (int i = 0; i < n; i++) {
                System.out.print("Enter key: ");
                String key = sc.nextLine();
                
                System.out.print("Enter value: ");
                int value = sc.nextInt();
                sc.nextLine();
                
                map.put(key, value);
            }
            
            System.out.println("\nMap: " + map);
            
            String maxKey = findKeyWithMaxValue(map);
            
            if (maxKey != null) {
                System.out.println("Key with maximum value: " + maxKey + " (Value: " + map.get(maxKey) + ")");
            } else {
                System.out.println("Map is empty!");
            }
        }
    }
}

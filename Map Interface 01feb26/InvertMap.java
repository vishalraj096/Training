import java.util.*;

public class InvertMap {
    public static Map<Integer, List<String>> invertMap(Map<String, Integer> originalMap) {
        Map<Integer, List<String>> invertedMap = new HashMap<>();
        
        for (Map.Entry<String, Integer> entry : originalMap.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            
            if (!invertedMap.containsKey(value)) {
                invertedMap.put(value, new ArrayList<>());
            }
            
            invertedMap.get(value).add(key);
        }
        
        return invertedMap;
    }
    
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            Map<String, Integer> originalMap = new HashMap<>();
            
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
                
                originalMap.put(key, value);
            }
            
            System.out.println("\nOriginal Map: " + originalMap);
            
            Map<Integer, List<String>> invertedMap = invertMap(originalMap);
            
            System.out.println("Inverted Map: " + invertedMap);
        }
    }
}

import java.util.*;

public class FindFrequency {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("Enter number of elements:");
            int n = sc.nextInt();
            sc.nextLine();
            
            List<String> list = new ArrayList<>();
            
            System.out.println("Enter elements:");
            for (int i = 0; i < n; i++) {
                String element = sc.nextLine();
                list.add(element);
            }
            
            Map<String, Integer> frequencyMap = findFrequency(list);
            
            System.out.println("Frequency Map: " + frequencyMap);
        }
    }
    
    public static Map<String, Integer> findFrequency(List<String> list) {
        Map<String, Integer> frequencyMap = new HashMap<>();
        
        for (String element : list) {
            if (frequencyMap.containsKey(element)) {
                frequencyMap.put(element, frequencyMap.get(element) + 1);
            } else {
                frequencyMap.put(element, 1);
            }
        }
        
        return frequencyMap;
    }
}

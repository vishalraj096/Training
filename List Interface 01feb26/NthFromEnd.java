import java.util.*;

public class NthFromEnd {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("Enter number of elements:");
            int n = sc.nextInt();
            sc.nextLine();
            
            LinkedList<String> list = new LinkedList<>();
            
            System.out.println("Enter elements:");
            for (int i = 0; i < n; i++) {
                String element = sc.nextLine();
                list.add(element);
            }
            
            System.out.println("Enter N (position from end):");
            int nthPosition = sc.nextInt();
            
            String result = findNthFromEnd(list, nthPosition);
            
            if (result != null) {
                System.out.println("Nth element from end: " + result);
            } else {
                System.out.println("Invalid position");
            }
        }
    }
    
    public static String findNthFromEnd(LinkedList<String> list, int n) {
        if (n <= 0) {
            return null;
        }
        
        int firstPointer = 0;
        int secondPointer = 0;
        
        for (int i = 0; i < n; i++) {
            if (firstPointer >= list.size()) {
                return null;
            }
            firstPointer++;
        }
        
        while (firstPointer < list.size()) {
            firstPointer++;
            secondPointer++;
        }
        
        return list.get(secondPointer);
    }
}

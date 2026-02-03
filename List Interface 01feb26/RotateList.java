import java.util.*;

public class RotateList {
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
            
            System.out.println("Enter number of positions to rotate:");
            int rotateBy = sc.nextInt();
            
            System.out.println("Original List: " + list);
            rotateList(list, rotateBy);
            System.out.println("Rotated List: " + list);
        }
    }
    
    public static void rotateList(List<Integer> list, int rotateBy) {
        int n = list.size();
        if (n == 0) return;
        
        rotateBy = rotateBy % n;
        
        List<Integer> temp = new ArrayList<>();
        
        for (int i = rotateBy; i < n; i++) {
            temp.add(list.get(i));
        }
        
        for (int i = 0; i < rotateBy; i++) {
            temp.add(list.get(i));
        }
        
        for (int i = 0; i < n; i++) {
            list.set(i, temp.get(i));
        }
    }
}

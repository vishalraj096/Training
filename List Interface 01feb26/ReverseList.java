import java.util.*;

public class ReverseList {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("Enter number of elements:");
            int n = sc.nextInt();
            
            ArrayList<Integer> arrayList = new ArrayList<>();
            LinkedList<Integer> linkedList = new LinkedList<>();
            
            System.out.println("Enter elements:");
            for (int i = 0; i < n; i++) {
                int num = sc.nextInt();
                arrayList.add(num);
                linkedList.add(num);
            }
            
            System.out.println("Original ArrayList: " + arrayList);
            reverseArrayList(arrayList);
            System.out.println("Reversed ArrayList: " + arrayList);
            
            System.out.println("Original LinkedList: " + linkedList);
            reverseLinkedList(linkedList);
            System.out.println("Reversed LinkedList: " + linkedList);
        }
    }
    
    public static void reverseArrayList(ArrayList<Integer> list) {
        int left = 0;
        int right = list.size() - 1;
        while (left < right) {
            Integer temp = list.get(left);
            list.set(left, list.get(right));
            list.set(right, temp);
            left++;
            right--;
        }
    }
    
    public static void reverseLinkedList(LinkedList<Integer> list) {
        int left = 0;
        int right = list.size() - 1;
        while (left < right) {
            Integer temp = list.get(left);
            list.set(left, list.get(right));
            list.set(right, temp);
            left++;
            right--;
        }
    }
}

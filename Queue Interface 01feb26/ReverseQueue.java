import java.util.*;

public class ReverseQueue {
    public static void reverseQueue(Queue<Integer> queue) {
        Stack<Integer> stack = new Stack<>();
        
        while (!queue.isEmpty()) {
            stack.push(queue.remove());
        }
        
        while (!stack.isEmpty()) {
            queue.add(stack.pop());
        }
    }
    
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            Queue<Integer> queue = new LinkedList<>();
            
            System.out.print("Enter number of elements: ");
            int n = sc.nextInt();
            
            System.out.println("Enter elements:");
            for (int i = 0; i < n; i++) {
                queue.add(sc.nextInt());
            }
            
            System.out.println("Original Queue: " + queue);
            
            reverseQueue(queue);
            
            System.out.println("Reversed Queue: " + queue);
        }
    }
}

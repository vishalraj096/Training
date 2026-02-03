import java.util.*;

class MyStack {
    Queue<Integer> q1;
    Queue<Integer> q2;
    
    public MyStack() {
        q1 = new LinkedList<>();
        q2 = new LinkedList<>();
    }
    
    public void push(int x) {
        q2.add(x);
        
        while (!q1.isEmpty()) {
            q2.add(q1.remove());
        }
        
        Queue<Integer> temp = q1;
        q1 = q2;
        q2 = temp;
    }
    
    public int pop() {
        if (q1.isEmpty()) {
            System.out.println("Stack is empty!");
            return -1;
        }
        return q1.remove();
    }
    
    public int top() {
        if (q1.isEmpty()) {
            System.out.println("Stack is empty!");
            return -1;
        }
        return q1.peek();
    }
    
    public boolean isEmpty() {
        return q1.isEmpty();
    }
    
    public void display() {
        if (q1.isEmpty()) {
            System.out.println("Stack is empty!");
        } else {
            System.out.println("Stack contents (top to bottom): " + q1);
        }
    }
}

public class StackUsingQueues {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        MyStack stack = new MyStack();
        
        while (true) {
            System.out.println("\n=== Stack Using Queues ===");
            System.out.println("1. Push");
            System.out.println("2. Pop");
            System.out.println("3. Top");
            System.out.println("4. Display");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            
            int choice = sc.nextInt();
            
            switch (choice) {
                case 1 -> {
                    System.out.print("Enter element to push: ");
                    int element = sc.nextInt();
                    stack.push(element);
                    System.out.println("Pushed " + element);
                }
                    
                case 2 -> {
                    int popped = stack.pop();
                    if (popped != -1) {
                        System.out.println("Popped: " + popped);
                    }
                }
                    
                case 3 -> {
                    int top = stack.top();
                    if (top != -1) {
                        System.out.println("Top element: " + top);
                    }
                }
                    
                case 4 -> stack.display();
                    
                case 5 -> {
                    System.out.println("Exiting...");
                    sc.close();
                    return;
                }
                    
                default -> System.out.println("Invalid choice!");
            }
        }
    }
}

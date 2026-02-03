import java.util.*;

class CircularBufferQueue {
    private final int[] buffer;
    private final int size;
    private int front;
    private int rear;
    private int count;
    
    public CircularBufferQueue(int size) {
        this.size = size;
        buffer = new int[size];
        front = 0;
        rear = -1;
        count = 0;
    }
    
    public void insert(int element) {
        if (count == size) {
            System.out.println("Buffer full! Overwriting oldest element.");
            front = (front + 1) % size;
            count--;
        }
        
        rear = (rear + 1) % size;
        buffer[rear] = element;
        count++;
        
        System.out.println("Inserted " + element);
    }
    
    public int remove() {
        if (count == 0) {
            System.out.println("Buffer is empty!");
            return -1;
        }
        
        int element = buffer[front];
        front = (front + 1) % size;
        count--;
        
        return element;
    }
    
    public void display() {
        if (count == 0) {
            System.out.println("Buffer is empty!");
            return;
        }
        
        System.out.print("Buffer contents: [");
        for (int i = 0; i < count; i++) {
            int index = (front + i) % size;
            System.out.print(buffer[index]);
            if (i < count - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }
    
    public boolean isEmpty() {
        return count == 0;
    }
    
    public boolean isFull() {
        return count == size;
    }
}

public class CircularBuffer {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Enter buffer size: ");
        int size = sc.nextInt();
        
        CircularBufferQueue buffer = new CircularBufferQueue(size);
        
        while (true) {
            System.out.println("\n=== Circular Buffer Simulation ===");
            System.out.println("1. Insert Element");
            System.out.println("2. Remove Element");
            System.out.println("3. Display Buffer");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            
            int choice = sc.nextInt();
            
            switch (choice) {
                case 1 -> {
                    System.out.print("Enter element to insert: ");
                    int element = sc.nextInt();
                    buffer.insert(element);
                    buffer.display();
                }
                    
                case 2 -> {
                    int removed = buffer.remove();
                    if (removed != -1) {
                        System.out.println("Removed: " + removed);
                    }
                    buffer.display();
                }
                    
                case 3 -> buffer.display();
                    
                case 4 -> {
                    System.out.println("Exiting...");
                    sc.close();
                    return;
                }
                    
                default -> System.out.println("Invalid choice!");
            }
        }
    }
}

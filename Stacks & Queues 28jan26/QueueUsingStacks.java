import java.util.*;

public class QueueUsingStacks {
    private final Deque<Integer> inStack = new ArrayDeque<>();
    private final Deque<Integer> outStack = new ArrayDeque<>();

    public void enqueue(int x) {
        inStack.push(x);
    }

    public int dequeue() {
        moveIfNeeded();
        if (outStack.isEmpty())
            throw new IllegalStateException("Queue is empty");
        return outStack.pop();
    }

    public int peek() {
        moveIfNeeded();
        if (outStack.isEmpty())
            throw new IllegalStateException("Queue is empty");
        return outStack.peek();
    }

    public boolean isEmpty() {
        return inStack.isEmpty() && outStack.isEmpty();
    }

    public int size() {
        return inStack.size() + outStack.size();
    }

    private void moveIfNeeded() {
        if (outStack.isEmpty()) {
            while (!inStack.isEmpty()) {
                outStack.push(inStack.pop());
            }
        }
    }

    public static void main(String[] args) {
        QueueUsingStacks q = new QueueUsingStacks();
        q.enqueue(10);
        q.enqueue(20);
        q.enqueue(30);
        System.out.println("Peek: " + q.peek());
        System.out.println("Dequeue: " + q.dequeue());
        System.out.println("Dequeue: " + q.dequeue());
        q.enqueue(40);
        System.out.println("Dequeue: " + q.dequeue());
        System.out.println("Dequeue: " + q.dequeue());
        System.out.println("Empty: " + q.isEmpty());
    }
}

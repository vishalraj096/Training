import java.util.Stack;

public class SortStackRecursively {
    public static void sort(Stack<Integer> st) {
        if (st.isEmpty())
            return;
        int top = st.pop();
        sort(st);
        insertInSortedOrder(st, top);
    }

    private static void insertInSortedOrder(Stack<Integer> st, int x) {
        if (st.isEmpty() || st.peek() <= x) {
            st.push(x);
            return;
        }
        int tmp = st.pop();
        insertInSortedOrder(st, x);
        st.push(tmp);
    }

    public static void main(String[] args) {
        Stack<Integer> st = new Stack<>();
        st.push(3);
        st.push(1);
        st.push(4);
        st.push(2);
        sort(st);
        while (!st.isEmpty()) {
            System.out.print(st.pop() + " ");
        }
        System.out.println();
    }
}

public class FibonacciComparison {
    static int fibRec(int n) {
        if (n <= 1)
            return n;
        return fibRec(n - 1) + fibRec(n - 2);
    }

    static int fibIter(int n) {
        if (n <= 1)
            return n;
        int a = 0, b = 1;
        for (int i = 2; i <= n; i++) {
            int s = a + b;
            a = b;
            b = s;
        }
        return b;
    }

    public static void main(String[] args) {
        int[] ns = { 10, 30, 40 };
        for (int n : ns) {
            long t1 = System.nanoTime();
            int r = fibRec(n);
            long t2 = System.nanoTime();
            long recMs = (t2 - t1) / 1_000_000;

            long k1 = System.nanoTime();
            int i = fibIter(n);
            long k2 = System.nanoTime();
            long iterMs = (k2 - k1) / 1_000_000;

            System.out.println("N=" + n + " Recursive=" + recMs + "ms Iterative=" + iterMs + "ms");
        }
    }
}

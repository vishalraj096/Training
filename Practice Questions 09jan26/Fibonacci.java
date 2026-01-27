public class Fibonacci {
    public static void main(String[] args) {
        int n = 10;
        int a = 0, b = 1;
        for (int i = 0; i < n; i++) {
            b = a + b;
            a = b - a;
            System.out.println(a);
        }
    }
}

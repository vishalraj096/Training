public class GCDAndLCM {
    public static int gcd(int a, int b) {
        while (b != 0) {
            int tmp = b;
            b = a % b;
            a = tmp;
        }
        return a;
    }

    public static int lcm(int a, int b) {
        return (a * b) / gcd(a, b);
    }

    public static void main(String[] args) {
        int num1 = 12, num2 = 18;
        System.out.println("GCD is " + gcd(num1, num2));
        System.out.println("LCM is " + lcm(num1, num2));
    }
}

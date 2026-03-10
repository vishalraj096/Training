import java.util.*;

public class Q18_SumOfDistances {
    private static double dist(double x1, double y1, double x2, double y2) {
        double dx = x2 - x1;
        double dy = y2 - y1;
        return Math.sqrt(dx * dx + dy * dy);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double x1 = sc.nextDouble();
        double y1 = sc.nextDouble();
        double x2 = sc.nextDouble();
        double y2 = sc.nextDouble();
        double x3 = sc.nextDouble();
        double y3 = sc.nextDouble();
        double sum = dist(x1, y1, x2, y2) + dist(x2, y2, x3, y3) + dist(x1, y1, x3, y3);
        System.out.println(sum);
    }
}

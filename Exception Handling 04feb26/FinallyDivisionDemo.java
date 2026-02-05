import java.util.InputMismatchException;
import java.util.Scanner;

public class FinallyDivisionDemo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.print("Enter first number: ");
            int a = sc.nextInt();
            System.out.print("Enter second number: ");
            int b = sc.nextInt();
            int result = a / b;
            System.out.println("Result: " + result);
        } catch (ArithmeticException e) {
            System.out.println("Division by zero not allowed");
        } catch (InputMismatchException e) {
            System.out.println("Invalid input");
        } finally {
            System.out.println("Operation completed");
            sc.close();
        }
    }
}

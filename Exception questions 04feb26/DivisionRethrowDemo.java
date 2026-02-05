public class DivisionRethrowDemo {

    public static int performDivision(int numerator, int denominator) {
        if (denominator == 0) {
            throw new ArithmeticException("Denominator cannot be zero");
        }
        return numerator / denominator;
    }

    public static int calculate(int numerator, int denominator) {
        try {
            return performDivision(numerator, denominator);
        } catch (ArithmeticException e) {
            throw new ArithmeticException("Error while performing division for " +
                    "numerator=" + numerator + ", denominator=" + denominator +
                    " -> " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        // Example that fails
        try {
            int result = calculate(10, 0);
            System.out.println("Result: " + result);
        } catch (ArithmeticException e) {
            System.err.println("Calculation failed: " + e.getMessage());
        }

        // Example that succeeds
        try {
            int result = calculate(20, 4);
            System.out.println("Result: " + result);
        } catch (ArithmeticException e) {
            System.err.println("Calculation failed: " + e.getMessage());
        }
    }
}

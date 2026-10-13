package exception_handling;

public class oct13_exception {
    public static void main(String[] args) {
        try {
            int a = 10;
            int b = 0;
            int result = a / b; // ArithmeticException throw(divide by zero)
            System.out.println("Result: " + result);
        } catch (ArithmeticException e) {
            System.out.println("Error:" + e.getMessage());
        } finally {
            System.out.println("Finally block:");
        }

        System.out.println("Program runs successfully");
    }
}

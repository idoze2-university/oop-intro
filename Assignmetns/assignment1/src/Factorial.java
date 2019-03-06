
 
class Factorial {
    /**
     * @param args holds command line arguments
     */
    public static void main(String[] args) {
        int n = 0;
        if (args.length > 0) {
            n = Integer.parseInt(args[0]);
        }
        if (n < 0) {
            System.out.println("Invalid Value " + n);
        } else {
            System.out.println("recursive: " + String.valueOf(factorialRecursive(n)));
            System.out.println("iterative: " + String.valueOf(factorialIter(n)));
        }
    }

    /**
     * <p>
     * Recursively Calculates the expression: n!, By calculating the factorial of
     * n-1 and multiplying it times n. Recursion breaks at n=0 (returns 1).
     * </p>
     *
     * @param n the number to calculate the factorial of.
     * @return the factorial of (n-1) times n.
     */
    private static long factorialRecursive(long n) {
        if (n == 0) {
            return 1;
        }
        return factorialRecursive(n - 1) * n;
    }

    /**
     * <p>
     * Calculates the expression: n!, by iterating i from 1 to n, repetadely
     * multiplying each value of i times the others.
     * </p>
     *
     * @param n the number to calculate the factorial of.
     * @return the factorial of n.
     */
    private static long factorialIter(long n) {
        long fact = 1;
        for (int i = 1; i <= n; i++) {
            fact *= i;
        }
        return fact;
    }
}
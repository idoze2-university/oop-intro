/**
 * The Sort class gets a list of numbers in the commandline, and prints them
 * sorted.
 *
 * @author Ido Zeira <ido.ze2@gmail.com>
 */
class Sort {
    /**
     * @param args command line arguments.
     */
    public static void main(String[] args) {
        int[] integers;
        if (args.length > 1) {
            integers = stringsToInts(args);
            if (args[0].equals("asc")) {
                printSortedASC(integers);
            } else {
                printSortedDESC(integers);
            }
            System.out.println("");
        }
    }

    /**
     * Converts an array of strings representing numbers to an array of integers
     * representing the numeric values of the strings.
     *
     * @param numbers Array of strings consisting of numeric values.
     * @return Array of elements representing the numeric values of the strings in
     *         'numbers', starting from index 1.
     */
    public static int[] stringsToInts(String[] numbers) {
        int[] integers = new int[numbers.length - 1];
        for (int i = 1; i <= integers.length; i++) {
            integers[i - 1] = Integer.parseInt(numbers[i]);
        }
        return integers;
    }

    /**
     * Prints an array of Integers, sorted in an ascending order.
     *
     * @param numbers Array of integers to sort.
     */
    public static void printSortedASC(int[] numbers) {
        int temp = 0;
        for (int i = 0; i < numbers.length; i++) {
            for (int k = 1; k < numbers.length; k++) {
                if (numbers[k] < numbers[k - 1]) {
                    temp = numbers[k - 1];
                    numbers[k - 1] = numbers[k];
                    numbers[k] = temp;
                }
            }
        }
        for (int i = 0; i < numbers.length; i++) {
            System.out.print(String.valueOf(numbers[i]) + " ");
        }
    }

    /**
     * Prints an array of Integers, sorted in an descending order.
     *
     * @param numbers Array of integers to sort.
     */
    public static void printSortedDESC(int[] numbers) {
        int temp = 0;
        for (int i = 0; i < numbers.length; i++) {
            for (int k = 1; k < numbers.length; k++) {
                if (numbers[k] > numbers[k - 1]) {
                    temp = numbers[k - 1];
                    numbers[k - 1] = numbers[k];
                    numbers[k] = temp;
                }
            }
        }
        for (int i = 0; i < numbers.length; i++) {
            System.out.print(String.valueOf(numbers[i]) + " ");
        }
    }
}
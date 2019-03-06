/**
 * The DescribeNumbers class gets a list of numbers in the commandline, and
 * prints their minimum, maximum, and average values.
 *
 * @author Ido Zeira <ido.ze2@gmail.com>
 */
class DescribeNumbers {

    /**
     * @param args command line arguments.
     */
    public static void main(String[] args) {
        int[] integers;
        if (args.length > 0) {
            integers = stringsToInts(args);
            System.out.println("min: " + String.valueOf(min(integers)));
            System.out.println("max: " + String.valueOf(max(integers)));
            System.out.println("avg: " + String.valueOf(avg(integers)));
        }
    }

    /**
     * Converts an array of strings representing numbers to an array of integers
     * representing the numeric values of the strings.
     *
     * @param numbers Array of strings consisting of numeric values.
     * @return Array of elements representing the numeric values of the strings in
     *         'numbers'
     */
    public static int[] stringsToInts(String[] numbers) {
        int[] integers = new int[numbers.length];
        for (int i = 0; i < integers.length; i++) {
            integers[i] = Integer.parseInt(numbers[i]);
        }
        return integers;
    }

    /**
     * Finds the lowest value of the numbers found in the array.
     *
     * @param numbers Array of integers.
     * @return The lowest value in the array.
     */
    public static int min(int[] numbers) {
        int min = numbers[0];
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] < min) {
                min = numbers[i];
            }
        }
        return min;
    }

    /**
     * Finds the highest value of the numbers found in the array.
     *
     * @param numbers Array of integers.
     * @return The highest value in the array.
     */
    public static int max(int[] numbers) {
        int max = numbers[0];
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] > max) {
                max = numbers[i];
            }
        }
        return max;
    }

    /**
     * Finds the average value of the numbers found in the array.
     *
     * @param numbers Array of integers.
     * @return The agerage value of the array.
     */
    public static float avg(int[] numbers) {
        float sum = 0;
        for (int i = 0; i < numbers.length; i++) {
            sum += numbers[i];
        }
        return sum / numbers.length;
    }
}
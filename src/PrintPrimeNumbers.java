/**
 * Prints all prime numbers up to 1000.
 */
public class PrintPrimeNumbers {
    public static void main(String[] args) {
        findPrimeNumber(1000);
    }

    private static void findPrimeNumber(int input) {
        //2 doesn't fit in the loop, so I have to print it out separately
        if (input >= 2 ){
            System.out.println(2);
        }

        for (int i = 3; i <= input; i = i + 2) {
            boolean isPrime = true;
            for (int j = 3; j < Math.sqrt(i); j++) {
                if (i % j == 0) {
                    isPrime = false;
                    break;
                }
            }
            if (isPrime) {
                System.out.println(i);
            }
        }
    }

}

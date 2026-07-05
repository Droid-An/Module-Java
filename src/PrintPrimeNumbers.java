/**
 * This class prints all prime numbers up to 1000.
 */
public class  PrintPrimeNumbers{
    public static void main(String[] args) {
        findPrimeNumber(1000);
    }

    private static void findPrimeNumber(int input) {

        for (int i = 3; i <= input; i=i+2) {
            boolean isPrime = true;
            for (int j = 3; j < i/2; j++) {
                if (i % j == 0) {
                    isPrime = false;
                }
            }
            if (isPrime) {
                System.out.println(i);
            }
        }
    }

}

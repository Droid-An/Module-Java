public class Main {
    public static void main(String[] args) {
        findPrimeNumber(1000);
    }

    private static void findPrimeNumber(int input) {

        for (int i = 2; i <= input; i++) {
            boolean isPrime = true;
            for (int j = 2; j < i; j++) {
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

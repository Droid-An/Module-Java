public class ReverseNumbers {
    public static void main(String[] args) {
        int Number =11111009;
        int reversedNumber = reverseNumbers(Math.abs(Number));
        if (Number < 0) {
            System.out.println("-" + reversedNumber);
        } else {
            System.out.println(reversedNumber);
        }
    }

    /**
     * Accepts a positive number as an argument and returns the number in reverse
     *
     * @param input number to reverse
     * @return reversed number
     */
    private static Integer reverseNumbers(int input) {
        StringBuilder reverse = new StringBuilder();
        String stringifiedInput = Integer.toString(input);
        for (int i = stringifiedInput.length() - 1; i >= 0; i--) {
            reverse.append(stringifiedInput.charAt(i));
        }

        return Integer.parseInt(reverse.toString());
    }

}
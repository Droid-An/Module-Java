public class Main {
    public static void main(String[] args) {
        int reversedNumber = reverseNumbers(12367);
        System.out.println(reversedNumber);
    }

    private static Integer reverseNumbers(int input) {
        String reverse = "";
        String stringifiedInput = Integer.toString(input);
        for (int i = stringifiedInput.length()-1; i>=0; i--){
           reverse += stringifiedInput.charAt(i);
        }

        return Integer.parseInt(reverse);
    }

}

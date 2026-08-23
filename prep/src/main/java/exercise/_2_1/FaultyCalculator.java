package exercise._2_1;

import java.util.Arrays;
import java.util.List;

public class FaultyCalculator {

    public static void main(String[] args) {
        List<Integer> values = Arrays.asList(10, 20, 30, 40);

        double average = calculateAverage(values);
        System.out.println("Average = " + average);  // Expected: 25
    }

    public static double calculateAverage(List<Integer> numbers) {
        int sum = 0;
        for (int i = 0; i < numbers.size(); i++) {
            sum += numbers.get(i);
        }

        return (double) sum / numbers.size();
    }
}
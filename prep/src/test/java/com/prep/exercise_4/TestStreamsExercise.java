package com.prep.exercise_4;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.String.format;
import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * =======================
 * Streams
 * =======================
 * <p>
 * Time to venture into the world of functional programming and try using some Streams.
 * <p>
 * Let's go... Have a go at solving the following exercises first using loops, and then convert them to use streams. As you work through, think about how the two approaches compare in terms of readability, how easy the code would be to adapt in future (as we know in enterprise software, code never stays the same forever as new requirements and bugs are worked on!), and compare the trade-offs in efficiency.
 */
public class TestStreamsExercise extends ExerciseTest {

    /**
     * For this first exercise, we will implement the {@link NameFinder#printNamesStartingWithA(Collection)}
     * method. It should take a collection of names and *use a stream* to find the names that start
     * with the letter 'A' and print them. It should not be case-sensitive, so both "Anne" and "andy"
     * would be printed.
     * <p>
     * This test will check your method's output.
     */
    @Test
    public void testPrintNamesStartingWithA() {
        List<String> names = Lists.newArrayList("Antony", "John", "amy", "Ben", "Dan", "Ian", "Andrew", "Peter", "Oliver");
        new NameFinder().printNamesStartingWithA(names);
        checkPrintedOutput(format("Antony%namy%nAndrew%n"));
    }


    /**
     * Now let's try something a bit more complicated. We need to implement the method
     * {@link NameFinder#vowelNamesCapitalisedDescending(Collection)} so that it will take
     * our list of names, find the ones that start with a vowel, capitalise them (in case the first
     * letter isn't already capitalised) and then return them in a list in reverse-alphabetical
     * order.
     * <p>
     * Got all that? Give it a go and the test below will check your result.
     */
    @Test
    public void testVowelNamesCapitalisedDescending() {
        List<String> names = Lists.newArrayList("Antony", "John", "amy", "Ben", "Dan", "Ian", "andrew", "Peter", "Oliver");
        List<String> expectedResult = Lists.newArrayList("Oliver", "Ian", "Antony", "Andrew", "Amy");

        //For some reason compiler can't resolve test name, so I comment it out

//        "Names should be as expected",
        assertEquals(expectedResult, new NameFinder().vowelNamesCapitalisedDescending(names));
    }


    /**
     * Here's a problem involving a Map. Let's see if we can solve it using a stream.
     * <p>
     * The method {@link ShapeCounter#countShapesWithSides(Map, int)} should take a map of shapes to
     * their number of sides and a number of sides and will tell us how many shapes in the map have
     * that many sides.
     * <p>
     * Use the test below to check your method is working.
     */
    @Test
    public void testStreamingMapEntries() {
        Map<String, Integer> shapesToSides = new HashMap<>();
        shapesToSides.put("Right-Angled Triangle", 3);
        shapesToSides.put("Isosceles Triangle", 3);
        shapesToSides.put("Square", 4);
        shapesToSides.put("Rhombus", 4);
        shapesToSides.put("Parallelogram", 4);
        shapesToSides.put("Hexagon", 6);
        shapesToSides.put("Octagon", 8);

        ShapeCounter shapeCounter = new ShapeCounter();
//        "Should be two 3-sided shapes"
        assertEquals(2, shapeCounter.countShapesWithSides(shapesToSides, 3));
//        "Should be three 4-sided shapes"
        assertEquals(3, shapeCounter.countShapesWithSides(shapesToSides, 4));
    }


    /**
     * Maths time again. For this test we will need to implement the method
     * {@link Remainders#printRemainders(int)} to print the remainders of the numbers
     * 1-20 when divided by a given divisor. The remainders should all be on one line
     * and should be separated by commas. There should be a line break at the end.
     * <p>
     * For example, for a divisor of 2 it would go:
     * <pre>
     *   1,0,1,0 ... and so on until we have 20 numbers
     * </pre>
     */
    @Test
    public void testPrintingRemainders() {
        Remainders remainders = new Remainders();
        remainders.printRemainders(2);
        remainders.printRemainders(5);
        checkPrintedOutput(format("1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0%n1,2,3,4,0,1,2,3,4,0,1,2,3,4,0,1,2,3,4,0%n"));
    }

    /**
     * Time to use one of the more 'interesting' terminal operations. In this exercise,
     * you'll need to use {@code .groupingBy()}.
     * <p>
     * To pass this test, implement the {@link Remainders#remainderGroups(int)} method
     * to return a {@code Map<Integer, List<Integer>>} with an entry for each different
     * remainder. The key should be the remainder itself and the value should be a list
     * of all the numbers between 1 and 20 that have that remainder.
     */
    @Test
    public void testGroupingRemainders() {
        Remainders remainders = new Remainders();

        Map<Integer, List<Integer>> expectedRemaindersFor5 = new HashMap<>();
        expectedRemaindersFor5.put(0, List.of(5, 10, 15, 20));
        expectedRemaindersFor5.put(1, List.of(1, 6, 11, 16));
        expectedRemaindersFor5.put(2, List.of(2, 7, 12, 17));
        expectedRemaindersFor5.put(3, List.of(3, 8, 13, 18));
        expectedRemaindersFor5.put(4, List.of(4, 9, 14, 19));
//        "Remainder groups for 5 should be correct"
        assertEquals(expectedRemaindersFor5, remainders.remainderGroups(5));

        Map<Integer, List<Integer>> expectedRemaindersFor8 = new HashMap<>();
        expectedRemaindersFor8.put(0, List.of(8, 16));
        expectedRemaindersFor8.put(1, List.of(1, 9, 17));
        expectedRemaindersFor8.put(2, List.of(2, 10, 18));
        expectedRemaindersFor8.put(3, List.of(3, 11, 19));
        expectedRemaindersFor8.put(4, List.of(4, 12, 20));
        expectedRemaindersFor8.put(5, List.of(5, 13));
        expectedRemaindersFor8.put(6, List.of(6, 14));
        expectedRemaindersFor8.put(7, List.of(7, 15));
        //"Remainder groups for 8 should be correct"
        assertEquals(expectedRemaindersFor8, remainders.remainderGroups(8));
    }

    /**
     * What if we wanted to know 'how many times does each remainder appear?'. We'd need to get a map
     * with an entry for each remainder where the value tells us the count as a Long.
     * <p>
     * That's exactly what the method {@link Remainders#remainderCounts(int)} should do. Let's
     * implement it to give us the count for each remainder for a given divisor.
     */
    @Test
    public void testCountingRemainders() {
        Remainders remainders = new Remainders();

        Map<Integer, Long> expectedRemaindersFor5 = new HashMap<>();
        expectedRemaindersFor5.put(0, 4L);
        expectedRemaindersFor5.put(1, 4L);
        expectedRemaindersFor5.put(2, 4L);
        expectedRemaindersFor5.put(3, 4L);
        expectedRemaindersFor5.put(4, 4L);
//        "Remainder counts for 5 should be correct"
        assertEquals(expectedRemaindersFor5, remainders.remainderCounts(5));

        Map<Integer, Long> expectedRemaindersFor8 = new HashMap<>();
        expectedRemaindersFor8.put(0, 2L);
        expectedRemaindersFor8.put(1, 3L);
        expectedRemaindersFor8.put(2, 3L);
        expectedRemaindersFor8.put(3, 3L);
        expectedRemaindersFor8.put(4, 3L);
        expectedRemaindersFor8.put(5, 2L);
        expectedRemaindersFor8.put(6, 2L);
        expectedRemaindersFor8.put(7, 2L);
//        "Remainder counts for 8 should be correct"
        assertEquals(expectedRemaindersFor8, remainders.remainderCounts(8));
    }
}



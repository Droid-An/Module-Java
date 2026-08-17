package com.prep.exercise_4;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Remainders {
    public void printRemainders(int divisor) {

        //loops
//        List<Integer> reminders = new ArrayList<>();
//        for (int i = 1; i < 21; i++) {
//            reminders.add(i % divisor);
//        }
//
//        List<String> remindersString = new ArrayList<>();
//        for (Integer i : reminders) {
//            remindersString.add(i.toString());
//        }
//
//        System.out.println(String.join(",", remindersString));


        //streams
        //It looks quite crazy, especially in the map part
        String remindersString = Stream.iterate(1, n -> n + 1)
                .limit(20)
                .map(item -> String.valueOf(item % divisor))
                .collect(Collectors.joining(","));
        System.out.println(remindersString);

    }

    public Map<Integer, List<Integer>> remainderGroups(int divisor) {

        // loops
//        Map<Integer, List<Integer>> groups = new HashMap<>();

//        for (int i = 1; i < 21; i++) {
//            int remainder = i % divisor;
//            groups.computeIfAbsent(remainder, k-> new ArrayList<>()).add(i);
//        }
//        return groups;

        // streams

        return Stream.iterate(1, n -> n + 1)
                .limit(20)
                .collect(Collectors.groupingBy(item -> item % divisor));

    }

    public Map<Integer, Long> remainderCounts(int divisor) {

//         loops
//        Map<Integer, Long> groups = new HashMap<>();
//
//        for (int i = 1; i < 21; i++) {
//            int remainder = i % divisor;
//
//            groups.merge(remainder, 1L, Long::sum);
//        }
//        return groups;

        // streams

        return Stream.iterate(1, n -> n + 1)
                .limit(20)
                .collect(Collectors.groupingBy(item -> (item % divisor), Collectors.counting()));
    }
}

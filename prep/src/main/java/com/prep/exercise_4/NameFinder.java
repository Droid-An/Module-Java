package com.prep.exercise_4;


import com.google.common.collect.Lists;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class NameFinder {
    static public List<String> vowelNamesCapitalisedDescending(Collection<String> names) {
        List<String> vowels = Lists.newArrayList("a", "e", "i", "o", "u", "A", "E", "I", "O", "U");

        //loops approach

//        List<String> filteredNames = new ArrayList<>();
//        for (String name:names){
//            if(vowels.contains(name.substring(0, 1))){
//                filteredNames.add(name.substring(0, 1).toUpperCase() + name.substring(1));
//            }
//        }
//        Collections.sort(filteredNames,Collections.reverseOrder());
//        return filteredNames;

        //streams approach

        return names.stream()
                .filter(name -> vowels.contains(name.substring(0, 1)))
                .map(name -> name.substring(0, 1)
                        .toUpperCase() + name.substring(1))
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
    }

    public void printNamesStartingWithA(Collection<String> names) {
        //loops approach

//            for (String name : namesList) {
//                String firstLetter = String.valueOf(name.charAt(0));
//                if (firstLetter.equalsIgnoreCase("a")) {
//                    System.out.println(name);
//                }
//            }

        //streams approach

        Stream<String> streamOfNames = names.stream()
                .filter(name -> name.startsWith("a") | name.startsWith("A"));
        streamOfNames.forEach(System.out::println);
    }
}


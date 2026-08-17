package com.prep.exercise_4;

import java.util.Map;

public class ShapeCounter {
    public Integer countShapesWithSides(Map<String, Integer> shapesToSides, int i) {

//        loops approach

//        Collection<Integer> sides = shapesToSides.values();
//        Integer counter = 0;
//        for (Integer side : sides) {
//            if (side.equals(i)) {
//                counter += 1;
//            }
//
//        }
//        return counter;

        //streams one-liner

        return Math.toIntExact(shapesToSides.values()
                .stream()
                .filter(sides -> sides.equals(i))
                .count());
    }
}

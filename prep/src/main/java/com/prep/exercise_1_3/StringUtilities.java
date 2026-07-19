package com.prep.exercise_1_3;

import java.util.List;

public class StringUtilities {

    public boolean isPalindrome(String s) {
        String regexp = "\\W";
        String cleaned = s.toLowerCase().replaceAll(regexp, "");
        if (cleaned.isEmpty()) {
            return false;
        }
        StringBuilder stringBuilder = new StringBuilder(cleaned);
        String reversed = stringBuilder.reverse().toString();
        return reversed.equals(cleaned);

    }

    public List<String> splitCommaSeparatedLine(String s) {
        if (s == null || s.isBlank()) {
            return List.of();
        }
        return List.of(s.split(","));
    }

}
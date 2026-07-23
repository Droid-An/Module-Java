package com.prep.exercise_1_3;

import java.util.List;

/**
 * Utility class
 * <p>
 * Implements isPalindrome and splitCommaSeparatedLine methods
 *
 * @since 1.0
 */
public class StringUtilities {

    /**
     *
     * @param s String to check
     * @return boolean
     * @apiNote empty "" is treated as false
     */
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

    /**
     * Split string by commas and return List object
     *
     * @param s string to separate
     * @return List of separated strings
     * @see List
     */
    public List<String> splitCommaSeparatedLine(String s) {
        if (s == null || s.isBlank()) {
            return List.of();
        }
        return List.of(s.split(","));
    }

}
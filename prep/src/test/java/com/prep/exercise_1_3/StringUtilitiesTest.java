package com.prep.exercise_1_3;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for {@link StringUtilities}.
 *
 * @see StringUtilities
 */
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class StringUtilitiesTest {

    /**
     * Instance under test, shared across all test methods.
     */
    StringUtilities stringUtilities = new StringUtilities();

    /**
     * Nested test class covering {@link StringUtilities#isPalindrome(String)}.
     */
    @Nested
    class is_a_palindrome_test {

        /**
         * checks if several different palindromes ar "true"
         * @param arg Value Source of palindromes to check
         */
        @ParameterizedTest(name = "{0} is a palindrome")
        @ValueSource(strings = {"radar", "Sit on a potato pan, Otis.", "Taco cat"})
        void string_is_palindrome(String arg) {
            assertTrue(stringUtilities.isPalindrome(arg));
        }

        /**
         * checks if non-palindrome word and empty string are not palindromes
         * @param arg Value Source of non-palindromes to check
         */
        @ParameterizedTest(name = "{0} isn't a palindrome")
        @ValueSource(strings = {"Cat", ""})
        void string_is_not_palindrome(String arg) {
            assertFalse(stringUtilities.isPalindrome(arg));
        }

    }

    /**
     * Nested test class covering
     * {@link StringUtilities#splitCommaSeparatedLine(String)}.
     */
    @Nested
    class splitCommaSeparatedLine {

        /**
         * Verifies that a line with three comma-separated values is
         * split into a list containing exactly those three items.
         */
        @Test
        void three_items_separated_by_comma() {
            assertEquals(Arrays.asList("a", "b", "c"), stringUtilities.splitCommaSeparatedLine("a,b,c"));
        }

        /**
         * Verifies that a line with no commas is split into a
         * single-element list containing the whole input.
         */
        @Test
        void one_item() {
            assertEquals(Arrays.asList("a"), stringUtilities.splitCommaSeparatedLine("a"));
        }

        /**
         * Verifies that a line containing only whitespace produces
         * an empty list.
         */
        @Test
        void space_input() {
            assertEquals(List.of(), stringUtilities.splitCommaSeparatedLine(" "));
        }

        /**
         * Verifies that an empty input string produces an empty list.
         */
        @Test
        void empty_input() {
            assertEquals(List.of(), stringUtilities.splitCommaSeparatedLine(""));
        }
    }
}

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

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class StringUtilitiesTest {
    StringUtilities stringUtilities = new StringUtilities();

    @Nested
    class is_a_palindrome_test {

        @ParameterizedTest(name = "{0} is a palindrome")
        @ValueSource(strings = {"radar", "Sit on a potato pan, Otis.", "Taco cat"})
        void string_is_palindrome(String arg) {
            assertTrue(stringUtilities.isPalindrome(arg));
        }

        @ParameterizedTest(name = "{0} isn't a palindrome")
        @ValueSource(strings = {"Cat", ""})
        void string_is_not_palindrome(String arg) {
            assertFalse(stringUtilities.isPalindrome(arg));
        }

    }

    @Nested
    class splitCommaSeparatedLine {

        @Test
        void three_items_separated_by_comma() {
            assertEquals(Arrays.asList("a", "b", "c"), stringUtilities.splitCommaSeparatedLine("a,b,c"));
        }

        @Test
        void one_item() {
            assertEquals(Arrays.asList("a"), stringUtilities.splitCommaSeparatedLine("a"));
        }

        @Test
        void space_input() {
            assertEquals(List.of(), stringUtilities.splitCommaSeparatedLine(" "));
        }

        @Test
        void empty_input() {
            assertEquals(List.of(), stringUtilities.splitCommaSeparatedLine(""));
        }
    }


}

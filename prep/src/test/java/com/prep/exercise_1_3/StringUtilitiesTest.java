package com.prep.exercise_1_3;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class StringUtilitiesTest {
    StringUtilities stringUtilities = new StringUtilities();

    @Nested
    class is_a_palindrome_test {
        @Test
        void one_word() {
            assertTrue(stringUtilities.isPalindrome("radar"));
        }

        @Test
        void sentence_with_punctuation() {
            assertTrue(stringUtilities.isPalindrome("Sit on a potato pan, Otis."));
        }

        @Test
        void sentence_without_punctuation() {
            assertTrue(stringUtilities.isPalindrome("Taco cat"));
        }

        @Test
        void no_palindrome() {
            assertFalse(stringUtilities.isPalindrome("Cat"));
        }

        @Test
        void no_input() {
            assertFalse(stringUtilities.isPalindrome(""));
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

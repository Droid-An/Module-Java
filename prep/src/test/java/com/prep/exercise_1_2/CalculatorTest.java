package com.prep.exercise_1_2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static java.lang.Double.NaN;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CalculatorTest {
    private Calculator calculator = new Calculator();

    // what's the difference between
    // declaring one calculator object to share between all tests
    // and
    // creating a new one every test?
    @BeforeEach
    void setUp() {

    }

    @Test
    void testAdd() {
        assertEquals(4, calculator.add(2, 2));
    }

    @Test
    void testDivide() {
        assertEquals(4, calculator.subtract(6, 2));
    }

    @Test
    void testDivideByZeroThrows() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> calculator.divide(6, 0));
        assertEquals("Division by zero", exception.getMessage());
    }

    @Test
    void testSquare() {
        assertEquals(9, calculator.square(3));
    }

    @Test
    void testSquareRoot() {
        assertEquals(5.477225575051661, calculator.squareRoot(30));
    }

    @Test
    void testSquareRootNegative() {
        assertEquals(NaN, calculator.squareRoot(-30));
    }
}
package com.prep;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CalculatorTest {
    Calculator calculator = new Calculator();

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
}
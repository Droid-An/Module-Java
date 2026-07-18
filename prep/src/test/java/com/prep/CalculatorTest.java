package com.prep;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class CalculatorTest {
    Calculator calculator = new Calculator();

    @BeforeEach
    void setUp() {

    }

    @Test
    void testAdd(

    ) {
        assertEquals(4, calculator.add(2,2));
    }
// what's the difference between "Assertions.assertEquals" and just "assertEquals"?
    @Test
    void testDivide() {
        Assertions.assertEquals(4, calculator.subtract(6,2));
    }

    @Test
    void testDivideByZeroThrows(

    ) {
        Exception exception = assertThrows(IllegalArgumentException.class, ()-> calculator.divide(6,0));
        assertEquals("Division by zero", exception.getMessage());
    }
}
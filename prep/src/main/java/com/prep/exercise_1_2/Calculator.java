package com.prep.exercise_1_2;

/**
 * Calculator that can handle basic arithmetic operations
 *
 * @see Integer
 * @see Math
 * @since 1.0
 */
public class Calculator {
    /**
     * Add two values
     *
     * @param a integer
     * @param b integer
     * @return sum of a and b
     */
    public int add(int a, int b) {
        return a + b;
    }

    /**
     * Subtracts second integer from a first passed
     * @param a integer
     * @param b integer
     * @return differnce between a and b
     */
    public int subtract(int a, int b) {
        return a - b;
    }

    /**
     * Multiply a b times
     * @param a integer to multiply
     * @param b how many times to multiply
     * @return product of a and b
     */
    public int multiply(int a, int b) {
        return a * b;
    }

    /**
     * devide first number by second number
     * @param a Dividend
     * @param b Divisor
     * @return Quotient
     * @throws IllegalArgumentException if divisor is 0
     */
    public int divide(int a, int b) {
        if (b == 0) {
            throw new IllegalArgumentException("Division by zero");
        }
        return a / b;
    }

    /**
     * calculates square root of a
     * @param a value to find the root of
     * @return root of a
     */
    public double squareRoot(int a) {
        return Math.sqrt(a);
    }

    /**
     * bring the integer passed to the power of 2
     * @param a
     * @return integer in a power of 2
     */
    public int square(int a) {
        return a * a;
    }

}
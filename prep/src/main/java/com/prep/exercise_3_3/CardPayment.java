package com.prep.exercise_3_3;

/**
 * Card payment containing CardDetails
 *
 * @param amount      Payment amount
 * @param cardDetails Payment destination
 */
public record CardPayment(Integer amount, Integer cardDetails) implements Payment {

    @Override
    public String getType() {
        return "Card";
    }
}

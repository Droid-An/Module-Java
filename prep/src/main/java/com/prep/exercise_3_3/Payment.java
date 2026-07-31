package com.prep.exercise_3_3;

/**
 * Object containing payment data
 */
public interface Payment {
    /**
     * get Payment amount
     *
     * @return Integer amount
     */
    Integer amount();

    /**
     * Get Payment type. Can be "Cash" or "Card"
     *
     * @return String type
     */
    String getType();
}

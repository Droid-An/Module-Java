package com.prep.exercise_3_3;

public interface PaymentProcessor {
    /**
     * process payment
     *
     * @param payment Payment object
     * @see Payment
     */
    void process(Payment payment);
}

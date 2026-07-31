package com.prep.exercise_3_3;

public class CardProcessor implements PaymentProcessor {
    public void process(Payment payment) {

        System.out.println("Card payment accepted: " + payment.amount());
    }
}

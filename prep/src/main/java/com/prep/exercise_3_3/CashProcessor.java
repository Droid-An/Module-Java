package com.prep.exercise_3_3;

public class CashProcessor implements PaymentProcessor {
    public void process(Payment payment) {

        System.out.println("Cash payment accepted: " + payment.amount());
    }
}

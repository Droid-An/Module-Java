package com.prep._3_1;

public interface PaymentProcessor {
    boolean processPayment(double amount);

    boolean refund(String transactionId);

    String getProviderName();
}

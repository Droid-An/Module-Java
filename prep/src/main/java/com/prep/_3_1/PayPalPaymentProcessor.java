package com.prep._3_1;

public class PayPalPaymentProcessor implements PaymentProcessor {
    @Override
    public boolean processPayment(double amount) {
        System.out.println("Processing £" + amount + " via PayPal…");
        return true;
    }

    @Override
    public boolean refund(String transactionId) {
        return false;
    }

    @Override
    public String getProviderName() {
        return "";
    }
}

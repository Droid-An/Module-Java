package com.prep._3_1;

public class StripePaymentProcessor implements PaymentProcessor {
    @Override
    public boolean processPayment(double amount) {
        System.out.println("Processing £" + amount + " via Stripe…");
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


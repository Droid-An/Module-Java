package com.prep._3_1;

import java.util.ArrayList;
import java.util.List;

public class DummyPaymentProcessor implements PaymentProcessor {
    static final List<Double> listOfAmountProcessed = new ArrayList<>();
    static final List<String> refundIds = List.of();

    public List<Double> getProcessedAmounts() {
        return listOfAmountProcessed;
    }

    @Override
    public boolean processPayment(double amount) {
        listOfAmountProcessed.add(amount);
        return true;
    }

    @Override
    public boolean refund(String transactionId) {
        refundIds.add(transactionId);
        return true;
    }

    @Override
    public String getProviderName() {
        return "";
    }
}
package com.prep._3_1;

import java.util.List;

public class CheckoutService {
    private final PaymentProcessor paymentProcessor;

    public CheckoutService(PaymentProcessor paymentProcessor) {
        this.paymentProcessor = paymentProcessor;
    }

    public static void main(String[] args) {

        List<Double> cart = List.of(55.5, 33.0);

        PaymentProcessor stripe = new StripePaymentProcessor();
        CheckoutService checkoutService = new CheckoutService(stripe);
        checkoutService.processCheckout(cart);

        PaymentProcessor paypal = new PayPalPaymentProcessor();
        CheckoutService checkoutService2 = new CheckoutService(paypal);
        checkoutService2.processCheckout(cart);
    }

    public boolean processCheckout(List<Double> amounts) {
        double total = amounts.stream().reduce(0.0, Double::sum);
        return paymentProcessor.processPayment(total);
    }
}

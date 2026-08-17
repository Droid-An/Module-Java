package com.prep.exercise_3_3;

/**
 * Universal Payment Handler
 */
public class PaymentHandler {

    CardProcessor cardProcessor = new CardProcessor();
    CashProcessor cashProcessor = new CashProcessor();

    /**
     * Demo method
     * <p>Creates card and cash Payments and processes them using Payment Handler</p>
     */
    public static void main(String[] args) {
        Payment cashPayment = new CashPayment(18);
        Payment cardPayment = new CardPayment(20, 123456789);
        PaymentHandler paymentHandler = new PaymentHandler();
        paymentHandler.processPayment(cashPayment);
        paymentHandler.processPayment(cardPayment);
    }

    /**
     * Process payment and router. Calls matching payment processor depending on Payment type.
     *
     * @param payment Payment object
     * @see CardProcessor
     * @see CashProcessor
     * @see <a href="https://curriculum.codeyourfuture.io/tracks/java/sprints/4/prep/#exceptions">Original pseudocode from 3.3</a>
     */
    void processPayment(Payment payment) {
        if (payment.getType()
                .equals("Card"))
            cardProcessor.process(payment);
        else if (payment.getType()
                .equals("Cash")) {
            cashProcessor.process(payment);
        }
    }
}

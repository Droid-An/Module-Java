package exercise3_3;

import exercise3_2.DirectDebit;
import exercise3_2.PaymentMethod;

/**
 * Pay using different payment methods
 */
public class Checkout {
    PaymentMethod paymentMethod;

    /**
     * Process payment using selected payment method
     * @param amount
     */
    public void processPayment(double amount) {
        paymentMethod.pay(amount);
    }

    /**
     * Switch payment method
     * @param paymentMethod
     */
    public void updatePaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public static void main(String[] args) {
        PaymentMethod paymentMethod = new PaymentMethod();
        DirectDebit directDebit = new DirectDebit();
        Checkout checkout = new Checkout();

        checkout.updatePaymentMethod(paymentMethod);
        checkout.processPayment(20);

        checkout.updatePaymentMethod(directDebit);
        checkout.processPayment(30);
    }
}

package com.prep._3_1;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class CheckoutServiceTest {
    @Test
    public void processCheckout_chargesEachCartItem() {
        DummyPaymentProcessor dummyProcessor = new DummyPaymentProcessor();
        CheckoutService dummyService = new CheckoutService(dummyProcessor);
        List<Double> cart = List.of(55.5, 33.0);
        dummyService.processCheckout(cart);
        assertEquals(List.of(88.5), dummyProcessor.getProcessedAmounts());
    }

}
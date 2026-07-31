package com.prep.exercise_3_3;

public record CashPayment(Integer amount) implements Payment {

    @Override
    public String getType() {
        return "Cash";
    }


}

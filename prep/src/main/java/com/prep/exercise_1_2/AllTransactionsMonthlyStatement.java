package com.prep.exercise_1_2;

/**
 * Includes all transactions, both ingoing and outgoing for the current month
 */
public class AllTransactionsMonthlyStatement implements Statement {

    /**
     * Return all transactions for the current month
     *
     * @return transactions of type String
     */
    @Override
    public String getStatement() {
        return """
                - 10 bus
                + 200 salary
                - 50 mortgage
                """;
    }
}


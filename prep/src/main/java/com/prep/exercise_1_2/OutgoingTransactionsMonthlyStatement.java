package com.prep.exercise_1_2;

/**
 * Only includes the outgoing transactions for the current month
 */
public class OutgoingTransactionsMonthlyStatement implements Statement {

    /**
     * Return outgoing transactions for the current month
     *
     * @return Transactions String typed
     */
    @Override
    public String getStatement() {
        return """
                - 10 bus
                - 50 mortgage
                """;
    }

}

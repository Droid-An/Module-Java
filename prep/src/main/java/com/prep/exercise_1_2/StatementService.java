package com.prep.exercise_1_2;

import com.prep.exercise_1_1.StatementDeliveryService;
import com.prep.exercise_1_1.StatementSender;

/**
 * Prepares different statements
 */
public class StatementService {

    /**
     * Return statement with all transactions for the current month
     *
     * @return statement
     */
    public AllTransactionsMonthlyStatement createAllTransactionsStatement() {
        return new AllTransactionsMonthlyStatement();
    }

    /**
     * Return statement with all outgoing transactions for the current month
     *
     * @return statement
     */
    public OutgoingTransactionsMonthlyStatement createOutgoingTransactionsStatement() {
        return new OutgoingTransactionsMonthlyStatement();
    }

    public void sendStatements(Statement statement, StatementSender sender) {
        StatementDeliveryService statementDeliveryService = new StatementDeliveryService();
        statementDeliveryService.deliverStatement(statement, sender);
    }
}

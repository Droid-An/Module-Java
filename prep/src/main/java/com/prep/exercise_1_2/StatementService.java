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
        AllTransactionsMonthlyStatement allTransactionsMonthlyStatement = new AllTransactionsMonthlyStatement();
        return allTransactionsMonthlyStatement;
    }

    /**
     * Return statement with all outgoing transactions for the current month
     *
     * @return statement
     */
    public OutgoingTransactionsMonthlyStatement createOutgoingTransactionsStatement() {
        OutgoingTransactionsMonthlyStatement outgoingTransactionsMonthlyStatement = new OutgoingTransactionsMonthlyStatement();
        return outgoingTransactionsMonthlyStatement;
    }

    public void sendStatements(Statement statement, StatementSender sender) {
        StatementDeliveryService statementDeliveryService = new StatementDeliveryService();
        statementDeliveryService.deliverStatement(statement, sender);
    }
}

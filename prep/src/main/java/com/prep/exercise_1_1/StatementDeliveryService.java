package com.prep.exercise_1_1;

import com.prep.exercise_1_2.AllTransactionsMonthlyStatement;
import com.prep.exercise_1_2.OutgoingTransactionsMonthlyStatement;
import com.prep.exercise_1_2.Statement;
import com.prep.exercise_1_2.StatementService;

/**
 * Handles statement delivery
 */
public class StatementDeliveryService {
    /**
     * Actual postage
     */
    public static void main(String[] args) {
        StatementDeliveryService statementDeliveryService = new StatementDeliveryService();

        // part of exercise 1.2
        StatementService statementService = new StatementService();
        AllTransactionsMonthlyStatement allTransactionsMonthlyStatement = statementService.createAllTransactionsStatement();
        OutgoingTransactionsMonthlyStatement outgoingTransactionsMonthlyStatement = statementService.createOutgoingTransactionsStatement();

        //Declare senders
        EmailStatementSender emailStatementSender = new EmailStatementSender();
        LetterStatementSender letterStatementSender = new LetterStatementSender();

        //Demo of functionality implemented in 1.1
        statementDeliveryService.deliverStatement("Very Important Statement", emailStatementSender);
        statementDeliveryService.deliverStatement("Very Important Statement", letterStatementSender);


        //demo of delivering statements using statement Delivery service from exercise 1.2
        statementDeliveryService.deliverStatement(outgoingTransactionsMonthlyStatement, emailStatementSender);
        statementDeliveryService.deliverStatement(allTransactionsMonthlyStatement, letterStatementSender);

        // working demo of using Statement from  exercise 1.2
        statementService.sendStatements(outgoingTransactionsMonthlyStatement, emailStatementSender);
        statementService.sendStatements(allTransactionsMonthlyStatement, letterStatementSender);

    }

    /**
     * Deliver statement
     *
     * @param statementContent Statement content in String type
     * @param statementSender  Sender to use
     * @since 1.1
     */
    public void deliverStatement(String statementContent, StatementSender statementSender) {
        statementSender.sendStatement(statementContent);
    }

    /**
     * Deliver statement
     *
     * @param statement       Statement content in a form of Statement interface
     * @param statementSender Sender to use
     * @since 1.2
     */
    public void deliverStatement(Statement statement, StatementSender statementSender) {
        statementSender.sendStatement(statement);
    }
}

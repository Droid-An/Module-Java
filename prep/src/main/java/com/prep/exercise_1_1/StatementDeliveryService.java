package com.prep.exercise_1_1;

/**
 * Handles statement delivery
 */
public class StatementDeliveryService {
    /**
     * Actual postage
     */
    public static void main(String[] args) {
        StatementDeliveryService statementDeliveryService = new StatementDeliveryService();

        EmailStatementSender emailStatementSender = new EmailStatementSender();
        LetterStatementSender letterStatementSender = new LetterStatementSender();
        statementDeliveryService.deliverStatement("Very Important Statement", emailStatementSender);
        statementDeliveryService.deliverStatement("Very Important Statement", letterStatementSender);

    }

    /**
     * Deliver statement
     *
     * @param statementContent Statement content
     * @param statementSender  Sender to use
     */
    private void deliverStatement(String statementContent, StatementSender statementSender) {
        statementSender.sendStatement(statementContent);
    }
}

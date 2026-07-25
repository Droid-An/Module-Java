package com.prep.exercise_1_1;

/**
 * StatementSender that sends emails
 */
public class EmailStatementSender implements StatementSender {

    /**
     * Send email with given statement content
     *
     * @param statementContent statement content
     */
    @Override
    public void sendStatement(String statementContent) {
        System.out.println("Email sent" + ": " + statementContent);
    }
}

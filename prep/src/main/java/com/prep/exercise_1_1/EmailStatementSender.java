package com.prep.exercise_1_1;

import com.prep.exercise_1_2.Statement;

/**
 * StatementSender that sends emails
 */
public class EmailStatementSender implements StatementSender {

    /**
     * Send email with given statement content
     *
     * @param statementContent statement content
     * @since 1.1
     */
    @Override
    public void sendStatement(String statementContent) {
        System.out.println("Email sent" + ": " + statementContent);
    }

    /**
     * Send email with given statement object
     *
     * @param statement statement to send
     * @since 1.2
     */
    @Override
    public void sendStatement(Statement statement) {
        System.out.println("Email sent" + ": " + "\n" + statement.getStatement());
    }
}

package com.prep.exercise_1_1;

import com.prep.exercise_1_2.Statement;

/**
 * StatementSender that sends letters
 */
public class LetterStatementSender implements StatementSender {

    /**
     * Send letter with given statement content
     *
     * @param statementContent statement content
     * @since 1.1
     */
    @Override
    public void sendStatement(String statementContent) {
        System.out.println("Letter sent" + ": " + statementContent);
    }

    /**
     * Send letter with given statement
     *
     * @param statement statement to send
     * @since 1.2
     */

    @Override
    public void sendStatement(Statement statement) {
        System.out.println("Letter sent" + ": " + "\n" + statement.getStatement());
    }
}

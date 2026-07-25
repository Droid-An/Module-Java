package com.prep.exercise_1_1;

/**
 * StatementSender that sends letters
 */
public class LetterStatementSender implements StatementSender {

    /**
     * Send letter with given statement content
     *
     * @param statementContent statement content
     */
    @Override
    public void sendStatement(String statementContent) {
        System.out.println("Letter sent" + statementContent);
    }
}

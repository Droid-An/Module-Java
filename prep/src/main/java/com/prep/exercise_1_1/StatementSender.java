package com.prep.exercise_1_1;

import com.prep.exercise_1_2.Statement;

/**
 * Blueprint for statement senders
 */
public interface StatementSender {
    /**
     * Send String statement
     *
     * @param statementContent String statement
     * @since 1.1
     */
    void sendStatement(String statementContent);

    /**
     * Send Statement using Statement object
     *
     * @param statement statement object
     * @since 1.2
     */
    void sendStatement(Statement statement);
}



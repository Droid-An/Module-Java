package exercise._1_1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggingDemo {
    private static final Logger log = LoggerFactory.getLogger(LoggingDemo.class);

    public static void main(String[] args) {
        log.info("Application starting...");
        log.debug("This is a debug message that you should NOT see yet.");
        log.warn("Low disk space!");
        log.error("Something went wrong.", new RuntimeException("Test error"));

        for (int i = 0; i < 50; i++) {
            if (i % 10 == 0) {
                log.info("10th iteration");
            }
        }
    }


}
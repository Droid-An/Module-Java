package exercise2_2;

public class ApplicationLogger {

    public void logInfo() {
        System.out.println("[INFO]");
        FileLogger FileLogger = new FileLogger();
        FileLogger.openFile();
        FileLogger.writeLine("message");
        FileLogger.closeFile();
    }

    public void logError() {
        System.out.println("[ERROR]");
    }
}

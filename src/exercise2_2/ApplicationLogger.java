package exercise2_2;

public class ApplicationLogger {

    public void log(){
        FileLogger FileLogger = new FileLogger();
        FileLogger.openFile();
        FileLogger.writeLine("message");
        FileLogger.closeFile();
    }
}

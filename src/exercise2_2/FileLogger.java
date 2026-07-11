package exercise2_2;

import java.io.File;

class FileLogger {
    private boolean fileOpen = false;
    public void openFile() {
        if (fileOpen) {
            throw new IllegalStateException("File already open");
        }
        System.out.println("Opening log file...");
        fileOpen = true;
    }
    public void writeLine(String line) {
        if (!fileOpen) {
            throw new IllegalStateException("Cannot write - file not open");
        }
        System.out.println("LOG: " + line);
    }
    public void closeFile() {
        if (!fileOpen) {
            throw new IllegalStateException("File already closed");
        }
        System.out.println("Closing log file...");
        fileOpen = false;
    }

    public static void main(String[] args) {
        ApplicationLogger ApplicationLogger = new ApplicationLogger();
        ApplicationLogger.log();

    }
}

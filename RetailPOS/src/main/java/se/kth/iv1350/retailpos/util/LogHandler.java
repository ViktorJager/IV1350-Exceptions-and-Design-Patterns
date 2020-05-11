package se.kth.iv1350.retailpos.util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * This class is responsible for the logging of the program.
 */
public class LogHandler {
    private static final String LOG_FILE_NAME = "retailPOS-log.txt";
    private PrintWriter logFile;
    
    /**
     * Creates a new instance of LogHandler.
     * @throws IOException 
     */
    public LogHandler() throws IOException {
        logFile = new PrintWriter(new FileWriter(LOG_FILE_NAME), true);
    }
    
    /**
     * Writes to the log describing an exception that was thrown.
     * 
     * @param exc The exception to be logged.
     */
    public void logException(Exception exc) {
        StringBuilder logMsgBuilder = new StringBuilder();
        logMsgBuilder.append(createTime());
        logMsgBuilder.append(", Exception thrown: ");
        logMsgBuilder.append(exc.getMessage());
        logFile.println(logMsgBuilder);
        exc.printStackTrace(logFile);
    }
    
    private String createTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
        return now.format(formatter);
    }
}

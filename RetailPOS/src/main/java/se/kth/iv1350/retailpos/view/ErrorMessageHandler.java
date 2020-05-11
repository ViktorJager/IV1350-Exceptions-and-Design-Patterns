package se.kth.iv1350.retailpos.view;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * Creates and shows error messages to the UI. In this implementation the 
 * message is printed to the console.
 */
class ErrorMessageHandler {
    
    /**
     * Prints the specified error message to the console with a timestamp.
     * 
     * @param msg The error message.
     */
    void showErrorMsg(String msg) {
        StringBuilder errorMsgBuilder = new StringBuilder();
        errorMsgBuilder.append("ERROR" + " | ");
        errorMsgBuilder.append(createTime());
        errorMsgBuilder.append(" | ");
        errorMsgBuilder.append(msg);
        System.out.println(errorMsgBuilder);
    }

    private String createTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
        return now.format(formatter);
    }
}

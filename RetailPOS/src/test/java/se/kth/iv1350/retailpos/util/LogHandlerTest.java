package se.kth.iv1350.retailpos.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import se.kth.iv1350.retailpos.integration.ItemIdNotFoundException;

public class LogHandlerTest {

    private LogHandler instance;
    private String logFileName = "retailPOS-log.txt";

    @BeforeEach
    public void createInstance() {
        try {
            instance = new LogHandler();
        } catch (IOException ex) {
            fail("Could not create log handler");
            ex.printStackTrace();
        }
    }

    @AfterEach
    public void deleteFile() {
        instance = null;
        File logFile = new File(logFileName);
        logFile.delete();
    }

    @Test
    public void testLogException() throws IOException {
        String itemIdentifier = "332233";
        ItemIdNotFoundException exception = new ItemIdNotFoundException(itemIdentifier);
        instance.logException(exception);
        String expResultMsg = "Invalid item identifier. Item with identifier '"
                + itemIdentifier + "' does not exist.";
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
        String expResultTime = now.format(formatter);
        String expResultStack = "at se.kth.iv1350.retailpos.util.LogHandlerTest.testLogException";
        assertTrue(fileContains(expResultMsg), "Wrong printout.");
        assertTrue(fileContains(expResultTime), "Wrong printout.");
        assertTrue(fileContains(expResultStack), "Wrong printout.");
    }

    private boolean fileContains(String searchedString) throws IOException {
        BufferedReader file = new BufferedReader(new FileReader(logFileName));
        String line = null;
        while ((line = file.readLine()) != null) {
            if (line.contains(searchedString)) {
                return true;
            }
        }
        return false;
    }
}

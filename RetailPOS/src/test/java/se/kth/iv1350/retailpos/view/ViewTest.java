package se.kth.iv1350.retailpos.view;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import se.kth.iv1350.retailpos.controller.Controller;
import se.kth.iv1350.retailpos.integration.ExternalSystemsCreator;
import se.kth.iv1350.retailpos.integration.Printer;


public class ViewTest {
    ExternalSystemsCreator creator;
    Printer printer;
    private View instanceToTest;
    private ByteArrayOutputStream printoutBuffer;
    private PrintStream originalSysOut;
    
    @BeforeEach
    public void setUp() throws IOException {
        creator = new ExternalSystemsCreator();
        printer = new Printer();
        Controller contr = new Controller(creator, printer);
        instanceToTest = new View(contr);
        
        printoutBuffer = new ByteArrayOutputStream();
        PrintStream inMemSysOut = new PrintStream(printoutBuffer);
        originalSysOut = System.out;
        System.setOut(inMemSysOut);
    }
    
    @AfterEach
    public void tearDown() {
        creator = null;
        printer = null;
        instanceToTest = null;
        
        printoutBuffer = null;
        System.setOut(originalSysOut);
    }

    @Test
    public void testRunFakeExecution() {
        instanceToTest.runFakeExecution();
        String printout = printoutBuffer.toString();
        String expectedOutput = "start";
        assertTrue(printout.contains(expectedOutput), "UI did not start correctly.");
    }
    
}

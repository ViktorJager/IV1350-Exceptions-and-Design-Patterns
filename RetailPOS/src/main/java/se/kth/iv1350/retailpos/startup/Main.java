package se.kth.iv1350.retailpos.startup;

import java.io.IOException;
import se.kth.iv1350.retailpos.controller.Controller;
import se.kth.iv1350.retailpos.integration.ExternalSystemsCreator;
import se.kth.iv1350.retailpos.integration.Printer;
import se.kth.iv1350.retailpos.view.View;

/**
 * Starts the entire application, contains the main method used to start the
 * application.
 */
public class Main {

    /**
     * The main method used to start the entire application.
     *
     * runFakeExecution() performs a hard coded sale. runIOProgram() starts an
     * interactive sale that requires user input.
     *
     * @param args The application does not take any command line parameters.
     */
    public static void main(String[] args) {
        try {
            ExternalSystemsCreator creator = new ExternalSystemsCreator();
            Printer printer = new Printer();
            Controller contr = new Controller(creator, printer);
            View view = new View(contr);
            
            view.runFakeExecution();
            //view.runIOProgram();
        } catch (IOException ex) {
            System.out.println("Unable to start the application");
        } 
    }
}

package se.kth.iv1350.retailpos.view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import se.kth.iv1350.retailpos.controller.Controller;
import se.kth.iv1350.retailpos.controller.OperationFailedException;
import se.kth.iv1350.retailpos.integration.ItemIdNotFoundException;
import se.kth.iv1350.retailpos.model.Amount;
import se.kth.iv1350.retailpos.model.RunningTotalDTO;
import se.kth.iv1350.retailpos.util.LogHandler;

/**
 * This is a placeholder for the real view. It contains a hardcoded execution
 * with calls to all system operations in the controller.
 */
public class View {

    private Controller contr;
    private LogHandler logger;
    private ErrorMessageHandler errorMsgHandler = new ErrorMessageHandler();

    /**
     * Creates a new instance, that uses the specified controller for all calls
     * to other layers.
     *
     * @param contr The controller to use for all calls to other layers.
     * @throws IOException
     */
    public View(Controller contr) throws IOException {
        this.contr = contr;
        this.logger = new LogHandler();
    }

    /**
     * Performs a fake sale, by calling all system operations in the controller.
     */
    public void runFakeExecution() {
        RunningTotalDTO currSaleInfo;
        List<String> items = new ArrayList<String>() {
            {
                add("555555"); add("999999"); add("111111"); add("999");
                add("555555"); add("999999"); add("444444"); add("123");
                add("444444"); add("666666"); add("666666"); add("4"); 
            }
        };

        contr.startSale();
        System.out.println("A new sale has been started.");

        for (String item : items) {
            try {
                currSaleInfo = contr.registerItem(item);
                System.out.println(currSaleInfo.toString());
            } catch (ItemIdNotFoundException exc) {
                System.out.println("Invalid item identifier: '" + exc.getInvalidItemIdentifier() + "'");
            } catch (OperationFailedException exc) {
                handleException("Could not register item", exc);
            }
        }

        Amount totalPrice = contr.endSale();
        System.out.println("The sale has ended.");
        System.out.println("Total price: " + totalPrice.toString());

        contr.pay(new Amount(1750));
    }

    /**
     * Performs an interactive sale with user input.
     */
    public void runIOProgram() {
        RunningTotalDTO currSaleInfo;
        Scanner in = new Scanner(System.in);
        String choice = null;
        String itemIdentifier = null;

        System.out.println("Press enter to start a new sale.");
        in.nextLine();
        contr.startSale();
        System.out.println("A new sale has been started.");

        System.out.println("Command Options: ");
        System.out.println("1: Register item");
        System.out.println("2: End sale");
        System.out.println("3: Show list of available items");
        System.out.println("x: Exit program");

        do {
            System.out.println();
            System.out.println("Enter new command: ");
            choice = in.nextLine();
            switch (choice) {
                case "1":
                    System.out.println("Enter item identifier: ");
                    itemIdentifier = in.nextLine();
                    try {
                        currSaleInfo = contr.registerItem(itemIdentifier);
                        System.out.println(currSaleInfo.toString());
                    } catch (ItemIdNotFoundException | OperationFailedException exc) {
                        System.out.println(exc.getMessage());
                    }
                    break;

                case "2":
                    Amount totalPrice = contr.endSale();
                    System.out.println("The sale has ended.");
                    System.out.println("Total price: " + totalPrice.toString());
                    System.out.println();
                    System.out.println("Enter amount paid by customer: ");
                    contr.pay(new Amount(in.nextInt()));
                    break;

                case "3":
                    System.out.println("Available items: ");
                    System.out.println("identifier: 111111\tName: TeeShirt");
                    System.out.println("identifier: 222222\tName: BasketballR");
                    System.out.println("identifier: 333333\tName: Short Socks");
                    System.out.println("identifier: 444444\tName: Destroyer");
                    System.out.println("identifier: 555555\tName: Firebird");
                    System.out.println("identifier: 666666\tName: AirMax2020T");
                    break;
            }
        } while (!choice.equals("x"));
    }
    
    private void handleException(String msg, Exception exc) {
        errorMsgHandler.showErrorMsg(msg);
        logger.logException(exc);
    }
}

package se.kth.iv1350.retailpos.integration;

import se.kth.iv1350.retailpos.model.Receipt;

/**
 * The interface to the printer. Printer is used for all printouts of receipt's
 * in this program.
 */
public class Printer {
    /**
     * Prints the specified receipt. This dummy implementation prints to
     * <code>System.out</code> instead of a printer.
     *
     * @param receipt The receipt to be printed.
     */
    public void printReceipt(Receipt receipt) {
         System.out.println(receipt.createReceiptString());
    }
}

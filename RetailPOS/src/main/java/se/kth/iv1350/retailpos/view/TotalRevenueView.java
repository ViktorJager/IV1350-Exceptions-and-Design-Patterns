package se.kth.iv1350.retailpos.view;

import se.kth.iv1350.retailpos.model.Amount;
import se.kth.iv1350.retailpos.model.SaleObserver;

/**
 * Observer that keeps track of total amount paid for purchases since the start 
 * of the program. This class implements an observer pattern.
 */
public class TotalRevenueView implements SaleObserver {
    private Amount totalRevenue;
    
    /**
     * Creates a new instance and set total amount for sales to zero.
     */
    public TotalRevenueView() {
        totalRevenue = new Amount(0);
    }
    
    /**
     * Adds the paid amount for a sale to the total 'revenue'. Broadcasts the 
     * total amount to the console.
     * 
     * @param amount The amount paid for the sale.
     */
    @Override
    public void newPayment(Amount amount) {
        addNewPayment(amount);
        printCurrentState();
    }

    /**
     * Adds a new payment to the total.
     * 
     * @param amount The amount for the completed sale.
     */
    private void addNewPayment(Amount amount) {
        totalRevenue = totalRevenue.plus(amount);
    }

    /**
     * Prints the total amount of 'revenue' since the program started.
     */
    private void printCurrentState() {
        System.out.print("***** Total amount for all completed sales: ");
        System.out.println(totalRevenue + " *****");
    }
    
}

package se.kth.iv1350.retailpos.model;

/**
 * Represents one specific cash payment for one specific sale. A sale should 
 * only have one 
 */
public class CashPayment {
    private Amount paidAmt;
    private Amount totalCost;
    
    /**
     * Creates a new instance. The customer handed over the specified amount 
     * of cash.
     *
     * @param paidAmt The amount of cash that was handed over by the
     *                customer.
     */
    public CashPayment(Amount paidAmt) {
        this.paidAmt = paidAmt;
    }
    
    /**
     * Calculates the total cost of the specified sale.
     * 
     * @param paidSale The sale for which the customer is paying.
     */
    void calculateTotalCost(Sale paidSale) {
        totalCost = paidSale.getTotalPriceTaxIncluded();
    }
    
    /**
     * Get the value of paidAmt
     * 
     * @return The amount paid in cash for the sale. 
     */
    Amount getPaidAmt() {
        return paidAmt;
    }
    
    /**
     * Get the value of totalCost
     * 
     * @return The total cost of the sale that was paid. 
     */
    Amount getTotalCost() {
        return totalCost;
    }

    /**
     * Calculates and returns the amount change for a customer after a cash
     * payment.
     * 
     * @return The amount of change the customer shall have.
     */
    Amount getChange() {
        return paidAmt.minus(totalCost);
    }
}

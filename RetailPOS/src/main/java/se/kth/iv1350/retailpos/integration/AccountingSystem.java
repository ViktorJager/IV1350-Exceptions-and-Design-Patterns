package se.kth.iv1350.retailpos.integration;

import se.kth.iv1350.retailpos.model.Sale;

/**
 * Class that represents the External Accounting System. Contains all calls to 
 * the accounting system.
 */
public class AccountingSystem {
    /**
     * Creates an instance of AccountingSystem.
     */
    AccountingSystem() {
    }
    
    /**
     * Dummy method for sending sale information to external accounting
     * system. Accounting is not accounted for any further then this in this 
     * program.
     * 
     * @param paidSale The sale that has been paid for by the customer.
     */
    public void updateAccountingSystem(Sale paidSale) {
    }
}

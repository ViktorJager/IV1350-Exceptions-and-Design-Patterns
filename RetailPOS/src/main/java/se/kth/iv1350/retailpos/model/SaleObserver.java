package se.kth.iv1350.retailpos.model;

/**
 * Listener interface for receiving notifications about completed sales. 
 * When a sale is completed, the observers are notified.
 */
public interface SaleObserver {
    /**
     * Invoked when a sale has been completed.
     * 
     * @param totalAmount The amount paid for the completed sale.
     */
    void newPayment(Amount totalAmount);
}

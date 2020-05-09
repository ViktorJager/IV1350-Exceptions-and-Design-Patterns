package se.kth.iv1350.retailpos.model;

/**
 * This class represents a cash register in the program. Its purpose is to keep 
 * track of the balance in the cash register. There should only exist one
 * instance of <code>CashRegister</code> per register.
 */
public class CashRegister {
    private Amount balance = new Amount(0);
    
    /**
     * Adds the specified amount to the balance of the <code>CashRegister</code>
     * 
     * @param payment The paid amount that will be added to the balance of the
     * <code>CashRegister</code>.
     */
    public void addPayment(CashPayment payment) {
        balance = balance.plus(payment.getTotalCost());
    }
    
    /**
     * Get the value of balance
     * 
     * @return The balance of the cash register. 
     */
    Amount getBalance(){
        return balance;
    }
}

package se.kth.iv1350.retailpos.model;

/**
 * Represents an amount of money. Instances are final and immutable.
 */
public final class Amount {
    final double amount;
    
    /**
     * Creates a new instance, representing the amount 0.
     */
    public Amount() {
        this(0);
    }
    
    /**
     * Creates a new instance, representing the specified amount.
     * 
     * @param amount The amount represented by the newly created instance.
     */
    public Amount(double amount) {
        this.amount = amount;
    }
    
    /**
     * Subtracts the specified <code>Amount</code> from this object and returns
     * an <code>Amount</code> instance with the result. The operation will
     * overflow if the result is smaller than <code>Integer.MIN_VALUE</code>.
     *
     * @param other The <code>Amount</code> to subtract.
     * @return The result of the subtraction.
     */
    public Amount minus(Amount other) {
        return new Amount(amount - other.amount);
    }

    /**
     * Adds the specified <code>Amount</code> to this object and returns an
     * <code>Amount</code> instance with the result. The operation will
     * overflow if the result is larger than <code>Integer.MAX_VALUE</code>.
     *
     * @param other The <code>Amount</code> to add.
     * @return The result of the addition.
     */
    public Amount plus(Amount other) {
        return new Amount(amount + other.amount);
    }
    
    /**
     * Multiplies the specified <code>Amount</code> with this object and 
     * returns an <code>Amount</code> instance with the result. The operation 
     * will overflow if the result is larger than <code>Integer.MAX_VALUE</code>.
     *
     * @param other The <code>Amount</code> to multiply.
     * @return The result of the multiplication.
     */
    public Amount multiply(Amount other) {
        return new Amount(amount * other.amount);
    }
    
    /**
     * Multiplies the specified <code>Double</code> with this object and 
     * returns an <code>Amount</code> instance with the result. The operation 
     * will overflow if the result is larger than <code>Integer.MAX_VALUE</code>.
     *
     * @param other The <code>Amount</code> to multiply.
     * @return The result of the multiplication.
     */
    public Amount multiplyDouble(Double other) {
        return new Amount(amount * other);
    }

    /**
     * Two <code>Amount</code>s are equal if they represent the same amount.
     *
     * @param other The <code>Amount</code> to compare with this amount.
     * @return <code>true</code> if the specified amount is equal to this
     *         amount, <code>false</code> if it is not.
     */
    @Override
    public boolean equals(Object other) {
        if (other == null || !(other instanceof Amount)) {
            return false;
        }
        Amount otherAmount = (Amount) other;
        return amount == otherAmount.amount;
    }

    
    /**
     * Converts the value of the amount to a <code>String</code>, overriding 
     * original toString().
     * 
     * @return The amount as a <code>String</code>.
     */
    @Override
    public String toString() {
        return Double.toString(amount);
    }
}

package se.kth.iv1350.retailpos.integration;

import java.util.Objects;
import se.kth.iv1350.retailpos.model.Amount;

/**
 * Represents an item in retail store. Instances are final and immutable.
 */
public final class ItemDTO {
    private final String itemIdentifier;
    private final Amount price;
    private final double taxRate;
    private final String itemName;
    private final String itemDescription;
    
    /**
     * Creates a new instance of ItemDTO.
     * 
     * @param itemIdentifier    The item identifier of the item.
     * @param price             The price of the item.
     * @param taxRate           The tax rate(VAT) of the item. The tax rate can
     *                          be 6%, 12% or 25%. A tax rate of 6% is 
     *                          represented as a double, with a value of 1.06.
     * @param itemName          The name of the item.
     * @param itemDescription   The description of the item.
     */
    public ItemDTO(String itemIdentifier, Amount price, double taxRate, 
            String itemName, String itemDescription) {
        this.itemIdentifier = itemIdentifier;
        this.price = price;
        this.taxRate = taxRate;
        this.itemName = itemName;
        this.itemDescription = itemDescription;
    }
    
     /**
     * Two ItemDTO instances are equal if all fields are equal.
     * If this method(and Override) are omitted, Java does not consider two
     * ItemDTO's equal unless they share the same memory address.
     *
     * @param otherObj The object to compare with this <code>ItemDTO</code>.
     * @return <code>true</code> if all fields in the specified
     *         object are equal to corresponding fields in this
     *         <code>ItemDTO</code>, <code>false</code> if they are not.
     */
    @Override
    public boolean equals(Object otherObj) {
        if (otherObj == null) {
            return false;
        }
        if (getClass() != otherObj.getClass()) {
            return false;
        }
        final ItemDTO other = (ItemDTO) otherObj;
        if (!itemIdentifier.equals(other.itemIdentifier)) {
            return false;
        }
        if (!Objects.equals(this.price, other.price)) {
            return false;
        }
        if (!Objects.equals(this.taxRate, other.taxRate)) {
            return false;
        }
        if (!itemName.equals(other.itemName)) {
            return false;
        }
        if (!itemDescription.equals(other.itemDescription)) {
            return false;
        }
        return true;
    }
    
    /**
     * Converts the content of this <code>ItemDTO</code> into a 
     * <code>String</code>.
     * 
     * @return <code>ItemDTO</code> as a <code>String</code>.
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("itemIdentifier: " + itemIdentifier + ", ");
        builder.append("price: " + price + ", ");
        builder.append("taxRate: " + taxRate + ", ");
        builder.append("itemName: " + itemName + ", ");
        builder.append("itemDescription: " + itemDescription);
        return builder.toString();
    }
    
    /**
     * Get the value of itemIdentifier
     *
     * @return the value of itemIdentifier
     */
    public String getItemIdentifier() {
        return itemIdentifier;
    }
    
    /**
     * Get the value of price
     *
     * @return the value of price
     */
    public Amount getPrice() {
        return price;
    }
    
    /**
     * Get the value of taxRate
     *
     * @return the value of taxRate
     */
    public double getTaxRate() {
        return taxRate;
    }
    
    /**
     * Get the value of itemName
     *
     * @return the value of itemName
     */
    public String getItemName() {
        return itemName;
    }
    
    /**
     * Get the value of itemDescription
     *
     * @return the value of itemDescription
     */
    public String getItemDescription() {
        return itemDescription;
    }
}

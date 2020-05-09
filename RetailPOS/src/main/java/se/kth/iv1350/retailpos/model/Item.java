package se.kth.iv1350.retailpos.model;

import java.util.Objects;
import se.kth.iv1350.retailpos.integration.ItemDTO;

/**
 * Represents an item in a sale. Contains information about the item as well 
 * as the quantity of the item in the sale. The items are created from an
 * ItemDTO that is provided by the database(InventoryRegistry).
 */
public class Item {
    private final String itemIdentifier;
    private final Amount priceTaxExcluded;
    private final Amount priceTaxIncluded;
    private final double taxRate;
    private final String itemName;
    private final String itemDescription;
    private int quantity;
    
    
    /**
     * Creates a new instance. Represents an item in a sale.
     * 
     * @param itemDTO A DTO with information that describes an item.
     */
    Item(ItemDTO itemDTO){
        this.itemIdentifier = itemDTO.getItemIdentifier();
        this.priceTaxExcluded = itemDTO.getPrice();
        this.taxRate = itemDTO.getTaxRate();
        this.itemName = itemDTO.getItemName();
        this.itemDescription = itemDTO.getItemDescription();
        this.quantity = 1;
        priceTaxIncluded = calculatePriceTaxIncluded(itemDTO);
    }
    
    /**
     * Calculates the total price of the item by multiplying the price with
     * the tax rate.
     * 
     * @param itemDTO A DTO with information that describes an item.
     */
    private Amount calculatePriceTaxIncluded(ItemDTO itemDTO) {
        Double itemTaxRate = itemDTO.getTaxRate();
        Amount itemPriceTaxExcluded = itemDTO.getPrice();
        Amount itemPriceTaxIncluded = itemPriceTaxExcluded.multiplyDouble(itemTaxRate);
        
        return itemPriceTaxIncluded;
    }
    
    /**
     * Two <code>Item</code> instances are equal if all fields are equal.
     *
     * @param otherObj The <code>Item</code> to compare with this
     *                 <code>Item</code>.
     * @return <code>true</code> if all fields in the specified
     *         <code>Item</code> are equal to corresponding fields in this
     *         <code>Item</code>, <code>false</code> if they are not.
     */
    @Override
    public boolean equals(Object otherObj) {
        if (otherObj == null) {
            return false;
        }
        if (getClass() != otherObj.getClass()) {
            return false;
        }
        final Item other = (Item) otherObj;
        if (!itemIdentifier.equals(other.itemIdentifier)) {
            return false;
        }
        if (!Objects.equals(this.priceTaxExcluded, other.priceTaxExcluded)) {
            return false;
        }
        if (!Objects.equals(this.priceTaxIncluded, other.priceTaxIncluded)) {
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
        if (quantity != other.quantity) {
            return false;
        }
        return true;
    }

    /**
     * Increases the quantity of an item by 1.
     */
    void increaseQuantity(){
        quantity = quantity + 1;
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
     * Get the value of priceTaxExcluded
     *
     * @return the value of priceTaxExcluded
     */
    public Amount getPriceTaxExcluded() {
        return priceTaxExcluded;
    }
    
    /**
     * Get the value of priceTaxIncluded
     *
     * @return the value of priceTaxIncluded
     */
    public Amount getPriceTaxIncluded() {
        return priceTaxIncluded;
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
    
    /**
     * Get the value of quantity
     *
     * @return the value of quantity
     */
    public int getQuantity() {
        return quantity;
    }
}

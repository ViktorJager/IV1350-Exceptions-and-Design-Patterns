package se.kth.iv1350.retailpos.model;

import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * A data transfer object that provides the UI (View) with information about 
 * the latest registered item and the current price for the ongoing sale.
 */
public class RunningTotalDTO {
    private final Amount price;
    private final String itemName;
    private final Amount runningTotal;
    private final int quantity;
    
    /**
     * Creates a new instance.
     * 
     * @param item                      The registered item.
     * @param runningTotalTaxIncluded   The running total with tax included.
     */
    public RunningTotalDTO(Item item, Amount runningTotalTaxIncluded) {
        this.price = item.getPriceTaxIncluded();
        this.itemName = item.getItemName();
        this.runningTotal = runningTotalTaxIncluded;
        this.quantity = item.getQuantity();
    }
    
    /**
     * Creates a formatted string with information about the DTO
     * 
     * @return A string with information about the DTO.
     */
    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.FLOOR);
        
        StringBuilder builder = new StringBuilder();
        builder.append("Price: " + df.format(price.amount) + ", ");
        builder.append("Name: " + itemName + ", ");
        builder.append("Quantity: " + quantity + ", ");
        builder.append("Total Price: " + df.format(runningTotal.amount));
        return builder.toString();
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
     * Get the value of itemName
     *
     * @return the value of itemName
     */
    public String getItemName() {
        return itemName;
    }
    
    /**
     * Get the value of runningTotal
     *
     * @return the value of runningTotal
     */
    public Amount getRunningTotal() {
        return runningTotal;
    }
}

package se.kth.iv1350.retailpos.model;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Represents one receipt, which proves the payment of one sale.
 */
public class Receipt {
    private final Sale sale;
    private final StoreInfo store = new StoreInfo();
    
    /**
     * Creates a new instance of a receipt.
     * 
     * @param sale The sale proved by this receipt.
     */
    Receipt(Sale sale) {
        this.sale = sale;
    }
    
     /**
     * Creates a well-formatted string with the entire content of the receipt.
     * The decimals are given with two decimal places. The printout for 
     * the receipt is currently hard coded and ugly.
     *
     * @return The well-formatted receipt string.
     */
    public String createReceiptString() {
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.FLOOR);
        LocalDateTime saleTime = sale.getSaleTime();
        String timeColonPattern = "HH:mm:ss";
        DateTimeFormatter timeColonFormatter = DateTimeFormatter.ofPattern(timeColonPattern);
        StringBuilder builder = new StringBuilder();
        
        appendLine(builder, "RECEIPT");
        appendLine(builder, store.getStoreName());
        appendLine(builder, store.getStoreAddress());
        endSection(builder);
        
        builder.append((DateTimeFormatter.ISO_DATE).format(saleTime) + " ");
        builder.append(timeColonFormatter.format(saleTime));
        newLine(builder);
        endSection(builder);
        
        List<Item> itemsInSale = sale.getItemsInSale();
        appendLine(builder, "Name:" + "\t\t" + "Quantity:" +"\t" + "Price:");
        for(Item item : itemsInSale) {
            //appendLine(builder, "Name: " + item.getItemName() + " Quantity: " + item.getQuantity() + " Price: " + item.getPriceTaxIncluded());
            builder.append(item.getItemName() + "\t\t");
            builder.append(item.getQuantity() + "\t");
            builder.append(df.format(item.getPriceTaxIncluded().amount * item.getQuantity()));
            newLine(builder);
        }
        endSection(builder);
        
        builder.append("Total price incl. VAT: ");
        appendLine(builder, df.format(sale.getTotalPriceTaxIncluded().amount));
        builder.append("Total VAT: ");
        appendLine(builder, df.format(sale.amountTaxTotal().amount));
        endSection(builder);
        
        builder.append("Amount paid: ");
        appendLine(builder, df.format(sale.getPayment().getPaidAmt().amount));
        builder.append("Change: ");
        appendLine(builder, df.format(sale.getPayment().getChange().amount));
        
        return builder.toString();
    }
    
    /**
     * @param builder The <code>StringBuilder</code> to append the 
     *                <code>String</code>.
     * 
     * @param line The <code>String</code> to append.
     */
    private void appendLine(StringBuilder builder, String line) {
        builder.append(line);
        builder.append("\n");
    }

    /**
     * @param builder The <code>StringBuilder</code> to append a line break to. 
     */
    private void endSection(StringBuilder builder) {
        builder.append("\n");
    }

    /**
     * @param builder The <code>StringBuilder</code> to append a line break to. 
     */
    private void newLine(StringBuilder builder) {
        builder.append("\n");
    }
}

package se.kth.iv1350.retailpos.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import se.kth.iv1350.retailpos.integration.ItemDTO;
import se.kth.iv1350.retailpos.integration.Printer;

/**
 * Represents one particular sale by one particular customer. Sale contains 
 * information about the particular sale, as well as business logic to perform 
 * a sale.
 */
public class Sale {
    private LocalDateTime saleTime;
    private Amount totalPriceTaxExcluded = new Amount(0);
    private Amount totalPriceTaxIncluded = new Amount(0);
    private List<Item> itemsInSale = new ArrayList<>();
    private CashPayment payment;
    private List <SaleObserver>  saleObservers = new ArrayList<>();
    
    /**
     * Creates a new instance and saves the time of this sale.
     */
    public Sale() {
        saleTime = LocalDateTime.now();
    }
    
    /**
     * Searches for an item by item identifier from the list of items in this 
     * sale.
     * 
     * @param itemIdentifier The item identifier of the item.
     * @return The searched item if a matching item identifier was found.
     *         <code>null</code> if no matching item identifier was found.
     */
    private Item findItemByID(String itemIdentifier) {
        for(Item item : itemsInSale) {
            if(item.getItemIdentifier().equals(itemIdentifier)) {
                return item;
            }
        }
        return null;
    }
    
    /**
     * Checks if an item with the specified item identifier exists in this sale.
     * 
     * @param itemIdentifier The item identifier of the item.
     * @return <code>true</code> if an item with the specified item identifier 
     *         exists in this sale.
     *         <code>false</code> if the item does not exist in this sale.
     */
    private boolean itemExistsInSale(String itemIdentifier) {
        if(findItemByID(itemIdentifier) != null) {
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * Increases the quantity of the specified item by one in this sale.
     * 
     * @param itemIdentifier The item identifier of the item.
     */
    private void incrQuantity(String itemIdentifier) {
        findItemByID(itemIdentifier).increaseQuantity();
    }
    
    /**
     * Adds an item to this sale in a list containing all the items in this sale. 
     * If an item with the same item identifier already exists in the list, the 
     * quantity is increased by one instead. A reference to the added item is 
     * returned.
     * 
     * @param itemDTO The item to be added to this sale.
     * @return The item added to this sale.
     */
    private Item addItemToSale(ItemDTO itemDTO) {
        if(itemExistsInSale(itemDTO.getItemIdentifier())) {
            incrQuantity(itemDTO.getItemIdentifier());
            return findItemByID(itemDTO.getItemIdentifier());
        } else {
            Item addedItem = new Item(itemDTO);
            itemsInSale.add(addedItem);
            return addedItem;
        }
    }
    
    /**
     * Updates the total price of this sale, with tax excluded and included.
     * 
     * @param item The items whose price is added to the total.
     */
    private void updateTotalPrice(Item item) {
        totalPriceTaxExcluded = totalPriceTaxExcluded.plus(item.getPriceTaxExcluded());
        totalPriceTaxIncluded = totalPriceTaxIncluded.plus(item.getPriceTaxIncluded());
    }
    
    /**
     * Creates a DTO object with information about the ongoing sale. This DTO
     * is used by the UI(View) to present information about an ongoing sale.
     * 
     * @param item The item to create the DTO from.
     * @return DTO with information about the ongoing sale. 
     */
    private RunningTotalDTO runningSaleInfo(Item item) {
        return new RunningTotalDTO(item, this.totalPriceTaxIncluded);
    }
    
    /**
     * Registers an item to this sale by adding it to the list of items in this 
     * sale, updates the total price and returns a DTO with information about 
     * the ongoing sale.
     * 
     * @param itemDTO The item to register in this sale.
     * @return DTO with information about the ongoing sale.
     */
    public RunningTotalDTO registerItemInSale(ItemDTO itemDTO) {
        Item addedItem = addItemToSale(itemDTO);
        updateTotalPrice(addedItem);
        
        return runningSaleInfo(addedItem);
    }
    
    /**
     * Calculates the total amount of tax for the entire sale.
     * 
     * @return The amount tax paid for the sale.
     */
    Amount amountTaxTotal() {
        Amount totalTaxExcl = totalPriceTaxExcluded;
        Amount totalTaxIncl = totalPriceTaxIncluded;
        Amount amountTaxTotal = totalTaxIncl.minus(totalTaxExcl);
        
        return amountTaxTotal;
    }
    
    /**
     * This sale is paid using the specified payment.
     * 
     * @param payment The payment used to pay this sale.
     */
    public void pay(CashPayment payment) {
        payment.calculateTotalCost(this);
        this.payment = payment;
        notifyObservers();
    }
    
    /**
     * Notifies all observers the total amount paid for the sale.
     */
    private void notifyObservers() {
        for(SaleObserver obs : saleObservers) {
            obs.newPayment(payment.getTotalCost());
        }
    }
    
    /**
     * The observers will be notified when this sale has been completed.
     * 
     * @param obs The observer to notify.
     */
    public void addSaleObserver(SaleObserver obs) {
        saleObservers.add(obs);
    }
    
    /**
     * All the observers will be notified when this sale has been completed.
     * 
     * @param observers The observers to notify. 
     */
    public void addSaleObservers(List<SaleObserver> observers) {
        saleObservers.addAll(observers);
    }
    
    /**
     * Prints a receipt for the this sale on the specified printer.
     * 
     * @param printer The printer where the receipt is printed.
     */
    public void printReceipt(Printer printer) {
        Receipt receipt = new Receipt(this);
        printer.printReceipt(receipt);
    }
    
    /**
     * Get the value of saleTime
     *
     * @return the value of saleTime
     */
    LocalDateTime getSaleTime() {
        return saleTime;
    }
    
    /**
     * Get the value of totalPriceTaxExcluded
     *
     * @return the <code>Amount</code> of totalPriceTaxExcluded
     */
    Amount getTotalPriceTaxExcluded() {
        return totalPriceTaxExcluded;
    }
    
    /**
     * Get the value of totalPriceTaxIncluded
     *
     * @return the <code>Amount</code> of totalPriceTaxIncluded
     */
    public Amount getTotalPriceTaxIncluded() {
        return totalPriceTaxIncluded;
    }
    
    
    /**
     * Get the list of itemsInSale
     *
     * @return the list of itemsInSale
     */
    List<Item> getItemsInSale() {
        return itemsInSale;
    }
    
    /**
     * Get the payment
     * 
     * @return The payment info for this sale.
     */
    public CashPayment getPayment() {
        return payment;
    }
}
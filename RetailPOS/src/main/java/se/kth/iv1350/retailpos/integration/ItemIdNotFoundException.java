package se.kth.iv1350.retailpos.integration;

/**
 * This exception is thrown when an invalid item identifier is searched for in 
 * the InventoryRegistry.
 */
public class ItemIdNotFoundException extends Exception {
    private String invalidItemIdentifier;
    
    /**
     * Creates an instance of an exception with a message specifying the invalid
     * item identifier.
     * 
     * @param invalidItemIdentifier The item with an invalid identifier.
     */
    public ItemIdNotFoundException(String invalidItemIdentifier) {
        super("Invalid item identifier. Item with identifier '" 
                + invalidItemIdentifier + "' does not exist.");
        this.invalidItemIdentifier = invalidItemIdentifier;
    }
    
    /**
     * @return The item identifier of the invalid item.
     */
    public String getInvalidItemIdentifier() {
        return invalidItemIdentifier;
    }
}

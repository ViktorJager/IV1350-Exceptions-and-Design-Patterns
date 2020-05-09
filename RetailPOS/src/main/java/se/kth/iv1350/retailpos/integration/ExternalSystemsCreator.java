package se.kth.iv1350.retailpos.integration;

/**
 * This class is responsible for instantiating all external registries.
 */
public class ExternalSystemsCreator {
    private InventoryRegistry inventoryRegistry = new InventoryRegistry();
    private AccountingSystem accountingSystem = new AccountingSystem();
    
    /**
     * Get the value of inventoryRegistry
     *
     * @return the value of inventoryRegistry
     */
    public InventoryRegistry getInventoryRegistry() {
        return inventoryRegistry;
    }
    
    /**
     * Get the value of accountingSystem
     *
     * @return the value of accountingSystem
     */
    public AccountingSystem getAccountingSystem() {
        return accountingSystem;
    }
}

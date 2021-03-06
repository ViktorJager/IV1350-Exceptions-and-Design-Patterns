package se.kth.iv1350.retailpos.controller;

import java.util.ArrayList;
import java.util.List;
import se.kth.iv1350.retailpos.integration.AccountingSystem;
import se.kth.iv1350.retailpos.integration.DatabaseFailureException;
import se.kth.iv1350.retailpos.integration.ExternalSystemsCreator;
import se.kth.iv1350.retailpos.integration.InventoryRegistry;
import se.kth.iv1350.retailpos.integration.ItemIdNotFoundException;
import se.kth.iv1350.retailpos.integration.Printer;
import se.kth.iv1350.retailpos.integration.ItemDTO;
import se.kth.iv1350.retailpos.model.Amount;
import se.kth.iv1350.retailpos.model.CashPayment;
import se.kth.iv1350.retailpos.model.CashRegister;
import se.kth.iv1350.retailpos.model.RunningTotalDTO;
import se.kth.iv1350.retailpos.model.Sale;
import se.kth.iv1350.retailpos.model.SaleObserver;

/**
 * This is the application's only controller. All calls to the model pass
 * through this class.
 */
public class Controller {

    private Sale sale;
    private InventoryRegistry inventoryRegistry;
    private AccountingSystem accountingSystem;
    private CashRegister cashReg;
    private Printer printer;
    private List<SaleObserver> saleObservers = new ArrayList<>();

    public Controller(ExternalSystemsCreator creator, Printer printer) {
        this.inventoryRegistry = creator.getInventoryRegistry();
        this.accountingSystem = creator.getAccountingSystem();
        this.printer = printer;

        this.cashReg = new CashRegister();
    }

    /**
     * Starts a new sale. This method must be called before any action is
     * registered in the sale.
     */
    public void startSale() {
        sale = new Sale();
        sale.addSaleObservers(saleObservers);
    }

    /**
     * Registers an item with the specified item identifier to the sale.This
     * method gets the item information from an external inventory system.It
     * passes the information to the sale and return a DTO with information
     * about the ongoing sale.
     *
     * @param itemIdentifier The identification number of the item.
     * @return currSaleInfo Information about the ongoing sale.
     * @throws ItemIdNotFoundException If item does not exist.
     * @throws OperationFailedException If unable to register item for any other
     * reason than item id not being valid.
     */
    public RunningTotalDTO registerItem(String itemIdentifier)
            throws ItemIdNotFoundException, OperationFailedException {
        try {
            ItemDTO itemDTO = inventoryRegistry.getItemFromInventoryRegistry(itemIdentifier);
            RunningTotalDTO currSaleInfo = sale.registerItemInSale(itemDTO);
            return currSaleInfo;
        } catch (DatabaseFailureException dbExc) {
            throw new OperationFailedException("Database connection error", dbExc);
        }
    }

    /**
     * The specified observer will be notified when a sale has been paid for.
     * There will be notifications only for sale that are started after this
     * method is called.
     *
     * @param obs The observer to notify.
     */
    public void addSaleObserver(SaleObserver obs) {
        saleObservers.add(obs);
    }

    /**
     * Ends the current sale.
     * 
     * @return Total price for the sale with tax included.
     */
    public Amount endSale() {
        return sale.getTotalPriceTaxIncluded();
    }

    /**
     * Handles sale payment. Updates the balance of the cash register where the
     * payment was performed. Calculates change. Prints the receipt. Sends sale
     * information to update accounting system and inventory registry.
     *
     * @param paidAmt The paid amount.
     */
    public void pay(Amount paidAmount) {
        CashPayment cashPayment = new CashPayment(paidAmount);
        sale.pay(cashPayment);
        cashReg.addPayment(cashPayment);
        sale.printReceipt(printer);
        updateExternalSystems();
    }

    /**
     * Method for updating external systems with information about a completed
     * sale.
     */
    private void updateExternalSystems() {
        accountingSystem.updateAccountingSystem(sale);
        inventoryRegistry.updateInventoryRegistry(sale);
    }
}

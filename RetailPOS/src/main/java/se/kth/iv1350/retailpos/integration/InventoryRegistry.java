package se.kth.iv1350.retailpos.integration;

import java.util.ArrayList;
import java.util.List;
import se.kth.iv1350.retailpos.model.Amount;
import se.kth.iv1350.retailpos.model.Sale;

/**
 * Class that represents the External Inventory System. Contains all calls to
 * the data store with items that are for sale in retail store.
 */
public class InventoryRegistry {

    private List<ItemData> items = new ArrayList<>();

    InventoryRegistry() {
        addItemsToInventoryRegistry();
    }

    /**
     * Adds items to InventoryRegistry to simulate an External Inventory System.
     */
    private void addItemsToInventoryRegistry() {
        items.add(new ItemData("111111", new Amount(200), 1.25, "TeeShirt", "Stylish t-shirt in cotton."));
        items.add(new ItemData("222222", new Amount(50), 1.12, "BasketballR", "Basketball in the new R-plastic line. Very bouncy."));
        items.add(new ItemData("333333", new Amount(75), 1.25, "Short Socks", "Comfortable socks in polyester."));
        items.add(new ItemData("444444", new Amount(225), 1.06, "Destroyer", "Discgolf disc by Innova in Star plastic."));
        items.add(new ItemData("555555", new Amount(200), 1.25, "Firebird", "Discgolf disc by Innova in Champion plastic."));
        items.add(new ItemData("666666", new Amount(100), 1.25, "AirMax2020T", "100% Tungsten sneakers with high comfort, high durability and high density."));
    }

    /**
     * Gets an item from the InventoryRegistry. If no item with the specified
     * item identifier exists, an exception is thrown.
     *
     * @param itemIdentifier The item identifier of the item.
     * @return DTO with information about an item with a corresponding item
     * identifier.
     * @throws InvalidItemIdentifierException if the item does not exist.
     */
    public ItemDTO getItemFromInventoryRegistry(String itemIdentifier)
            throws InvalidItemIdentifierException, DatabaseFailureException {
        if(itemIdentifier.equals("999999")) {
            throw new DatabaseFailureException("Could not connect to database");
        }
        for (ItemData item : items) {
            if (item.itemIdentifier.equals(itemIdentifier)) {
                return new ItemDTO(item.itemIdentifier, item.price, item.taxRate,
                        item.itemName, item.itemDescription);
            }
        }
        throw new InvalidItemIdentifierException(itemIdentifier);
    }

    /**
     * Inventory holds objects of the class <code>ItemData</code> in order to
     * mimic a store with primitive data. This solution is chosen since there is
     * no real database to call. The class shall not be used anywhere outside
     * InventoryRegistry. It follows the same reasoning as the course
     * literature, page 114-115.
     */
    private static class ItemData {

        private String itemIdentifier;
        private Amount price;
        private double taxRate;
        private String itemName;
        private String itemDescription;

        public ItemData(String itemIdentifier, Amount price, double taxRate,
                String itemName, String itemDescription) {
            this.itemIdentifier = itemIdentifier;
            this.price = price;
            this.taxRate = taxRate;
            this.itemName = itemName;
            this.itemDescription = itemDescription;
        }
    }

    /**
     * Updates the InventoryRegistry. Dummy method acting as a placeholder since
     * there is no inventory registry to update.
     *
     * @param completedSale The sale that is completed and paid for by the
     * customer.
     */
    public void updateInventoryRegistry(Sale completedSale) {
    }
}

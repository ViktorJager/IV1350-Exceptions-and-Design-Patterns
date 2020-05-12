package se.kth.iv1350.retailpos.integration;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import se.kth.iv1350.retailpos.model.Amount;

public class InventoryRegistryTest {

    @Test
    public void testGetItemFromRegistryExisting() throws ItemIdNotFoundException {
        ItemDTO expResult = new ItemDTO("555555", new Amount(200), 1.25, "Firebird", "Discgolf disc by Innova in Champion plastic.");
        InventoryRegistry instance = new InventoryRegistry();
        try {
            ItemDTO result = instance.getItemFromInventoryRegistry("555555");
            assertEquals(expResult, result, "Existing item was not found");
        } catch (ItemIdNotFoundException exc) {
            fail("Existing item was not found");
            exc.printStackTrace();
        }
    }

    @Test
    public void testGetItemFromRegistryNotExisting() throws ItemIdNotFoundException {
        InventoryRegistry instance = new InventoryRegistry();
        String nonExistingItemId = "888888";

        try {
            ItemDTO result = instance.getItemFromInventoryRegistry(nonExistingItemId);
            fail("Non existing item was found");
        } catch (ItemIdNotFoundException exc) {
            assertTrue(exc.getMessage().contains(nonExistingItemId), "Exception message "
                    + "does not contain specified item identifier '"
                    + nonExistingItemId + "'");
        }
    }

    /**
     * Since there is no database to test, a hard coded item identifier '999999'
     * should return a DatabaseFailureException instead.
     */
    @Test
    public void testGetItemFromRegistryNoDatabaseConnection() throws
            DatabaseFailureException, ItemIdNotFoundException {
        InventoryRegistry instance = new InventoryRegistry();
        String dbFailureId = "999999";

        try {
            ItemDTO result = instance.getItemFromInventoryRegistry(dbFailureId);
            fail("DatabaseFailureException was not thrown correctly.");
        } catch (DatabaseFailureException exc) {
            assertTrue(exc.getMessage().contains("Unable"));
            assertTrue(exc.getMessage().contains("connection"));
        }
    }

}

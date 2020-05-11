package se.kth.iv1350.retailpos.integration;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import se.kth.iv1350.retailpos.model.Amount;

/**
 * TODO: Add more tests
 */
public class InventoryRegistryTest {

    @Test
    public void testGetItemFromRegistryExisting() throws ItemIdNotFoundException {
        ItemDTO existingItem = new ItemDTO("555555", new Amount(200), 1.25, "Firebird", "Discgolf disc by Innova in Champion plastic.");
        InventoryRegistry instance = new InventoryRegistry();
        ItemDTO expResult = existingItem;
        ItemDTO result = instance.getItemFromInventoryRegistry("555555");
        assertEquals(expResult, result, "Existing item was not found");
    }
    
    @Test
    public void testGetItemFromRegistryNotExisting() throws ItemIdNotFoundException {
        ItemDTO expResult = null;
        InventoryRegistry instance = new InventoryRegistry();
        ItemDTO result = instance.getItemFromInventoryRegistry("888888");
        assertEquals(expResult, result, "Non-existing item was found");
    }
}

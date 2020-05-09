package se.kth.iv1350.retailpos.integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;


public class ExternalSystemsCreatorTest {
    ExternalSystemsCreator instance;
    
    @BeforeEach
    public void createInstanceOfExternalSystemsCreator() {
        instance = new ExternalSystemsCreator();
    }
    
    @AfterEach
    public void destoryInstanceOfExternalSystemsCreator() {
        instance = null;
    }
    
    @Test
    public void testCreateInventoryRegistry() {
        InventoryRegistry result = instance.getInventoryRegistry();
        assertTrue(result instanceof InventoryRegistry, "ExternalSystemsCreator"
                + "did not create an instance of InventoryRegistry");
    }

    @Test
    public void testCreateAccountingSystem() {
        AccountingSystem result = instance.getAccountingSystem();
        assertTrue(result instanceof AccountingSystem, "ExternalSystemsCreator"
                + "did not create an instance of AccountingSystem");
    }
    
}

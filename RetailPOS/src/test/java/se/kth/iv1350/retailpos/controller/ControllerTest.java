/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.iv1350.retailpos.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import se.kth.iv1350.retailpos.integration.ExternalSystemsCreator;
import se.kth.iv1350.retailpos.integration.ItemDTO;
import se.kth.iv1350.retailpos.integration.ItemIdNotFoundException;
import se.kth.iv1350.retailpos.integration.Printer;
import se.kth.iv1350.retailpos.model.Amount;
import se.kth.iv1350.retailpos.model.RunningTotalDTO;

public class ControllerTest {

    private Controller contr;
    private ExternalSystemsCreator creator;

    @BeforeEach
    public void setUp() {
        creator = new ExternalSystemsCreator();
        Printer printer = new Printer();
        contr = new Controller(creator, printer);
        contr.startSale();
    }

    @AfterEach
    public void tearDown() {
        contr = null;
        creator = null;
    }

    @Test
    public void testRegisterItem() throws
            ItemIdNotFoundException, OperationFailedException {

        String expItemIdentifier = "111111";
        Amount expPrice = new Amount(250);
        Amount expRunningTotal = new Amount(250);
        double expTaxRate = 1.25;
        String expItemName = "TeeShirt";
        String expItemDescription = "Stylish t-shirt in cotton.";
        ItemDTO expItemDTO = new ItemDTO(expItemIdentifier, expPrice, expTaxRate,
                expItemName, expItemDescription);

        try {
            RunningTotalDTO result = contr.registerItem(expItemIdentifier);
            assertEquals(result.getPrice(), expPrice);
            assertEquals(result.getItemName(), expItemName);
            assertEquals(result.getRunningTotal(), expRunningTotal);
        } catch (Exception exc) {
            fail("Could not register valid item");
        }
    }

    @Test
    public void testRegisterNotExistingItem() throws
            ItemIdNotFoundException, OperationFailedException {

        String itemIdentifier = "123456";
        try {
            RunningTotalDTO result = contr.registerItem(itemIdentifier);
            fail("Could register non-existing item");
        } catch (ItemIdNotFoundException exc) {
            assertTrue(exc.getInvalidItemIdentifier().contains(itemIdentifier));
        } catch (OperationFailedException exc) {
            exc.printStackTrace();
        }
    }

    @Test
    public void testRegisterItemNoDBConnection() throws
            ItemIdNotFoundException, OperationFailedException {

        // ID to force a No DB Connection Exception
        String itemIdentifier = "999999";

        try {
            RunningTotalDTO result = contr.registerItem(itemIdentifier);
            fail("Could register item while no connection to database");
        } catch (OperationFailedException exc) {
            assertTrue(exc.getMessage().contains("Database connection error"));
        }
    }

}

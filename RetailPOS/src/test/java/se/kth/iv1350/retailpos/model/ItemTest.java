/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.iv1350.retailpos.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import se.kth.iv1350.retailpos.integration.ItemDTO;

/**
 *
 * @author Viktor
 */
public class ItemTest {
    @Test
    public void testIncreaseQuantity() {
        String itemIdentifier = "123456";
        Amount price = new Amount(100);
        double taxRate = 1.25;
        String itemName = "IKEA Pants";
        String itemDescription = "100% Polyester, yellow and blue patented square-pattern.";
        ItemDTO itemDTO = new ItemDTO(itemIdentifier, price, taxRate, itemName, itemDescription);
        Item instance = new Item(itemDTO);
        
        instance.increaseQuantity();
        int expResult = 2;
        int result = instance.getQuantity();
        assertEquals(expResult, result, "increaseQuantity does not increment by one");
    }

    @Test
    public void testGetItemIdentifier() {
        String itemIdentifier = "123456";
        Amount price = new Amount(100);
        double taxRate = 1.25;
        String itemName = "IKEA Pants";
        String itemDescription = "100% Polyester, yellow and blue patented square-pattern.";
        ItemDTO itemDTO = new ItemDTO(itemIdentifier, price, taxRate, itemName, itemDescription);
        Item instance = new Item(itemDTO);
        
        String expResult = itemIdentifier;
        String result = instance.getItemIdentifier();
        assertEquals(expResult, result, "Incorrect item identifier");
    }
    
    @Test
    public void testEquals() {
        String itemIdentifier = "123456";
        Amount price = new Amount(100);
        double taxRate = 1.25;
        String itemName = "IKEA Pants";
        String itemDescription = "100% Polyester, yellow and blue patented square-pattern.";
        ItemDTO itemDTO = new ItemDTO(itemIdentifier, price, taxRate, itemName, itemDescription);
        Item instance = new Item(itemDTO);
        Item equalInstance = new Item(itemDTO);
        
        boolean expResult = true;
        boolean result = instance.equals(equalInstance);
        assertEquals(expResult, result, "Items with same states are not equal");
    }
}

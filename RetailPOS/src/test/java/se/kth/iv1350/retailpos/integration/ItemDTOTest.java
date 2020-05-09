package se.kth.iv1350.retailpos.integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import se.kth.iv1350.retailpos.model.Amount;


public class ItemDTOTest {

    @BeforeEach
    public void cleanContents() {
        String itemIdentifier = null;
        Amount price = null;
        double taxRate = Double.NaN;
        String itemName = null;
        String itemDescription = null;
        ItemDTO instance = null;
    }
    
    @AfterEach
    public void resetContents() {
        String itemIdentifier = null;
        Amount price = null;
        double taxRate = Double.NaN;
        String itemName = null;
        String itemDescription = null;
        ItemDTO instance = null;
    }
    
    @Test
    public void testToString() {
        String itemIdentifier = "123456";
        Amount price = new Amount(100);
        double taxRate = 1.25;
        String itemName = "IKEA Pants";
        String itemDescription = "100% Polyester, yellow and blue patented square-pattern.";
        ItemDTO instance = new ItemDTO(itemIdentifier, price, taxRate, itemName, itemDescription);
        String expResult = "itemIdentifier: " + itemIdentifier + ", price: " + price + ", taxRate: " + taxRate + ", itemName: " + itemName + ", itemDescription: " + itemDescription;
        String result = instance.toString();
        assertEquals(expResult, result, "Wrong string returned by toString");
    }
    
    @Test
    public void testToStringNullParam() {
        String itemIdentifier = null;
        Amount price = null;
        double taxRate = Double.NaN;
        String itemName = null;
        String itemDescription = null;
        ItemDTO instance = new ItemDTO(itemIdentifier, price, taxRate, itemName, itemDescription);
        String expResult = "itemIdentifier: " + itemIdentifier + ", price: " + price + ", taxRate: " + taxRate + ", itemName: " + itemName + ", itemDescription: " + itemDescription;
        String result = instance.toString();
        assertEquals(expResult, result, "Wrong string returned by toString");
    }
    
    @Test
    public void testToStringEmptyStringParam() {
        String itemIdentifier = "";
        Amount price = new Amount(100);
        double taxRate = 1.25;
        String itemName = "";
        String itemDescription = "";
        ItemDTO instance = new ItemDTO(itemIdentifier, price, taxRate, itemName, itemDescription);
        String expResult = "itemIdentifier: " + itemIdentifier + ", price: " + price + ", taxRate: " + taxRate + ", itemName: " + itemName + ", itemDescription: " + itemDescription;
        String result = instance.toString();
        assertEquals(expResult, result, "Wrong string returned by toString");
    }
    
    @Test
    public void testEquals() {
        String itemIdentifier = "123456";
        Amount price = new Amount(100);
        double taxRate = 1.25;
        String itemName = "IKEA Pants";
        String itemDescription = "100% Polyester, yellow and blue patented square-pattern.";
        ItemDTO instance = new ItemDTO(itemIdentifier, price, taxRate, itemName, itemDescription);       
        ItemDTO equalInstance = new ItemDTO(itemIdentifier, price, taxRate, itemName, itemDescription); 
        boolean expResult = true;
        boolean result = instance.equals(equalInstance);
        assertEquals(expResult, result, "ItemDTO instances with same states are not equal.");
    }
    
    @Test
    public void testNotEqualsItemIdentifier() {
        String itemIdentifier = "123456";
        String otherItemIdentifier = "654321";
        Amount price = new Amount(100);
        double taxRate = 1.25;
        String itemName = "IKEA Pants";
        String itemDescription = "100% Polyester, yellow and blue patented square-pattern.";
        ItemDTO instance = new ItemDTO(itemIdentifier, price, taxRate, itemName, itemDescription);       
        ItemDTO notEqualInstance = new ItemDTO(otherItemIdentifier, price, taxRate, itemName, itemDescription); 
        boolean expResult = false;
        boolean result = instance.equals(notEqualInstance);
        assertEquals(expResult, result, "ItemDTO instances with different states are equal.");
    }
    
    @Test
    public void testNotEqualsPrice() {
        String itemIdentifier = "123456";
        Amount price = new Amount(100);
        Amount otherPrice = new Amount(600);
        double taxRate = 1.25;
        String itemName = "IKEA Pants";
        String itemDescription = "100% Polyester, yellow and blue patented square-pattern.";
        ItemDTO instance = new ItemDTO(itemIdentifier, price, taxRate, itemName, itemDescription);       
        ItemDTO notEqualInstance = new ItemDTO(itemIdentifier, otherPrice, taxRate, itemName, itemDescription); 
        boolean expResult = false;
        boolean result = instance.equals(notEqualInstance);
        assertEquals(expResult, result, "ItemDTO instances with different states are equal.");
    }
    
    @Test
    public void testNotEqualsTaxRate() {
        String itemIdentifier = "123456";
        Amount price = new Amount(100);
        double taxRate = 1.25;
        double otherTaxRate = 1.06;
        String itemName = "IKEA Pants";
        String itemDescription = "100% Polyester, yellow and blue patented square-pattern.";
        ItemDTO instance = new ItemDTO(itemIdentifier, price, taxRate, itemName, itemDescription);       
        ItemDTO notEqualInstance = new ItemDTO(itemIdentifier, price, otherTaxRate, itemName, itemDescription); 
        boolean expResult = false;
        boolean result = instance.equals(notEqualInstance);
        assertEquals(expResult, result, "ItemDTO instances with different states are equal.");
    }
    
    @Test
    public void testNotEqualsItemName() {
        String itemIdentifier = "123456";
        Amount price = new Amount(100);
        double taxRate = 1.25;
        String itemName = "IKEA Pants";
        String otherItemName = "ICA Pants";
        String itemDescription = "100% Polyester, yellow and blue patented square-pattern.";
        ItemDTO instance = new ItemDTO(itemIdentifier, price, taxRate, itemName, itemDescription);       
        ItemDTO notEqualInstance = new ItemDTO(itemIdentifier, price, taxRate, otherItemName, itemDescription); 
        boolean expResult = false;
        boolean result = instance.equals(notEqualInstance);
        assertEquals(expResult, result, "ItemDTO instances with different states are equal.");
    }
    
    @Test
    public void testNotEqualsItemDescription() {
        String itemIdentifier = "123456";
        Amount price = new Amount(100);
        double taxRate = 1.25;
        String itemName = "IKEA Pants";
        String itemDescription = "100% Polyester, yellow and blue patented square-pattern.";
        String otherItemDescription = "Red striped basic pants.";
        ItemDTO instance = new ItemDTO(itemIdentifier, price, taxRate, itemName, itemDescription);       
        ItemDTO notEqualInstance = new ItemDTO(itemIdentifier, price, taxRate, itemName, otherItemDescription); 
        boolean expResult = false;
        boolean result = instance.equals(notEqualInstance);
        assertEquals(expResult, result, "ItemDTO instances with different states are equal.");
    }
}

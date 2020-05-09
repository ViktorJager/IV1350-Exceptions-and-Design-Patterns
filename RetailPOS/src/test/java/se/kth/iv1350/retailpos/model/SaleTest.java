/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.iv1350.retailpos.model;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import se.kth.iv1350.retailpos.integration.ItemDTO;
import se.kth.iv1350.retailpos.integration.Printer;

/**
 *
 * @author Viktor
 */
public class SaleTest {
    private ByteArrayOutputStream outputContent;
    private PrintStream originalSystemOut;
    CashRegister cashRegister;
    ItemDTO itemDTO;
    
    
    @BeforeEach
    public void setUp() {       
        cashRegister = new CashRegister();
        
        String itemIdentifier = "123456";
        Amount price = new Amount(100);
        double taxRate = 1.25;
        String itemName = "IKEA Pants";
        String itemDescription = "100% Polyester, yellow and blue patented square-pattern.";
        itemDTO = new ItemDTO(itemIdentifier, price, taxRate, itemName, itemDescription);
        
        
        originalSystemOut = System.out;
        outputContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputContent));
    }
    
    @AfterEach
    public void cleanUp() {
        itemDTO = null;
        cashRegister = null;
        
        outputContent = null;
        System.setOut(originalSystemOut);
    }
    
    @Test
    public void testRegisterItemInSale() {
        Sale sale = new Sale();
        sale.registerItemInSale(itemDTO);
        
        List<Item> saleList = sale.getItemsInSale();
        String result = saleList.get(0).getItemIdentifier();
        String expResult = "123456";
        
        assertEquals(expResult, result, "Registered item missing from sale");
    }

    @Test
    public void testAmountTaxTotal() {
        Sale instance = new Sale();
        instance.registerItemInSale(itemDTO);
        instance.registerItemInSale(itemDTO);
        
        Amount expResult = new Amount(50);
        Amount result = instance.amountTaxTotal();
        assertEquals(expResult, result, "Total amount tax is not equal to expected value");
    }
    
    @Test
    public void testPrintReceipt() {
        Sale sale = new Sale();
        Printer printer = new Printer();
        sale.registerItemInSale(itemDTO);
        sale.registerItemInSale(itemDTO);
        
        CashPayment cashPayment = new CashPayment(new Amount(600));
        sale.pay(cashPayment);
        cashRegister.addPayment(cashPayment);
        sale.printReceipt(printer);
        
        String result = outputContent.toString();
        
        String regItem = "IKEA Pants";
        String paidAmt = "600";
        
        assertTrue(result.contains(regItem), "Registered item missing from receipt");
        assertTrue(result.contains(paidAmt), "Paid amount is wrong on receipt");
    }
}

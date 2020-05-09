/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.iv1350.retailpos.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import se.kth.iv1350.retailpos.integration.ItemDTO;

/**
 *
 * @author Viktor
 */
public class ReceiptTest {
    Sale sale = new Sale();
    CashRegister cashRegister = new CashRegister();
        
    @BeforeEach
    public void setUp() {
        String itemIdentifier = "123456";
        Amount price = new Amount(100);
        double taxRate = 1.25;
        String itemName = "IKEA Pants";
        String itemDescription = "100% Polyester, yellow and blue patented square-pattern.";
        ItemDTO itemDTO = new ItemDTO(itemIdentifier, price, taxRate, itemName, itemDescription);

        String otheritemIdentifier = "999999";
        Amount otherprice = new Amount(100);
        double othertaxRate = 1.25;
        String otheritemName = "Fikon";
        String otheritemDescription = "Fruity fruit";
        ItemDTO otherItemDTO = new ItemDTO(otheritemIdentifier, otherprice, othertaxRate, otheritemName, otheritemDescription);

        sale.registerItemInSale(itemDTO);
        sale.registerItemInSale(otherItemDTO);
        
        CashPayment cashPayment = new CashPayment(new Amount(600));
        sale.pay(cashPayment);
        cashRegister.addPayment(cashPayment);
    }

    @AfterEach
    public void tearDown() {
        sale = new Sale();
        cashRegister = new CashRegister();
    }
    
    @Test
    public void testReceiptStringAddedITems() {
        Receipt receipt = new Receipt(sale);
        String result = receipt.createReceiptString();
        
        List<Item> saleList = sale.getItemsInSale();
        
        String firstItemName = saleList.get(0).getItemName();
        String secondItemName = saleList.get(1).getItemName();
        
        assertTrue(result.contains(firstItemName), "First item missing on receipt string");
        assertTrue(result.contains(secondItemName), "Second item missing on receipt string");
    }
    
    @Test
    public void testReceiptTimeDate() {
        Receipt receipt = new Receipt(sale);
        String result = receipt.createReceiptString();
        
        String timeColonPattern = "HH:mm:ss";
        DateTimeFormatter timeColonFormatter = DateTimeFormatter.ofPattern(timeColonPattern);
        LocalDateTime saleTime = LocalDateTime.now();
        
        String expSaleDate = (DateTimeFormatter.ISO_DATE).format(saleTime);
        String expSaleTime = timeColonFormatter.format(saleTime);
        
        assertTrue(result.contains(expSaleDate), "Date is wrong on receipt");
        assertTrue(result.contains(expSaleTime), "Times is wrong on receipt");
    }
    
    @Test
    public void testReceiptStoreInfo() {
        Receipt receipt = new Receipt(sale);
        String result = receipt.createReceiptString();
        
        StoreInfo store = new StoreInfo();
        
        String storeName = store.getStoreName();
        String storeAddr = store.getStoreAddress();
        
        assertTrue(result.contains(storeName), "Receipt does not contain store name");
        assertTrue(result.contains(storeAddr), "Receipt does not contain store address");
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.iv1350.retailpos.integration;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import se.kth.iv1350.retailpos.controller.Controller;
import se.kth.iv1350.retailpos.model.Amount;
import se.kth.iv1350.retailpos.model.Receipt;
import se.kth.iv1350.retailpos.model.RunningTotalDTO;
import se.kth.iv1350.retailpos.view.View;


public class PrinterTest {
    private ByteArrayOutputStream outputContent;
    private PrintStream originalSystemOut;
    
    @BeforeEach
    void setUp() {
        originalSystemOut = System.out;
        outputContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputContent));
    }
    
    @AfterEach
    void tearDown() {
        outputContent = null;
        System.setOut(originalSystemOut);
    }
    
    @Test
    public void testPrintReceipt() {
        ExternalSystemsCreator creator = new ExternalSystemsCreator();
        Printer printer = new Printer();
        Controller contr = new Controller(creator, printer);
        View view = new View(contr);
        RunningTotalDTO currSaleInfo;
        
        String timeColonPattern = "HH:mm:ss";
        DateTimeFormatter timeColonFormatter = DateTimeFormatter.ofPattern(timeColonPattern);
        LocalDateTime saleTime = LocalDateTime.now();
        
        String expSaleDate = (DateTimeFormatter.ISO_DATE).format(saleTime);
        String expSaleTime = timeColonFormatter.format(saleTime);
        
        contr.startSale();
        
        currSaleInfo = contr.registerItem("555555");
        currSaleInfo = contr.registerItem("555555");
        currSaleInfo = contr.registerItem("111111");
        currSaleInfo = contr.registerItem("222222");
        currSaleInfo = contr.registerItem("333333");
        currSaleInfo = contr.registerItem("444444");
        currSaleInfo = contr.registerItem("666666");
        currSaleInfo = contr.registerItem("666666");
        
        Amount totalPrice = contr.endSale();
        contr.pay(new Amount(1600));
        
        String addedItem = "Firebird";
        String paidAmt = "1600";
        
        String result = outputContent.toString();
        
        assertTrue(result.contains(addedItem), "Registered item missing from receipt");
        assertTrue(result.contains(paidAmt), "Paid amount is wrong on receipt");
        assertTrue(result.contains(expSaleDate), "Date is wrong on receipt");
        assertTrue(result.contains(expSaleTime), "Times is wrong on receipt");
    }
    
}

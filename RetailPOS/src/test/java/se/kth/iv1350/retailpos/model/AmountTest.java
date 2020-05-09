package se.kth.iv1350.retailpos.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class AmountTest {
    private Amount amtNoArgConstr;
    private Amount amtWithAmtThree;
    
    @BeforeEach
    public void setUp() {
        amtNoArgConstr = new Amount();
        amtWithAmtThree = new Amount(3);
    }
    
    @AfterEach
    public void tearDown() {
        amtNoArgConstr = null;
        amtWithAmtThree = null;
    }

    @Test
    public void testNotEqualsNull() {
        Object other = null;
        boolean expResult = false;
        boolean result = amtNoArgConstr.equals(other);
        assertEquals(expResult, result, "Amount instance equal to null.");
    }
    
    @Test
    public void testNotEqualsJavaLangObject() {
        Object other = new Object();
        boolean expResult = false;
        boolean result = amtNoArgConstr.equals(other);
        assertEquals(expResult, result, "Amount instance equal to java.lang.Object instance.");
    }

    @Test
    public void testNotEqualUsingNoArgConstr() {
        boolean expResult = false;
        boolean result = amtNoArgConstr.equals(amtWithAmtThree);
        assertEquals(expResult, result, "Amount instances with different states are equal.");
    }

    @Test
    public void testNotEqual() {
        double amountOfOther = 2;
        Amount other = new Amount(amountOfOther);
        boolean expResult = false;
        boolean result = amtWithAmtThree.equals(other);
        assertEquals(expResult, result, "Amount instances with different states are equal.");
    }

    @Test
    public void testEqual() {
        double amountOfOther = 3;
        Amount other = new Amount(amountOfOther);
        boolean expResult = true;
        boolean result = amtWithAmtThree.equals(other);
        assertEquals(expResult, result, "Amount instances with same states are not equal.");
    }

    @Test
    public void testEqualNoArgConstr() {
        double amountOfOther = 0;
        Amount other = new Amount(amountOfOther);
        boolean expResult = true;
        boolean result = amtNoArgConstr.equals(other);
        assertEquals(expResult, result, "Amount instances with same states are not equal.");
    }

    @Test
    public void testMinus() {
        double amountOfOperand1 = 10;
        double amountOfOperand2 = 3;
        Amount operand1 = new Amount(amountOfOperand1);
        Amount operand2 = new Amount(amountOfOperand2);
        Amount expResult = new Amount(amountOfOperand1 - amountOfOperand2);
        Amount result = operand1.minus(operand2);
        assertEquals(expResult, result, "Wrong subtraction result");
    }

    @Test
    public void testMinusNegResult() {
        double amountOfOperand1 = 3;
        double amountOfOperand2 = 10;
        Amount operand1 = new Amount(amountOfOperand1);
        Amount operand2 = new Amount(amountOfOperand2);
        Amount expResult = new Amount(amountOfOperand1 - amountOfOperand2);
        Amount result = operand1.minus(operand2);
        assertEquals(expResult, result, "Wrong subtraction result");
    }
    
    @Test
    public void testMinusZeroResultNegOperand() {
        double amountOfOperand1 = -3;
        double amountOfOperand2 = -3;
        Amount operand1 = new Amount(amountOfOperand1);
        Amount operand2 = new Amount(amountOfOperand2);
        Amount expResult = new Amount(amountOfOperand1 - amountOfOperand2);
        Amount result = operand1.minus(operand2);
        assertEquals(expResult, result, "Wrong subtraction result");
    }
    
    @Test
    public void testPlus() {
        double amountOfOperand1 = 10;
        double amountOfOperand2 = 3;
        Amount operand1 = new Amount(amountOfOperand1);
        Amount operand2 = new Amount(amountOfOperand2);
        Amount expResult = new Amount(amountOfOperand1 + amountOfOperand2);
        Amount result = operand1.plus(operand2);
        assertEquals(expResult, result, "Wrong addition result");
    }

    @Test
    public void testPlusNegResult() {
        double amountOfOperand1 = 3;
        double amountOfOperand2 = -10;
        Amount operand1 = new Amount(amountOfOperand1);
        Amount operand2 = new Amount(amountOfOperand2);
        Amount expResult = new Amount(amountOfOperand1 + amountOfOperand2);
        Amount result = operand1.plus(operand2);
        assertEquals(expResult, result, "Wrong addition result");
    }
    
    @Test
    public void testPlusZeroResultNegOperand() {
        double amountOfOperand1 = -3;
        double amountOfOperand2 = 3;
        Amount operand1 = new Amount(amountOfOperand1);
        Amount operand2 = new Amount(amountOfOperand2);
        Amount expResult = new Amount(amountOfOperand1 + amountOfOperand2);
        Amount result = operand1.plus(operand2);
        assertEquals(expResult, result, "Wrong addition result");
    }
    
    @Test
    public void testMultiply() {
        double amountOfOperand1 = 10;
        double amountOfOperand2 = 3;
        Amount operand1 = new Amount(amountOfOperand1);
        Amount operand2 = new Amount(amountOfOperand2);
        Amount expResult = new Amount(amountOfOperand1 * amountOfOperand2);
        Amount result = operand1.multiply(operand2);
        assertEquals(expResult, result, "Wrong multiplication result");
    }
    
    @Test
    public void testMultiplyDouble() {
        double amountOfOperand1 = 10;
        double amountOfOperand2 = 3;
        Amount operand1 = new Amount(amountOfOperand1);
        Amount expResult = new Amount(amountOfOperand1 * amountOfOperand2);
        Amount result = operand1.multiplyDouble(amountOfOperand2);
        assertEquals(expResult, result, "Wrong multiplication result");
    }

    @Test
    public void toStringPosAmt() {
        double representedAmt = 10;
        Amount amount = new Amount(representedAmt);
        String expResult = Double.toString(representedAmt);
        String result = amount.toString();
        assertEquals(expResult, result, "Wrong string returned by toString");
    }

    @Test
    public void toStringNegAmt() {
        double representedAmt = -10;
        Amount amount = new Amount(representedAmt);
        String expResult = Double.toString(representedAmt);
        String result = amount.toString();
        assertEquals(expResult, result, "Wrong string returned by toString");
    }

    @Test
    public void toStringZeroAmt() {
        double representedAmt = 0;
        Amount amount = new Amount(representedAmt);
        String expResult = Double.toString(representedAmt);
        String result = amount.toString();
        assertEquals(expResult, result, "Wrong string returned by toString");
    }
}

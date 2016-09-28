/*
 * TCSS 305 - Winter 2015
 * Assignment 2 - Shopping Cart
 */

package tests;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import model.Item;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for Item.
 * 
 * @author Damiene Stewart
 * @version 21 January 2015.
 */
public class ItemTest {

    /**
     * An instance of the Item class
     * for testing.
     */
    private Item myItem;
    
    /**
     * Sets environment for testing.
     */
    @Before
    public void setUp() {
        // Create a new Item for use in tests.
        myItem = new Item("Advil", new BigDecimal("4.99"), 5, 
                          new BigDecimal("20.00"));
    }

    /**
     * A test of the hashCode() method.
     */
    @Test
    public void testHashCode() {
        final Item testItem = new Item("Advil", new BigDecimal("4.99"), 5, 
                                       new BigDecimal("20.00"));
        
        assertEquals(testItem.hashCode(), myItem.hashCode());
    }

    /**
     * A test of the toString() method.
     */
    @Test
    public void testItemStringBigDecimalIntBigDecimal() {
        assertEquals("Advil, $4.99 (5 for $20.00)", myItem.toString());
    }

    /**
     * A test of the toString() method.
     */
    @Test
    public void testItemStringBigDecimal() {
        final Item testItem = new Item("Forty-Two", new BigDecimal("4.99"));
        
        assertEquals("Forty-Two, $4.99", testItem.toString());
    }
    
    /**
     * A test of the calculateItemTotal() method.
     */
    @Test
    public void testCalculateItemTotal() {
        assertEquals(new BigDecimal("44.99"), myItem.calculateItemTotal(11));
    }

    /**
     * A test of the calculateItemTotal() method when client orders
     * less than bulk quantity.
     */
    @Test
    public void testCalculateItemTotalOrderLessThanBulkQuantity() {
        assertEquals(new BigDecimal("19.96"), myItem.calculateItemTotal(4));
    }
    
    /**
     * A test of the calculateItemTotal() method when there is
     * no bulk price or bulk quantity.
     */
    @Test
    public void testCalculateItemTotalNoBulkPriceOrBulkQuantity() {
        final Item testItem = new Item("Forty-Two", new BigDecimal("4.99"));
        
        assertEquals(new BigDecimal("54.89"), testItem.calculateItemTotal(11));
    }
    
    /**
     * A test of the equals() method.
     */
    @Test
    public void testEqualsObject() {
        final Item testItem = new Item("Advil", new BigDecimal("4.99"), 5, 
                                       new BigDecimal("20.00"));
        
        assertEquals(true, myItem.equals(testItem));
        
        // Alternatively I believe it's possible to simply say:
        // assertEquals(testItem, myItem);
    }
    
    /**
     * A test of the equals() method where comparing the same
     * exact object, at same memory address.
     */
    @Test
    public void testEqualsObjectSameObjectAtSameMemoryAddress() {      
        assertEquals(true, myItem.equals(myItem));
    }
    
    /**
     * A test of the equals() method where comparing with
     * null.
     */
    @Test
    public void testEqualsObjectWithNull() {
        final Item testItem = null;
        
        assertEquals(false, myItem.equals(testItem));
    }
    
    /**
     * A test of the equals() method where comparing with
     * an object of the wrong class.
     */
    @Test
    public void testEqualsObjectWithWrongClass() {
        final Object testObject = new Object();
        
        assertEquals(false, myItem.equals(testObject));
    }
    
}

/*
 * TCSS 305 - Winter 2015
 * Assignment 2 - Shopping Cart
 */

package model;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Objects;

/**
 * Represents a particular item.
 * Encapsulates item information such as name, price,
 * and, if applicable, bulk quantity and bulk price.
 * 
 * @author Damiene Stewart
 * @version 16 January 2015.
 */
public final class Item {
    
    /**
     * Represents the name of the item.
     */
    private final String myName;
    
    /**
     * Represents the price of the item.
     */
    private final BigDecimal myPrice;
    
    /**
     * Represents the item's bulk quantity.
     */
    private final int myBulkQuantity;
    
    /**
     * Represents the item's bulk quantity
     * price.
     */
    private final BigDecimal myBulkPrice;
    
    /**
     * Name, price constructor.
     * 
     * @param theName is the item's name.
     * @param thePrice is the item's price.
     */
    public Item(final String theName, final BigDecimal thePrice) {
        this(theName, thePrice, 0, null);  
    }

    /**
     * Name, price, bulk options constructor.
     * Designated constructor.
     * 
     * @param theName is the item's name.
     * @param thePrice is the item's price.
     * @param theBulkQuantity is the bulk quantity represents
     * the quantity of items that the bulk price will apply to.
     * @param theBulkPrice is the bulk price applied to a
     * certain quantity of items.
     */
    public Item(final String theName, final BigDecimal thePrice, final int theBulkQuantity,
                final BigDecimal theBulkPrice) {
        
        myName = theName;
        myPrice = thePrice;
        
        myBulkQuantity = theBulkQuantity;
        myBulkPrice = theBulkPrice;

    }
    
    /**
     * Returns the price of a specified amount
     * of items.
     * 
     * @param theQuantity is the amount of items
     * that the price should be calculated for.
     * @return the total price of the specified
     * quantity of this item.
     */
    public BigDecimal calculateItemTotal(final int theQuantity) {
        // Keep track of the total.
        BigDecimal totalPrice = BigDecimal.ZERO;
        
        // How many regularly priced items?
        int nonBulkQuantity = theQuantity; 
        
        // If this item has a bulk quantity, and the client
        // is requesting more that quantity or more.
        if (myBulkQuantity > 0 && theQuantity >= myBulkQuantity) {
            
            // How many bulk priced items?
            final int bulkGroups = theQuantity / myBulkQuantity;
            
            nonBulkQuantity = theQuantity - bulkGroups * myBulkQuantity;
            
            totalPrice = totalPrice.add(
                                   myBulkPrice.multiply(
                                                    new BigDecimal(bulkGroups)
                                                    ));
        }
        
        totalPrice = totalPrice.add(myPrice.multiply(new BigDecimal(nonBulkQuantity)));
        
        return totalPrice;
    }

    /**
     * Returns the string representation of
     * this item.
     * 
     * Format: X, $xx.xx [(x for $xx.xx)].
     * 
     * @return this item's string representation.
     * @see java.lang.Object#toString().
     */
    @Override
    public String toString() {
        
        final NumberFormat format = NumberFormat.getCurrencyInstance();
        final StringBuilder itemString = new StringBuilder();
        
        itemString.append(myName);
        itemString.append(", ");
        itemString.append(format.format(myPrice));
        
        if (myBulkQuantity > 0) {
            itemString.append(" (");
            itemString.append(myBulkQuantity);
            itemString.append(" for ");
            itemString.append(format.format(myBulkPrice));
            itemString.append(')');
        }
        
        return itemString.toString();
    }


    /**
     * Overridden equals method.
     * Judges equality based on the item's
     * name, price, bulk price, and bulk quantity
     * being the same.
     * 
     * @see java.lang.Object#equals(java.lang.Object).
     */
    @Override
    public boolean equals(final Object theOther) {
        
        boolean isEqual = false;
        
        if (this == theOther) {
            isEqual = true;
        } else if (theOther != null && this.getClass() == theOther.getClass()) {
            final Item otherItem = (Item) theOther;
            
            isEqual = toString().equals(otherItem.toString());
        }
        
        return isEqual;
    }


    /**
     * Overridden hashcode method.
     * Generates hashcode based on the item's
     * name, price, bulk price, and bulk quantity.
     * 
     * @see java.lang.Object#hashCode().
     */
    @Override
    public int hashCode() {       
        return Objects.hash(myName, myPrice, myBulkQuantity, myBulkPrice);
    }

}

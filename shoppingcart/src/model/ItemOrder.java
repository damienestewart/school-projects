/*
 * TCSS 305 - Winter 2015
 * Assignment 2 - Shopping Cart
 */

package model;

import java.math.BigDecimal;

/**
 * Represents an order of a particular item.
 * Encapsulates order information such as the
 * particular item, and the quantity ordered.
 * 
 * @author Damiene Stewart
 * @version 16 January 2015.
 */
public final class ItemOrder {
    
    /**
     * Represents the particular item being
     * ordered.
     */
    private final Item myItem;
    
    /**
     * Represents the quantity of the item
     * being ordered.
     */
    private final int myQuantity;
    
    /**
     * Constructs a new ItemOrder.
     * Designated constructor.
     * 
     * @param theItem the assigned item.
     * @param theQuantity the assigned quantity.
     */
    public ItemOrder(final Item theItem, final int theQuantity) {
        myItem = theItem;
        myQuantity = theQuantity;
    }

    /**
     * Calculates this order's total cost.
     * 
     * @return the total cost of this order.
     */
    public BigDecimal calculateOrderTotal() {
        return myItem.calculateItemTotal(myQuantity);
    }

    /**
     * Queries for the item of this order.
     * 
     * @return the item of this order.
     */
    public Item getItem() {
        return myItem;
    }
    
    /**
     * Returns the string representation of
     * this order.
     * 
     * Format: Order: x# X, $xx.xx [(x for $xx.xx)].
     * 
     * @return this item's string representation.
     * @see java.lang.Object#toString().
     */
    @Override
    public String toString() {
        final StringBuilder string = new StringBuilder();
        string.append("Order: x");
        string.append(myQuantity);
        string.append(" (");
        string.append(myItem.toString());
        string.append(')');
        
        return string.toString();
    }

}

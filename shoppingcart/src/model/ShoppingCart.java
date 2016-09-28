/*
 * TCSS 305 - Winter 2015
 * Assignment 2 - Shopping Cart
 */

package model;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

/**
 * Represents a shopping cart.
 * Encapsulates order information, and
 * operations to be performed on those orders.
 * 
 * @author Damiene Stewart
 * @version 16 January 2015.
 */
public class ShoppingCart {
    /**
     * Represents orders in the shopping cart.
     */
    private List<ItemOrder> myOrders;
    
    /**
     * Represents client's membership status.
     */
    private boolean myMembershipStatus;

    /**
     * Constructs a new ShoppingCart.
     */
    public ShoppingCart() {
        myOrders = new LinkedList<ItemOrder>();
        myMembershipStatus = false;
    }

    /**
     * Adds a new order to current list of
     * orders.
     * 
     * @param theOrder the order to add.
     */
    public void add(final ItemOrder theOrder) {
        int indexOfItem = -1;
        
        int index = 0;
        for (final ItemOrder itemOrder : myOrders) {
            
            if (itemOrder.getItem().equals(theOrder.getItem())) {
                indexOfItem = index;
            }
            
            index++;
        }
        
        if (indexOfItem > -1) {
            myOrders.set(indexOfItem, theOrder);
        } else {
            myOrders.add(theOrder);
        }
    }

    /**
     * Sets the membership status.
     * 
     * @param theMembership the assigned membership status.
     */
    public void setMembership(final boolean theMembership) {
        myMembershipStatus = theMembership;
    }


    /**
     * Calculates the total cost of what is
     * 'in' the current shopping cart.
     * Accounts for 10% price reduction if client
     * is a member.
     * 
     * @return the total cost of orders.
     */
    public BigDecimal calculateTotal() {
        BigDecimal total = BigDecimal.ZERO;
        total = total.setScale(2, BigDecimal.ROUND_HALF_EVEN);
        
        for (final ItemOrder itemOrder : myOrders) {
            total = total.add(itemOrder.calculateOrderTotal());
        }
        
        if (total.compareTo(new BigDecimal("20.00")) > 0
                                        && myMembershipStatus) {
            total = total.multiply(new BigDecimal("0.90"));
        }
        
        return total;
    }
    
    /**
     * Removes all the orders in the list.
     */
    public void clear() {
        myOrders = new LinkedList<ItemOrder>();
    }
    
    /**
     * Returns the string representation of
     * the shopping cart.
     * 
     * Format: 
     * 
     * ItemOrder : total
     * total: $xx.xx
     * 
     * @return this item's string representation.
     * @see java.lang.Object#toString().
     */
    @Override
    public String toString() {
        final StringBuilder string = new StringBuilder(20);
        
        for (final ItemOrder order : myOrders) {
            string.append(order.toString());
            string.append(" : Total - ");
            string.append(order.calculateOrderTotal());
            string.append('\n');
        }
        
        string.append("Total: ");
        string.append(calculateTotal());
        
        return string.toString();
    }

}

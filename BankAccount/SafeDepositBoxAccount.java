/*
 * SafeDepositBox.java
 * Damiene Stewart
 * TCSS 143
 * David Schuessler
 * Programming Assignment 4
 * DUE: Tuesday, October 21, 2014 by 11:59 p.m.
 */

/**
 * This class provides the client with the ability
 * to create a safe deposit box account, along
 * with a few methods to manipulate the account. It
 * inherits from BankAccount.
 * 
 * @author damienestewart
 * @version 1.0
 */
public class SafeDepositBoxAccount implements NamedAccount {
	/**
	 * Stores the name of the safe deposit box's owner.
	 */
	private String myOwnerName;
	
	/**
	 * Constructs a new SafeDepositBoxAccount object.
	 * 
	 * @param nameOfHolder is the name of the box's owner.
	 */
	public SafeDepositBoxAccount(final String nameOfHolder) {
		myOwnerName = nameOfHolder;
	}
	
	/**
	 * Getter method for the account owner's name.
	 * @return the account owner's name.
	 */
	public String getAccountHolderName() {
		return myOwnerName;
	}
	
	/**
	 * Setter method for the account owner's name.
	 * @param newName the new name for the account owner.
	 */
	public void setAccountHolderName(final String newName) {
		if(newName == null || newName.length() == 0) {
			throw new IllegalArgumentException("Inappropriate new name for account holder.");
		}
		myOwnerName = newName;
	}
	
	/**
	 * Necessary toString() method as per specification.
	 * @return a string version of the object.
	 */
	public String toString() {
		return String.format(this.getClass().getName() + "[owner: %s]", 
				getAccountHolderName());
	}
}

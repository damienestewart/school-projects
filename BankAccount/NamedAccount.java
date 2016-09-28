/*
 * NamedAccount.java
 * Damiene Stewart
 * TCSS 143
 * David Schuessler
 * Programming Assignment 4
 * DUE: Tuesday, October 21, 2014 by 11:59 p.m.
 */

/**
 * Creates interface to control the name of
 * an account's onwer.
 * 
 * @author damienestewart
 * @version 1.0
 */
public interface NamedAccount {
	/**
	 * Getter method for the account owner's name.
	 * @return the account owner's name.
	 */
	String getAccountHolderName();
	
	/**
	 * Setter method for the account owner's name.
	 * @param newName is the new name for the account owner to.
	 */
	void setAccountHolderName(final String newName);
}
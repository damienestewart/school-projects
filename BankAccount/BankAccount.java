/*
 * BankAccount.java
 * Damiene Stewart
 * TCSS 143
 * David Schuessler
 * Programming Assignment 4
 * DUE: Tuesday, October 21, 2014 by 11:59 p.m.
 */

/**
 * This class provides the client with the ability
 * to create a bank account for a customer, along
 * with a few methods to manipulate the account.
 * 
 * @author damienestewart
 * @version 1.0
 */
public class BankAccount implements NamedAccount{
	/**
	 * Represents the number of months in a year.
	 * Used to calculate monthly interest.
	 */
	private final int MONTHS_IN_YEAR;
	
	/**
	 * Stores the name of the account's owner.
	 */
	private String myOwnerName;
	
	/**
	 * Keeps track of the account's balance.
	 */
	private double myAccountBalance;
	
	/**
	 * Stores the account's interest rate.
	 */
	private double myInterestRate;
	
	/**
	 * Keeps track of how often a withdrawal occurs.
	 */
	protected int myMonthlyWithdrawCount;
	
	/**
	 * Keeps track of service charges to be rendered at
	 * the end of the month.
	 */
	protected double myMonthlyServiceCharges;
	
	/**
	 * Constructs a BankAccount object, and initializes
	 * using the parameters.
	 * 
	 * @param nameOfOwner is the name of the account's owner.
	 * @param interestRate is the initial interest rate for the account.
	 */
	public BankAccount(final String nameOfOwner, final double interestRate) {
		// Set constant.
		MONTHS_IN_YEAR = 12;
		
		// Set private fields with self-documenting names.
		myOwnerName = nameOfOwner;
		myAccountBalance = 0.0;
		
		// Ensure that interest rate is valid.
		if(Double.compare(interestRate, 0.0) >= 0) {
			myInterestRate = interestRate;
		}
		else {
			myInterestRate = 0.0;
		}
		
		// Set protected fields.
		myMonthlyWithdrawCount = 0;
		myMonthlyServiceCharges = 0.0;
	}
	
	/**
	 * Returns the current balance of the account.
	 * @return the account's balance.
	 */
	public double getBalance() {
		return myAccountBalance;
	}
	
	/**
	 * Adds specified amount to the account balance.
	 * 
	 * @param amount is the amount to be added to the balance.
	 * @return true if successful, false if unsuccessful.
	 */
	public boolean processDeposit(final double amount) {
		boolean flag = false;
		
		// Add to account if amount is an acceptable value.
		if(Double.compare(amount, 0.0) > 0) {
			myAccountBalance += amount;
			flag = true;
		}
		return flag;
	}
	
	/**
	 * Withdraws specified amount from the account balance.
	 * This will fail if the amount is negative or greater than
	 * the current balance.
	 * 
	 * @param amount the amount to be withdrawn.
	 * @return true if successful, false if unsuccessful.
	 */
	public boolean processWithdrawal(final double amount) {
		boolean flag = false;
		
		// Check if amount is positive and check if amount is less than
		// or equal to the account balance.
		if(Double.compare(amount, 0.0) > 0 && Double.compare(amount, myAccountBalance) <= 0) {
			myAccountBalance -= amount;
			myMonthlyWithdrawCount++;
			flag = true;
		}
		return flag;
	}
	
	/**
	 * Calculates the interest amount for a given month.
	 * @return the interest amount.
	 */
	public double calculateInterest() {
		return ((double)myInterestRate/MONTHS_IN_YEAR) * getBalance();
	}
	
	/**
	 * Called by the client to handle end of month tasks, such as
	 * adding interest and subtracting service charges.
	 */
	public void performMonthlyProcess() {
		if((myAccountBalance - myMonthlyServiceCharges) >= 0) {
			myAccountBalance -= myMonthlyServiceCharges;
		}
		myAccountBalance += calculateInterest();
		
		myMonthlyServiceCharges = 0.0;
		myMonthlyWithdrawCount = 0;
	}
	
	// Methods from interface.
	/**
	 * Returns the name of the account owner.
	 * @return the owner's name.
	 */
	public String getAccountHolderName() {
		return myOwnerName;
	}
	
	/**
	 * Sets the name of the account owner.
	 * @param newName the new name for the account owner.
	 */
	public void setAccountHolderName(final String newName) {
		// Ensure that the newName parameter is of an acceptable value.
		if(newName == null || newName.length() == 0) {
			throw new IllegalArgumentException("Inappropriate new name for account holder.");
		}
		myOwnerName = newName;
	}
	
	// Extra methods.
	/**
	 * A getter for the monthly withdraw count. Set to
	 * protected to prevent direct client access while
	 * keeping accessibility to child classes.
	 * 
	 * @return the monthly withdraw count.
	 */
	protected int getMonthlyWithdrawCount() {
		return myMonthlyWithdrawCount;
	}
	
	/**
	 * A getter for the monthly service charges. Set to
	 * protected to prevent direct client access while
	 * keeping accessibility to child classes.
	 * 
	 * @return monthly service charges amount.
	 */
	protected double getMonthlyServiceCharges() {
		return myMonthlyServiceCharges;
	}
	
	/**
	 * A getter for the monthly service charges. Set to
	 * protected to prevent direct client access while
	 * keeping accessibility to child classes.
	 * 
	 * @return the interest rate for the account.
	 */
	protected double getInterestRate() {
		return myInterestRate;
	}
	
	/**
	 * Necessary toString() method as per specification.
	 * @return a string version of the object.
	 */
	public String toString() {
		return String.format(this.getClass().getName() + "[owner: %s, balance: %.2f, "
				+ "interest rate: %.2f, number of withdrawls this month: %d, "
				+ "service charges for this month: %.1f]", getAccountHolderName(),
				getBalance(), getInterestRate(), getMonthlyWithdrawCount(), 
				getMonthlyServiceCharges());
	}
}

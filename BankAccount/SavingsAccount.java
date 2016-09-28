/*
 * SavingsAccount.java
 * Damiene Stewart
 * TCSS 143
 * David Schuessler
 * Programming Assignment 4
 * DUE: Tuesday, October 21, 2014 by 11:59 p.m.
 */

/**
 * This class provides the client with the ability
 * to create a savings account for a customer, along
 * with a few methods to manipulate the account. It
 * inherits from BankAccount.
 * 
 * @author damienestewart
 * @version 1.0
 */
public class SavingsAccount extends BankAccount{
	/**
	 * Keeps track of active/inactive account
	 * status.
	 */
	private boolean myStatusIsActive;
	
	/**
	 * Constructs a SavingsAccount object, and initializes
	 * using the parameters.
	 * 
	 * @param nameOfOwner is the name of the account's owner.
	 * @param interestRate is the interest rate for the account.
	 */
	public SavingsAccount(final String nameOfOwner, final double interestRate) {
		super(nameOfOwner, interestRate); // Call super's constructor.
		myStatusIsActive = false; // Initialize account to inactive.
	}
	
	
	
	/**
	 * Adds specified amount to the account balance. Toggles
	 * active/inactive as per balance requirement.
	 * 
	 * @param amount is the amount to be added to the balance.
	 * @return true if successful, false if unsuccessful.
	 */
	public boolean processDeposit(final double amount) {
		boolean depositSuccessStatus = super.processDeposit(amount);
		
		if(!myStatusIsActive && Double.compare(super.getBalance(), 25.00) >= 0) {
			myStatusIsActive = true;
		}
		
		return depositSuccessStatus;
	}
	
	/**
	 * Withdraws specified amount from the account balance.
	 * This will fail if the amount is negative or greater than
	 * the current balance. Toggles active/inactive as per 
	 * balance requirement.
	 * 
	 * @param amount the amount to be withdrawn.
	 * @return true if successful, false if unsuccessful.
	 */
	public boolean processWithdrawal(final double amount) {
		boolean withdrawSuccessStatus = false;
		
		// If the account is active, proceed.
		if(myStatusIsActive) {	
			// If withdrawal was a success, proceed.
			if(withdrawSuccessStatus = super.processWithdrawal(amount)) {
				// Check to see how often withdrawals have been made and
				// charge appropriately.
				if(myMonthlyWithdrawCount >= 5) {
					myMonthlyServiceCharges += 1.00;
				}
				
				// Toggle active/inactive based on remaining balance.
				if(Double.compare(super.getBalance(), 25.00) < 0) {
					myStatusIsActive = false;
				}
			}
		}
		return withdrawSuccessStatus;
	}
	
	/**
	 * Called by the client to handle end of month tasks, such as
	 * adding interest and subtracting service charges. Toggles
	 * active/inactive based on balance.
	 */
	public void performMonthlyProcess() {
		super.performMonthlyProcess();
		
		if(Double.compare(super.getBalance(), 25.00) >= 0) {
			myStatusIsActive = true;
		}
		else {
			myStatusIsActive = false;
		}
	}
	
	// Extra method.
	/**
	 * Getter method for the account's active/inactive status.
	 * 
	 * @return true if active, false if inactive.
	 */
	private boolean getActiveStatus() {
		return myStatusIsActive;
	}
	
	/**
	 * Necessary toString() method as per specification.
	 * @return a string version of the object.
	 */
	public String toString() {
			return String.format(this.getClass().getName() + "[owner: %s, balance: %.2f, "
					+ "interest rate: %.2f, number of withdrawls this month: %d, "
					+ "service charges for this month: %.1f, myStatusIsActive: %b]", 
					getAccountHolderName(),
					getBalance(), getInterestRate(), getMonthlyWithdrawCount(), 
					getMonthlyServiceCharges(), getActiveStatus());
		}
}

/*
 * Wolf.java
 * Damiene Stewart
 * TCSS 143
 * David Schuessler
 * Programming Assignment 5
 * DUE: Tuesday, October 28, 2014 by 11:59 p.m.
 *
 */

/**
 * This class outlines the Wolf critter.
 * It defines behavior such as movement.
 * 
 * @author damienestewart
 * @version 1.0
 */
public class Wolf extends AbstractCritter {
	// Instance fields.
	/** Keeps track of the wolf's last direction. **/
	private int myDirection;
	
	/** Keeps track of travel duration in current direction **/
	private int myCount;
	
	/** Keeps track of whether or not a direction change occurs. **/
	private boolean myFirst;
	
	/** Controls how long wolf moves in a given direction. **/
	private int myTarget;
	
	/**
	 * Creates a new Wolf object.
	 */
	public Wolf() {
		// Construct the super class.
		super('W');
		
		// Initialize instance fields according to specification.
		myDirection = EAST;
		myCount = 0;
		myFirst = true;
		myTarget = 1;
	}
	
	/**
	 * Runs the logic deciding which direction the wolf
	 * should go.
	 * @param theInfo - unused parameter.
	 * @return integer representing the direction to travel.
	 */
	public int getMove(CritterInfo theInfo) {
		// Create local variable to hold direction.
		int lDirection = myDirection;
		
		// Increment the counter as per specification.
		myCount++;
		
		// Are the count and target distance equal? If so:
		if(myCount == myTarget) {
			// If myFirst is false.
			if(!myFirst) {
				// Increase the travel distance.
				myTarget++;
			}
			// Toggle myFirst and reset the counter.
			myFirst = !myFirst;
			myCount = 0;
			
			// Increment myDirection and make sure it does not overflow.
			if(++myDirection > 3) {
				myDirection = NORTH;
			}
		}
		return lDirection;
	}
}

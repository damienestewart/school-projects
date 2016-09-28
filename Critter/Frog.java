/*
 * Frog.java
 * Damiene Stewart
 * TCSS 143
 * David Schuessler
 * Programming Assignment 5
 * DUE: Tuesday, October 28, 2014 by 11:59 p.m.
 *
 */

/**
 * This class outlines the Frog critter.
 * It defines behavior such as movement.
 * 
 * @author damienestewart
 * @version 1.0
 */
public class Frog extends AbstractCritter {
	// Instance fields.
	/** Keep track of direction moved. **/
	private int myDirection;
	
	/** Keep track of distance moved. **/
	private int myCount;
	
	/**
	 * Constructs a new Frog object.
	 */
	public Frog() {
		// Construct super class.
		super('F');
		
		// Initialize class fields.
		myDirection = CENTER;
		myCount = 0;
	}
	
	/**
	 * Runs the logic deciding which direction the frog
	 * should go.
	 * @param theInfo allows the frog to know if a stone
	 * is in it's direction of travel so the frog can avoid it.
	 * @return integer representing the direction to travel.
	 */
	public int getMove(CritterInfo theInfo) {
		// Create local variables.
		double randomValue = getRandomObj().nextDouble();
		
		// Set to negative one in case there is a problem later.
		// The program will then return -1, which will be an error.
		int lDirection = -1;
		
		// Is this the first time we're moving in this direction?
		// If so:
		if(myCount == 0) {
			// Check to see if our random value meets the right
			// conditions.
			if(isLessThan(randomValue, .25)) {
				myDirection = NORTH;
			} else if (isLessThan(randomValue, 0.5) && isGreaterThan(randomValue, 0.24)) {
				myDirection = SOUTH;
			} else if(isLessThan(randomValue, 0.75) && isGreaterThan(randomValue, 0.74)) {
				myDirection = EAST;
			} else {
				myDirection = WEST;
			}
		}
		
		// Increment and reset if greater than 3.
		if(++myCount > 3) {
			myCount = 0;
		}
		
		// Make sure we're not going into a rock.
		if(theInfo.getNeighbor(myDirection) == 'S') {
			lDirection = CENTER;
		} else {
			lDirection = myDirection;
		}
		return lDirection;
	}
	
	/**
	 * Helper method to check if a double is less than another
	 * double.
	 * @param theFirst is the first value being compared.
	 * @param theSecond	is the second value being compared.
	 * @return True if the first is less than the second.
	 */
	private boolean isLessThan(double theFirst, double theSecond) {
		return Double.compare(theFirst, theSecond) < 0;
	}
	
	/**
	 * Helper method to check if a double is greater than another
	 * double.
	 * @param theFirst is the first value being compared.
	 * @param theSecond	is the second value being compared.
	 * @return True if the first is greater than the second.
	 */
	private boolean isGreaterThan(final double theFirst,
								  final double theSecond) {
		return Double.compare(theFirst, theSecond) > 0;
	}
}

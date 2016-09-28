/*
 * Mouse.java
 * Damiene Stewart
 * TCSS 143
 * David Schuessler
 * Programming Assignment 5
 * DUE: Tuesday, October 28, 2014 by 11:59 p.m.
 *
 */

/**
 * This class outlines the Mouse critter.
 * It defines behavior such as movement.
 * 
 * @author damienestewart
 * @version 1.0
 */
public class Mouse extends AbstractCritter {
	// Constants.
	
	/** 
	 * Constant representing the 4 cardinal
	 * points, North, South, East, and West.
	 */
	private final int CARDINAL_POINTS = 4;
	
	/** 
	 * Constant representing the maximum amount
	 * of time that a mouse travels along a
	 * diagonal.
	 */
	private final int MAX_DIAGONAL_TRAVEL = 50;
	
	// Instance variables.
	
	/** 
	 * Records the initial chosen at each interval. The
	 * diagonal path is calculated relative to this value.
	 * By toggling between this direction and 90 degrees away.
	 * For some amount of time.
	 */
	private int myDirection;
	
	/** 
	 * Tracks how long a mouse has been traveling along a
	 * diagonal path.
	 */
	private int myCount;
	
	/** 
	 * The amount of time a mouse should spend along a diagonal. 
	 */
	private int myTarget;
	
	/**
	 * Gives the mouse more flexibility in choosing direction.
	 * Without this value, the mouse would predictably choose to
	 * go in one set direction after it's initial direction. Eg.
	 * If initially North, it would always then go West, and repeat.
	 * This boolean value provides a way for the mouse to also choose to
	 * head East after going North.
	 */
	private boolean myDirectionChange;
	
	/**
	 * Constructs a new Mouse object.
	 */
	public Mouse() {
		// Construct the super class.
		super('M');
		
		// Initialize class fields.
		myDirection = getRandomObj().nextInt(CARDINAL_POINTS);
		myDirectionChange = getRandomObj().nextBoolean();
		myCount = 0;
		myTarget = getRandomObj().nextInt(MAX_DIAGONAL_TRAVEL);
	}
	
	/**
	 * Runs the logic deciding which direction the mouse
	 * should go.
	 * @param theInfo - unused parameter.
	 * @return integer representing the direction to travel.
	 */
	public int getMove(CritterInfo theInfo) {
		// Create a local direction variable. Helps 
		// to manipulate direction of travel without
		// affecting the myDirection class field.
		int lDirection = myDirection;
		
		// Is this the first time the mouse moved in
		// this direction on this interval? If so,
		// Skip this, and return If not:
		if(myCount != 0) {
			
			// Check the boolean value to decide which
			// direction we should travel in after the
			// initial direction.
			if(myDirectionChange) {
				
				// For even counts, return the initial
				// direction + 1 or else return the initial
				// direction. This gives the effect of
				// traveling the diagonal.
				if(!isEven(myCount)) {
					lDirection = myDirection + 1;
					
					// Since we're increasing, and the Cardinal Points
					// are increasing from 0 - 3, we want to make sure
					// we restart the cycle after 3.
					if(lDirection > EAST) {
						lDirection = NORTH;
					}
				}
			} else {
				
				// For even counts, return the initial
				// direction - 1 or else return the initial
				// direction. This gives the effect of
				// traveling the diagonal.
				if(!isEven(myCount)) {
					lDirection = myDirection - 1;
					
					// Since we're decreasing, and the Cardinal Points
					// are from 0 to 3, we want to make sure that the
					// cycle restarts from 3 if 0--.
					if(lDirection < NORTH) {
						lDirection = EAST;
					}
				}
			}
		}
		
		// Increment the count and then check to see if it is
		// greater than or equal to however long we are suppose
		// to travel along the diagonal.
		if(++myCount >= myTarget) {
			// Create new values for new diagonal.
			myDirection = getRandomObj().nextInt(CARDINAL_POINTS);
			myDirectionChange = getRandomObj().nextBoolean();
			myCount = 0;
			myTarget = getRandomObj().nextInt(MAX_DIAGONAL_TRAVEL);
		}
		return lDirection;
	}
	
	/**
	 * Helper method to evaluate whether a value
	 * is even.
	 * @param theValue is the value being checked.
	 * @return True/False based on evenness of theValue.
	 */
	private boolean isEven(final int theValue) {
		return (theValue % 2) == 0;
	}
}

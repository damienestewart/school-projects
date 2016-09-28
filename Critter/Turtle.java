/*
 * Turtle.java
 * Damiene Stewart
 * TCSS 143
 * David Schuessler
 * Programming Assignment 5
 * DUE: Tuesday, October 28, 2014 by 11:59 p.m.
 *
 */

/**
 * This class outlines the Turtle critter.
 * It defines behavior such as movement.
 * 
 * @author damienestewart
 * @version 1.0
 */
public class Turtle extends AbstractCritter {
	// Instance variables.
	/** Keep track of distance moved. **/
	private int myCount;
	
	/**
	 * Constructs a new Turtle object.
	 */
	public Turtle() {
		// Construct the super class.
		super('T');
		
		// Initialize instance variables.
		// Create random starting time.
		myCount = getRandomObj().nextInt(3);
	}
	
	/**
	 * Runs the logic deciding which direction the turtle
	 * should go.
	 * @param theInfo - unused parameter.
	 * @return integer representing the direction to travel.
	 */
	public int getMove(CritterInfo theInfo) {
		// Create local direction variable. Set to -1
		// in case something goes wrong, this will
		// result in an error and the program will stop.
		int lDirection = -1;
		
		// Both used to determine new direction:
		boolean flag = getRandomObj().nextBoolean();
		int direction = getRandomObj().nextInt(4);
		
		// If we have waited long enough (3) calls, then:
		if(myCount == 2) {
			// Use boolean and case statements to return
			// the direction as per specification.
			if(flag) {
				switch(direction) {
					case 0: lDirection = NORTH;
							break;
					case 1: lDirection = EAST;
							break;
					case 2: lDirection = SOUTH;
							break;
					case 3: lDirection = WEST;
							break;
					default: break;
				}
			} else {
				switch(direction) {
					case 0: lDirection = SOUTH;
							break;
					case 1: lDirection = WEST;
							break;
					case 2: lDirection = NORTH;
							break;
					case 3: lDirection = EAST;
							break;
					default: break;
				}
			}
			// Reset the counter;
			myCount = 0;
		} else {
			// If we have not waited long enough, don't move:
			lDirection = CENTER;
			myCount++;
		}		
		return lDirection;
	}
}

/*
 * Bat.java
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
public class Bat extends AbstractCritter {
	
	/**
	 * Constructs a new Bat object.
	 */
	public Bat() {
		// Construct the super class.
		super('B');
	}
	
	/**
	 * Runs the logic deciding which direction the bat
	 * should go.
	 * @param theInfo - unused parameter.
	 * @return integer representing the direction to travel.
	 */
	public int getMove(CritterInfo theInfo) {
		// Local variable to store random
		// cardinal direction.
		int randomValue = getRandomObj().nextInt(4);
		
		int returnValue = -1; // Set return value.
		
		// Switch statement to determine direction returned.
		switch (randomValue) {
			case 0: returnValue = NORTH;
					break;
			case 1: returnValue = WEST;
					break;
			case 2: returnValue = SOUTH;
					break;
			case 3: returnValue = EAST;
					break;
		}
		return returnValue;
	}
}
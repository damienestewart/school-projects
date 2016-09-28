/*
 * Stone.java
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
 * Or in this case, non-movement.
 * 
 * @author damienestewart
 * @version 1.0
 */
public class Stone extends AbstractCritter {
	/**
	 * Constructs a new Stone object.
	 */
	public Stone() {
		// Construct super class.
		super('S');
	}
	
	/**
	 * Runs the logic deciding which direction the Stone
	 * should go.
	 * @param theInfo - unused parameter.
	 * @return integer representing the direction to travel.
	 */
	public int getMove(CritterInfo theInfo) {
		return CENTER;
	}
}

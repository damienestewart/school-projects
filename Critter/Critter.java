/*
 * Critter.java
 * Damiene Stewart
 * TCSS 143
 * David Schuessler
 * Programming Assignment 5
 * DUE: Tuesday, October 28, 2014 by 11:59 p.m.
 *
 */

/**
 * This interface enforces the construction of getChar()
 * and getMove() methods in subclasses.
 * 
 * @author damienestewart
 * @version 1.0
 */
public interface Critter {
	// Constants.
	/** Represents the North direction. **/
	public final int NORTH = 0;
	
	/** Represents the West direction. **/
	public final int WEST = 1;
	
	/** Represents the South direction. **/
	public final int SOUTH = 2;
	
	/** Represents the East direction. **/
	public final int EAST = 3;
	
	/** Represents remaining still. **/
	public final int CENTER = 4;
	
	/**
	 * This method returns the character representing
	 * a particular 'critter'.
	 * @return the character representing a 'critter'.
	 */
	public char getChar();
	
	/**
	 * This method returns the direction that a
	 * critter has decided to move in.
	 * @param theInfo an interface already defined and 
	 * used by an inner class of the CritterModel class.
	 * @return an integer representing the movement direction.
	 */
	public int getMove(CritterInfo theInfo);
}

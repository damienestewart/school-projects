/*
 * AbstractCritter.java
 * Damiene Stewart
 * TCSS 143
 * David Schuessler
 * Programming Assignment 5
 * DUE: Tuesday, October 28, 2014 by 11:59 p.m.
 *
 */

import java.util.Random;

/**
 * This abstract class implements the Critter interface
 * and defines the getChar() method for all subclasses
 * to inherit.
 * 
 * @author damienestewart
 * @version 1.0
 */
public abstract class AbstractCritter implements Critter {
	// Instance fields.
	/** Character representing the 'critter'. **/
	private char myCritterLetter;
	
	/** Random object for subclasses to use. **/
	private Random myRandomObj;
	
	/**
	 * Constructor to initialize myCritterLetter.
	 * @param theChar is a character representing
	 * the particular kind of 'critter'.
	 */
	public AbstractCritter(final char theChar) {
		myCritterLetter = theChar;
		myRandomObj = new Random(); // Initialize random.
	}
	
	/**
	 * Returns the letter representing the critter
	 * to the calling program.
	 * @return the letter representing the critter.
	 */
	public char getChar() {
		return myCritterLetter;
	}
	
	/**
	 * Returns the random object.
	 * @return Random object.
	 */
	protected Random getRandomObj() {
		return myRandomObj;
	}
}

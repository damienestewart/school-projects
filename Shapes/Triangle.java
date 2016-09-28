/*
 * Triangle.java
 * Damiene Stewart
 * TCSS 143
 * David Schuessler
 * Programming Assignment 7
 * DUE: Tuesday, November 11, 2014 by 11:59 p.m.
 */

/**
 * Class that defines a Triangle.
 * @author damienestewart
 * @version 1.0
 */
public class Triangle extends Shape {
	// Class fields.
	/** Stores a triangle side. **/
	private double mySideA;
	
	/** Stores a triangle side. **/
	private double mySideB;
	
	/** Stores a triangle side. **/
	private double mySideC;
	
	/**
	 * Constructor for a Rectangle.
	 * @param theSideA a triangle side.
	 * @param theSideB a triangle side.
	 * @param theSideC a triangle side.
	 */
	public Triangle(final double theSideA, final double theSideB,
			final double theSideC) {
		// Check for valid arguments.
		if(theSideA <= 0.0 || theSideB <= 0.0 || theSideC <= 0.0) {
			throw new IllegalArgumentException("ERROR! Negative or "
					+ "0 value(s) can't be applied to a triangle side.");
		}
		if(theSideA >= theSideB + theSideC || 
				theSideB >= theSideA + theSideC || 
				theSideC >= theSideA + theSideB) {
			throw new IllegalArgumentException("ERROR! Not a Triangle. "
					+ "Longest side too long.");
		}
		
		mySideA = theSideA;
		mySideB = theSideB;
		mySideC = theSideC;
	}
	
	/**
	 * Setter method for a side.
	 * @param theSideA the new side length.
	 */
	public void setSideA(final double theSideA) {
		if(theSideA <= 0.0) {
			throw new IllegalArgumentException("ERROR! Negative or "
					+ "0 value can't be applied to a triangle side.");
		}
		if(theSideA >= mySideB + mySideC || 
				mySideB >= theSideA + mySideC || 
				mySideC >= theSideA + mySideB) {
			throw new IllegalArgumentException("ERROR! Not a Triangle. "
					+ "Longest side too long.");
		}
		
		mySideA = theSideA;
	}

	/**
	 * Setter method for a side.
	 * @param theSideB the new side length.
	 */
	public void setSideB(final double theSideB) {
		if(theSideB <= 0.0) {
			throw new IllegalArgumentException("ERROR! Negative or "
					+ "0 value can't be applied to a triangle side.");
		}
		if(mySideA >= theSideB + mySideC || 
				theSideB >= mySideA + mySideC || 
				mySideC >= mySideA + theSideB) {
			throw new IllegalArgumentException("ERROR! Not a Triangle. "
					+ "Longest side too long.");
		}
		
		mySideB = theSideB;
	}
	
	/**
	 * Setter method for a side.
	 * @param theSideC the new side length.
	 */
	public void setSideC(final double theSideC) {
		if(theSideC <= 0.0) {
			throw new IllegalArgumentException("ERROR! Negative or "
					+ "0 value can't be applied to a triangle side.");
		}
		if(mySideA >= mySideB + theSideC || 
				mySideB >= mySideA + theSideC || 
				theSideC >= mySideA + mySideB) {
			throw new IllegalArgumentException("ERROR! Not a Triangle. "
					+ "Longest side too long.");
		}
		
		mySideC = theSideC;
	}
	
	/**
	 * Returns the value of the shape's area.
	 * @return the calculated area.
	 */
	public double calculateArea() {
		double s = (mySideA + mySideB + mySideC) / 2;
		return Math.sqrt(s*(s-mySideA)*(s-mySideB)*(s-mySideC));
	}
	
	/**
	 * Returns the string version of 
	 * the class.
	 * @return the string representation of class.
	 */
	public String toString() {
		return String.format("Triangle [Side A: %.2f, Side B: %.2f "
				+ "Side C: %.2f] Area: %.2f", mySideA, mySideB,
				mySideC, calculateArea());
	}
}

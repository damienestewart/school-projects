/*
 * Rectangle.java
 * Damiene Stewart
 * TCSS 143
 * David Schuessler
 * Programming Assignment 7
 * DUE: Tuesday, November 11, 2014 by 11:59 p.m.
 */

/**
 * Class that defines a Rectangle.
 * @author damienestewart
 * @version 1.0
 */
public class Rectangle extends Shape {
	// Class fields.
	
	/** Stores rectangle length. **/
	private double myLength;
	/** Stores rectangle width. **/
	private double myWidth;
	
	/**
	 * Constructor for a Rectangle.
	 * @param theLength the length of the rectangle.
	 * @param theWidth the width of the rectangle.
	 */
	public Rectangle(final double theLength, final double theWidth) {
		if(theLength <= 0.0 || theWidth <= 0.0) {
			throw new IllegalArgumentException("ERROR! Negative or "
					+ "0 value(s) can't be applied to a rectangle.");
		}
		myLength = theLength;
		myWidth = theWidth;
	}
	
	/**
	 * Setter method for the length.
	 * @param theLength the new length.
	 */
	public void setLength(final double theLength) {
		if(theLength <= 0.0) {
			throw new IllegalArgumentException("ERROR! Negative or "
					+ "0 value(s) can't be applied to a rectangle.");
		}
		
		myLength = theLength;
	}
	
	/**
	 * Setter method for the width.
	 * @param theWidth the new width.
	 */
	public void setWidth(final double theWidth) {
		if(theWidth <= 0.0) {
			throw new IllegalArgumentException("ERROR! Negative or "
					+ "0 value(s) can't be applied to a rectangle.");
		}
		
		myLength = theWidth;
	}
	
	/**
	 * Returns the value of the shape's area.
	 * @return the calculated area.
	 */
	public double calculateArea() {
		return myLength * myWidth;
	}
	
	/**
	 * Returns the string version of 
	 * the class.
	 * @return the string representation of class.
	 */
	public String toString() {
		return String.format("Rectangle [Length: %.2f, Width: %.2f] "
				+ "Area: %.2f", myLength, myWidth, calculateArea());
	}
}

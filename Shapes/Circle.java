/*
 * Circle.java
 * Damiene Stewart
 * TCSS 143
 * David Schuessler
 * Programming Assignment 7
 * DUE: Tuesday, November 11, 2014 by 11:59 p.m.
 */

/**
 * Class that defines a Circle.
 * @author damienestewart
 * @version 1.0
 */
public class Circle extends Shape {
	// Class field.
	
	/** Contains the radius value. **/
	private double myRadius;
	
	/**
	 * Constructor for a Circle.
	 * @param theRadius radius to initialize with.
	 */
	public Circle(final double theRadius) {
		// Validity check.
		if(theRadius <= 0.0) {
			throw new IllegalArgumentException("ERROR! Negative or "
					+ "0 value can't be applied to a circle radius.");
		}	
		myRadius = theRadius;
	}
	
	/**
	 * Setter method for the radius.
	 * @param theRadius the new radius to set to.
	 */
	public void setRadius(final double theRadius) {
		if(theRadius <= 0.0) {
			throw new IllegalArgumentException();
		}
		myRadius = theRadius;
	}
	
	/**
	 * Returns the value of the shape's area.
	 * @return the calculated area.
	 */
	public double calculateArea() {
		return Math.PI * myRadius * myRadius;
	}
	
	/**
	 * Returns the string version of 
	 * the class.
	 * @return the string representation of class.
	 */
	public String toString() {
		return String.format("Circle [Radius: %.2f] Area: %.2f", 
				myRadius, calculateArea());
	}
}

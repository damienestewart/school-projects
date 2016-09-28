/*
 * Shape.java
 * Damiene Stewart
 * TCSS 143
 * David Schuessler
 * Programming Assignment 7
 * DUE: Tuesday, November 11, 2014 by 11:59 p.m.
 */

/**
 * This abstract class represents all shapes.
 * @author damienestewart
 * @version 1.0
 */
public abstract class Shape implements Comparable<Shape> {
	
	/**
	 * Returns the value of the shape's area.
	 * @return the calculated area.
	 */
	public abstract double calculateArea();
	
	/**
	 * Used to determine relative size of shape.
	 * @param theOther other shape to compare to.
	 * @return the integer indicating the relative
	 * order of the shape in terms of area.
	 */
	public int compareTo(final Shape theOther) {
		// Construct values as Double objects, use compareTo.
		return (new Double(calculateArea())).compareTo(
				new Double(theOther.calculateArea()));
	}
}

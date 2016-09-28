/* 
 * SizeComparator.java
 * Damiene Stewart
 * TCSS 143
 * David Schuessler
 * Programming Assignment 9.
 * DUE: Tuesday, November 25, 2014 by 11:59 p.m.
 */

import java.util.Comparator;

/**
 * Comparator for size-based comparison
 * of AnagramFamily objects.
 * @author damienestewart
 * @version 1.0
 */
public class SizeComparator implements Comparator<AnagramFamily> {

	/**
	 * Compares two AnagramFamily objects.
	 * @param theFirstFamily the first family in comparison.
	 * @param theSecondFamily the second family in comparison.
	 * @return an integer indicating the order of AnagramFamily objects.
	 */
	public int compare(AnagramFamily theFirstFamily, AnagramFamily theSecondFamily) {
		return theSecondFamily.getSize() - theFirstFamily.getSize(); 
	}
}

/* 
 * Word.java
 * Damiene Stewart
 * TCSS 143
 * David Schuessler
 * Programming Assignment 9.
 * DUE: Tuesday, November 25, 2014 by 11:59 p.m.
 */

import java.util.Arrays;

/**
 * Class outlines a Word object.
 * @author damienestewart
 * @version 1.0
 */
public class Word implements Comparable<Word> {
	/**
	 * Stores the normal form of the word.
	 */
	private final String NORMAL_FORM;
	
	/**
	 * Stores the canonical form of the word.
	 */
	private String CANONICAL_FORM;
	
	/**
	 * Constructor for a word object.
	 * Sets the myNormaForm and myCanonicalForm
	 * fields.
	 * @param theString used to set NORMAL_FORM
	 * and CANONICAL_FORM.
	 */
	public Word(final String theString) {
		NORMAL_FORM = theString;
		CANONICAL_FORM = canonicalize(theString);
	}
	
	/**
	 * Returns the normal form of the word.
	 * @return the normal form of the word.
	 */
	public String getNormalForm() {
		return NORMAL_FORM;
	}
	
	/**
	 * Returns the canonical form of the word.
	 * @return the canonical form of the word.
	 */
	public String getCanonicalForm() {
		return CANONICAL_FORM;
	}
	
	/**
	 * Compares this Word to an incoming Word object based
	 * on canonical form - alphabetically.
	 * @param theWord the other word to compare with.
	 * @return an integer representing the order of Word objects.
	 */
	public int compareTo(final Word theWord) {
		return this.getCanonicalForm().compareTo(theWord.getCanonicalForm());
	}
	
	/**
	 * Returns the String representation of a word.
	 * @return the String representation of a word.
	 */
	public String toString() {
		return NORMAL_FORM;
	}
	
	/**
	 * Converts the normal form of the string to it's
	 * canonical form.
	 * @param theString the string to be converted.
	 * @return the canonical form of the string.
	 */
	private String canonicalize(final String theString) {
		char[] tempArray = theString.toCharArray();
		Arrays.sort(tempArray);
		return new String(tempArray);
	}
}

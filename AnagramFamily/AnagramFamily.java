/* 
 * AnagramFamily.java
 * Damiene Stewart
 * TCSS 143
 * David Schuessler
 * Programming Assignment 9.
 * DUE: Tuesday, November 25, 2014 by 11:59 p.m.
 */

import java.util.LinkedList;
import java.util.List;

/**
 * Class outlining an anagram family
 * which contains all the Word objects
 * that have the same canonical form of
 * their stored words.
 * @author damienestewart
 * @version 1.0
 */
public class AnagramFamily {
	/**
	 * List of Word objects with a common
	 * canonical form.
	 */
	private List<Word> myWordFamily;
	
	/**
	 * Stores the size of the Word object list.
	 */
	private int myListSize;
	
	/**
	 * Constructor for an AnagramFamily object.
	 * Sets an empty list and the size to zero.
	 */
	public AnagramFamily() {
		myWordFamily = new LinkedList<Word>();
		myListSize = 0;
	}
	
	/**
	 * Adds a new word to the list that has the same canonical
	 * form as the others in the list.
	 * @param theWord is the new word to be added.
	 * @return true if addition was successful, otherwise false.
	 */
	public boolean addWord(Word theWord) {
		boolean flag;
		
		if(flag = myWordFamily.add(theWord)) {
			myListSize++;
		}
		
		return flag;
	}
	
	/**
	 * Returns the size of the Word object list.
	 * @return an integer representing the size of the object list.
	 */
	public int getSize() {
		return myListSize;
	}
	
	/**
	 * Returns the string representation of the Anagram Family.
	 * @return the string representation of the Anagram Family.
	 */
	public String toString() {
		String temp = "Canonical Form: " + myWordFamily.get(0).getCanonicalForm();
		temp += "\n" + "Size: " + getSize();
		temp += "\n" + "Words: ";
		
		// Add each word to the 'list'.
		for(Word i : myWordFamily) {
			temp += i.toString() + " ";
		}
		
		return temp;
	}
}

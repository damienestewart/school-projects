/* 
 * WordRecursion.java
 * Damiene Stewart
 * TCSS 143
 * David Schuessler
 * Programming Assignment 8.
 * DUE: Tuesday, November 18, 2014 by 11:59 p.m.
 */

import java.io.File;
import java.io.PrintStream;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * This program recusively reads
 * words from an input file and
 * add each word that contains a
 * particular character to a set.
 * The set is then printed.
 * @author damienestewart
 * @version 1.0
 */
public class WordRecursion {
	/**
	 * Constant representing the difference between
	 * upper and lowercase letters.
	 */
	public static final char ALPHA_RANGE = 32;
	
	/**
	 * This is the driver method for our
	 * recusion testing program.
	 * @param theArgs are command line arguments.
	 */
	public static void main(String[] theArgs) {
		// Driver variables.
		Scanner inputFile = null;
		PrintStream outputFile = null;
		
		// Open input and output file.
		try {
			inputFile = new Scanner(new File("In8.txt"));
			outputFile = new PrintStream(new File("out8.txt"));
		} catch (Exception e) {
			System.out.println("There was an issue opening "
					+ "files. " + e);
			System.exit(1);
		}
		
		// Get Set.
		Set<String> wordsSet = getWordsSet(getWordsString(inputFile, 'a'));
		
		// Files have been open, go to work:
		outputFile.println(wordsSet);
	}
	
	/**
	 * This function reads in the words from an input file.
	 * It returns a string that contains all the words that
	 * contain a certain character.
	 * @param theFile is the input file.
	 * @param theC is the character to search for.
	 * @return a string containing all relevant words.
	 */
	public static String getWordsString(final Scanner theFile, final char theC) {
		// Variables.
		String resultString = "";
		String containerString = "";
		
		if(theFile.hasNext()) {
			containerString = cleanString(theFile.next());
			// Does this word contain the character?
			if(hasCharacter(containerString, theC)) {
				// The word does contain it, hold on to it.
				resultString += containerString;
				
				// If there are more words in the input file
				// Let's call the method again to grab them.
				// We can call hasNext() because we just took
				// a line from the file.
				if(theFile.hasNext()) {
					// Avoid space at the end of the string.
					resultString += 
							(containerString = 
							getWordsString(theFile, theC)).equals("") ? 
									"" : (" " + containerString);
				}
			} else {
				// This word does not have the character we need.
				// The search goes on!
				resultString += getWordsString(theFile, theC);
			}
		}	
		return resultString;
	}
	
	/**
	 * This function checks a string to see if it contains
	 * a given character.
	 * @param theS is the string to be checked.
	 * @param theC is the lowercase character to search for.
	 * @return true or false if the string contains the character.
	 */
	public static boolean hasCharacter(final String theS, final char theC) {
		boolean flag = false; 
		
		// Have we exhausted all options?
		if(theS.length() < 1) {
			// Yes.
			flag = false;
		} else {
			// We have more characters in our string.
			char firstLetter = theS.charAt(0);
			
			// A match was found.
			if(firstLetter == theC || firstLetter == theC - ALPHA_RANGE) {
				flag = true;
			} else {
				flag = hasCharacter(theS.substring(1), theC);
			}
		}

		return flag;
	}
	
	/**
	 * This function adds all the words in a string
	 * to a set.
	 * @param theS is the string to take words from.
	 * @return the set containing the added words.
	 */
	public static Set<String> getWordsSet(final String theS) {
		// Create set to hold the elements of the string.
		Set<String> resultSet = new HashSet<String>();
		
		if(theS.indexOf(' ') != -1) {
			// If there is a space left in the string
			// we have more than one word left.
			
			// Add the word at the front.
			resultSet.add(theS.substring(0, theS.indexOf(' ')));
			// Recusive call to get the rest, if any.
			resultSet.addAll(getWordsSet(theS.substring(
					theS.indexOf(' ') + 1
					)));
		} else {
			// We don't have a space. This means there is
			// only one word left.
			resultSet.add(theS);
		}
		
		return resultSet;
	}
	
	/**
	 * This method attempts to trivially strip
	 * punctuation from strings.
	 * @param theS the string from which punctuation
	 * should be removed.
	 * @return the 'clean' string.
	 */
	public static String cleanString(String theS) {
		// Check if the string ends in some sort of punctuation
		//or apostrophe-s.
		char endChar = theS.charAt(theS.length()-1);
		char firstChar = theS.charAt(0);
		
		if(theS.indexOf("'s") != -1) {
			theS = theS.substring(0, theS.indexOf("'s"));
		} else if(endChar < 'A' || endChar > 'z') {
			theS = theS.substring(0, theS.indexOf(endChar));
		}
		
		if(firstChar < 'A' || firstChar > 'z') {
			theS = theS.substring(1);
		}
		
		return theS;
	}
}

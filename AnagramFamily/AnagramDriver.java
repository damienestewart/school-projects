/* 
 * Assignment9.java
 * Damiene Stewart
 * TCSS 143
 * David Schuessler
 * Programming Assignment 9.
 * DUE: Tuesday, November 25, 2014 by 11:59 p.m.
 */

import java.io.File;
import java.io.PrintStream;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * Driver class for our
 * program.
 * @author damienestewart
 * @version 1.0
 */
public class Assignment9 {

	/**
	 * Driver method that reads words in from a file,
	 * creates two linked lists, sorts those lists,
	 * and prints the output to a file.
	 * @param theArgs command-line arguments.
	 */
	public static void main(String[] theArgs) {
		// Declare file handlers and other list variables.
		Scanner input = null;
		PrintStream output = null;
		List<Word> wordList = new LinkedList<Word>();
		List<AnagramFamily> anagramFamilyList = new LinkedList<AnagramFamily>();
		
		// Open the files we need.
		try {
			input = new Scanner(new File("words.txt"));
			output = new PrintStream(new File("out9.txt"));
		} catch (Exception e) {
			System.out.println(e);
		}
		
		// Fill this list with words and then sort
		// the list based on ascending canonical form.
		getWordList(input, wordList);
		Collections.sort(wordList);
		
		// Create a Linked List for AnagramFamily Objects and
		// sort based on Anagram Family size.
		getAnagramFamilyList(wordList, anagramFamilyList);
		Collections.sort(anagramFamilyList, new SizeComparator());
		
		// Print results to the output file handler.
		printTopTenFamilies(output, anagramFamilyList);
		
		// Close handles.
		input.close();
		output.close();
	}
	
	/**
	 * Get all the words from the input file, creates a new
	 * word object with each, adds each word object to a LinkedList,
	 * and returns the LinkedList.
	 * @param theInput is our file hander from which we fetch each word.
	 * @param theList is our list to add input words to.
	 */
	public static void getWordList(Scanner theInput, List<Word> theList) {
		String tempString = "";
		
		// Loop through input and retrieve Words.
		while(theInput.hasNext()) {
			if(!(tempString = theInput.nextLine()).isEmpty()) {
				theList.add(new Word(tempString));
			}
		}
	}
	
	/**
	 * Creates the list of AnagramFamily based on the list
	 * of Word objects.
	 * @param theList is the list of Word objects.
	 * @return a LinkedList of AnagramFamily based on the
	 * list of Word objects.
	 */
	public static void getAnagramFamilyList(List<Word> theList, List<AnagramFamily> theAList) {
		// LOOP SETUP!
		// Variables to keep track of current word-family, and
		// the current AnagramFamily being constructed.
		Iterator<Word> itr = theList.iterator();
		Word tempWord = null;
		AnagramFamily anagramFamily = new AnagramFamily();
		String canonicalFamily = "";
		// Add the first word to the list.
		if(itr.hasNext()) {
			tempWord = itr.next();
			anagramFamily.addWord(tempWord);
			canonicalFamily = tempWord.getCanonicalForm();
			// If our list only has one element, we add the anagramFamily
			// that we just constructed to the list.
			if(!itr.hasNext()) {
				theAList.add(anagramFamily);
			}
		}
		
		// Add to anagramFamilyList by fully constructing each
		// AnagramFamily object outside of the loop before 
		// adding it to the list.
		while(itr.hasNext()) {
			tempWord = itr.next();
			
			if(tempWord.getCanonicalForm().equals(canonicalFamily)) {
				// Add word to last element in list.
				anagramFamily.addWord(tempWord);
				
				// Check if we are at the last word.
				// If so, add the word to the list.
				if(!itr.hasNext()) {
					theAList.add(anagramFamily);
				}
			} else {
				// Set current canonical family.
				canonicalFamily = tempWord.getCanonicalForm();
				
				// Add anagramFamily we have been
				// making thus far.
				theAList.add(anagramFamily);
				
				// Make new AnagramFamily
				anagramFamily = new AnagramFamily();
				
				// Add word to new AnagramFamily
				anagramFamily.addWord(tempWord);
				
				// Check if we are at the last word.
				// If so, add the word to the list.
				if(!itr.hasNext()) {
					theAList.add(anagramFamily);
				}
			}
			
		}
	}
	
	/**
	 * Outputs the top-ten Anagram Families to our output file.
	 * @param theOutput the output file handler.
	 * @param theList is the AnagramFamily list to be used for output.
	 */
	public static void printTopTenFamilies(PrintStream theOutput, List<AnagramFamily> theList) {
		theOutput.println("Top Ten Longest Anagram Families:\n");
		if(theList.size() > 10) {
			Iterator<AnagramFamily> itr = theList.subList(0,10).iterator();
			while(itr.hasNext()) {
				theOutput.println(itr.next() + "\n");
			}
		} else {
			for(AnagramFamily af : theList) {
				theOutput.println(af + "\n");
			}
		}
		
	}
}

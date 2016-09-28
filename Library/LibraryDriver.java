/*
 * LibraryDriver.java
 * Damiene Stewart
 * TCSS 143
 * David Schuessler
 * Programming Assignment 6
 * DUE: Tuesday, November 4, 2014 by 11:59 p.m.
 *
 */

import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Driver program for managing
 * the library.
 * @author damienestewart
 * @version 1.0
 */
public class LibraryDriver {	
	/**
	 * Driver method for library
	 * program.
	 * @param theArgs command-line arguments.
	 */
	public static void main(String[] theArgs) {
		Scanner inputFile = null;
		PrintStream outputFile = null;
		
		// Open necessary files.
		try {
			inputFile = new Scanner(new File("LibraryIn1.txt"));
			outputFile = new PrintStream(new File("LibraryOut.txt"));
		} catch (Exception e) {
			System.out.println("Difficulties opening the file! " + e);
			System.exit(1);
		}
		
		ArrayList<String> authors = new ArrayList<String>();
		ArrayList<Book> books = new ArrayList<Book>();
		
		// Read in input.
		while(inputFile.hasNext()) {
			String title = inputFile.nextLine();
			String authorlist = inputFile.nextLine();
	
			authors = new ArrayList<String>(getAuthors(authorlist));
			books.add(new Book(title, authors));
			authors.clear();
		}
		
		// New library.
		Library library = new Library(books);
		
		// Print library.
		outputFile.println("PRINTS INITIAL BOOK LIST:");
		outputFile.println(library);
		
		// Sort library and then print.
		library.sort();
		
		// Print sorted library.
		outputFile.println("\nPRINTS SORTED BOOK LIST:");
		outputFile.println(library);
		
		// Close the first input file.
		inputFile.close();
		
		// Open next input file.
		try {
			inputFile = new Scanner(new File("LibraryIn2.txt"));
		} catch (Exception e) {
			System.out.println("Difficulties opening the file! " + e);
			System.exit(1);
		}
		
		// Read in books from second file.
		while(inputFile.hasNext()) {
			String title = inputFile.nextLine();
			String authorlist = inputFile.nextLine();
			
			authors = getAuthors(authorlist);
			library.add(new Book(title, authors));
		}
		
		// Print extended library.
		outputFile.println("\nPRINTS WITH NEW BOOKS UNSORTED:");
		outputFile.println(library);
		
		// Sort and print.
		library.sort();
		
		// Print sorted extended library.
		outputFile.println("\nPRINTS ALL SORTED BOOK LIST:");
		outputFile.println(library);
		
		// Print all acer dumplings.
		outputFile.println("\nPRINTS ALL ACER DUMPLINGS:");
		for(Book book : library.findTitles("Acer Dumpling")) {
			outputFile.println(book);
		}
		
		// Print all the bluffs.
		outputFile.println("\nPRINTS ALL THE BLUFFS:");
		for(Book book : library.findTitles("The Bluff")) {
			outputFile.println(book);
		}
	}
	
	/**
	 * Method used to split the authors
	 * from a string.
	 * @param theString to be split.
	 * @return an ArrayList with separated authors.
	 */
	public static ArrayList<String> getAuthors(String theString) {
		String[] temp = theString.split("\\*");
		return new ArrayList<String>(Arrays.asList(temp));
	}
}

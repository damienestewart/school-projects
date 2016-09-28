/*
 * Shapes.java
 * Damiene Stewart
 * TCSS 143
 * David Schuessler
 * Programming Assignment 7
 * DUE: Tuesday, November 11, 2014 by 11:59 p.m.
 */

import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * Driver class.
 * @author damienestewart
 * @version 1.0
 */
public class Shapes {
	/**
	 * Driver method, main.
	 * @param theArgs command-line arguments.
	 */
	public static void main(String[] theArgs) {
		// Variables.
		Scanner inputFile = null;
		PrintStream outputFile = null;
		List <Shape> myList = new LinkedList<Shape>();
		
		// Try to open files.
		try {
			inputFile = new Scanner(new File("In7.txt"));
			outputFile = new PrintStream(new File("out7.txt"));
		} catch (Exception e) {
			System.out.println("There was an issue opening "
					+ "files. " + e);
			System.exit(1);
		}
		
		// Read in data, put result in myList variable.
		readData(inputFile, myList);
		
		// Create a copy of the list.
		List<Shape> copyList = new ArrayList<Shape>(myList);
		
		// Display original List.
		outputFile.println("Original List[unsorted]:");
		displayData(outputFile, myList);
		
		// Sort copy list.
		Collections.sort(copyList);
		
		// Display sorted copy.
		outputFile.println("\nCopied List[sorted]:");
		displayData(outputFile, copyList);
		
		// Display original list again.
		outputFile.println("\nOriginal List[unsorted]:");
		displayData(outputFile, myList);
		
		// Close files.
		outputFile.close();
		inputFile.close();
	}
	
	/**
	 * Display the list in output file.
	 * @param theOutputFile the file to print to.
	 * @param theList the list to be printed.
	 */
	public static void displayData(final PrintStream theOutputFile, 
			final List<Shape> theList) {
		for(Shape s : theList) {
			theOutputFile.println(s.toString());
		}
	}
	
	/**
	 * Read in data from input file.
	 * @param theInput input scanner to read from.
	 * @param theList the list to add data to.
	 */
	public static void readData(final Scanner theInputFile, 
			final List<Shape> theList) {
		// Local variables.
		ArrayList<Double> tempList = new ArrayList<Double>();
		Scanner tempScan = null;
		
		// Proceed through each line of
		// input file.
		while(theInputFile.hasNextLine()) {
			// Create temprary scanner.
			// Initialize with next line of input.
			tempScan = new Scanner(theInputFile.nextLine());
			
			// Step through the line we just retrieved
			// with the new scanner.
			while(tempScan.hasNextInt() || tempScan.hasNextDouble()) {
					// If it is an int or double
					// treat it as a double and push it
					// to our temprary list.
					tempList.add(tempScan.nextDouble());
			}
			
			// If there is another element on the input line
			// and it is not a double or integer, then we have
			// a problem, and need to discard everything and cry.
			if(tempScan.hasNext()) {
				tempList.clear();
			}
			
			// Construct object based on how many numbers were read.
			try {
				switch(tempList.size()) {
					case 1: theList.add(new Circle(tempList.get(0)));
							break;
					case 2: theList.add(new Rectangle(tempList.get(0),
							tempList.get(1)));
							break;
					case 3: theList.add(new Triangle(tempList.get(0),
							tempList.get(1), tempList.get(2)));
							break;
				}
			} catch(Exception e) {
				System.out.println(e);
			}	
			// Clear the list for restarting the process.
			tempList.clear();
		}
	}
}
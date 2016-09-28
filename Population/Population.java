/* 
 * Population.java
 * Damiene Stewart
 * TCSS 143
 * David Schuessler
 * Programming Assignment 1: bonus attempt.
 * DUE: Tuesday, September 30, 2014 by 11:59 p.m.
 */

import java.util.Scanner;

/**
 * This program prompts the user for the population
 * of five (5) individual towns.
 * It then displays a horizontal bar graph made of
 * asterisks, where each asterisk represents 1,000 people.
 * 
 * @author damienestewart
 * @version 1.1
 */
public class Population {

	/**
	 * Driver method that calls getPopulation and
	 * drawPopulationBar.
	 * @param theArgs Used for command-line input.
	 */
	public static void main(String[] theArgs) {
		
		Scanner console = new Scanner(System.in);	// Scanner-keyboard input.
		
		// Integer array variable to store the population of
		// each of the five (5) towns.
		int[] townPopulationArray = new int[5];
		
		// For loop that calls getPopulation five times in order
		// to fill townPopulationArray with necessary data.
		for(int i = 1; i <= 5; i++) {
			townPopulationArray[i-1] = getPopulation(i, console);
		}
		
		System.out.println();
		System.out.print("POPULATION GRAPH:");
		
		// For loop that calls drawPopulationBar five times in order
		// to draw the horizontal bar of each town.
		for(int i = 1; i <= 5; i++) {
			drawPopulationBar(i, townPopulationArray[i-1]);
		}
	}
	
	/**
	 * getPopulation prompts the user for the population size
	 * of a particular town, specified by the parameter, theTown.
	 * 
	 * @param theTown Governs which town the user is prompted for.
	 * @param theConsole Allows the program to accept keyboard input.
	 * @return getPopulation returns the number entered by the user.
	 */
	public static int getPopulation(int theTown, Scanner theConsole) {
		
		int population;	// Used to store the user's console input.
		
		System.out.print("Enter the population of town " + theTown + ": ");
		
		// 3 sections of while-loop which repeats if user enters a
		// negative number.
		population = theConsole.nextInt();	// 1. Initialize.
		while (population < 0) {			// 2. Test condition.
			System.out.println("The population can not be negative, try again.");
			System.out.print("Enter the population of town " + theTown + ": ");
			population = theConsole.nextInt();	// 3. Get new value to test.
		}
		return population;
	}
	
	/**
	 * drawPopulationBar draws a bar graph of asterisks for the town
	 * specified by the parameter, theTown. The amount of asterisks
	 * is calculated as per 1,000 people of the population parameter,
	 * thePopulation.
	 * 
	 * @param theTown Represents the town that the asterisks are printed for.
	 * @param thePopulation Used to calculate the amount of asterisks necessary.
	 */
	public static void drawPopulationBar(int theTown, int thePopulation) {
		// Put the cursor on the next line and begin output.
		System.out.println();
		System.out.print("Town " + theTown + ": ");
		
		// Print an asterisk per thousand people.
		for(int i = 0; i < thePopulation/1000; i++) {	
			System.out.print("*");
		}	
	}
}

/*
 * LostPuppy.java
 * Damiene Stewart
 * TCSS 143
 * David Schuessler
 * Programming Assignment 3: LostPuppy
 * DUE: Tuesday, October 14, 2014 by 11:59 p.m.
 */

import java.util.Arrays;
import java.util.Random;

/**
 * The LostPuppy class provides the necessary
 * facilities that a client needs to establish
 * a game involving a lost puppy. These facilities
 * include an array structure to represent the 
 * building, methods to track which rooms have been checked,
 * etc.
 * 
 * @author damienestewart
 * @version 1.0
 */
public class LostPuppy {
	
	// Constant.
	/**
	 * This is a simple constant that represents the 
	 * amount of underscores to be used when printing the
	 * "building" and it's contents.
	 */
	private final int UNDERSCORES = 3; 	// The client does not need to know this.
	
	// Instance variables.
	/** An array used to represent the building housing the lost puppy. */
	private char[][] myHidingPlaces;
	
	/** An integer representing the floor where the puppy is located. */
	private int myFloorLocation;
	
	/** An integer representing the room where the puppy is located. */
	private int myRoomLocation;
	
	/** Stores the character that represents the winning player. */
	private char myWinner;
	
	/** Contains a boolean value that indicates whether or not the puppy has been found . */
	private boolean myFound;
	
	/**
	 * This is the constructor of the LostPuppy Class.
	 * It takes two parameters which it uses to construct
	 * the building. It then sets the other instance
	 * variables to default values.
	 * 
	 * @param theFloors Represents the number of floors in the building.
	 * @param theRooms Represents the number of rooms in the building.
	 */
	public LostPuppy(int theFloors, int theRooms) {
		//Throw IllegalArgumentException for invalid input.
		if(theFloors <= 0 || theRooms <= 0) {
			throw new IllegalArgumentException("Values must be positive integers.");
		}
		
		// Allocate space for the array, and assign to
		// myHidingPlaces.
		myHidingPlaces = new char[theFloors][theRooms];
		
		// Loop through and initialize character array to
		// a single space, ' '.
		for(int i = 0; i < theFloors; i++) {
			Arrays.fill(myHidingPlaces[i], ' ');
		}
		
		// Create a new random object for use in generating
		// the floor and room position for the puppy.
		Random random = new Random();
		
		// Use random object to get values for the myFloorLocation
		// and myRoomLocation.
		myFloorLocation = random.nextInt(theFloors);	// Get a 0 <= x < theFloors.
		myRoomLocation = random.nextInt(theRooms);	// Get a 0 <= x < theRoom.
		
		// Set a single character P to represent the dog's position.
		myHidingPlaces[myFloorLocation][myRoomLocation] = 'P'; 	
															
		// Set remaining instance variables to default values.
		myWinner = ' ';
		myFound = false;
	}
	
	/**
	 * This method checks to see if the supplied room, given
	 * via the parameters, has already been checked.
	 * 
	 * @param theFloor Represents a particular floor in the building.
	 * @param theRoom Represents a particular room in the building.
	 * @return The function returns "true" if the room has already
	 * been checked, and "false" otherwise.
	 */
	public boolean roomSearchedAlready(int theFloor, int theRoom) {	
		boolean flag = true; // Set default flag.
		char roomContent = myHidingPlaces[theFloor][theRoom]; // For easier reference.
		
		// If the room is empty or contains the puppy, mark as not searched.
		if((roomContent == ' ') || (roomContent == 'P')) {
			flag = false; 	
		}
		return flag;
	}
	
	/**
	 * puppyLocation checks the given input location
	 * (based on supplied parameters) to see if the
	 * puppy is located at that position.
	 * 
	 * @param theFloor Represents a particular floor in the building.
	 * @param theRoom Represents a particular room in the building.
	 * @return The function returns "true" if the puppy is at the
	 * supplied input location and "false" otherwise.
	 */
	public boolean puppyLocation(int theFloor, int theRoom) {
		boolean flag = false; // Set default flag.
		char roomContent = myHidingPlaces[theFloor][theRoom];	// For easier reference.
		
		if(roomContent == 'P') {
			flag = true; // Set flag to true if puppy is in this location.
		}
		return flag;
	}
	
	/**
	 * indicesOK checks the supplied input to make sure that
	 * they are both valid, and the room they represent exists.
	 * 
	 * @param theFloor Represents a particular floor in the building.
	 * @param theRoom Represents a particular room in the building.
	 * @return The function returns "true" if the input is valid
	 * and "false" otherwise.
	 */
	public boolean indicesOK(int theFloor, int theRoom) {
		boolean flag = false; // Set default flag.
		
		// Use boolean variables to store result of logical statements.
		boolean floorValid = (theFloor >= 0) && (theFloor < myHidingPlaces.length);
		boolean roomValid = (theRoom >= 0) && (theRoom < myHidingPlaces[0].length);
		
		if(floorValid && roomValid) {
			flag = true;
		}
		return flag;
	}

	/**
	 * This function returns the number of floors in the building.
	 * @return the number of floors in the building.
	 */
	public int numberOfFloors() {
		return myHidingPlaces.length; // Single line return number of floors.
	}
	
	/**
	 * This function returns the number of rooms in the building.
	 * @return the number of rooms in the building.
	 */
	public int numberOfRooms() {
		return myHidingPlaces[0].length; // Single line return number of rooms.
	}
	
	/**
	 * searchRoom provides the client with the ability to check
	 * different rooms in the building for the lost puppy. 
	 * It will update the room with the character that represents 
	 * the player that checked it. If the puppy is found, this
	 * function will also update myFound and myWinner appropriately.
	 * 
	 * @param theFloor Represents a particular floor in the building.
	 * @param theRoom Represents a particular room in the building.
	 * @param thePlayer A character that represents the player.
	 * @return This function returns "true" if the puppy has been found
	 * and "false" otherwise.
	 */
	public boolean searchRoom(int theFloor, int theRoom, char thePlayer) {
		boolean flag = false; // Set default flag.
		boolean puppyFound = puppyLocation(theFloor, theRoom);
		
		if(!puppyFound) {
			// Set room to reflect that it was searched.
			myHidingPlaces[theFloor][theRoom] = thePlayer;											
		}
		else { 
			// The player has found the puppy.
			myWinner = thePlayer;
			myFound = true;
			flag = true;
		}
		return flag;
	}
	
	/**
	 * The LostPuppy toString method saves a string representation
	 * of the current status of the building. This includes the empty
	 * rooms, and rooms that have been checked. When the game ends,
	 * the method will also display the location of the puppy, and
	 * the player that found it. 
	 */
	public String toString() {
		String arrayString = " "; // String that will be passed back to client.
		int numberOfRooms = numberOfRooms(); 	
		
		// There are 3 underscores per cell + the ones at each cell junction
		// on the first line.
		int cellJunctions = numberOfRooms - 1; 	
		
		//Loop to print underscores for first line.
		for(int i = 0; i < cellJunctions + UNDERSCORES * numberOfRooms; i++) {
			arrayString += "_";
		}
		arrayString += " \n";
		
		// Perform magic loop, haha.
		// Loop through floors from bottom to top.
		for(int i = numberOfFloors() - 1; i >= 0; i--) {
			
			arrayString += "|"; // Add first pipe.
			
			for(int j = 0; j < numberOfRooms; j++) { // Loop through rooms.
				
				if(puppyLocation(i, j) && myFound) {
					// If the puppy has been found here:
					arrayString += "" + myWinner + "" + myHidingPlaces[i][j] + " ";
				}
				else if(myHidingPlaces[i][j] != ' ' && myHidingPlaces[i][j] != 'P') {
					// If the cell contains a player representing 
					//character:
					arrayString += " " + myHidingPlaces[i][j] + " ";
				} else {
					// Fill empty cell with spaces.
					for(int k = 0; k < UNDERSCORES; k++) {
						arrayString += " ";
					}
				}
				arrayString += "|";	// Add closing pipe for cell.
			} // First room loop ends here.
			
			arrayString += "\n"; // Add new-line character.
			arrayString += "|"; // Add beginning pipe for second row of cells.
			
			// We need to loop through the rooms again since each cell has two
			// lines inside.
			for(int j = 0; j < numberOfRooms; j++) {
				for(int k = 0; k < UNDERSCORES; k++) {
					// Underscores represent the bottom cell border.
					arrayString += "_";
				}
				arrayString += "|"; // Closing pipe.
			}
			arrayString += "\n"; // Final new line before next floor.
		}
		return arrayString;
	}
}

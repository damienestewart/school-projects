/*
 * Book.java
 * Damiene Stewart
 * TCSS 143
 * David Schuessler
 * Programming Assignment 6
 * DUE: Tuesday, November 4, 2014 by 11:59 p.m.
 */

import java.util.ArrayList;

/**
 * This class defines Books.
 * It stores the book's title
 * and authors.
 * @author damienestewart
 * @version 1.0
 */
public class Book implements Comparable<Book> {
	// Class constants.
	/** Constant to represent the book title. **/
	private final String TITLE;
	
	/** Constant to represent the book's authors. **/
	private final ArrayList<String> AUTHORS;
	
	// Constructors.
	/**
	 * Constructs a new book object.
	 * @param theTitle represent the book's title.
	 * @param theAuthors represents the book's authors.
	 */
	public Book(final String theTitle, final ArrayList<String> theAuthors) {
		// Check if theTitle/theAuthors is null/empty.
		if(theTitle == null || theTitle == "" || theAuthors.size() == 0 || 
		   theAuthors == null) {
			throw new IllegalArgumentException();
		}
		// Set constants.
		TITLE = theTitle;
		AUTHORS = new ArrayList<String>(theAuthors);
	}
	
	/**
	 * Getter method for the book's title.
	 * @return the book's title.
	 */
	public String getTitle() {
		return TITLE;
	}
	
	/**
	 * Getter method for the book's authors.
	 * @return an ArrayList representing the book's authors.
	 */
	public ArrayList<String> getAuthors() {
		return AUTHORS;
	}
	
	/**
	 * Returns string representation of the title
	 * and each author.
	 * @return string representation of the book.
	 */
	public String toString() {
		String authorList = "";
		
		for(String author : getAuthors()) {
			authorList += " " + author + ";";
		}
		
		// Remove last semicolon.
		authorList = authorList.substring(0, authorList.lastIndexOf(';'));
		
		return "\"" + TITLE + ",\" " + "by" + authorList;
	}
	
	/**
	 * Compares this book with another book in order
	 * to determine their order.
	 * @param the book to be compared with.
	 * @return an integer representing the order.
	 */
	public int compareTo(final Book theOtherBook) {
		int result = 0;
		
		// Perform comparison.
		if((this.getTitle()).compareTo(theOtherBook.getTitle()) < 0){
			result = -1;
		} else if(this.getTitle().compareTo(theOtherBook.getTitle()) > 0) {
			result = 1;
		} else if((this.getAuthors()).get(0).compareTo(theOtherBook.getAuthors().get(0)) < 0) {
			result = -1;
		} else if((this.getAuthors()).get(0).compareTo(theOtherBook.getAuthors().get(0)) < 0) {
			result = 1;
		}
		return result;
	}
	
	/**
	 * Checks to see if this book is equal to another.
	 * @param the object to compare this book to.
	 * @return true if the object is equal to this book.
	 */
	public boolean equals(final Object theOther) {
		boolean flag = theOther instanceof Book;
		if(flag) {
			flag = this.compareTo((Book) theOther) != 0;
		}
		return flag;
	}
}

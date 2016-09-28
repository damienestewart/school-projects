/*
 * Library.java
 * Damiene Stewart
 * TCSS 143
 * David Schuessler
 * Programming Assignment 6
 * DUE: Tuesday, November 4, 2014 by 11:59 p.m.
 *
 */

import java.util.ArrayList;
import java.util.Collections;

/**
 * Outlines the Library class.
 * Stores books.
 * @author damienestewart
 * @version 1.0
 */
public class Library {
	// Class field.
	/** Contains books. **/
	private ArrayList<Book> myBooks;
	
	/**
	 * Creates a new library object.
	 * @param theOther the books to initialize the
	 * Library with.
	 */
	public Library(final ArrayList<Book> theOther) {
		if(theOther == null) {
			throw new NullPointerException();
		}	
		myBooks = new ArrayList<Book>(theOther);
	}
	
	/**
	 * Creates an empty library.
	 */
	public Library() {
		myBooks = new ArrayList<Book>();
	}
	
	/**
	 * Adds a new book to the library.
	 * @param theBook is the book to be added.
	 * @return true if addition was a success.
	 */
	public boolean add(final Book theBook) {
		// No further checks need to be performed
		// as Book's constructor already checks TITLE
		// and AUTHORS.
		if(theBook == null) {
			throw new NullPointerException();
		}

		return myBooks.add(theBook);
	}
	
	/**
	 * Get all books with specified title.
	 * @param theTitle is the title to search for.
	 * @return an ArrayList of matching books.
	 */
	public ArrayList<Book> findTitles(final String theTitle) {
		ArrayList<Book> temp = new ArrayList<Book>();
		
		for(Book i : myBooks) {
			// Don't call compareTo on theTitle as it might be null.
			if(i.getTitle().compareTo(theTitle) == 0) {
				temp.add(i);
			}
		}
		return temp;
	}
	
	/**
	 * Sorts books by title.
	 */
	public void sort() {
		Collections.sort(myBooks);
	}
	
	/**
	 * Create a string representation of
	 * library.
	 * @return properly formatted string
	 * representation.
	 */
	public String toString() {
		String temp = "";
		
		// Add each book to temp to be returned.
		for(Book book : myBooks) {
			temp += book.toString() + "\n";
		}
		
		return temp;
	}
}

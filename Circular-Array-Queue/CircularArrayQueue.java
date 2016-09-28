import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A dynamic, circular-array based queue.
 * 
 * @author Damiene Stewart
 * @version Febuary 3rd, 2015
 * @param <D> A generic object type to contain in the queue.
 */
public class MyQueue<D> implements Iterable<D> {

	/**
	 * Default size for queue.
	 */
	private static final int DEFAULT_SIZE = 25;
	
	/**
	 * Current number of elements.
	 */
	private int mySize;
	
	/**
	 * Internal array storage for data.
	 */
	private D[] myArray;
	
	/**
	 * Front element index.
	 */
	private int myFrontIndex;
	
	/**
	 * Last element index.
	 */
	private int myLastIndex;
	
	/**
	 * Constructs a new queue.
	 */
	@SuppressWarnings("unchecked")
	public MyQueue() {
		mySize = 0;
		myFrontIndex = 0;
		myLastIndex = -1;
		
		myArray = (D []) new Object[DEFAULT_SIZE];
	}
	
	/**
	 * Used to check if the queue is empty.
	 * 
	 * @return true/false.
	 */
	public boolean isEmpty() {
		return mySize == 0;
	}
	
	/**
	 * Used to check if array is full.
	 * 
	 * @return true/false.
	 */
	private boolean isFull() {
		return mySize == myArray.length;
	}
	
	/**
	 * Returns number of elements in
	 * queue.
	 * 
	 * @return number of elements in queue.
	 */
	public int getSize() {
		return mySize;
	}
	
	/**
	 * Increases index appropriately.
	 * 
	 * @param index front or back index of array.
	 * @return incremented index.
	 */
	private int increment(int index) {
		return ++index % myArray.length;
	}
	
	/**
	 * Adds element to queue. Queue will
	 * only be filled when we run out of
	 * memory.
	 * 
	 * @param element to add to the queue.
	 */
	@SuppressWarnings("unchecked")
	public void enqueue(D element) {
		// Add element to our list.
		// Copy it first. We'll worry about
		// that part later.
		
		// Check if full.
		if (isFull()) {
			// Double array size.
			D [] doubledArray = (D []) new Object [mySize * 2];
			
			// We can move this into a separate method.
			for (int i = 0; i < mySize; i++, 
					myFrontIndex = increment(myFrontIndex)) {
				doubledArray[i] = myArray[myFrontIndex];
			}
			
			myFrontIndex = 0;
			myLastIndex = mySize - 1;
			myArray = doubledArray;
		}
		
		myLastIndex = increment(myLastIndex);
		myArray[myLastIndex] = element;
		
		mySize++;
	}
	
	/**
	 * Removes and returns the first element
	 * in the queue.
	 * 
	 * @return element from the front of the
	 * queue.
	 * @throws NoSuchElementException if attempt
	 * to remove from an empty array is made.
	 */
	@SuppressWarnings("unchecked")
	public D dequeue() {
		// Check if we have elements.
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		
		D copyElement = myArray[myFrontIndex];
		
		myFrontIndex = increment(myFrontIndex);
		mySize--;
		
		// Shrink when we're not using at least
		// 30% of size. Do this by halving size.
		
		if (myArray.length > 25 
				&& new Double((double) mySize / myArray.length).compareTo(0.3)
				< 0) {
			
			D [] shrinkedArray = (D []) new Object [myArray.length / 2];
			
			for (int i = 0; i < (myArray.length / 2); i++, 
					myFrontIndex = increment(myFrontIndex)) {
				shrinkedArray[i] = myArray[myFrontIndex];
					
			}
			
			System.out.print("new size: " + shrinkedArray.length);
			
			myFrontIndex = 0;
			myLastIndex = mySize - 1;
			myArray = shrinkedArray;
		}
		
		return copyElement;
	}
	
	/**
	 * Returns a string representation of
	 * this queue.
	 * @see java.lang.Object#toString().
	 */
	@Override
	public String toString() {
		StringBuilder stringRep = new StringBuilder();
		Iterator<D> itr = iterator();
		
		stringRep.append('[');
		
		if (mySize == 1) {
			stringRep.append(itr.next());
		} else if (mySize > 1) {
			stringRep.append(itr.next());
			while (itr.hasNext()) {
				stringRep.append(", ");
				stringRep.append(itr.next());
			}
		}
		
		stringRep.append(']');
		
		return stringRep.toString();	
	}
	
	/**
	 * Returns an iterator for the queue.
	 * 
	 * @return an iterator for the queue.
	 */
	public Iterator<D> iterator() {
		return new MyQueueIterator();
	}
	
	/**
	 * 
	 * @author Damiene Stewart
	 * @version Febuary 3rd, 2015
	 */
	private class MyQueueIterator implements Iterator<D> {

		/**
		 * Keeps track of current position in list.
		 */
		private int position;
		
		/**
		 * Keeps track of how many elements have been
		 * read thus far.
		 */
		private int elementsRead;
		
		/**
		 * Constructs and initializes a new
		 * iterator.
		 */
		public MyQueueIterator() {
			position = myFrontIndex;
			elementsRead = 0;
		}
		
		/**
		 * Returns whether or not another element
		 * is present.
		 * @see java.util.Iterator.
		 */
		@Override
		public boolean hasNext() {
			return elementsRead < mySize;
		}

		/**
		 * Retrieves the next element in the
		 * iteration.
		 * @throws NoSuchElementException.
		 * @see java.util.Iterator.
		 */
		@Override
		public D next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			
			D returnElement = myArray[position];
			
			position = increment(position);
			elementsRead++;

			return returnElement;
		}
		
		/**
		 * Unsupported in this implementation.
		 * @throws NoSuchElementException.
		 * @see java.util.Iterator.
		 */
		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
		
	}
}

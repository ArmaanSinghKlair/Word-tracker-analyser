package utilities;

import java.util.NoSuchElementException;

import adt.Iterator;
import adt.ListADT;
/**
 * ArrayList Iterator
 * @author Armaan Singh Klair
 *
 * @param <E> type-to-be-specified later
 */
public class ArrayListIterator<E> implements Iterator<E>{
	/**
	 * Private Data fields
	 */
	private int total;
	private int current = 0;
	private ListADT<E> list;
	
	/**
	 * Constructor 
	 * @param list The list to iterate upon
	 * @param size Size of list
	 */
	public ArrayListIterator(MyArrayList<E> list, int size) {
		this.list = list;
		this.total = size;
	}
	
	/**
	 * Tells whether iterator has more elements or not
	 */
    @Override
	public boolean hasNext() {
		if(current >= total)
			return false;
		else
			return true;
	}
    
    /**
     * Returns the next element in ArrayList
     */

	@Override
	public E next() throws NoSuchElementException {
		if(current >= total )
			throw new NoSuchElementException("End of List Reached");
		return list.get(current++);
	}
    
}

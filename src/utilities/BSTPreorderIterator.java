package utilities;

import java.util.NoSuchElementException;
import adt.Iterator;
/**
 * Provider for an pre-order iteration over a BSTree
 * @author Armaan Singh Klair
 * @version 12/09/2020
 * @param <E> type-to-be-specified-later
 */
public class BSTPreorderIterator<E extends Comparable<? super E>> implements Iterator<E>{
	/**
	 * Private data fields
	 */
	private BSTNode<E> root;
	private int remaining;
	private int current;
	private MyArrayList<BSTNode<E>> inorderBst;
	
	/**
	 * Constructor
	 * @param root Root of BST
	 * @param size Size of BST
	 */
	public BSTPreorderIterator(BSTNode<E> root, int size) {
		this.root = root;
		this.remaining = size;
		this.current = 0;
		this.inorderBst = new MyArrayList<>();	
		this.preorder(this.root);
	}
	
	/**
	 * Does the pre-order iteration and fills the ArrayList with elements
	 * @param node Node currently being operated upon
	 */
	private void preorder(BSTNode<E> node) {
		if(node != null) {
			this.inorderBst.add(node);
			preorder(node.getLeft());	
			preorder(node.getRight());
		}
	}
	/**
	 * Tells whether the iteration has more elements
	 */
	@Override
	public boolean hasNext() {
		return this.remaining != 0;
	}

	/**
	 * Returns the next element in iteration
	 */
	@Override
	public E next() throws NoSuchElementException {
		if(!this.hasNext()) 
			throw new NoSuchElementException();
		this.remaining--;
		return (E) inorderBst.get(this.current++).getElem();
		
	}

}

package utilities;

import java.util.NoSuchElementException;

import adt.Iterator;
/**
 * Provider for an post-order iteration over a BSTree
 * @author Armaan Singh Klair
 * @version 12/09/2020
 * @param <E> type-to-be-specified-later
 */
public class BSTPostorderIterator<E extends Comparable<? super E>> implements Iterator<E>{
	/**
	 * Private data fiels
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
	public BSTPostorderIterator(BSTNode<E> root, int size) {
		this.root = root;
		this.remaining = size;
		this.current = 0;
		this.inorderBst = new MyArrayList<>();	
		this.postorder(this.root);
	}
	
	/**
	 * Does the post-order iteration and fills the ArrayList with elements
	 * @param node Node currently being operated upon
	 */
	private void postorder(BSTNode<E> node) {
		if(node != null) {
			postorder(node.getLeft());	
			postorder(node.getRight());
			this.inorderBst.add(node);
		}
	}
	
	/**
	 * Tells whether more elements are left in iteration
	 */
	@Override
	public boolean hasNext() {
		return this.remaining != 0;
	}

	/**
	 * Returns next element in iteration
	 */
	@Override
	public E next() throws NoSuchElementException {
		if(!this.hasNext()) 
			throw new NoSuchElementException();
		this.remaining--;
		return (E) inorderBst.get(this.current++).getElem();
		
	}

}

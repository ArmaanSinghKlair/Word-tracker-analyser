package utilities;

import java.util.NoSuchElementException;
import adt.Iterator;

/**
 * Provider for an in-order iteration over a BSTree
 * @author Armaan Singh Klair
 * @version 12/09/2020
 * @param <E> type-to-be-specified-later
 */
public class BSTInorderIterator<E extends Comparable<? super E>> implements Iterator<E>{
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
	public BSTInorderIterator(BSTNode<E> root, int size) {
		this.root = root;
		this.remaining = size;
		this.current = 0;
		this.inorderBst = new MyArrayList<>();	
		this.inorder(this.root);
	}
	
	/**
	 * Does the in-order iteration and fills the ArrayList with elements
	 * @param node Node currently being operated upon
	 */
	private void inorder(BSTNode<E> node) {
		if(node != null) {
			inorder(node.getLeft());
			this.inorderBst.add(node);
			inorder(node.getRight());
		}
	}
	
	/**
	 * Tells whether more elements are left in BST
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

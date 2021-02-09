	package utilities;

import adt.BSTreeADT;
import adt.Iterator;
import exceptions.TreeException;

/**
 * This is a reference based implementation of the BST
 * @author Armaan Singh Klair
 * @version 12/10/2020
 * @param <E> type-to-be-specified-later
 */
public class BSTReferenceBased<E extends Comparable<? super E>> implements BSTreeADT<E>{

	/**
	 * private data fields
	 */
	private static final long serialVersionUID = -5532448974971836890L;
	private BSTNode<E> root;
	private int size;
	private int height;
	
	/**
	 * No-arg constructor
	 */
	public BSTReferenceBased() {
		this.root = null;
		this.size = 0;
		this.height = 0;
	}
	
	/**
	 * Constructor
	 * @param root Root node value
	 */
	public BSTReferenceBased(E root) {
		this.root = new BSTNode<E>(root);
		this.size = 0;
		this.height = 0;
	}
		
	/**
	 * Gets Root data
	 * @return Root data
	 * @throws TreeException Thrown when tree empty
	 */
	@Override
	public E getRootData() throws TreeException {
		if(size == 0)
			throw new TreeException("Tree is empty. No root found");
		else
			return this.root.getElem();
	}

	/**
	 * Returns height of tree
	 * @return Height of tree
	 */
	@Override
	public int getHeight() {
		return this.height;
	}

	/**
	 * Returns size of tree
	 * @return Size of tree
	 */
	@Override
	public int size() {
		return this.size;
	}

	/**
	 * Tells whether tree is empty
	 */
	@Override
	public boolean isEmpty() {
		return this.size() == 0;
	}

	/**
	 * Clears the tree, sets size and height to zero
	 */
	@Override
	public void clear() {
		this.root = null;
		this.size = 0;
		this.height = 0;
	}

	/**
	 * Tests whether BST contains specified element
	 * @param entry Value to be searched
	 * @throws TreeException Thrown when tree empty
	 * @return boolean depending whether it contains entry
	 */
	@Override
	public boolean contains(E entry) throws TreeException {
		if( this.size() == 0)
			throw new TreeException("Tree is empty");
		else {
			Iterator<E> iter = this.inorderIterator();
			while(iter.hasNext()) {
				E curr = iter.next();
				if(curr.compareTo(entry)==0)
					return true;
			}
			return false;
		}
			
	}

	/**
	 * Gets E for specified value or null if not found
	 * @param entry Value to be searched
	 * @throws TreeException Thrown when tree empty
	 * @return Value found or null if not found
	 */
	@Override
	public E getEntry(E entry) throws TreeException {
		if(this.size() == 0)
			throw new TreeException("Tree is empty");
		else {
			Iterator<E> iter = this.inorderIterator();
			while(iter.hasNext()) {
				E curr =  iter.next();
				if(curr.equals(entry))
					return entry;
			}
			return null;
		}
	}

	/**
	 * Adds new value to rightful place
	 * @param newEntry value to be added
	 * @throws NullPointerException Thrown when newEntry is null
	 * @return Boolean depending upon whether it was added or not
	 */
	@Override
	public boolean add(E newEntry) throws NullPointerException {
		if(newEntry == null)
			throw new NullPointerException();
		
		BSTNode<E> curr = this.root;
		BSTNode<E> parent = null;
		int level=0;
		while(true) {
			level++;
			if(this.root == null) {
				this.root = new BSTNode<>(newEntry);
				this.size++;
				this.height = level;
				return true;
				
			}  else if(curr==null) {
				if(parent.getElem().compareTo(newEntry) < 0  || parent.getElem().compareTo(newEntry) == 0) {
					parent.setRight(new BSTNode<E>(newEntry));
					if(parent.getLeft() == null) {
						if(level > this.height)
							this.height = level;
					}
				}
				else {
					parent.setLeft(new BSTNode<E>(newEntry));
					if(parent.getRight() == null) {
						if(level > this.height)
							this.height = level;
					}
				}
				this.size++;
				return true;
			}   else if(curr.getElem().compareTo(newEntry) < 0  || curr.getElem().compareTo(newEntry) == 0) {
				parent = curr;
				curr = curr.getRight();
			}
			else if(curr.getElem().compareTo(newEntry) > 0) {
				parent = curr;
				curr = curr.getLeft();
			}
			
		} 
	}

	/**
	 * Gets the in-order iterator
	 * @return In-order iterator
	 */
	@Override
	public Iterator<E> inorderIterator() {
		return new BSTInorderIterator<E>(this.root, this.size);
	}

	/**
	 * Gets the pre-order iterator
	 * @return Pre-order iterator
	 */
	@Override
	public Iterator<E> preorderIterator() {
		return new BSTPreorderIterator<E>(this.root, this.size);
	}
	
	/**
	 * Gets the post-order iterator
	 * @return Post-order iterator
	 */
	@Override
	public Iterator<E> postorderIterator() {
		return new BSTPostorderIterator<E>(this.root, this.size);

	}

	
}

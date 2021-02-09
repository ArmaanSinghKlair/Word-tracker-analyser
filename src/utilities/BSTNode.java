package utilities;

import java.io.Serializable;

/**
 * Represents a node in a BST
 * @author Armaan Singh Klair
 * @version 12/09/2020
 * @param <E> type-to-be-specified-later
 */
public class BSTNode<E extends Comparable<? super E>> implements Serializable {
	/**
	 * Private data fields
	 */
	private static final long serialVersionUID = -9000657547258524759L;
	private E elem;
	private BSTNode<E> left;
	private BSTNode<E> right;
	
	/**
	 * Constructor
	 * @param elem Element to be added
	 */
	public BSTNode(E elem) {
		this.elem = elem;
		this.left = null;
		this.right = null;
	}
	
	/**
	 * Constructor
	 * @param elem Element to be added
	 * @param left Left child of BST
	 * @param right right child of BST
	 */
	public BSTNode(E elem, BSTNode<E> left, BSTNode<E> right) {
		this.elem = elem;
		this.left = left;
		this.right = right;
	}
	
	/**
	 * Getter for element
	 * @return Element
	 */
	public E getElem() {
		return elem;
	}
	
	/**
	 * Setter for element
	 * @param elem Element 
	 */
	public void setElem(E elem) {
		this.elem = elem;
	}
	
	/**
	 * Getter for left child
	 * @return Left child
	 */
	public BSTNode<E> getLeft() {
		return left;
	}
	
	/**
	 * Setter for left child
	 * @param left Left child
	 */
	public void setLeft(BSTNode<E> left) {
		this.left = left;
	}
	
	/**
	 * Getter for right child
	 * @return Right child
	 */
	public BSTNode<E> getRight() {
		return right;
	}
	
	/**
	 * Setter for right child
	 * @param right Right child
	 */
	public void setRight(BSTNode<E> right) {
		this.right = right;
	}
	
	
}

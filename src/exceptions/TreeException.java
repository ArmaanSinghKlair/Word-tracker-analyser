package exceptions;

/**
 * Thrown when exception occurs with Tree. For instance, when an operation requires some values in a tree but tree is empty
 * @author 839645
 *
 */
public class TreeException extends Exception {
	/**
	 * Constructor that sets the exception message to specifed string
	 * @param errMsg The exception message to be shown
	 */
	public TreeException(String errMsg) {
		super(errMsg);
	}
	
	/**
	 * No-arg constructor
	 */
	public TreeException() {
		super("Tree Exception");
	}
}

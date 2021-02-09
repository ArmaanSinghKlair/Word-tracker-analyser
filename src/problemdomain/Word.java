package problemdomain;

import java.io.Serializable;

/**
 * Structure of Word class that stores the word and its meta data( i.e. line number, file number, frequency)
 * @author Armaan Singh Klair
 * @version 12/09/2020
 */
public class Word implements Serializable, Comparable<Word>{
	/**
	 * Private data fields
	 */
	private static final long serialVersionUID = -4637145475788980161L;
	private String word;
	private String fileName;
	private int lineNumber;
	private double frequency;
	
	/**
	 * Constructor
	 * @param word Actual word string
	 * @param fileName File in which word is present
	 * @param lineNumber Line number on which word is present
	 */
	public Word(String word, String fileName, int lineNumber) {
		this.word = word;
		this.fileName = fileName;
		this.lineNumber = lineNumber;
		this.frequency = 0;
	}
	
	/**
	 * 
	 * @param word Actual word string
	 * @param fileName File in which word is present
	 * @param lineNumber Line number on which word is present
	 * @param frequency The frequency of word i.e. Total number of same words in file / Total lines in file =  Frequency of word per line
	 */
	public Word(String word, String fileName, int lineNumber, double frequency) {
		this.word = word;
		this.fileName = fileName;
		this.lineNumber = lineNumber;
		this.frequency = frequency;
	}

	/**
	 * Getter for Word string
	 * @return Word String
	 */
	public String getWord() {
		return word;
	}

	/**
	 * Setter for Word
	 * @param word Updated word
	 */
	public void setWord(String word) {
		this.word = word;
	}

	/**
	 * Getter for filename
	 * @return The filename
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * Setter for filename
	 * @param fileName the updated filename
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * Getter for line number
	 * @return Line number of this Word
	 */
	public int getLineNumber() {
		return lineNumber;
	}
	
	/**
	 * Setter for line number 
	 * @param lineNumber New and updated line number
	 */
	public void setLineNumber(int lineNumber) {
		this.lineNumber = lineNumber;
	}

	/**
	 * Getter for frequency of this word in file i.e. Frequency of this word per line in file
	 * @return
	 */
	public double getFrequency() {
		return frequency;
	}

	/**
	 * Setter for frequency for this word
	 * @param frequency Frequency of this word per line in a file
	 */
	public void setFrequency(double frequency) {
		this.frequency = frequency;
	}

	/**
	 * Compare To implemented depending upon the Word String
	 */
	@Override
	public int compareTo(Word o) {
		return this.getWord().compareTo(o.getWord());
	}
	
	
}

package appdriver;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.regex.Pattern;

import adt.Iterator;
import problemdomain.Word;
import utilities.BSTReferenceBased;
import utilities.MyArrayList;
/**
 * This class takes in user input for file to be analysed and stored in tree
 * It also takes type of report to be generated and also the filename(optional) where the results will be stored
 * @author Armaan Singh Klair
 * @version 12/09/2020
 *
 */
public class WordTracker {
	/**
	 * The tree where Word(s) will be stored
	 */
	static BSTReferenceBased<Word> wordTree;
	
	/**
	 * Main method takes in file containing data, report type, name of file where report goes(optional)
	 * It then loads from 'repository.ser' if it exists, then creates a tree from current file and stores the updated tree
	 * @param args Arguements to program i.e. the report type and filename of report output(optional)
	 */
	public static void main(String[] args) {
		boolean reportToFile = false;
		String reportFileName = null;
		String reportType =null;
		boolean doReport = false;
		String datafile = null;
		String filename = "res\\repository.ser";
		File f = new File(filename);
		File dataf = null;
		Scanner lineScan = null; 
		// Parsing args
		for(int i= 0; i < args.length; i++) {
			if(Pattern.matches("[-][\\w&&[^fF]]{1}[\\w]{1}",args[i])) {
				doReport = true;
				args[i] = args[i].substring(1).toLowerCase();
				reportType = args[i];
			} else if(Pattern.matches("[-][fF]", args[i])){
				reportToFile = true;
				reportFileName = args[++i];
			}
		}
		
		if(reportType == null) {
			System.out.println("Report type must be specified:");
			System.out.println("1. -pf to print in alphabetic order all words along with the corresponding list of files in which the words occur.");
			System.out.println("2. -pl to print in alphabetic order all words along with the corresponding list of files and numbers of the lines in which the word occur.");
			System.out.println("3. -po to print in alphabetic order all words along with the corresponding list of files, numbers of the lines in which the word occur and the frequency of occurrence of the words.");
			System.out.println("Also, -f <<filepath>> to direct the report to <<filepath>> instead of printing directly");
			return;
		}
		//Getting path of text file to be analyzed
		Scanner userInput = new Scanner(System.in);
		
		System.out.print("Enter path of file to be analyzed: ");
		datafile = userInput.nextLine();
		
		while(datafile.trim().length() == 0 || !(dataf = new File(datafile)).exists()) {
			if(datafile.trim().length() == 0)
				System.out.println("Path cannot be empty!");
			else if(! dataf.exists())
				System.out.println("File not found. Please check path and try again :)");
			
			System.out.print("Enter path of file to be analyzed: ");
			datafile = userInput.nextLine();
					
		}
		userInput.close();
		
		try {
		if(f.exists())
			loadExistingTree(filename);
		else {
			wordTree = new BSTReferenceBased<Word>();
			if(!f.getParentFile().exists())
				f.getParentFile().mkdirs();
		}
		
			BufferedReader br = new BufferedReader(new FileReader(dataf));
			String line = br.readLine();
			int lineNumber = 0;
			Word curWord;
			
			while(line != null) {
				lineNumber++;
				
				lineScan = new Scanner(line);
				lineScan.useDelimiter("[\",\\s-;\n.!?)(]+");
				while(lineScan.hasNext()) {
					curWord = new Word(lineScan.next(), datafile, lineNumber);
					wordTree.add(curWord);
				}
				
				line = br.readLine();
			}
			
			lineScan.close();
			updateFrequencies(lineNumber, datafile);
			writeUpdatedTree(filename);
			
			if(doReport)
			 report(reportFileName,reportType, reportToFile);
			br.close();
			System.out.println("File analyzed.");
			System.out.println("Total lines analyzed = "+lineNumber);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	/**
	 * This method loads existing tree from 'repository.ser' if it exists
	 * @param filename File containing the stored binary tree
	 * @throws IOException Thrown when File not found
	 * @throws ClassNotFoundException Thrown when stored object's class doesn't match definition of class it's being cast to
	 */
	private static void loadExistingTree(String filename) throws IOException, ClassNotFoundException{
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(filename)));
		wordTree = (BSTReferenceBased<Word>) ois.readObject();
		ois.close();
	}
	
	/**
	 * This method writes the updated tree to specified file
	 * @param filename Filename to write tree to 
	 * @throws IOException Thrown when File not found
	 * @throws ClassNotFoundException Thrown when stored object's class doesn't match definition of class it's being cast to
	 */
	private static void writeUpdatedTree(String filename) throws IOException, ClassNotFoundException{
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(filename)));
		oos.writeObject(wordTree);
		oos.close();
	}
	
	/**
	 * Updates the frequencies of words of current file in the tree
	 * @param totLines Total number of lines in current file
	 * @param datafile The file containing the data that's being analyzed
	 */
	private static void updateFrequencies(int totLines, String datafile) {
		Iterator<Word> iter = wordTree.inorderIterator();
		Iterator<Word> listIter = null;
		MyArrayList<Word> tempSameWordsList = new MyArrayList<>();
		int totalCount=0;
		String curWord = "";
		Word currentWord = null;
		
		while(iter.hasNext()) {
			currentWord = iter.next();
			if(currentWord.getWord().equals(curWord)) {
				
				// Making sure only current file words' frequencies are updated
					if(currentWord.getFileName().equals(datafile)) {
						totalCount++;
						tempSameWordsList.add(currentWord);
					}
			
			}
			else {
				// Frequency = no of words in current file / number of lines in current file
				listIter = tempSameWordsList.iterator();
				while(listIter.hasNext()) {
					listIter.next().setFrequency(1.0*totalCount/totLines);
				}
				
				tempSameWordsList.clear();
				
				// Making sure only current file words' frequencies are updated
				if(currentWord.getFileName().equals(datafile)) {
					tempSameWordsList.add(currentWord);
					totalCount = 1;				
					curWord = currentWord.getWord();
				}
			}
				
		}
		// takes care of last words
		listIter = tempSameWordsList.iterator();
		while(listIter.hasNext()) {
			listIter.next().setFrequency(1.0*totalCount/totLines);
		}
		
	}
	
	/**
	 * This method reports(prints to console/file) depending upon user choice
	 * @param filename File to print report output to
	 * @param reportType The type of report
	 * @param doReport Indicates whether user wants to print report to a file or console
	 */
	private static void report(String filename, String reportType, boolean doReport) {
		
		BSTReferenceBased<Word> storedTree = new BSTReferenceBased<>();
		PrintWriter pw = null;
		ObjectInputStream ois = null;
		try(FileInputStream fis = new FileInputStream("res\\repository.ser")) {
			if(filename != null && doReport == true) {
				File f = new File(filename);
				if(!f.exists()) {
					if(!f.getParentFile().exists())
						f.getParentFile().mkdirs();
					f.createNewFile();
				}
				pw = new PrintWriter(f);
			}
			Word cur = null;
			ois = new ObjectInputStream(fis);
			storedTree = (BSTReferenceBased<Word>) ois.readObject();
			Iterator<Word> iter = storedTree.inorderIterator();
		
			switch(reportType) {
				case "pf":
					while(iter.hasNext()) {
						cur = iter.next();
						if(!doReport)
							System.out.println("Word= "+cur.getWord() + ", File= "+cur.getFileName());
						else
							pw.println("Word= "+cur.getWord() + ", File= "+cur.getFileName());
					}
					break;
				case "pl":
					while(iter.hasNext()) {
						cur = iter.next();
						if(!doReport)
							System.out.println("Word= "+cur.getWord() + ", File= "+cur.getFileName()+", Line= "+cur.getLineNumber());
						else
							pw.println("Word= "+cur.getWord() + ", File= "+cur.getFileName()+", Line= "+cur.getLineNumber());
					}
					break;
				case "po":
					while(iter.hasNext()) {
						cur = iter.next();
						if(!doReport)
							System.out.println("Word= "+cur.getWord() + ", File= "+cur.getFileName()+", Line= "+cur.getLineNumber()+", Frequency= "+cur.getFrequency());
						else
							pw.println("Word= "+cur.getWord() + ", File= "+cur.getFileName()+", Line= "+cur.getLineNumber()+", Frequency= "+cur.getFrequency());
					}
					break;
				
			}
			ois.close();

		} catch(IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if(pw != null)
				pw.close();
		}
		
	}
}

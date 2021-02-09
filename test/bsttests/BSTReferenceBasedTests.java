/**
 * 
 */
package bsttests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import exceptions.TreeException;

import utilities.BSTReferenceBased;

/**
 * JUnit tests for BSTReferenceBased
 * @author Armaan Singh Klair
 *
 */
public class BSTReferenceBasedTests {

	private BSTReferenceBased<Integer> bst;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		this.bst = new BSTReferenceBased<>();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		this.bst = null;
	}


	/**
	 * Test method for {@link utilities.BSTReferenceBased#getRootData()}.
	 */
	@Test
	public void testGetRootDataThrowTreeException() {
		assertThrows(TreeException.class, () -> bst.getRootData());
	}

	/**
	 * Test method for {@link utilities.BSTReferenceBased#getRootData()}.
	 */
	@Test
	public void testGetRootData() {
		bst.add(1);
		try {
			assertTrue(bst.getRootData() == 1);
		} catch (TreeException e) {
			assertTrue(false);
		}
	}
	
	/**
	 * Test method for {@link utilities.BSTReferenceBased#getHeight()}.
	 */
	@Test
	public void testGetHeightEmpty() {
		assertTrue(bst.getHeight() == 0);
	}
	
	/**
	 * Test method for {@link utilities.BSTReferenceBased#getHeight()}.
	 */
	@Test
	public void testGetHeightNotEmpty() {
		bst.add(10);
		bst.add(2);
		bst.add(22);
		bst.add(1);
		assertTrue(bst.getHeight() == 3);
	}

	/**
	 * Test method for {@link utilities.BSTReferenceBased#size()}.
	 */
	@Test
	public void testSizeEmpty() {
		assertEquals(bst.size(), 0);
		assertTrue(bst.isEmpty());
	}

	/**
	 * Test method for {@link utilities.BSTReferenceBased#size()}.
	 */
	@Test
	public void testSizeNotEmpty() {
		bst.add(10);
		bst.add(2);
		bst.add(22);
		bst.add(1);		
		assertEquals(bst.size(), 4);
		assertEquals(bst.getHeight(), 3);
		assertFalse(bst.isEmpty());
	}
	
	/**
	 * Test method for {@link utilities.BSTReferenceBased#isEmpty()}.
	 */
	@Test
	public void testIsEmptyTrue() {
		assertTrue(bst.isEmpty());
	}
	
	/**
	 * Test method for {@link utilities.BSTReferenceBased#isEmpty()}.
	 */
	@Test
	public void testIsEmptyFalse() {
		bst.add(21);
		assertFalse(bst.isEmpty());
	}

	/**
	 * Test method for {@link utilities.BSTReferenceBased#clear()}.
	 */
	@Test
	public void testClearAlreadyEmpty() {
		assertTrue(bst.isEmpty());
		assertEquals(bst.size(), 0);
	}

	/**
	 * Test method for {@link utilities.BSTReferenceBased#clear()}.
	 */
	@Test
	public void testClear() {
		bst.add(10);
		bst.add(2);
		bst.add(22);
		bst.add(1);
		bst.clear();
		assertTrue(bst.isEmpty());
		assertEquals(bst.size(), 0);
	}
	
	/**
	 * Test method for {@link utilities.BSTReferenceBased#contains(java.lang.Comparable)}.
	 */
	@Test
	public void testContainsThrowTreeException() {
		assertThrows(TreeException.class,()->{
			bst.contains(2);
		});
	}
	
	/**
	 * Test method for {@link utilities.BSTReferenceBased#contains(java.lang.Comparable)}.
	 */
	@Test
	public void testContainsFalse() {
		bst.add(12);
		bst.add(13);
		bst.add(-1);
		try {
			assertFalse(bst.contains(22));
		} catch (TreeException e) {
			assertTrue(false);
		}
	}
	
	/**
	 * Test method for {@link utilities.BSTReferenceBased#contains(java.lang.Comparable)}.
	 */
	@Test
	public void testContainsTrue() {
		bst.add(21);
		bst.add(23);
		bst.add(40);
		try {
			assertTrue(bst.contains(23));
		} catch (TreeException e) {
			assertTrue(false);
		}
	}

	/**
	 * Test method for {@link utilities.BSTReferenceBased#getEntry(java.lang.Comparable)}.
	 */
	@Test
	public void testGetEntryThrowTreeException() {
		assertThrows(TreeException.class, ()->{
			bst.getEntry(23);
		});
	}
	
	/**
	 * Test method for {@link utilities.BSTReferenceBased#getEntry(java.lang.Comparable)}.
	 */
	@Test
	public void testGetEntryNull() {
		bst.add(21);
		try {
			assertEquals(bst.getEntry(221), null);
		} catch (TreeException e) {
			assertTrue(false);
		}
	}
	
	/**
	 * Test method for {@link utilities.BSTReferenceBased#getEntry(java.lang.Comparable)}.
	 */
	@Test
	public void testGetEntry() {
		bst.add(21);
		try {
			assertTrue(bst.getEntry(21).equals(21));
		} catch (TreeException e) {
			assertTrue(false);
		}
	}

	/**
	 * Test method for {@link utilities.BSTReferenceBased#add(java.lang.Comparable)}.
	 */
	@Test
	public void testAddThrowNullPointerException() {
		assertThrows(NullPointerException.class, ()->bst.add(null));
	}
	
	/**
	 * Test method for {@link utilities.BSTReferenceBased#add(java.lang.Comparable)}.
	 */
	@Test
	public void testAddOnlyRoot() {
		bst.add(21);
		assertEquals(bst.getHeight(), 1);
		assertEquals(bst.size(),1);
		try {
			assertTrue(bst.getEntry(21).equals(21));
		} catch (TreeException e) {
			assertTrue(false);
		}
	}
	
	/**
	 * Test method for {@link utilities.BSTReferenceBased#add(java.lang.Comparable)}.
	 */
	@Test
	public void testAddMultipleElements() {
		bst.add(21);
		bst.add(40);
		bst.add(30);
		bst.add(35);
		bst.add(34);
		assertEquals(bst.getHeight(), 5);
		assertEquals(bst.size(), 5);
		try {
		assertTrue(bst.getEntry(21).equals(21));
		assertTrue(bst.getEntry(40).equals(40));
		assertTrue(bst.getEntry(30).equals(30));
		assertTrue(bst.getEntry(35).equals(35));
		assertTrue(bst.getEntry(34).equals(34));
		
		} catch (TreeException e) {
			assertTrue(false);
		}
	}
	
	/**
	 * Test method for {@link utilities.BSTReferenceBased#add(java.lang.Comparable)}.
	 */
	@Test
	public void testAddDuplicateElements() {
		bst.add(21);
		bst.add(21);
		bst.add(21);
		bst.add(21);
		bst.add(21);
		assertEquals(bst.getHeight(), 5);
		assertEquals(bst.size(), 5);
		try {
		assertTrue(bst.getEntry(21).equals(21));
		} catch (TreeException e) {
			assertTrue(false);
		}
	}

	/**
	 * Test method for {@link utilities.BSTReferenceBased#inorderIterator()}.
	 */
	@Test
	public void testInorderIteratorEmpty() {
		adt.Iterator<Integer> iter = bst.inorderIterator();
		assertFalse(iter.hasNext());
	}
	
	/**
	 * Test method for {@link utilities.BSTReferenceBased#inorderIterator()}.
	 */
	@Test
	public void testInorderIteratorNotEmpty() {
		bst.add(23);
		bst.add(30);
		bst.add(40);
		bst.add(35);
		bst.add(2);
		bst.add(34);
		assertEquals(bst.getHeight(), 5);
		assertEquals(bst.size(), 6);
		adt.Iterator<Integer> iter = bst.inorderIterator();
		assertTrue(iter.next().equals(2));
		assertTrue(iter.next().equals(23));
		assertTrue(iter.next().equals(30));
		assertTrue(iter.next().equals(34));
		assertTrue(iter.next().equals(35));
		assertTrue(iter.next().equals(40));
		assertFalse(iter.hasNext());
	}
	
	
	/**
	 * Test method for {@link utilities.BSTReferenceBased#preorderIterator()}.
	 */
	@Test
	public void testPreorderIteratorEmpty() {
		adt.Iterator<Integer> iter = bst.preorderIterator();
		assertFalse(iter.hasNext());	
	}
	
	/**
	 * Test method for {@link utilities.BSTReferenceBased#preorderIterator()}.
	 */
	@Test
	public void testPreorderIteratorNotEmpty() {
		bst.add(23);
		bst.add(40);
		bst.add(30);
		bst.add(35);
		bst.add(34);
		assertEquals(bst.getHeight(), 5);
		assertEquals(bst.size(), 5);
		adt.Iterator<Integer> iter = bst.preorderIterator();		
		assertTrue(iter.next().equals(23));
		assertTrue(iter.next().equals(40));
		assertTrue(iter.next().equals(30));
		assertTrue(iter.next().equals(35));
		assertTrue(iter.next().equals(34));
		assertFalse(iter.hasNext());
	}
	
	/**
	 * Test method for {@link utilities.BSTReferenceBased#postorderIterator()}.
	 */
	@Test
	public void testPostorderIteratorEmpty() {
		adt.Iterator<Integer> iter = bst.postorderIterator();
		assertFalse(iter.hasNext());		
	}
	
	/**
	 * Test method for {@link utilities.BSTReferenceBased#postorderIterator()}.
	 */
	@Test
	public void testPostorderIteratorNotEmpty() {
		bst.add(23);
		bst.add(40);
		bst.add(30);
		bst.add(35);
		bst.add(34);
		assertEquals(bst.getHeight(), 5);
		assertEquals(bst.size(), 5);
		adt.Iterator<Integer> iter = bst.postorderIterator();
		assertTrue(iter.next().equals(34));
		assertTrue(iter.next().equals(35));
		assertTrue(iter.next().equals(30));
		assertTrue(iter.next().equals(40));
		assertTrue(iter.next().equals(23));
		assertFalse(iter.hasNext());
	}

}

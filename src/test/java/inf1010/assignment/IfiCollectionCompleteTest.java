package inf1010.assignment;

import org.testng.annotations.*;
import static org.testng.Assert.*;

import inf1010.lib.two.IfiCollection;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;




/** Extends IfiCollectionTest with remove-tests. */
public abstract class IfiCollectionCompleteTest extends IfiCollectionTest {

	@Test(dependsOnGroups = { "collection-core" },
				description="Tests if the remove() method works as specified in the interface.")
	public void remove() {
		int length = NAMES.length;
		for (String name : NAMES) {
			TestPerson p = new TestPerson(name);

			assertTrue(c.remove(p),
					"The remove() method does not return true when removing "
							+ "an existing item");
			assertFalse(c.contains(p), "Removing an item does not make "
					+ "contains(item) return false.");
			assertEquals(c.size(), --length,
					"Removing an item does not decrease the size of the collection.");
		}
	}

	@Test(dependsOnGroups = { "collection-core" },
			expectedExceptions = { NullPointerException.class },
			description="Test if remove() throws NullPointerException as documented.")
	public void removeNull() {
		c.remove(null);
		emptyc.remove(null);
	}


	/**
	 * Tests if calling remove() twice on an iterator throws IllegalStateException.
	 */
	@Test(dependsOnGroups = { "collection-core" },
			expectedExceptions = { IllegalStateException.class },
			description="Tests if calling remove() twice on an iterator throws IllegalStateException.")
	public void iteratorTestNextCallTwice() {
		
		Iterator <TestPerson> it = c.iterator();
		
		assertTrue(it.hasNext(), "hasNext returns false with elements in the collection!");
		
		it.remove();
		it.remove();
	}



	/**
	 * Tests if removal of the element at the specified index in the iterator
	 * works (used by the iteratorRemove* testcases).
	 * 
	 * @param i
	 *            The index of element you wish to remove.
	 * */
	protected void iteratorRemove(int i) {
		Iterator<TestPerson> it = c.iterator();
		assertNotNull(it, "The iterator() method returns null.");

		int count = -1;
		while (count < i) {
			// we assume that i is not larger than the number of
			// elements in the iterator.
			it.next();
			count++;
		}
		it.remove();
		assertEquals(c.size(), NAMES.length - 1, "Removing item number "
				+ count + "from the iterator does not "
				+ "decrease the size of the collection by 1.");
	}



	@Test(dependsOnGroups = { "collection-core" },
		description="Tests if removing the first item in the iterator decreases the size of the collection by one.")
	public void iteratorRemoveFirst() {
		iteratorRemove(0);
	}

	@Test(dependsOnGroups = { "collection-core" },
		description="Tests if removing an item from the middle of the iterator decreases the size of the collection by one.")
	public void iteratorRemoveMiddle() {
		iteratorRemove(NAMES.length / 2);
	}

	@Test(dependsOnGroups = { "collection-core" },
			description="Tests if removing the last item in the iterator decreases the size of the collection by one.")
	public void iteratorRemoveLast() {
		iteratorRemove(NAMES.length - 1);
	}

	@Test(expectedExceptions = { IllegalStateException.class },
			description="Tests if calling remove() on a iterator without first calling next() throws IllegalStateException.")
	public void iteratorRemoveWithoutNext() {
		c.iterator().remove();
	}

	@Test(dependsOnGroups = { "collection-core" },
			description="Tests if removal of all the elements in an iterator works")
	public void iteratorRemoveAll() {
		Iterator<TestPerson> it = c.iterator();
		while (it.hasNext()) {
			it.next();
			it.remove();
		}
		assertEquals(c.size(), 0,
				"Removing all items from the iterator does not decrease "
						+ "the size of the collection to 0.");
	}

}

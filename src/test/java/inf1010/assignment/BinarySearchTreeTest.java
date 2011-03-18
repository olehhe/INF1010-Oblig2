package inf1010.assignment;
import org.testng.annotations.*;
import static org.testng.Assert.*;


@Test
public class BinarySearchTreeTest extends IfiCollectionTest {

	@BeforeMethod(alwaysRun=true,
			description="Creates a BinarySearchTree containing a test-people used by all the tests. Fails if the constructor or add() crashes or throws an exception.")
	public void createTreeUsedByAllTests() {
		c = new BinarySearchTree<TestPerson>();
		emptyc = new BinarySearchTree<TestPerson>();
		for (String name : NAMES)
			c.add(new TestPerson(name));
	}
}

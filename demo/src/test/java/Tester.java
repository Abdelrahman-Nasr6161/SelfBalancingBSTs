import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import selfbalacingbsts.Trees.RedBlackTree;


public class Tester {
    private RedBlackTree tree;

    @Before
    public void setUp() {
        tree = new RedBlackTree();
    }

    @Test
    public void testInsertIntoEmptyTree() {
        assertTrue(tree.insert("apple"));
        assertTrue(tree.search("apple"));
        assertEquals(1, tree.getHeight());
    }

    @Test
    public void testInsertDuplicate() {
        assertTrue(tree.insert("banana"));
        assertFalse(tree.insert("banana"));
        assertEquals(1, tree.getHeight());
    }

    @Test
    public void testMultipleInserts() {
        assertTrue(tree.insert("dog"));
        assertTrue(tree.insert("cat"));
        assertTrue(tree.insert("fish"));
        assertTrue(tree.search("dog"));
        assertTrue(tree.search("cat"));
        assertTrue(tree.search("fish"));
        assertEquals(2, tree.getHeight());
    }

    @Test
    public void testDelete() {
        tree.insert("apple");
        tree.insert("banana");
        assertTrue(tree.delete("apple"));
        assertFalse(tree.search("apple"));
        assertTrue(tree.search("banana"));
    }

    @Test
    public void testDeleteNonExistent() {
        tree.insert("orange");
        assertFalse(tree.delete("apple"));
        assertTrue(tree.search("orange"));
    }

    @Test
    public void testSearchEmptyTree() {
        assertFalse(tree.search("anything"));
    }

    @Test
    public void testTreeBalance() {
        // Insert elements that would cause rotations
        tree.insert("5");
        tree.insert("3");
        tree.insert("7");
        tree.insert("1");
        tree.insert("4");
        tree.insert("6");
        tree.insert("8");
        // Height should be relatively balanced
        assertTrue(tree.getHeight() <= 4);
    }

    @Test
    public void testDeleteRoot() {
        tree.insert("root");
        tree.insert("left");
        tree.insert("right");
        assertTrue(tree.delete("root"));
        assertFalse(tree.search("root"));
        assertTrue(tree.search("left"));
        assertTrue(tree.search("right"));
    }

    @Test
    public void testInsertAndDeleteMany() {
        String[] words = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j"};
        for (String word : words) {
            assertTrue(tree.insert(word));
        }
        for (String word : words) {
            assertTrue(tree.delete(word));
        }
        for (String word : words) {
            assertFalse(tree.search(word));
        }
    }

    @Test
    public void testTreeProperties() {
        tree.insert("50");
        tree.insert("30");
        tree.insert("70");
        tree.insert("20");
        tree.insert("40");
        
        // Test if tree maintains proper height
        assertTrue(tree.getHeight() >= 3);
        assertTrue(tree.getHeight() <= 4);
        
        // Test search functionality
        assertTrue(tree.search("50"));
        assertTrue(tree.search("30"));
        assertFalse(tree.search("60"));
    }
     @Test
public void testBatchInsertFromDataFile() {
    tree.batchInsert("data.txt");
    assertTrue(tree.getHeight() > 0); // Ensure tree isn't empty
    int height = tree.getHeight();
    assertTrue("Tree height too large for 1000 nodes: " + height, height <= 50);
}

    @Test
    public void testBatchDelete() {
        tree.batchInsert("data.txt");
    assertTrue(tree.getHeight() > 0); // Ensure tree isn't empty
    int height = tree.getHeight();
    assertTrue("Tree height too large for 1000 nodes: " + height, height <= 50);
    tree.batchDelete("data.txt");
    assertTrue(tree.getHeight() == 0);
    }
}

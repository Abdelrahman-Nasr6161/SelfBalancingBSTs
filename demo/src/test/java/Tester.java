import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import selfbalacingbsts.Trees.AVLTree;
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


    private AVLTree AVLtree;

    @Before
    public void AVLsetUp() {
        AVLtree = new AVLTree();
    }

    @Test
    public void AVLtestInsertIntoEmptyTree() {
        assertTrue(AVLtree.insert("apple"));
        assertTrue(AVLtree.search("apple"));
        assertEquals(1, AVLtree.getHeight());
    }

    @Test
    public void AVLtestInsertDuplicate() {
        assertTrue(AVLtree.insert("banana"));
        assertFalse(AVLtree.insert("banana"));
        assertEquals(1, AVLtree.getHeight());
    }

    @Test
    public void AVLtestMultipleInserts() {
        assertTrue(AVLtree.insert("dog"));
        assertTrue(AVLtree.insert("cat"));
        assertTrue(AVLtree.insert("fish"));
        assertTrue(AVLtree.search("dog"));
        assertTrue(AVLtree.search("cat"));
        assertTrue(AVLtree.search("fish"));
        assertEquals(1, AVLtree.getHeight());
    }

    @Test
    public void AVLtestDelete() {
        AVLtree.insert("apple");
        AVLtree.insert("banana");
        assertTrue(AVLtree.delete("apple"));
        assertFalse(AVLtree.search("apple"));
        assertTrue(AVLtree.search("banana"));
    }

    @Test
    public void AVLtestDeleteNonExistent() {
        AVLtree.insert("orange");
        assertFalse(AVLtree.delete("apple"));
        assertTrue(AVLtree.search("orange"));
    }

    @Test
    public void AVLtestSearchEmptyTree() {
        assertFalse(AVLtree.search("anything"));
    }

    @Test
    public void AVLtestTreeBalance() {
        // Insert elements that would cause rotations
        AVLtree.insert("5");
        AVLtree.insert("3");
        AVLtree.insert("7");
        AVLtree.insert("1");
        AVLtree.insert("4");
        AVLtree.insert("6");
        AVLtree.insert("8");
        // Height should be relatively balanced
        assertTrue(AVLtree.getHeight() <= 3);
    }

    @Test
    public void AVLtestDeleteRoot() {
        AVLtree.insert("root");
        AVLtree.insert("left");
        AVLtree.insert("right");
        assertTrue(AVLtree.delete("root"));
        assertFalse(AVLtree.search("root"));
        assertTrue(AVLtree.search("left"));
        assertTrue(AVLtree.search("right"));
    }

    @Test
    public void AVLtestInsertAndDeleteMany() {
        String[] words = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j"};
        for (String word : words) {
            assertTrue(AVLtree.insert(word));
        }
        for (String word : words) {
            assertTrue(AVLtree.delete(word));
        }
        for (String word : words) {
            assertFalse(AVLtree.search(word));
        }
    }


    @Test
    public void AVLtestTreeProperties() {
        AVLtree.insert("50");
        AVLtree.insert("30");
        AVLtree.insert("70");
        AVLtree.insert("20");
        AVLtree.insert("40");
        
        // Test if tree maintains proper height
        assertTrue(AVLtree.getHeight() >= 2);
        assertTrue(AVLtree.getHeight() <= 3);
        
        // Test search functionality
        assertTrue(AVLtree.search("50"));
        assertTrue(AVLtree.search("30"));
        assertFalse(AVLtree.search("60"));
    }


    @Test
    public void AVLtestRotation() {
        AVLtree.insert("10");
        AVLtree.insert("20");
        AVLtree.insert("30");
        assertEquals(1, AVLtree.getHeight());
    }


     @Test
public void AVLtestBatchInsertFromDataFile() {
    AVLtree.batchInsert("data.txt");
    assertTrue(AVLtree.getHeight() > 0); // Ensure tree isn't empty
    int height = AVLtree.getHeight();
    assertTrue("Tree height too large for 1000 nodes: " + height, height <= 14);
}

    @Test
    public void AVLtestBatchDelete() {
        AVLtree.batchInsert("data.txt");
    assertTrue(AVLtree.getHeight() > 0); // Ensure tree isn't empty
    int height = AVLtree.getHeight();
    assertTrue("Tree height too large for 1000 nodes: " + height, height <= 50);
    AVLtree.batchDelete("data.txt");
    assertTrue(AVLtree.getHeight() == -1);
    }
}

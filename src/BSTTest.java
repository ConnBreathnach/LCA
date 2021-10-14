import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

//-------------------------------------------------------------------------
/**
 *  Test class for Doubly Linked List
 *
 *  @version 3.1 09/11/15 11:32:15
 *
 *  @author  Conn Breathnach
 *
 *
 */

@RunWith(JUnit4.class)
public class BSTTest
{

    @Test
    public void testEmpty()
    {
        BST<Integer, Integer> bst = new BST<Integer, Integer>();
        assertTrue("Testing isEmpty on empty tree", bst.isEmpty());
        bst.put(1, 1);
        assertFalse("Testing isEmpty on tree with 1 node", bst.isEmpty());
        bst.put(2, 2);
        bst.put(3, 3);
        assertFalse("Testing isEmpty on tree with 3 nodes", bst.isEmpty());
    }

    @Test
    public void testSize()
    {
        BST<Integer, Integer> bst = new BST<Integer, Integer>();
        assertEquals("Testing size on empty tree", 0, bst.size());
        bst.put(1, 1);
        assertEquals("Testing size on empty tree", 1, bst.size());
        bst.put(3, 3);
        assertEquals("Testing size on empty tree", 2, bst.size());
    }

    @Test
    public void testContains()
    {
        BST<Integer, Integer> bst = new BST<Integer, Integer>();
        assertFalse("Testing contains on empty tree", bst.contains(2));
        bst.put(2, 2);
        assertTrue("Testing contains for true", bst.contains(2));
        assertFalse("Testing contains for false", bst.contains(5));
    }

    @Test
    public void testGet()
    {
        BST<Integer, Integer> bst = new BST<Integer, Integer>();
        assertNull("Testing get on empty tree", bst.get(2));
        bst.put(2, 2);
        assertEquals("Testing get for item in tree", (Integer)2, bst.get(2));
        assertNull("Testing get for item not in tree", bst.get(5));
        assertNull("Testing get for item not in tree", bst.get(1));
    }


    @Test
    public void testPut()
    {
        BST<Integer, Integer> bst = new BST<Integer, Integer>();
        bst.put(2, null);
        assertFalse("Testing put when input value is null", bst.contains(2));
        bst.put(3, 3);
        assertTrue("Testing put for normal input", bst.contains(3));
        bst.put(3, 4);
        assertEquals("Testing put to change value in node", (Integer)4, bst.get(3));
    }




    /** <p>Test {@link BST#prettyPrintKeys()}.</p> */

    @Test
    public void testPrettyPrint() {
        BST<Integer, Integer> bst = new BST<Integer, Integer>();
        assertEquals("Checking pretty printing of empty tree",
                "-null\n", bst.prettyPrintKeys());

        //  -7
        //   |-3
        //   | |-1
        //   | | |-null
        bst.put(7, 7);       //   | |  -2
        bst.put(8, 8);       //   | |   |-null
        bst.put(3, 3);       //   | |    -null
        bst.put(1, 1);       //   |  -6
        bst.put(2, 2);       //   |   |-4
        bst.put(6, 6);       //   |   | |-null
        bst.put(4, 4);       //   |   |  -5
        bst.put(5, 5);       //   |   |   |-null
        //   |   |    -null
        //   |    -null
        //    -8
        //     |-null
        //      -null

        String result =
                "-7\n" +
                        " |-3\n" +
                        " | |-1\n" +
                        " | | |-null\n" +
                        " | |  -2\n" +
                        " | |   |-null\n" +
                        " | |    -null\n" +
                        " |  -6\n" +
                        " |   |-4\n" +
                        " |   | |-null\n" +
                        " |   |  -5\n" +
                        " |   |   |-null\n" +
                        " |   |    -null\n" +
                        " |    -null\n" +
                        "  -8\n" +
                        "   |-null\n" +
                        "    -null\n";
        assertEquals("Checking pretty printing of non-empty tree", result, bst.prettyPrintKeys());

        bst = new BST<Integer, Integer>();
        bst.put(1, 1);
        result =
                "-1\n" +
                        " |-null\n" +
                        "  -null\n";
        assertEquals("Checking pretty printing of single node tree", result, bst.prettyPrintKeys());
    }


    /** <p>Test {@link BST#delete(Comparable)}.</p> */
    @Test
    public void testDelete() {
        BST<Integer, Integer> bst = new BST<Integer, Integer>();
        bst.delete(1);
        assertEquals("Deleting from empty tree", "()", bst.printKeysInOrder());

        bst.put(7, 7);   //        _7_
        bst.put(8, 8);   //      /     \
        bst.put(3, 3);   //    _3_      8
        bst.put(1, 1);   //  /     \
        bst.put(2, 2);   // 1       6
        bst.put(6, 6);   //  \     /
        bst.put(4, 4);   //   2   4
        bst.put(5, 5);   //        \
        //         5

        assertEquals("Checking order of constructed tree",
                "(((()1(()2()))3((()4(()5()))6()))7(()8()))", bst.printKeysInOrder());

        bst.delete(9);
        assertEquals("Deleting non-existent key",
                "(((()1(()2()))3((()4(()5()))6()))7(()8()))", bst.printKeysInOrder());

        bst.delete(8);
        assertEquals("Deleting leaf", "(((()1(()2()))3((()4(()5()))6()))7())", bst.printKeysInOrder());

        bst.delete(6);
        assertEquals("Deleting node with single child",
                "(((()1(()2()))3(()4(()5())))7())", bst.printKeysInOrder());

        bst.delete(3);
        assertEquals("Deleting node with two children",
                "(((()1())2(()4(()5())))7())", bst.printKeysInOrder());
    }

    @Test
    public void testHeight() {
        BST<Integer, Integer> bst = new BST<Integer, Integer>();
        bst.put(7, 7);   //        _7_
        bst.put(8, 8);   //      /     \
        bst.put(3, 3);   //    _3_      8
        bst.put(1, 1);   //  /     \
        bst.put(2, 2);   // 1       6
        bst.put(6, 6);   //  \     /
        bst.put(4, 4);   //   2   4
        bst.put(5, 5);   //        \
        //         5
        assertEquals("BST height of 4 with 8 nodes", 4, bst.height());
        bst = new BST<Integer, Integer>();
        bst.put(7, 7);   //        _7_
        bst.put(8, 8);   //      /     \
        bst.put(3, 3);   //    _3_      8
        assertEquals("BST height of 1 with 3 nodes", 1, bst.height());
        bst = new BST<Integer, Integer>();
        assertEquals("BST height on empty tree", -1, bst.height());
        bst.put(1, 1);
        assertEquals("BST height on tree with 1 node", 0, bst.height());
        bst = new BST<Integer, Integer>();
        bst.put(2, 2);   //        _2
        bst.put(1, 1);   //      /     \
        bst.put(3, 3);   //    _1_      3
        bst.put(4, 4);   //  			  \
        //			   4
        assertEquals("BST height on sample tree (height 2, nodes 4)", 2, bst.height());
    }


    @Test
    public void testPrintInOrder()
    {
        BST<Integer, Integer> bst = new BST<Integer, Integer>();
        bst.put(2, 2);   //        _2
        bst.put(1, 1);   //      /     \
        bst.put(3, 3);   //    _1_      3
        bst.put(4, 4);   //  			  \
        //			   4
        assertEquals("Printing keys in order with sample tree", "((()1())2(()3(()4())))", bst.printKeysInOrder());

        bst = new BST<Integer, Integer>();
        assertEquals("Printing keys in order on empty tree", "()", bst.printKeysInOrder());
        bst.put(3, 3);
        assertEquals("Printing keys in order on tree with single node", "(()3())", bst.printKeysInOrder());
    }

    @Test
    public void testMedian()
    {
        BST<Integer, Integer> bst = new BST<Integer, Integer>();
        assertEquals("Testing median on empty tree", null, bst.median());
        bst.put(1, 1);
        assertEquals("Testing median on tree with single node", (Integer)1, bst.median());
        bst = new BST<Integer, Integer>();
        bst.put(2, 2);   //        _2
        bst.put(1, 1);   //      /     \
        bst.put(3, 3);   //    _1_      3
        bst.put(4, 4);   //  			  \
        //			   4
        assertEquals("Testing median on tree with 4 nodes", (Integer)2, bst.median());
        bst = new BST<Integer, Integer>();
        bst.put(1, 1);
        bst.put(2, 2);
        bst.put(3, 3);
        bst.put(4, 4);
        bst.put(5, 5);
        bst.put(6, 6);
        assertEquals("Testing median on right tree with 6 nodes as linkedlist", (Integer)3, bst.median());
        bst = new BST<Integer, Integer>();
        bst.put(6, 6);
        bst.put(5, 5);
        bst.put(4, 4);
        bst.put(3, 3);
        bst.put(2, 2);
        bst.put(1, 1);
        assertEquals("Testing median on left tree with 6 nodes as linkedlist", (Integer)3, bst.median());

    }

    @Test
    public void testLCA()
    {
        BST<Integer, Integer> bst = new BST<Integer, Integer>();
        assertEquals("Testing LCA on empty tree", null, bst.lowestCommonAncestor(1, 2));
        bst.put(2, 2);   //        _2
        bst.put(1, 1);   //      /     \
        bst.put(3, 3);   //    _1_      3
        bst.put(4, 4);   //  			  \
        assertEquals("Testing LCA on tree with root as LCA", (Integer)2, bst.lowestCommonAncestor(1, 3));
        bst = new BST<Integer, Integer>();
        bst.put(6, 6);
        bst.put(5, 5);
        bst.put(4, 4);
        bst.put(3, 3);
        bst.put(2, 2);
        bst.put(1, 1);
        assertEquals("Testing LCA on tree that is essentially linkedlist", (Integer)3, bst.lowestCommonAncestor(1, 3));
        bst = new BST<Integer, Integer>();
        bst.put(1, 1);
        bst.put(3, 3);
        bst.put(2, 2);
        bst.put(4, 4);
        bst.put(0, 0);
        bst.put(5, 5);
        assertEquals("Testing LCA on larger tree with root not as LCA", (Integer)3, bst.lowestCommonAncestor(2, 4));
    }
}

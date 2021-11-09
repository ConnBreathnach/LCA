import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class DAGTest {

    @Test
    public void testEmpty()
    {
        DAG<Integer, Integer> graph = new DAG<Integer, Integer>();
        assertTrue("Testing empty DAG", graph.isEmpty());
        Node root = new Node(1, 1);
        graph.add(root);
        assertFalse("Testing empty on DAG with root", graph.isEmpty());
        Node firstNode = new Node(2, 2);
        graph.connect(root, firstNode);
        assertFalse("Testing empty on DAG with connected root and node", graph.isEmpty());
    }

    @Test
    public void testContains()
    {
        DAG<Integer, Integer> graph = new DAG<Integer, Integer>();
        assertFalse("Testing contains on empty graph", graph.contains(3));
        Node root = new Node(1, 1);
        graph.add(root);
        assertTrue("Testing contains when it should be true, with root node", graph.contains(1));
        assertFalse("Testing contains with only root but not the node we want", graph.contains(2));
        Node firstNode = new Node(2, 2);
        graph.connect(root, firstNode);
        assertTrue("Testing contains with connected node", graph.contains(2));
    }

    @Test
    public void testConnected()
    {
        DAG<Integer, Integer> graph = new DAG<Integer, Integer>();
        Node root = new Node(1, 1);
        graph.add(root);
        Node firstNode = new Node(2, 2);
        graph.connect(root, firstNode);
        assertTrue("Testing connected on connected nodes", graph.isConnected(root, firstNode));
        assertTrue("Testing connected with node that is itself", graph.isConnected(root, root));
        assertFalse("Testing connected when connected is directed wrong way", graph.isConnected(firstNode, root));
        Node secondNode = new Node(3, 3);
        assertFalse("Testing connected when one node does not exits", graph.isConnected(firstNode, secondNode));
    }

    @Test
    public void LCA()
    {
        DAG<Integer, Integer> graph = new DAG<Integer, Integer>();
        Node root = new Node(0, 0);
        graph.add(root);
        Node firstNode = new Node(1, 1);
        graph.connect(root, firstNode);
        Node secondNode = new Node(2, 2);
        graph.connect(root, secondNode);
        Node thirdNode = new Node(3, 3);
        graph.connect(firstNode, thirdNode);
        Node fourthNode = new Node(4, 4);
        graph.connect(secondNode, fourthNode);
        Node finalNode = new Node(5, 5);
        graph.connect(thirdNode, finalNode);
        graph.connect(fourthNode, finalNode);
        ArrayList<Node> firstLCAAnswer = graph.LCA(thirdNode, fourthNode);
        ArrayList<Node> secondLCAAnswer = graph.LCA(thirdNode, finalNode);
        assertEquals("Testing LCA on connected graph with root as LCA", root, firstLCAAnswer.get(0));
        assertEquals("Testing LCA with one of nodes being LCA", thirdNode, secondLCAAnswer.get(0));
        //       0
        //     /   \
        //    1     2
        //    |     |
        //    3     4
        //     \   /
        //       5

        //This graph looks like the wikipedia example Professor Barrett showed us
        DAG<Integer, Integer> secondGraph = new DAG<Integer, Integer>();
        Node root = new Node(0, 0); // a
        Node firstNode = new Node(1, 1); // b
        Node secondNode = new Node(2, 2); // c
        Node thirdNode = new Node(3, 3); // d
        Node fourthNode = new Node(4, 4); // e

        secondGraph.add(root); // a
        secondGraph.connect(root, firstNode); // a->b
        secondGraph.connect(root, secondNode); // a->c
        secondGraph.connect(root, thirdNode); // a-> d
        secondGraph.connect(root, fourthNode); // a -> e
        secondGraph.connect(firstNode, thirdNode); // b -> d
        secondGraph.connect(secondNode, thirdNode); // c -> d
        secondGraph.connect(secondNode, fourthNode); // c -> e
        secondGraph.connect(thirdNode, fourthNode); // d -> e

        Node[] firstAnswerNodes = {root, secondNode};
        assertArrayEquals("Testing LCA on wikipedia graph", firstAnswerNodes, secondGraph.LCA(thirdNode, fourthNode));
    }


}



/*************************************************************************
 *  Binary Search Tree class.
 *  Adapted from Sedgewick and Wayne.
 *
 *  @version 3.0 1/11/15 16:49:42
 *
 *  @author Conn Breathnach
 *
 *************************************************************************/

import java.util.NoSuchElementException;

public class BST<Key extends Comparable<Key>, Value> {
    private Node root;             // root of BST

    /**
     * Private node class.
     */
    private class Node {
        private Key key;           // sorted by key
        private Value val;         // associated data
        private Node left, right;  // left and right subtrees
        private int N;             // number of nodes in subtree

        public Node(Key key, Value val, int N) {
            this.key = key;
            this.val = val;
            this.N = N;
        }
    }

    // is the symbol table empty?
    public boolean isEmpty() { return size() == 0; }

    // return number of key-value pairs in BST
    public int size() { return size(root); }

    // return number of key-value pairs in BST rooted at x
    private int size(Node x) {
        if (x == null) return 0;
        else return x.N;
    }

    /**
     *  Search BST for given key.
     *  Does there exist a key-value pair with given key?
     *
     *  @param key the search key
     *  @return true if key is found and false otherwise
     */
    public boolean contains(Key key) {
        return get(key) != null;
    }

    /**
     *  Search BST for given key.
     *  What is the value associated with given key?
     *
     *  @param key the search key
     *  @return value associated with the given key if found, or null if no such key exists.
     */
    public Value get(Key key) { return get(root, key); }

    private Value get(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if      (cmp < 0) return get(x.left, key);
        else if (cmp > 0) return get(x.right, key);
        else              return x.val;
    }

    /**
     *  Insert key-value pair into BST.
     *  If key already exists, update with new value.
     *
     *  @param key the key to insert
     *  @param val the value associated with key
     */
    public void put(Key key, Value val) {
        if (val == null) { delete(key); return; }
        root = put(root, key, val);
    }

    private Node put(Node x, Key key, Value val) {
        if (x == null) return new Node(key, val, 1);
        int cmp = key.compareTo(x.key);
        if      (cmp < 0) x.left  = put(x.left,  key, val);
        else if (cmp > 0) x.right = put(x.right, key, val);
        else              x.val   = val;
        x.N = 1 + size(x.left) + size(x.right);
        return x;
    }

    /**
     * Tree height.
     *
     * Asymptotic worst-case running time using Theta notation: Theta(N)
     *
     * @return the number of links from the root to the deepest leaf.
     *
     * Example 1: for an empty tree this should return -1.
     * Example 2: for a tree with only one node it should return 0.
     * Example 3: for the following tree it should return 2.
     *   B
     *  / \
     * A   C
     *      \
     *       D
     */
    public int height() {
        //Test against base cases
        if(isEmpty()) {
            return -1;
        }
        else if(size() == 1)
        {
            return 0;
        }

        return height(root);
    }

    //recursive algorithm to find height of specific node by adding heights of child nodes with greatest height
    private int height(Node x)
    {
        int height = 0;
        if(x == null)
        {
            return 0;
        }
        else if(size(x) == 1)
        {
            return 0;
        }
        else
        {
            //If node has child(ren), it has a height greater than 0. The height is the max height of the children plus 1
            height += 1;
            return height + Math.max(height(x.left), height(x.right));
        }
    }

    /**
     * Median key.
     * If the tree has N keys k1 < k2 < k3 < ... < kN, then their median key 
     * is the element at position (N+1)/2 (where "/" here is integer division)
     *
     * @return the median key, or null if the tree is empty.
     */
    //Code adapted from lecture slides 13, from the select method
    public Key median() {
        if (isEmpty()) return null;
        if(size(root) == 1)
        {
            return root.key;
        }
        int medianPosition = (size()-1)/2;
        Node medianNode = median(medianPosition, root);
        return medianNode.key;
    }

    private Node median(int medianPos, Node base)
    {
        int leftSize = size(base.left);
        //Find out which subtree the median is in, and traverse that recursively until median node is found
        if(leftSize > medianPos)
        {
            return median(medianPos, base.left);
        }
        else if(leftSize < medianPos)
        {
            return median(medianPos - leftSize - 1, base.right);
        }
        else
        {
            return base;
        }
    }
    /**
     * Print all keys of the tree in a sequence, in-order.
     * That is, for each node, the keys in the left subtree should appear before the key in the node.
     * Also, for each node, the keys in the right subtree should appear before the key in the node.
     * For each subtree, its keys should appear within a parenthesis.
     *
     * Example 1: Empty tree -- output: "()"
     * Example 2: Tree containing only "A" -- output: "(()A())"
     * Example 3: Tree:
     *   B
     *  / \
     * A   C
     *      \
     *       D
     *
     * output: "((()A())B(()C(()D())))"
     *
     * output of example in the assignment: (((()A(()C()))E((()H(()M()))R()))S(()X()))
     *
     * @return a String with all keys in the tree, in order, parenthesized.
     */
    public String printKeysInOrder() {
        if (isEmpty()) return "()";
        return printNode(root);

    }
    //Recursively return String of nodes with their expected format
    private String printNode(Node x)
    {
        if(x == null)
        {
            return "()";
        }
        else
        {
            return "(" + printNode(x.left) + x.key + printNode(x.right) + ")";
        }
    }

    /**
     * Pretty Printing the tree. Each node is on one line -- see assignment for details.
     *
     * @return a multi-line string with the pretty ascii picture of the tree.
     */
    public String prettyPrintKeys() {
        return printPrettyNode(root, "");
    }
    //Add to prefix each time recursively, and change for left and right prefix
    private String printPrettyNode(Node x, String prefix)
    {
        if(x == null)
        {
            return prefix + "-null\n";
        }
        else
        {
            String leftPrefix = prefix + " " + "|";
            String rightPrefix = prefix + " " + " ";
            return prefix + "-" + x.key + "\n" + printPrettyNode(x.left, leftPrefix) + printPrettyNode(x.right, rightPrefix) ;
        }
    }

    /**
     * Deletes a key from a tree (if the key is in the tree).
     * Note that this method works symmetrically from the Hibbard deletion:
     * If the node to be deleted has two child nodes, then it needs to be
     * replaced with its predecessor (not its successor) node.
     *
     * @param key the key to delete
     */
    public void delete(Key key) {
        root = delete(root, key);
    }

    //Code modified from deleteMin code in lecture 13
    private Node deleteMax(Node x)
    {
        if(x.right == null) return x.left;
        x.right = deleteMax(x.right);
        x.N = 1 + size(x.left) + size(x.right);
        return x;
    }

    //Hibbard deletion modified from lecture 13 but with predecessor rather than successor
    private Node delete(Node x, Key key)
    {
        if(x == null) return null;
        int cmp = key.compareTo(x.key);
        if(cmp < 0)
        {
            x.left = delete(x.left, key);
        }
        else if(cmp > 0)
        {
            x.right = delete(x.right, key);
        }
        else
        {
            if(x.right == null) return x.left;
            if(x.left == null) return x.right;
            Node t = x;
            x = getMax(t.left);
            x.left = deleteMax(t.left);
            x.right = t.right;
        }

        x.N = 1 + size(x.left) + size(x.right);
        return x;
    }

    //Traverse right links until null is reached to get max
    private Node getMax(Node x)
    {
        if(x.right == null)
        {
            return x;
        }
        return x.right;
    }

    public Value lowestCommonAncestor(Key nodeOne, Key nodeTwo)
    {
        Node LCA = lowestCommonAncestor(root, nodeOne, nodeTwo);
        return (LCA == null) ? null : LCA.val;
    }

    private Node lowestCommonAncestor(Node currentNode, Key nodeOne, Key nodeTwo)
    {
        if(currentNode == null)
        {
            return null;
        }

        if(currentNode.key == nodeOne || currentNode.key == nodeTwo)
        {
            return currentNode;
        }

        Node leftNode = lowestCommonAncestor(currentNode.left, nodeOne, nodeTwo);
        Node rightNode = lowestCommonAncestor(currentNode.right, nodeOne, nodeTwo);

        if(leftNode != null && rightNode != null)
        {
            return currentNode;
        }

        return (leftNode != null) ? leftNode : rightNode;

    }
}
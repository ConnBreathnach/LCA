import java.util.ArrayList;
import java.util.HashMap;

public class DAG<Key extends Comparable<Key>, Value>
{
    private Node root;
    public HashMap<Key, Node> nodes;
    public class Node
    {
        Key key;
        Value val;
        ArrayList<Node> children;

        Node(Key key, Value val)
        {
            this.key = key;
            this.val = val;
        }

        void addChild(Node childNode)
        {
            this.children.add(childNode);
        }

    }

    public boolean isEmpty() { return root == null; }; // If root isn't set then we know it is an empty graph

    public void add(Node node)
    {
        if(root == null)
        {
            root = node;
            nodes.put(node.key, node);
        }
        else if(!nodes.containsKey(node.key))
        {
            nodes.put(node.key, node);
        }
    }

    public void connect(Key firstKey, Key secondKey)
    {
        Node firstNode = nodes.get(firstKey);
        Node secondNode = nodes.get(secondKey);
        firstNode.addChild(secondNode);
    }

    public boolean contains(Key nodeKey)
    {
        return nodes.containsKey(nodeKey);
    }


    public ArrayList<Node> LCA(Node firstKey, Node secondKey)
    {
        return null;
    }
}

package selfbalacingbsts.Nodes;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Node {
    public enum Color
    {
        RED,
        BLACK
    }
    private Node left;
    private Node right;
    private String word;
    private Node parent;
    private Color color;
    public Node(String word)
    {
        this.left = null;
        this.right = null;
        this.word = word;
        this.color = Color.RED;
        this.parent = null;
    }
    public Node(String word , Node parent)
    {
        this.left = null;
        this.right = null;
        this.word = word;
        this.color = Color.RED;
        this.parent = parent;
    }
}

package selfbalacingbsts.Nodes;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Node {
    public enum Color {
        RED,
        BLACK
    }

    private Node left;
    private Node right;
    private String word;
    private Node parent;
    private Color color;
    private int height;

    public Node(String word) {
        this.left = null;
        this.right = null;
        this.word = word;
        this.color = Color.RED;
        this.parent = null;
        this.height = 1;
    }

    public Node(String word, Node parent) {
        this.left = null;
        this.right = null;
        this.word = word;
        this.color = Color.RED;
        this.parent = parent;
        this.height = 1;  // Added height initialization
    }

    // Explicit setters in case Lombok doesn't work
    public void setColor(Color color) {
        this.color = color;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}

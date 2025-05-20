package selfbalacingbsts.Trees;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import lombok.Getter;
import lombok.Setter;
import selfbalacingbsts.Nodes.Node;
import selfbalacingbsts.Nodes.Node.Color;

@Getter
@Setter
public class RedBlackTree implements SelfBalancingTrees {
    private Node root;

    public RedBlackTree() {
        this.root = null;
    }

    public RedBlackTree(String word) {
        this.root = new Node(word);
        this.root.setColor(Color.BLACK);
    }

    public void insert(String word) {
        Node newNode = new Node(word);
        newNode.setColor(Color.RED);

        if (root == null) {
            root = newNode;
            root.setColor(Color.BLACK);
            return;
        }

        Node parent = null;
        Node current = root;
        int hash = word.hashCode();

        while (current != null) {
            parent = current;
            if (hash < current.getWord().hashCode()) {
                current = current.getLeft();
            } else {
                current = current.getRight();
            }
        }

        newNode.setParent(parent);
        if (hash < parent.getWord().hashCode()) {
            parent.setLeft(newNode);
        } else {
            parent.setRight(newNode);
        }

        fixInsert(newNode);
    }

    private void fixInsert(Node node) {
        while (node != root && node.getParent().getColor() == Color.RED) {
            Node parent = node.getParent();
            Node grandparent = parent.getParent();

            if (parent == grandparent.getLeft()) {
                Node uncle = grandparent.getRight();
                if (uncle != null && uncle.getColor() == Color.RED) {
                    parent.setColor(Color.BLACK);
                    uncle.setColor(Color.BLACK);
                    grandparent.setColor(Color.RED);
                    node = grandparent;
                } else {
                    if (node == parent.getRight()) {
                        node = parent;
                        leftRotate(node);
                    }
                    parent.setColor(Color.BLACK);
                    grandparent.setColor(Color.RED);
                    rightRotate(grandparent);
                }
            } else {
                Node uncle = grandparent.getLeft();
                if (uncle != null && uncle.getColor() == Color.RED) {
                    parent.setColor(Color.BLACK);
                    uncle.setColor(Color.BLACK);
                    grandparent.setColor(Color.RED);
                    node = grandparent;
                } else {
                    if (node == parent.getLeft()) {
                        node = parent;
                        rightRotate(node);
                    }
                    parent.setColor(Color.BLACK);
                    grandparent.setColor(Color.RED);
                    leftRotate(grandparent);
                }
            }
        }
        root.setColor(Color.BLACK);
    }

    private void leftRotate(Node x) {
        Node y = x.getRight();
        x.setRight(y.getLeft());
        if (y.getLeft() != null)
            y.getLeft().setParent(x);
        y.setParent(x.getParent());

        if (x.getParent() == null)
            root = y;
        else if (x == x.getParent().getLeft())
            x.getParent().setLeft(y);
        else
            x.getParent().setRight(y);

        y.setLeft(x);
        x.setParent(y);
    }

    private void rightRotate(Node y) {
        Node x = y.getLeft();
        y.setLeft(x.getRight());
        if (x.getRight() != null)
            x.getRight().setParent(y);
        x.setParent(y.getParent());

        if (y.getParent() == null)
            root = x;
        else if (y == y.getParent().getLeft())
            y.getParent().setLeft(x);
        else
            y.getParent().setRight(x);

        x.setRight(y);
        y.setParent(x);
    }

    public void delete(String word) {
        Node node = searchNode(root, word.hashCode());
        if (node == null)
            return;

        Node y = node;
        Node x;
        Color originalColor = y.getColor();

        if (node.getLeft() == null) {
            x = node.getRight();
            transplant(node, node.getRight());
        } else if (node.getRight() == null) {
            x = node.getLeft();
            transplant(node, node.getLeft());
        } else {
            y = minimum(node.getRight());
            originalColor = y.getColor();
            x = y.getRight();

            if (y.getParent() == node) {
                if (x != null)
                    x.setParent(y);
            } else {
                transplant(y, y.getRight());
                y.setRight(node.getRight());
                if (y.getRight() != null)
                    y.getRight().setParent(y);
            }

            transplant(node, y);
            y.setLeft(node.getLeft());
            y.getLeft().setParent(y);
            y.setColor(node.getColor());
        }

        if (originalColor == Color.BLACK && x != null) {
            fixDelete(x);
        }
    }

    public void batchInsert(String path) {
        try (Scanner scanner = new Scanner(new File(path))) {
            while (scanner.hasNextLine()) {
                String word = scanner.nextLine().trim();
                if (!word.isEmpty()) {
                    insert(word);
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + path);
        }
    }

    public void batchDelete(String path) {
        try (Scanner scanner = new Scanner(new File(path))) {
            while (scanner.hasNextLine()) {
                String word = scanner.nextLine().trim();
                if (!word.isEmpty()) {
                    delete(word);
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + path);
        }
    }

    public boolean search(String word) {
        return searchNode(root, word.hashCode()) != null;
    }
    public void displayTree() {
    System.out.println("Tree (in-order):");
    inOrderTraversal(root);
    System.out.println();
}
    private void inOrderTraversal(Node node) {
        if (node != null) {
            inOrderTraversal(node.getLeft());
            System.out.print(node.getWord() + " ");
            inOrderTraversal(node.getRight());
        }
    }

    public int getHeight() {
        return computeHeight(root);
    }

    private int computeHeight(Node node) {
        if (node == null)
            return 0;
        return 1 + Math.max(computeHeight(node.getLeft()), computeHeight(node.getRight()));
    }

    private void fixDelete(Node x) {
        while (x != root && getColor(x) == Color.BLACK) {
            if (x == x.getParent().getLeft()) {
                Node w = x.getParent().getRight();
                if (getColor(w) == Color.RED) {
                    w.setColor(Color.BLACK);
                    x.getParent().setColor(Color.RED);
                    leftRotate(x.getParent());
                    w = x.getParent().getRight();
                }

                if (getColor(w.getLeft()) == Color.BLACK && getColor(w.getRight()) == Color.BLACK) {
                    w.setColor(Color.RED);
                    x = x.getParent();
                } else {
                    if (getColor(w.getRight()) == Color.BLACK) {
                        if (w.getLeft() != null)
                            w.getLeft().setColor(Color.BLACK);
                        w.setColor(Color.RED);
                        rightRotate(w);
                        w = x.getParent().getRight();
                    }

                    w.setColor(x.getParent().getColor());
                    x.getParent().setColor(Color.BLACK);
                    if (w.getRight() != null)
                        w.getRight().setColor(Color.BLACK);
                    leftRotate(x.getParent());
                    x = root;
                }
            } else {
                Node w = x.getParent().getLeft();
                if (getColor(w) == Color.RED) {
                    w.setColor(Color.BLACK);
                    x.getParent().setColor(Color.RED);
                    rightRotate(x.getParent());
                    w = x.getParent().getLeft();
                }

                if (getColor(w.getRight()) == Color.BLACK && getColor(w.getLeft()) == Color.BLACK) {
                    w.setColor(Color.RED);
                    x = x.getParent();
                } else {
                    if (getColor(w.getLeft()) == Color.BLACK) {
                        if (w.getRight() != null)
                            w.getRight().setColor(Color.BLACK);
                        w.setColor(Color.RED);
                        leftRotate(w);
                        w = x.getParent().getLeft();
                    }

                    w.setColor(x.getParent().getColor());
                    x.getParent().setColor(Color.BLACK);
                    if (w.getLeft() != null)
                        w.getLeft().setColor(Color.BLACK);
                    rightRotate(x.getParent());
                    x = root;
                }
            }
        }

        if (x != null)
            x.setColor(Color.BLACK);
    }

    private void transplant(Node u, Node v) {
        if (u.getParent() == null) {
            root = v;
        } else if (u == u.getParent().getLeft()) {
            u.getParent().setLeft(v);
        } else {
            u.getParent().setRight(v);
        }
        if (v != null)
            v.setParent(u.getParent());
    }

    private Node searchNode(Node node, int hash) {
        while (node != null) {
            int nodeHash = node.getWord().hashCode();
            if (hash == nodeHash)
                return node;
            else if (hash < nodeHash)
                node = node.getLeft();
            else
                node = node.getRight();
        }
        return null;
    }

    private Node minimum(Node node) {
        while (node.getLeft() != null) {
            node = node.getLeft();
        }
        return node;
    }

    private Color getColor(Node node) {
        return node == null ? Color.BLACK : node.getColor();
    }
}

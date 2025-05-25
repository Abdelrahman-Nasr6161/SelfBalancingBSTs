package selfbalacingbsts.Trees;
import selfbalacingbsts.Nodes.Node;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AVLTree implements SelfBalancingTrees {
    private Node root;
    private int size;

    public AVLTree() {
        this.root = null;
        this.size = 0;
    }

    public AVLTree(String word) {
        this.root = new Node(word);
    }

    private int calcHeight(Node node) {
        if (node == null) {
            return -1;
        }
        return 1 + Math.max(calcHeight(node.getLeft()), calcHeight(node.getRight()));
    }

    public boolean insert(String word){
        Node newNode = new Node(word);
        if(root == null){
            root = newNode;
            return true;
        }
        Node parent = null;
        Node current = root;
        int hashCode = word.hashCode();


        while(current != null) {
            parent = current;
            if (hashCode < current.getWord().hashCode()) {
                current = current.getLeft();
            } 
            else if (hashCode > current.getWord().hashCode()) {
                current = current.getRight();
            } 
            else {
                return false; 
            }
        }

        newNode.setParent(parent);
        if (hashCode < parent.getWord().hashCode()) {
            parent.setLeft(newNode);
        } 
        else{
            parent.setRight(newNode);
        }
        size++;

        Node checkNode = newNode;
        while(checkNode != null){
            checkNode.setHeight(1 +  Math.max(calcHeight(checkNode.getLeft()), calcHeight(checkNode.getRight())));
            int balanceFactor = calcHeight(checkNode.getLeft()) - calcHeight(checkNode.getRight());
            
            // System.out.println("+++++Node: " + checkNode.getWord() + " balance factor: " + balanceFactor);
            // System.out.println("-------Node left height: " + calcHeight(checkNode.getLeft()) + " Node right height: " + calcHeight(checkNode.getRight()) + " balance factor: " + balanceFactor);
            if(balanceFactor > 1 || balanceFactor < -1) {
                // System.out.println("+++++Node: " + checkNode.getWord() + " balance factor: " + balanceFactor);
                Node balanceNode = treeBalancing(checkNode, balanceFactor);
                if (balanceNode.getParent() == null) {
                    root = balanceNode;
                }
            } 
            checkNode = checkNode.getParent();
        }
        return true;

    }

    public Node treeBalancing(Node node, int balanceFactor){
        int leftBalance = 0;
        int rightBalance = 0;

        if (node.getLeft() != null) {
            leftBalance = calcHeight(node.getLeft().getLeft()) - calcHeight(node.getLeft().getRight());
        }
        if (node.getRight() != null) {
            rightBalance = calcHeight(node.getRight().getLeft()) - calcHeight(node.getRight().getRight());
        }

        if (balanceFactor > 1 && leftBalance >= 0) {
            // System.out.println("case LLR");
            return LLR(node); }

        else if (balanceFactor < -1 && rightBalance <= 0) {
            // System.out.println("case RRR");
            return  RRR(node); }

        else if (balanceFactor > 1 && leftBalance < 0) { 
            // System.out.println("case LRR");
            node.setLeft(RRR(node.getLeft())); 
            return LLR(node); 
        } 

        else if (balanceFactor < -1 && rightBalance > 0) {
            // System.out.println("case RLR");
            node.setRight(LLR(node.getRight())); 
            return RRR(node); 
        } 
        return node;
    }

    public Node LLR(Node node){

        // System.out.println("LLR");
        if(node == null || node.getLeft() == null) return node;

        Node leftNode = node.getLeft(); 
        Node rightTree = leftNode.getRight(); 
        Node parent = node.getParent();

        
        node.setLeft(rightTree); 

        if(rightTree != null) rightTree.setParent(node);
        leftNode.setRight(node); 
        leftNode.setParent(parent);
        node.setParent(leftNode);
        if (parent != null) {
            if (node.getWord().hashCode() < parent.getWord().hashCode()) {
                parent.setLeft(leftNode);
            } 
            else {
                parent.setRight(leftNode);
            }
            parent.setHeight(1 + Math.max(calcHeight(parent.getLeft()), calcHeight(parent.getRight())));
            // System.out.println("node: " + node.getWord() + "node parent:" + node.getParent().getWord() + " leftNode: " + leftNode.getWord() + "leftNode parent:" + leftNode.getParent().getWord());

            }
        else {
            root = leftNode;
        }
        // System.out.println("node: " + node.getParent().getWord() + " leftNode: " + leftNode.getWord());

        node.setHeight(1 + Math.max(calcHeight(node.getLeft()), calcHeight(node.getRight())));
        leftNode.setHeight(1 + Math.max(calcHeight(leftNode.getLeft()), calcHeight(leftNode.getRight())));
        
        return leftNode;
    }

    public Node RRR(Node node){

        // System.out.println("RRR");
        if(node == null || node.getRight() == null) return node;

        Node rightNode = node.getRight(); 
        Node leftTree = rightNode.getLeft(); 
        Node parent = node.getParent();

        node.setRight(leftTree); 


        if(leftTree != null) leftTree.setParent(node);
        rightNode.setLeft(node); 
        rightNode.setParent(parent);
        node.setParent(rightNode);

        if (parent != null) {
            if (node.getWord().hashCode() < parent.getWord().hashCode()) {
                parent.setLeft(rightNode);
            } 
            else {
                parent.setRight(rightNode);
            }
            parent.setHeight(1 + Math.max(calcHeight(parent.getLeft()), calcHeight(parent.getRight()))); // Update the height of the parent node
            
        // System.out.println("node: " + node.getWord() + "node parent:" + node.getParent().getWord() + " rightNode: " + rightNode.getWord() + "rightNode parent:" + rightNode.getParent().getWord());
        } 
        else {
            root = rightNode;
        }
        
        // System.out.println("node: " + node.getWord() + " rightNode: " + rightNode.getWord());
        node.setHeight(1 + Math.max(calcHeight(node.getLeft()), calcHeight(node.getRight())));
        rightNode.setHeight(1 + Math.max(calcHeight(rightNode.getLeft()), calcHeight(rightNode.getRight())));
        
        return rightNode; 
    }

    public boolean delete(String word){
        Node nodeToDelete = searchNode(root, word.hashCode());
        if(nodeToDelete == null) return false;

        Node parent = nodeToDelete.getParent();
        Node checkNode = parent;
        
        if(nodeToDelete.getLeft() == null && nodeToDelete.getRight() == null){
            if (parent == null) {
                root = null;
            } 
            else if (parent.getLeft() == nodeToDelete) {
                parent.setLeft(null);
            } 
            else {
                parent.setRight(null);
            }
        }
        else if(nodeToDelete.getLeft() == null || nodeToDelete.getRight() == null){
            Node child = (nodeToDelete.getLeft() != null) ? nodeToDelete.getLeft() : nodeToDelete.getRight();
            child.setParent(parent);
            if (parent == null) {
                root = child;
            } 
            else if (parent.getLeft() == nodeToDelete) {
                parent.setLeft(child);
            } 
            else {
                parent.setRight(child);
            }
        }
        else {
            // Leftmost of the right subtree
            Node grandchild = nodeToDelete.getRight();
            Node gcsParent = nodeToDelete;     // Grandchild's parent
            while (grandchild.getLeft() != null) {
                gcsParent = grandchild;
                grandchild = grandchild.getLeft();
            }
            
            nodeToDelete.setWord(grandchild.getWord());
            
            if (gcsParent == nodeToDelete) {
                gcsParent.setRight(grandchild.getRight());
                if (grandchild.getRight() != null) {
                    grandchild.getRight().setParent(gcsParent);
                }
            } 
            else {
                gcsParent.setLeft(grandchild.getRight());
                if (grandchild.getRight() != null) {
                    grandchild.getRight().setParent(gcsParent);
                }
            }
            checkNode = gcsParent;
        }
        size--;
        
        while (checkNode != null) {
            checkNode.setHeight(1 + Math.max( calcHeight(checkNode.getLeft()), calcHeight(checkNode.getRight())));
            int balanceFactor = calcHeight(checkNode.getLeft()) - calcHeight(checkNode.getRight());
            
            if (balanceFactor > 1 || balanceFactor < -1) {
                Node balanceNode = treeBalancing(checkNode, balanceFactor);
                if (balanceNode.getParent() == null) {
                    root = balanceNode;
                }
            }
            checkNode = checkNode.getParent();
        }
        return true;
    }

    public void batchInsert(String path) {
        try (Scanner scanner = new Scanner(new File(path))) {
            int newEntries = 0;
            int existingEntries = 0;
            while (scanner.hasNextLine()) {
                String word = scanner.nextLine().trim();
                if (!word.isEmpty()) {
                    boolean newEntry = insert(word);
                    if (newEntry)
                        newEntries++;
                    else
                        existingEntries++;
                }
            }
            System.out.println("Inserted " + newEntries + " entries in the tree");
            System.out.println(existingEntries + " entries already exist in the tree");
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + path);
        }
    }

    public void batchDelete(String path) {
        try (Scanner scanner = new Scanner(new File(path))) {
            int deletedEntries = 0;
            int nonExistingEntries = 0;
            while (scanner.hasNextLine()) {
                String word = scanner.nextLine().trim();
                if (!word.isEmpty()) {
                    boolean deleted = delete(word);
                    if (deleted)
                        deletedEntries++;
                    else
                        nonExistingEntries++;
                }
            }
            System.out.println("Deleted " + deletedEntries + " entries from the tree");
            System.out.println(nonExistingEntries + " entries don't exist in the tree");
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + path);
        }
    }

    public boolean search(String word) {
        return searchNode(root, word.hashCode()) != null;
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

    public void displayTree() {
        System.out.println("Tree (in-order):");
        inOrderTraversal(root);
    }

    private void inOrderTraversal(Node node) {
        if (node != null) {
            inOrderTraversal(node.getLeft());
            System.out.print(node.getWord() + " ");
            inOrderTraversal(node.getRight());
        }
    }

    public int getHeight(){
        if(root == null) {
            return -1;
        }
        return root.getHeight();
    }

}
    

package selfbalacingbsts;

import selfbalacingbsts.Trees.RedBlackTree;

public class RedBlackTreeDemo {
    public static void main(String[] args) {
        RedBlackTree redBlackTree = new RedBlackTree();
        redBlackTree.insert("hello");
        System.out.println(String.format("added %s to the tree with hashcode %d", "hello" , "hello".hashCode()));
        redBlackTree.insert("brbs");
        System.out.println(String.format("added %s to the tree with hashcode %d", "brbs" , "brbs".hashCode()));
        redBlackTree.insert("hi");
        System.out.println(String.format("added %s to the tree with hashcode %d", "hi" , "hi".hashCode()));
        redBlackTree.insert("who");
        System.out.println(String.format("added %s to the tree with hashcode %d", "who" , "who".hashCode()));
        redBlackTree.displayTree();
        redBlackTree.delete("brbs");
        redBlackTree.displayTree();
        redBlackTree.delete("hello");
        redBlackTree.displayTree();
        redBlackTree.delete("hi");
        redBlackTree.displayTree();
        redBlackTree.delete("who");
        redBlackTree.displayTree();
        redBlackTree.batchInsert("data.txt");
        redBlackTree.displayTree();
        redBlackTree.batchDelete("data.txt");
        redBlackTree.displayTree();
        
    }
}

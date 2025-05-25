package selfbalacingbsts;

import java.util.Scanner;
import selfbalacingbsts.Trees.RedBlackTree;
import selfbalacingbsts.Trees.SelfBalancingTrees;
import selfbalacingbsts.Trees.AVLTree;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SelfBalancingTrees tree = null;

        System.out.println("Choose tree type: \n1. AVL Tree\n2. Red Black Tree");
        int mainChoice = -1;
        try {
            mainChoice = scanner.nextInt();
            scanner.nextLine();
        } catch (Exception e) {
            System.out.println("Invalid input");
            System.exit(1);
        }

        switch (mainChoice) {
            case 1:
                // throw new UnsupportedOperationException("not implemented yet");
                tree = new AVLTree();
                break;

            case 2:
                tree = new RedBlackTree();
                break;
            default:
                System.out.println("Invalid choice!");
                System.exit(1);

        }

        while (true) {
            int subChoice = -1;

            System.out.println(
                    "1. Insert a string \n2. Delete a string \n3. Search for a string \n4. Batch insert a list of strings \n5. Batch delete a list of strings \n6. Size of dictionary \n7. Height of tree \n8. Display tree \n9. Exit");

            String str;
            try {
                subChoice = scanner.nextInt();
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("Invalid input");
                scanner.nextLine();
                continue;
            }

            switch (subChoice) {
                case 1:
                    // throw new UnsupportedOperationException("insert should be boolean");
                    System.out.println("Enter the string to insert:");
                    str = scanner.nextLine();
                    boolean isInserted = tree.insert(str);
                    if (isInserted) {
                        System.out.println("String inserted succefully!\n");
                    } else {
                        System.out.println("String is already in the tree!\n");
                    }

                    break;

                case 2:
                    // throw new UnsupportedOperationException("delete should be boolean");
                    System.out.println("Enter the string to delete:");
                    str = scanner.nextLine();
                    boolean isDeleted = tree.delete(str);
                    if (isDeleted) {
                        System.out.println("String deleted succefully!\n");
                    } else {
                        System.out.println("String isn't in the tree!\n");
                    }

                    break;

                case 3:
                    System.out.println("Enter the string to search for:");
                    str = scanner.nextLine();
                    boolean exists = tree.search(str);
                    if (exists) {
                        System.out.println("String exists in the tree!\n");
                    } else {
                        System.out.println("String isn't in the tree!\n");
                    }

                    break;

                case 4:
                    System.out.println("Batch insert; Enter the path of the file (data.txt): ");
                    str = scanner.nextLine();
                    if (str.isEmpty()) {
                        str = "data.txt";
                    }
                    tree.batchInsert(str);

                    break;

                case 5:
                    System.out.println("Batch delete; Enter the path of the file (data.txt): ");
                    str = scanner.nextLine();
                    if (str.isEmpty()) {
                        str = "data.txt";
                    }
                    tree.batchDelete(str);

                    break;

                case 6:
                    throw new UnsupportedOperationException("getSize is not implemented yet.");
                // System.out.println("Size of the dictionary: " + tree.getSize());
                // break;

                case 7:
                    System.out.println("Height of the backend tree: " + tree.getHeight());
                    break;

                case 8:
                    tree.displayTree();
                    break;

                case 9:
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    subChoice = -1;
                    break;
            }

            if (subChoice == -1) {
                System.out.println("Invalid choice!\n");
                continue;
            }

        }

    }

}
import java.util.Scanner;

class Node {
    int value;
    Node left, right;

    public Node(int item) {
        value = item;
        left = right = null;
    }
}

public class BinarySearchTree {
    Node root;

    // Method to insert a new value into the BST
    public void getInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of values to insert: ");
        int count = scanner.nextInt();

        if (count <= 0) {
            System.out.println("Number of values must be greater than zero.");
            return;
        }

        System.out.print("Enter the value for the root node: ");
        int rootValue = scanner.nextInt();
        root = insertRec(null, rootValue);

        for (int i = 1; i < count; i++) {
            System.out.print("Enter value " + (i + 1) + ": ");
            int val = scanner.nextInt();
            insertRec(root, val);
        }
    }

    // Recursive function to insert a new value
    private Node insertRec(Node root, int value) {
        if (root == null) {
            System.out.println("Inserted " + value + " as a new node.");
            return new Node(value);
        }
        if (value < root.value) {
            System.out.println("Inserting " + value + " to the left of " + root.value);
            root.left = insertRec(root.left, value);
        } else if (value > root.value) {
            System.out.println("Inserting " + value + " to the right of " + root.value);
            root.right = insertRec(root.right, value);
        } else {
            System.out.println("Value " + value + " already exists in the tree.");
        }
        return root;
    }

    // Method to search for a value in the BST
    public boolean searchValue(int value) {
        return searchRec(root, value);
    }

    // Recursive function to search for a value
    private boolean searchRec(Node root, int value) {
        if (root == null) {
            return false;
        }
        if (value == root.value) {
            return true;
        }
        return value < root.value ? searchRec(root.left, value) : searchRec(root.right, value);
    }

    // Method to display the tree or its subtrees
    public void displayTree() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nDisplay Options:");
            System.out.println("1. Show Left Subtree");
            System.out.println("2. Show Right Subtree");
            System.out.println("3. Show Entire Tree");
            System.out.println("4. Exit Display");
            System.out.print("Enter your choice (1-4): ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.println("\nLeft Subtree:");
                    displayRec(root.left, "", "Left");
                    break;
                case "2":
                    System.out.println("\nRight Subtree:");
                    displayRec(root.right, "", "Right");
                    break;
                case "3":
                    System.out.println("\nEntire Tree:");
                    displayRec(root, "", "Root");
                    break;
                case "4":
                    return;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 4.");
            }
        }
    }

    // Recursive function to display the tree
    private void displayRec(Node node, String indent, String position) {
        if (node != null) {
            System.out.println(indent + "[" + position + "] - " + node.value);
            displayRec(node.left, indent + "   ", "Left ");
            displayRec(node.right, indent + "   ", "Right");
        } else {
            System.out.println(indent + "[" + position + "] - None");
        }
    }

    // Main method to run the BST program
    public static void main(String[] args) {
        BinarySearchTree tree = new BinarySearchTree();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Insert Values");
            System.out.println("2. Search Value");
            System.out.println("3. Display Tree");
            System.out.println("4. Exit");
            System.out.print("Enter your choice (1-4): ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    tree.getInput();
                    break;
                case "2":
                    System.out.print("Enter integer value to search: ");
                    try {
                        int val = Integer.parseInt(scanner.nextLine());
                        boolean found = tree.searchValue(val);
                        System.out.println("Value " + val + (found ? " found" : " not found") + " in the tree.");
                    } catch (NumberFormatException e) {
                        System.out.println("Please enter a valid integer.");
                    }
                    break;
                case "3":
                    tree.displayTree();
                    break;
                case "4":
                    System.out.println("Exiting program.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 4.");
            }
        }
    }
}
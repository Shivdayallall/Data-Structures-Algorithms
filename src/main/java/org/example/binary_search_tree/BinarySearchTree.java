package org.example.binary_search_tree;

public class BinarySearchTree {

    // =========================
    // 1) NODE (the building block)
    // =========================
    private static class Node {
        int value;      // the number stored in this node
        Node left;      // points to smaller numbers
        Node right;     // points to bigger numbers

        Node(int value) {
            this.value = value; // store the number
        }
    }

    // The top of the tree (the first node)
    private Node root;

    // =========================
    // 2) INSERT
    // =========================
    public void insert(int value) {
        root = insertRecursive(root, value);
    }

    private Node insertRecursive(Node current, int value) {
        // If we reached an empty spot, create a new node here
        if (current == null) {
            return new Node(value);
        }

        // If the new value is smaller, go left
        if (value < current.value) {
            current.left = insertRecursive(current.left, value);
        }
        // If the new value is bigger, go right
        else if (value > current.value) {
            current.right = insertRecursive(current.right, value);
        }
        // If it's equal, we do nothing (no duplicates in this BST)
        // else { do nothing }

        return current; // return the (possibly updated) node
    }

    // =========================
    // 3) SEARCH (contains)
    // =========================
    public boolean contains(int value) {
        return containsRecursive(root, value);
    }

    private boolean containsRecursive(Node current, int value) {
        // If we hit an empty spot, the value isn't in the tree
        if (current == null) return false;

        // If we found the value, return true
        if (value == current.value) return true;

        // Smaller? Search left. Bigger? Search right.
        if (value < current.value) {
            return containsRecursive(current.left, value);
        } else {
            return containsRecursive(current.right, value);
        }
    }

    // =========================
    // 4) DELETE (remove)
    // =========================
    public void remove(int value) {
        root = removeRecursive(root, value);
    }

    private Node removeRecursive(Node current, int value) {
        // If empty, nothing to remove
        if (current == null) return null;

        // Walk left or right to find the node
        if (value < current.value) {
            current.left = removeRecursive(current.left, value);
            return current;
        } else if (value > current.value) {
            current.right = removeRecursive(current.right, value);
            return current;
        }

        // ✅ Now: value == current.value → this is the node to delete

        // Case 1: No children
        if (current.left == null && current.right == null) {
            return null;
        }

        // Case 2: One child (right only)
        if (current.left == null) {
            return current.right;
        }

        // Case 2: One child (left only)
        if (current.right == null) {
            return current.left;
        }

        // Case 3: Two children
        // Replace current value with the smallest value in the right subtree
        int smallestValue = findMin(current.right);
        current.value = smallestValue;

        // Then remove that smallest value from the right subtree
        current.right = removeRecursive(current.right, smallestValue);

        return current;
    }

    // =========================
    // 5) MIN / MAX
    // =========================
    public int min() {
        if (root == null) throw new IllegalStateException("Tree is empty");
        return findMin(root);
    }

    private int findMin(Node current) {
        // Keep going left until there's no more left
        while (current.left != null) {
            current = current.left;
        }
        return current.value;
    }

    public int max() {
        if (root == null) throw new IllegalStateException("Tree is empty");
        Node current = root;
        // Keep going right until there's no more right
        while (current.right != null) {
            current = current.right;
        }
        return current.value;
    }

    // =========================
    // 6) TRAVERSALS (printing orders)
    // =========================
    public void inOrder() {
        inOrderRecursive(root);
        System.out.println();
    }

    private void inOrderRecursive(Node current) {
        if (current == null) return;
        inOrderRecursive(current.left);
        System.out.print(current.value + " ");
        inOrderRecursive(current.right);
    }

    public void preOrder() {
        preOrderRecursive(root);
        System.out.println();
    }

    private void preOrderRecursive(Node current) {
        if (current == null) return;
        System.out.print(current.value + " ");
        preOrderRecursive(current.left);
        preOrderRecursive(current.right);
    }

    public void postOrder() {
        postOrderRecursive(root);
        System.out.println();
    }

    private void postOrderRecursive(Node current) {
        if (current == null) return;
        postOrderRecursive(current.left);
        postOrderRecursive(current.right);
        System.out.print(current.value + " ");
    }

    // =========================
    // 7) HEIGHT
    // =========================
    public int height() {
        return heightRecursive(root);
    }

    private int heightRecursive(Node current) {
        // Empty tree has height -1 (common definition)
        if (current == null) return -1;

        int leftHeight = heightRecursive(current.left);
        int rightHeight = heightRecursive(current.right);

        return Math.max(leftHeight, rightHeight) + 1;
    }

    // =========================
    // 8) TEST IT
    // =========================
    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();

        bst.insert(10);
        bst.insert(5);
        bst.insert(15);
        bst.insert(2);
        bst.insert(7);
        bst.insert(20);

        System.out.print("In-order (sorted): ");
        bst.inOrder(); // 2 5 7 10 15 20

        System.out.println("Contains 7? " + bst.contains(7));   // true
        System.out.println("Contains 99? " + bst.contains(99)); // false

        System.out.println("Min: " + bst.min()); // 2
        System.out.println("Max: " + bst.max()); // 20
        System.out.println("Height: " + bst.height()); // 2

        bst.remove(10); // delete root (has two children)

        System.out.print("After removing 10, in-order: ");
        bst.inOrder();
    }
}

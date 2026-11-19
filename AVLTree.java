
import java.util.LinkedList;
import java.util.Queue;

/*
 * I certify that the Java code I am submitting is all my own work.
* None of the code is copied from any source or any person.
* I did not use AI to generate the code for my program.
* Signed: Trevis Tonan
* Date: 11/18/2026
 *
 * Author: Trevis Tonan
 * Course: CSS405 - Data Structures and Algorithms
 * Assignment: 4 - AVL Tree Word Frequency Analyzer
 * Date: November 18, 2026
 */

/**
 * Class Name: AVLTree
 * Description: Implements a self-balancing binary search tree.
 * Each node stores a word and its frequency. The tree uses
 * rotations to maintain balance after insertion and deletion.
 */
public class AVLTree {

    // Root node of the AVL tree.
    private Node root;

    /**
     * Method Name: height
     * Description: Gets the height of a node. A null node
     * has a height of zero.
     *
     * @param node The node whose height is being checked.
     * @return The height of the node.
     */
    private int height(Node node) {
        if (node == null) {
            return 0;
        }

        return node.height;
    }

    /**
     * Method Name: getBalance
     * Description: Calculates the difference between the
     * heights of the left and right subtrees.
     *
     * @param node The node being checked for balance.
     * @return The balance factor of the node.
     */
    private int getBalance(Node node) {
        if (node == null) {
            return 0;
        }

        return height(node.left) - height(node.right);
    }

    /**
     * Method Name: updateHeight
     * Description: Updates the height of a node using
     * the heights of its left and right children.
     *
     * @param node The node whose height is updated.
     */
    private void updateHeight(Node node) {
        node.height = 1 + Math.max(
            height(node.left), height(node.right)
        );
    }

    /**
     * Method Name: rotateRight
     * Description: Performs a right rotation to correct
     * a left-heavy subtree while maintaining BST order.
     *
     * @param node The root of the unbalanced subtree.
     * @return The new root after rotation.
     */
    private Node rotateRight(Node node) {

        // Save the left child and its right subtree.
        Node newRoot = node.left;
        Node middle = newRoot.right;

        // Rearrange references to perform the rotation.
        newRoot.right = node;
        node.left = middle;

        // Update heights from the bottom upward.
        updateHeight(node);
        updateHeight(newRoot);

        return newRoot;
    }

    /**
     * Method Name: rotateLeft
     * Description: Performs a left rotation to correct
     * a right-heavy subtree while maintaining BST order.
     *
     * @param node The root of the unbalanced subtree.
     * @return The new root after rotation.
     */
    private Node rotateLeft(Node node) {

        // Save the right child and its left subtree.
        Node newRoot = node.right;
        Node middle = newRoot.left;

        // Rearrange references to perform the rotation.
        newRoot.left = node;
        node.right = middle;

        // Update heights from the bottom upward.
        updateHeight(node);
        updateHeight(newRoot);

        return newRoot;
    }

    /**
     * Method Name: rebalance
     * Description: Updates a node's height and performs
     * rotations when its balance factor exceeds AVL limits.
     * Handles LL, LR, RR, and RL imbalance cases.
     *
     * @param node The subtree root being rebalanced.
     * @return The root of the balanced subtree.
     */
    private Node rebalance(Node node) {

        // Recalculate height after a structural change.
        updateHeight(node);
        int balance = getBalance(node);

        // Handle left-heavy subtrees.
        if (balance > 1) {

            // Convert an LR case into an LL case.
            if (getBalance(node.left) < 0) {
                node.left = rotateLeft(node.left);
            }

            return rotateRight(node);
        }

        // Handle right-heavy subtrees.
        if (balance < -1) {

            // Convert an RL case into an RR case.
            if (getBalance(node.right) > 0) {
                node.right = rotateRight(node.right);
            }

            return rotateLeft(node);
        }

        return node;
    }

    /**
     * Method Name: insert
     * Description: Inserts a word into the AVL tree.
     * Updates the root if a rotation changes its position.
     *
     * @param key The word being inserted.
     */
    public void insert(String key) {
        root = insert(root, key);
    }

    /**
     * Method Name: insert
     * Description: Recursively finds the correct position
     * for a word. Increments the count if it already exists
     * and rebalances the tree after a new insertion.
     *
     * @param node The current node being examined.
     * @param key The word being inserted.
     * @return The root of the updated subtree.
     */
    private Node insert(Node node, String key) {

        // Create a node when an empty position is found.
        if (node == null) {
            return new Node(key);
        }

        int comparison = key.compareTo(node.key);

        // Smaller words belong in the left subtree.
        if (comparison < 0) {
            node.left = insert(node.left, key);

        // Larger words belong in the right subtree.
        } else if (comparison > 0) {
            node.right = insert(node.right, key);

        // Repeated words increase the existing frequency.
        } else {
            node.count++;
            return node;
        }

        return rebalance(node);
    }

    /**
     * Method Name: search
     * Description: Searches the tree for a word by
     * comparing it with the keys of existing nodes.
     *
     * @param key The word being searched for.
     * @return The word frequency, or zero if not found.
     */
    public int search(String key) {
        Node current = root;

        // Follow the BST ordering until the word is found.
        while (current != null) {
            int comparison = key.compareTo(current.key);

            if (comparison == 0) {
                return current.count;
            } else if (comparison < 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        return 0;
    }

    /**
     * Method Name: delete
     * Description: Removes a word and all its occurrences
     * from the AVL tree.
     *
     * @param key The word being removed.
     */
    public void delete(String key) {
        root = delete(root, key);
    }

    /**
     * Method Name: delete
     * Description: Recursively removes a node using the
     * appropriate BST deletion case. Rebalances the
     * remaining subtree after removal.
     *
     * @param node The current subtree root.
     * @param key The word being removed.
     * @return The updated subtree root.
     */
    private Node delete(Node node, String key) {

        // An empty subtree means the word was not found.
        if (node == null) {
            return null;
        }

        int comparison = key.compareTo(node.key);

        // Locate the node using BST ordering.
        if (comparison < 0) {
            node.left = delete(node.left, key);
        } else if (comparison > 0) {
            node.right = delete(node.right, key);
        } else {

            // Remove a node with no left child.
            if (node.left == null) {
                return node.right;
            }

            // Remove a node with no right child.
            if (node.right == null) {
                return node.left;
            }

            // Find the successor for a node with two children.
            Node successor = findMinimum(node.right);

            // Copy the successor's word and frequency.
            node.key = successor.key;
            node.count = successor.count;

            // Remove the original successor node.
            node.right = delete(node.right, successor.key);
        }

        // Restore balance while returning up the tree.
        return rebalance(node);
    }

    /**
     * Method Name: findMinimum
     * Description: Finds the smallest word in a subtree
     * by following left child references.
     *
     * @param node The starting subtree root.
     * @return The node containing the smallest word.
     */
    private Node findMinimum(Node node) {

        // The leftmost node has the smallest key.
        while (node.left != null) {
            node = node.left;
        }

        return node;
    }

    /**
     * Method Name: inOrderTraversal
     * Description: Displays the tree's words and
     * frequencies in alphabetical order.
     */
    public void inOrderTraversal() {
        System.out.println(
            "=== In-Order Traversal (Alphabetical) ==="
        );

        inOrder(root);
    }

    /**
     * Method Name: inOrder
     * Description: Recursively visits the left subtree,
     * current node, and right subtree in that order.
     *
     * @param node The current node being visited.
     */
    private void inOrder(Node node) {

        // Stop when an empty subtree is reached.
        if (node == null) {
            return;
        }

        // Visit nodes in ascending alphabetical order.
        inOrder(node.left);
        System.out.println(node.key + ": " + node.count);
        inOrder(node.right);
    }

    /**
     * Method Name: levelOrderTraversal
     * Description: Uses a queue to visit nodes one level
     * at a time, starting at the root of the tree.
     */
    public void levelOrderTraversal() {
        System.out.println(
            "=== Level-Order Traversal (Tree Structure) ==="
        );

        // Display a message when no nodes are stored.
        if (root == null) {
            System.out.println("Tree is empty.");
            return;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        // Process nodes using first-in, first-out order.
        while (!queue.isEmpty()) {
            Node current = queue.remove();

            System.out.println(
                current.key + ": " + current.count
            );

            // Add existing children to the queue.
            if (current.left != null) {
                queue.add(current.left);
            }

            if (current.right != null) {
                queue.add(current.right);
            }
        }
    }
}
/*
 * References:
 *
 * GeeksforGeeks. (n.d.). AVL tree program in Java.
 * https://www.geeksforgeeks.org/java/avl-tree-program-in-java/
 *
 * GeeksforGeeks. (n.d.). Deletion in an AVL tree.
 * https://www.geeksforgeeks.org/dsa/deletion-in-an-avl-tree/
 *
 * Sedgewick, R., & Wayne, K. (n.d.).
 * Java algorithms and clients.
 * https://algs4.cs.princeton.edu/code/
 *
 * zyBooks. (n.d.). Data Structures and Algorithms.
 * https://www.zybooks.com/
 *
 * Course notes: the notes from class
 */


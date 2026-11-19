
import java.util.LinkedList;
import java.util.Queue;

/*
 * Academic Honesty Statement:
 * Insert the exact honesty statement required by your instructor.
 *
 * Author: Trevis Tonan
 * Course: CSS405 - Data Structures and Algorithms
 * Assignment: 4 - AVL Tree Word Frequency Analyzer
 * Date: November 19, 2026
 */

/**
 * Implements an AVL tree that stores words and their frequencies.
 * The tree automatically balances itself after insertions
 * and deletions.
 */
public class AVLTree {

    private Node root;

    // Returns the height of a node or zero if it is null.
    private int height(Node node) {
        return node == null ? 0 : node.height;
    }

    // Calculates the balance factor of a node.
    private int getBalance(Node node) {
        if (node == null) {
            return 0;
        }

        return height(node.left) - height(node.right);
    }

    // Updates a node's height using its children's heights.
    private void updateHeight(Node node) {
        node.height = 1 + Math.max(
            height(node.left), height(node.right)
        );
    }

    // Performs a right rotation to correct left-heavy trees.
    private Node rotateRight(Node y) {
        Node x = y.left;
        Node middle = x.right;

        x.right = y;
        y.left = middle;

        updateHeight(y);
        updateHeight(x);

        return x;
    }

    // Performs a left rotation to correct right-heavy trees.
    private Node rotateLeft(Node x) {
        Node y = x.right;
        Node middle = y.left;

        y.left = x;
        x.right = middle;

        updateHeight(x);
        updateHeight(y);

        return y;
    }

    // Restores AVL balance after a structural change.
    private Node rebalance(Node node) {
        updateHeight(node);

        int balance = getBalance(node);

        // Left-heavy case: LL or LR rotation.
        if (balance > 1) {
            if (getBalance(node.left) < 0) {
                node.left = rotateLeft(node.left);
            }

            return rotateRight(node);
        }

        // Right-heavy case: RR or RL rotation.
        if (balance < -1) {
            if (getBalance(node.right) > 0) {
                node.right = rotateRight(node.right);
            }

            return rotateLeft(node);
        }

        return node;
    }

    // Inserts a word into the AVL tree.
    public void insert(String key) {
        root = insert(root, key);
    }

    // Recursively inserts or increments a word.
    private Node insert(Node node, String key) {
        if (node == null) {
            return new Node(key);
        }

        int comparison = key.compareTo(node.key);

        if (comparison < 0) {
            node.left = insert(node.left, key);
        } else if (comparison > 0) {
            node.right = insert(node.right, key);
        } else {
            node.count++;
            return node;
        }

        return rebalance(node);
    }

    // Searches for a word and returns its frequency.
    public int search(String key) {
        Node current = root;

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

    // Deletes an entire word entry from the tree.
    public void delete(String key) {
        root = delete(root, key);
    }

    // Recursively removes a node and restores AVL balance.
    private Node delete(Node node, String key) {
        if (node == null) {
            return null;
        }

        int comparison = key.compareTo(node.key);

        if (comparison < 0) {
            node.left = delete(node.left, key);
        } else if (comparison > 0) {
            node.right = delete(node.right, key);
        } else {
            // A node with one or no children is removed.
            if (node.left == null) {
                return node.right;
            }

            if (node.right == null) {
                return node.left;
            }

            // Replace a two-child node with its successor.
            Node successor = findMinimum(node.right);

            node.key = successor.key;
            node.count = successor.count;

            node.right = delete(node.right, successor.key);
        }

        return rebalance(node);
    }

    // Finds the smallest node in a subtree.
    private Node findMinimum(Node node) {
        while (node.left != null) {
            node = node.left;
        }

        return node;
    }

    // Prints words alphabetically using in-order traversal.
    public void inOrderTraversal() {
        System.out.println(
            "=== In-Order Traversal (Alphabetical) ==="
        );

        inOrder(root);
    }

    // Visits the left subtree, current node, then right.
    private void inOrder(Node node) {
        if (node == null) {
            return;
        }

        inOrder(node.left);

        System.out.println(node.key + ": " + node.count);

        inOrder(node.right);
    }

    // Uses a queue to display nodes level by level.
    public void levelOrderTraversal() {
        System.out.println(
            "=== Level-Order Traversal (Tree Structure) ==="
        );

        if (root == null) {
            System.out.println("Tree is empty.");
            return;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        // Process nodes in first-in, first-out order.
        while (!queue.isEmpty()) {
            Node current = queue.remove();

            System.out.println(
                current.key + ": " + current.count
            );

            if (current.left != null) {
                queue.add(current.left);
            }

            if (current.right != null) {
                queue.add(current.right);
            }
        }
    }
}

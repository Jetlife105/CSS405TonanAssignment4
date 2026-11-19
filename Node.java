
/*
 * Academic Honesty Statement:
 * Insert your instructor's required honesty statement here.
 *
 * Author: Trevis Tonan
 * Course: CSS405 - Data Structures and Algorithms
 * Assignment: 4 - AVL Tree Word Frequency Analyzer
 * Date: November 19, 2026
 */

/**
 * Represents a node in an AVL tree.
 * Each node stores a word, its frequency, height,
 * and references to its left and right children.
 */
public class Node {

    String key;
    int count;
    int height;
    Node left;
    Node right;

    // Creates a new node with an initial frequency of one.
    public Node(String key) {
        this.key = key;
        this.count = 1;
        this.height = 1;
        this.left = null;
        this.right = null;
    }
}

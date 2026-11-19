
/*
 * I certify that the Java code I am submitting is all my own work.
* None of the code is copied from any source or any person.
* I did not use AI to generate the code for my program.
* Signed: Trevis Tonan
* Date: 11/17/2026
 *
 * Author: Trevis Tonan
 * Course: CSS405 - Data Structures and Algorithms
 * Assignment: 4 - AVL Tree Word Frequency Analyzer
 * Date: November 16, 2026
 */


/**
 * Represents a node in an AVL tree.
 * Each node stores a word, its frequency, height,
 * and references to its left and right children.
 */
public class Node {

    // Store the word and the number of times it appears.
    String key;
    int count;

    // Store the height used to maintain AVL tree balance.
    int height;

    // References to the left and right child nodes.
    Node left;
    Node right;

    /**
     * Method Name: Node
     * Description: Creates a new node containing a word.
     * The frequency starts at one because the word has
     * appeared once. The height starts at one because
     * a new node is initially a leaf node.
     *
     * @param key The word stored in the new node.
     */
    public Node(String key) {

        // Assign the word to the new node.
        this.key = key;

        // Initialize the frequency and height to one.
        this.count = 1;
        this.height = 1;

        // A new node does not have any children.
        this.left = null;
        this.right = null;
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
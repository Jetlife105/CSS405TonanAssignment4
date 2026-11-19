
import java.util.Scanner;

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
 * Date: November 17, 2026
 */

/*
 * Description:
 * This file contains the main method that starts the
 * AVL Tree Word Frequency Analyzer program. It allows
 * you  to enter a text file containing words to be
 * checked. The program displays the word frequencies
 * in alphabetical and level-order traversal formats.
 * you can also search for a word and remove a word
 * from the tree. The updated results are shown
 * after the deletion.
 */

/**
 * Class Name: Main
 * Description: Runs the AVL Tree Word Frequency Analyzer.
 * Allows the user to enter a text file, display word
 * frequencies, search for a word, and delete a word.
 */
public class Main {

    /**
     * Method Name: main
     * Description: Starts the program and allows the user
     * to interact with the AVL tree. Reads a text file,
     * displays the tree traversals, searches for a word,
     * and removes a word from the tree.
     *
     * @param args Command-line arguments for the program.
     */
    public static void main(String[] args) {

        // Create a Scanner to read keyboard input.
        Scanner input = new Scanner(System.in);

        // Create an analyzer to manage word frequencies.
        WordFrequencyAnalyzer analyzer =
            new WordFrequencyAnalyzer();

        // Ask the user to provide a text file.
        System.out.print("Enter text file name: ");
        String fileName = input.nextLine();

        // Read the file and insert its words into the tree.
        analyzer.processFile(fileName);

        // Display words alphabetically and by tree level.
        analyzer.displayResults();

        // Ask the user to enter a word to search for.
        System.out.print("\nEnter a word to search: ");
        String searchWord = input.nextLine();

        // Display the frequency of the requested word.
        System.out.println(
            "Frequency: " + analyzer.searchWord(searchWord)
        );

        // Ask the user to enter a word to delete.
        System.out.print("\nEnter a word to delete: ");
        String deleteWord = input.nextLine();

        // Remove the requested word from the AVL tree.
        analyzer.deleteWord(deleteWord);

        // Display the tree after the deletion.
        System.out.println("\nTree after deletion:");
        analyzer.displayResults();

        // Close the Scanner when input is finished.
        input.close();
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
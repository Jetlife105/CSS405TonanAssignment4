
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

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
 * Reads a text file and stores word frequencies in an AVL tree.
 */
public class WordFrequencyAnalyzer {

    private AVLTree tree;

    // Creates an analyzer with an empty AVL tree.
    public WordFrequencyAnalyzer() {
        tree = new AVLTree();
    }

    // Reads words from a file and inserts them into the tree.
    public void processFile(String fileName) {
        try (Scanner scanner = new Scanner(new File(fileName))) {

            // Process each line in the input file.
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                // Normalize words to lowercase.
                line = line.toLowerCase();

                // Replace punctuation with spaces.
                line = line.replaceAll("[^a-z\\s]", " ");

                // Separate the line into individual words.
                String[] words = line.trim().split("\\s+");

                // Insert nonempty words into the AVL tree.
                for (String word : words) {
                    if (!word.isEmpty()) {
                        tree.insert(word);
                    }
                }
            }

            System.out.println("File processed successfully.");

        } catch (FileNotFoundException exception) {
            System.out.println(
                "Error: File not found - " + fileName
            );
        }
    }

    // Displays alphabetical and level-order traversals.
    public void displayResults() {
        tree.inOrderTraversal();

        System.out.println();

        tree.levelOrderTraversal();
    }

    // Searches for a word in the tree.
    public int searchWord(String word) {
        return tree.search(word.toLowerCase());
    }

    // Deletes a word from the tree.
    public void deleteWord(String word) {
        tree.delete(word.toLowerCase());
    }
}

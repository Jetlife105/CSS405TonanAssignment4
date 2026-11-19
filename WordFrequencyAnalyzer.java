
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Locale;
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
 * This file processes text files to determine how
 * frequently each word appears. It reads the input
 * file line by line, then converts words to lowercase,
 * and removes punctuation before inserting them
 * into the AVL tree. Repeated words increase their
 * existing frequency counts. The class also allows
 * users to search for and delete words and display
 * the results using tree traversals.
 */

/**
 * Class Name: WordFrequencyAnalyzer
 * Description: Reads words from a text file and stores
 * their frequencies in an AVL tree. Words are normalized
 * before insertion to ensure consistent counting.
 */
public class WordFrequencyAnalyzer {

    // Store the AVL tree used to count word frequencies.
    private AVLTree tree;

    /**
     * Method Name: WordFrequencyAnalyzer
     * Description: Creates a word frequency analyzer
     * containing an empty AVL tree.
     */
    public WordFrequencyAnalyzer() {
        tree = new AVLTree();
    }

    /**
     * Method Name: processFile
     * Description: Reads a text file one line at a time.
     * Converts words to lowercase, removes punctuation,
     * and inserts each word into the AVL tree.
     *
     * @param fileName The name or path of the input file.
     */
    public void processFile(String fileName) {

        // Open the file and close it automatically when done.
        try (Scanner scanner = new Scanner(new File(fileName))) {

            // Process each line until the end of the file.
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                // Convert uppercase letters to lowercase.
                line = line.toLowerCase(Locale.ROOT);

                // Replace punctuation and numbers with spaces.
                line = line.replaceAll("[^a-z\\s]", " ");

                // Split the cleaned line into individual words.
                String[] words = line.trim().split("\\s+");

                // Insert each valid word into the AVL tree.
                for (String word : words) {
                    if (!word.isEmpty()) {
                        tree.insert(word);
                    }
                }
            }

            System.out.println("File processed successfully.");

        } catch (FileNotFoundException exception) {

            // Explain the problem if the file cannot be opened.
            System.out.println(
                "Error: File not found - " + fileName
            );
        }
    }

    /**
     * Method Name: displayResults
     * Description: Displays the AVL tree using an in-order
     * traversal followed by a level-order traversal.
     */
    public void displayResults() {

        // Display the words in alphabetical order.
        tree.inOrderTraversal();

        // Separate the two traversals for readability.
        System.out.println();

        // Display the structure using a queue.
        tree.levelOrderTraversal();
    }

    /**
     * Method Name: searchWord
     * Description: Converts the requested word to lowercase
     * and searches the AVL tree for its frequency.
     *
     * @param word The word being searched for.
     * @return The frequency, or zero if the word is absent.
     */
    public int searchWord(String word) {

        // Normalize the search word before looking it up.
        String normalizedWord = word.toLowerCase(Locale.ROOT);

        return tree.search(normalizedWord);
    }

    /**
     * Method Name: deleteWord
     * Description: Converts the requested word to lowercase
     * and removes its entire entry from the AVL tree.
     *
     * @param word The word being removed.
     */
    public void deleteWord(String word) {

        // Normalize the word before deleting its node.
        String normalizedWord = word.toLowerCase(Locale.ROOT);

        tree.delete(normalizedWord);
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
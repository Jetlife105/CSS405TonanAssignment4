
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
 * Main class for the AVL Tree Word Frequency Analyzer.
 */
public class Main {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        // Create the word frequency analyzer.
        WordFrequencyAnalyzer analyzer =
            new WordFrequencyAnalyzer();

        // Ask the user to enter the input file name.
        System.out.print("Enter text file name: ");
        String fileName = input.nextLine();

        // Read the file and display the tree traversals.
        analyzer.processFile(fileName);
        analyzer.displayResults();

        // Allow the user to search for a word.
        System.out.print("\nEnter a word to search: ");
        String searchWord = input.nextLine();

        System.out.println(
            "Frequency: " + analyzer.searchWord(searchWord)
        );

        // Allow the user to delete a word.
        System.out.print("\nEnter a word to delete: ");
        String deleteWord = input.nextLine();

        analyzer.deleteWord(deleteWord);

        // Display the updated AVL tree.
        System.out.println("\nTree after deletion:");
        analyzer.displayResults();

        input.close();
    }
}

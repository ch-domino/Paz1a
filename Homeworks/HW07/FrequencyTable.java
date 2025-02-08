package sk.upjs.paz;

import java.util.Arrays;


public class FrequencyTable {

    // Array to store words along wth their occurrence counts
    private Word[] wordList;

    // Current number of words in the table
    private int size;

    // Initial capacity of the word list
    private int capacity = 10;

    // Constructor to create an empty frequency table
    public FrequencyTable() {
        wordList = new Word[capacity];
        size = 0;
    }

    // Clears the frequency table, resetting its contents
    public void clear() {
        wordList = new Word[capacity];
        size = 0;
    }

    // Returns the number of occurrences of a specific word
    public int getNumberOfOccurrences(String word) {
        if (word == null) {
            System.out.println("Error: WordEntry cannot be null!");
            return -1;
        }

        // Itterate through the table to find the word
        for (int i = 0; i < size; i++) {
            if (wordList[i] != null && wordList[i].getWord().equals(word)) {
                return wordList[i].getOccurrences();
            }
        }

        // Word not found in the table
        return 0;
    }

    // Adds a new occurrence of a word or initializes its cunt if not present
    public void addOccurrence(String word) {
        if (word == null) {
            System.out.println("Error: WordEntry cannot be null!");
            return;
        }

        // Check if the word already exists in the table
        for (int i = 0; i < size; i++) {
            if (wordList[i] != null && wordList[i].getWord().equals(word)) {
                wordList[i].incrementOccurrences();
                return;
            }
        }

        // Resize the array if the capacity is reached
        if (size >= wordList.length) {
            wordList = Arrays.copyOf(wordList, wordList.length * 2);
        }

        // Add the word with an initial count of 1
        wordList[size++] = new Word(word);
    }

    // Returns the total number of unique words in the table
    public int getWordCount() {
        return size;
    }

    // Returns an array of all words in the table
    public String[] getWords() {
        String[] words = new String[size];

        // Populate te array with words from the table
        for (int i = 0; i < size; i++) {
            words[i] = wordList[i].getWord();
        }


        return words;
    }


    // Returns a formatted string representation of the frequency table
    @Override
    public String toString() {
        if (size == 0) {
            return "[]";
        }

        StringBuilder stringBuilder = new StringBuilder("[");

        // Append each word and its occurrence count to the string
        for (int i = 0; i < size - 1; i++) {
            stringBuilder.append(wordList[i].getWord()).append("=").append(wordList[i].getOccurrences()).append(", ");
        }

        stringBuilder.append(wordList[size - 1].getWord()).append("=").append(wordList[size - 1].getOccurrences()).append("]");

        return stringBuilder.toString();
    }
}

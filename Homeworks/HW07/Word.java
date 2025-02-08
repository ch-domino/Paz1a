package sk.upjs.paz;

public class Word {

    // The word being tracked
    private String word;

    // The number of times the word has occurred
    private int occurrences;

    // Constructor to initialize Word with a word and default occurrences of 1
    public Word(String word) {
        this.word = word;
        this.occurrences = 1;
    }

    // Getter method to retrieve the word
    public String getWord() {
        return word;
    }

    // Getter method to retrieve the occurrences of the word
    public int getOccurrences() {
        return occurrences;
    }

    // Method to increment the occurrences of the word
    public void incrementOccurrences() {
        occurrences++;
    }
}

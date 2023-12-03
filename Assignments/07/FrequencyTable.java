package paz1a.dominik.chrobak.a7;

//Represents a WordEntry containing a word and its occurrences
class WordEntry {
	private String word;
	private int occurrences;

	// Constructor to initialize WordEntry with a word and default occurrences of 1
	public WordEntry(String word) {
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

//Represents a FrequencyTable to store WordEntry instances
public class FrequencyTable {
	// Array to store WordEntry objects
	private WordEntry[] wordList;
	// Current size of the wordList array
	private int size;
	// Initial capacity of the wordList array
	private int capacity = 10;

	// Constructor to initialize the FrequencyTable
	public FrequencyTable() {
		// Initializes the wordList array with the initial capacity
		wordList = new WordEntry[capacity];
		// Initializes the current size to 0
		size = 0;
	}

	// Method to clear the FrequencyTable by resetting the wordList and size
	public void clear() {
		// Resets the wordList array to initial capacity
		wordList = new WordEntry[capacity];
		// Resets the current size to 0
		size = 0;
	}

	// Method to get the number of occurrences for a specific word in the
	// FrequencyTable
	public int getNumberOfOccurrences(String word) {
		// Print an error message if the word is null
		if (word == null) {
			System.out.println("Error: Word cannot be null");
			// Returns -1 to indicate an error
			return -1;
		}
		for (int i = 0; i < size; i++) {
			if (wordList[i] != null && wordList[i].getWord().equals(word)) {
				// Returns occurrences if the word is found
				return wordList[i].getOccurrences();
			}
		}
		// Returns 0 if the word is not found
		return 0;
	}

	// Method to add an occurrence of a word to the FrequencyTable
	public void addOccurrence(String word) {
		// Print an error message if the word is null
		if (word == null) {
			System.out.println("Error: Word cannot be null");
			// Returns to avoid further processing
			return;
		}
		for (int i = 0; i < size; i++) {
			if (wordList[i] != null && wordList[i].getWord().equals(word)) {
				// Increment occurrences if the word is found
				wordList[i].incrementOccurrences();
				// Returns to avoid adding a new WordEntry
				return;
			}
		}
		// Resizes the array if it reaches its capacity
		if (size >= wordList.length) {
			resizeArray();
		}
		// Adds a new WordEntry for the word
		wordList[size++] = new WordEntry(word);
	}

	// Method to resize the wordList array when it reaches its capacity
	private void resizeArray() {
		// Doubles the capacity
		int newSize = capacity * 2;
		// Creates a new array with the increased capacity
		WordEntry[] newArray = new WordEntry[newSize];
		// Copies elements to the new array
		System.arraycopy(wordList, 0, newArray, 0, size);
		// Assigns the new array to wordList
		wordList = newArray;
		// Updates the capacity
		capacity = newSize;
	}

	// Method to get the count of unique words in the FrequencyTable
	public int getWordCount() {
		// Returns the current size, representing the count of unique words
		return size;
	}

	// Method to get an array of words stored in the FrequencyTable
	public String[] getWords() {
		// Creates a new array to hold words
		String[] words = new String[size];
		for (int i = 0; i < size; i++) {
			// Copies words from WordEntry objects to the array
			words[i] = wordList[i].getWord();
		}
		// Returns the array of words
		return words;
	}

	// Override of toString() method to represent the FrequencyTable as a String
	@Override
	public String toString() {
		if (size == 0) {
			// Returns an empty representation if there are no elements in the table
			return "[]";
		}
		StringBuilder result = new StringBuilder("[");
		for (int i = 0; i < size - 1; i++) {
			result.append(wordList[i].getWord()).append("=").append(wordList[i].getOccurrences()).append(", ");
		}
		result.append(wordList[size - 1].getWord()).append("=").append(wordList[size - 1].getOccurrences()).append("]");
		// Returns the String representation of the FrequencyTable
		return result.toString();
	}
}

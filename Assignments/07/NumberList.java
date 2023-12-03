package paz1a.dominik.chrobak.a7;

import java.util.Arrays;

public class NumberList {
	// Array to store elements
	private int[] elements;
	// Current size of the list
	private int size;

	// Constructor: initializes an empty NumberList with a default capacity of 10
	public NumberList() {
		elements = new int[10];
		size = 0;
	}

	// Constructor: initializes NumberList with values from an input array
	public NumberList(int[] numbers) {
		// Check if the input array is null
		if (numbers == null) {
			System.out.println("Error: Input array cannot be null");
			return;
		}
		// Copy input array to the NumberList
		this.elements = Arrays.copyOf(numbers, numbers.length);
		// Set the size of the NumberList
		this.size = numbers.length;
	}

	// Constructor: initializes NumberList with values from another NumberList
	public NumberList(NumberList numberList) {
		// Copy elements from another NumberList
		this.elements = Arrays.copyOf(numberList.elements, numberList.size);
		// Set the size of the NumberList
		this.size = numberList.size;
	}

	// Retrieves the element at the specified index in the NumberList
	public int get(int index) {
		// Check if the index is within bounds
		if (index < 0 || index >= size) {
			System.out.println("Error: Index out of bounds");
			// Return -1 to indicate an error
			return -1;
		}
		// Return the element at the specified index
		return elements[index];
	}

	// Sets the element at the specified index in the NumberList to the given value
	public void set(int index, int value) {
		// Check if the index is within bounds
		if (index < 0 || index >= size) {
			System.out.println("Error: Index out of bounds");
			// Exit the method without performing the operation
			return;
		}
		// Set the element at the specified index to the given value
		elements[index] = value;
	}

	// Adds a new element to the end of the NumberList
	public void add(int value) {
		// Check if the NumberList is full
		if (size >= elements.length) {
			// Increase the capacity of the NumberList
			elements = Arrays.copyOf(elements, elements.length * 2);
		}
		// Add the value to the end of the NumberList and increment size
		elements[size++] = value;
	}

	// Returns the number of elements in the NumberList
	public int size() {
		// Return the current size of the NumberList
		return size;
	}

	// Clears the NumberList by resetting its elements and size
	public void clear() {
		// Create a new array with default capacity
		elements = new int[10];
		// Reset the size to zero
		size = 0;
	}

	// Removes the element at the specified index from the NumberList and returns
	// its value
	public int remove(int index) {
		// Check if the NumberList is empty
		if (size == 0) {
			System.out.println("Error: List is empty");
			// Return -1 to indicate an error
			return -1;
		}
		// Check if the index is within bounds
		if (index < 0 || index >= size) {
			System.out.println("Error: Index out of bounds");
			// Return -1 to indicate an error
			return -1;
		}
		// Store the value being removed
		int removedValue = elements[index];
		// Shift elements to fill the gap
		System.arraycopy(elements, index + 1, elements, index, size - index - 1);
		// Decrement the size of the NumberList
		size--;
		// Return the removed value
		return removedValue;
	}

	// Returns a string representation of the NumberList
	@Override
	public String toString() {
		// Check if the NumberList is empty
		if (size == 0) {
			// Return an empty representation
			return "[]";
		}

		StringBuilder sb = new StringBuilder("[");
		for (int i = 0; i < size - 1; i++) {
			// Append elements to the string representation
			sb.append(elements[i]).append(", ");
		}
		// Append the last element
		sb.append(elements[size - 1]).append("]");
		// Return the string representation of the NumberList
		return sb.toString();
	}

	/*
	 * // Main method demonstrating the usage of NumberList public static void
	 * main(String[] args) { int[] numbers = new int[] { 4, 6, 2, 1, 3, 8 };
	 * System.out.println(Arrays.toString(numbers));
	 * 
	 * NumberList z1 = new NumberList(numbers); z1.set(1, 8);
	 * System.out.println(z1.toString());
	 * 
	 * System.out.println(Arrays.toString(numbers));
	 * 
	 * NumberList z2 = new NumberList(z1); System.out.println(z1.toString());
	 * System.out.println(z2.toString());
	 * 
	 * z2.set(1, 10);
	 * 
	 * System.out.println(z1.toString()); System.out.println(z2.toString()); }
	 */
}

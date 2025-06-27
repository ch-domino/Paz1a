package sk.upjs.paz;

import java.util.Arrays;

public class NumberList {

    // Array to store the elements of the number list
    private int[] elements;

    // Current size of the number list
    private int size;

    // Default constructor that initializes an empty number list
    public NumberList() {
        elements = new int[0];
        size = 0;
    }

    // Constructor that initializes the list with values from an input array
    public NumberList(int[] numbers) {
        if (numbers == null) {
            System.out.println("Error: Input array cannot be null!");
            return;
        }

        // Copy the input array to initialize the list
        this.elements = Arrays.copyOf(numbers, numbers.length);
        this.size = numbers.length;

    }

    // Constructor that initializes the list with values from another NumberList
    public NumberList(NumberList numberList) {
        if (numberList == null) {
            System.out.println("Error: Input list cannot be null!");
            return;
        }

        // Copy the elements from the input NumberList
        this.elements = Arrays.copyOf(numberList.elements, numberList.size);
        this.size = numberList.size;

    }

    // Returns the number at the specified index in the list
    public int get(int index) {
        if (index < 0 || index >= size) {
            System.out.println("Error: Index out of bounds!");
            return -1;
        }

        return elements[index];
    }

    // Sets the value of an existing element at a specified index
    public void set(int index, int value) {
        if (index < 0 || index >= size) {
            System.out.println("Error: Index out of bounds!");
            return;
        }

        elements[index] = value;
    }

    // Adds a new element to the end of the list
    public void add(int value) {
        if (size >= elements.length) {
            elements = Arrays.copyOf(elements, elements.length + 1);
        }

        elements[size++] = value;
    }

    // Returns the current number of elements in the list
    public int size() {
        return size;
    }

    // Clears the list by resetting its size and contents
    public void clear() {

        // Initialize a new array with default capacity
        elements = new int[10];
        size = 0;
    }

    // Removes the element at the specified index and returns its value
    public int remove(int index) {
        if (size == 0) {
            System.out.println("Error: List is empty!");
            return -1;
        }

        if (index < 0 || index >= size) {
            System.out.println("Error: Index out of bounds!");
            return -1;
        }

        int removedValue = elements[index];

        // Shift elements to fill the gap left by the removed element
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        size--;

        return removedValue;
    }

    // Returns a formatted string representation of the list contents
    @Override
    public String toString() {
        if (size == 0) {
            return "[]";
        }

        StringBuilder stringBuilder = new StringBuilder("[");

        // Append elements to the string representation
        for (int i = 0; i < size - 1; i++) {
            stringBuilder.append(elements[i]).append(", ");
        }
        stringBuilder.append(elements[size - 1]).append("]");

        return stringBuilder.toString();
    }
}



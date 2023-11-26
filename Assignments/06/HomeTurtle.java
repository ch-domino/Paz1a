package paz1a.dominik.chrobak.a6;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import sk.upjs.jpaz2.*;

public class HomeTurtle extends Turtle {

	public int pocetNulVSucine(String nazovSuboruSCislami) {
		int totalZeros = 0; // Counter to track the total number of trailing zeros
		int countOfTwos = 0; // Counter for divisions by 2
		int countOfFives = 0; // Counter for divisions by 5
		File file = new File(nazovSuboruSCislami); // Create a File object with the provided file path
		Scanner scanner = null; // Scanner object to read from the file
		try {
			scanner = new Scanner(file); // Initialize the scanner to read from the file
			while (scanner.hasNextLine()) { // Loop through each line in the file
				String line = scanner.nextLine(); // Read the next line from the file
				String[] numbers = line.split("\\s+"); // Split the line by whitespace to get individual numbers
				for (String numStr : numbers) { // Iterate through each number in the line
					long num = Long.parseLong(numStr); // Parse the number string to a long
					if (num == 0) {
						return 0; // If the number is zero, return zero immediately
					}

					// Count divisions by 2 and 5 for the current number
					while (num % 2 == 0) {
						countOfTwos++; // Increment count for divisions by 2
						num /= 2; // Divide the number by 2
					}
					while (num % 5 == 0) {
						countOfFives++; // Increment count for divisions by 5
						num /= 5; // Divide the number by 5
					}
				}
			}
			// Accumulate the total counts of divisions by 2 and 5
			totalZeros += Math.min(countOfTwos, countOfFives);
		} catch (FileNotFoundException | NumberFormatException e) {
			return 0; // Error reading file or parsing number
		} finally {
			if (scanner != null) {
				scanner.close(); // Close the scanner in the finally block to ensure it's closed
			}
		}
		return totalZeros; // Return the total count of trailing zeros in the product
	}

	public int najproduktivnejsiHrac(String nazovSuboru) {
		// Check if the file name is null
		if (nazovSuboru == null) {
			return -1; // Return -1 if the file name is null
		}

		// Create a 2D array to store player statistics (99 players, 2 stats: points,
		// punishments)
		int[][] players = new int[99][2];

		try {
			File file = new File(nazovSuboru); // Create a File object with the provided file name
			Scanner scanner = new Scanner(file); // Initialize Scanner to read from the file
			boolean fileIsEmpty = true; // Flag to check if the file is empty

			// Loop through each line in the file
			while (scanner.hasNextLine()) {
				fileIsEmpty = false; // File is not empty
				String line = scanner.nextLine(); // Read the next line from the file
				Scanner lineScanner = new Scanner(line); // Scanner to parse the line
				String word = lineScanner.next(); // Read the first word
				int playerNumber = lineScanner.nextInt() - 1; // Get player number (starting from 0)

				// Update player statistics based on the action word (GOL, ASISTENCIA, TREST)
				if (word.equals("GOL")) {
					players[playerNumber][0] += 3; // Add 3 points for a goal
				} else if (word.equals("ASISTENCIA")) {
					players[playerNumber][0] += 2; // Add 2 points for an assist
				} else if (word.equals("TREST")) {
					players[playerNumber][0] -= 2; // Deduct 2 points for a punishment
					players[playerNumber][1] += 1; // Increment punishment count
				}

				lineScanner.close(); // Close the line scanner
			}

			scanner.close(); // Close the file scanner

			// Return -1 if the file is empty
			if (fileIsEmpty) {
				return -1;
			}

			// Initialize variables to track the best player and least punishments
			int maxPoints = Integer.MIN_VALUE;
			int bestPlayer = -1;
			int leastPunishments = Integer.MAX_VALUE;

			// Check if the current player's points are greater than the current maxPoints
			// Also, ensure the player has accumulated points (not zero)
			for (int i = 0; i < 99; i++) {
				if (players[i][0] > maxPoints && players[i][0] != 0) {
					maxPoints = players[i][0]; // Update maxPoints with the new higher value
					bestPlayer = i + 1; // Record the current player as the potential best player (incremented by 1 as
										// player numbers start from 1)
					leastPunishments = players[i][1]; // Record the punishments of this potential best player
				}
				// If the player has the same points as the current maxPoints,
				// compare the punishments to find the player with the least punishments
				else if (players[i][0] == maxPoints && players[i][1] < leastPunishments) {
					leastPunishments = players[i][1]; // Update leastPunishments with the lower value
					bestPlayer = i + 1; // Update the bestPlayer as the current player number
				}
			}

			// Return the best player number or -1 if no best player found
			if (bestPlayer != -1) {
				return bestPlayer;
			} else {
				return -1;
			}

		} catch (FileNotFoundException | NumberFormatException e) {
			e.printStackTrace(); // Print the stack trace for file-related exceptions
			return -1; // Return -1 in case of an exception
		}
	}

	public void spirala(File subor, int[] cisla, int pocetRiadkov) {
		try (FileWriter writer = new FileWriter(subor)) {
			// Calculate the number of columns in the matrix based on the input array and row count
            int columns = cisla.length / pocetRiadkov;
            
            // Create a 2D matrix based on the row count and calculated column count
            int[][] matrix = new int[pocetRiadkov][columns];
            
            // Determine the actual number of elements to be used from the input array
            int value = Math.min(cisla.length, pocetRiadkov * columns);

            // Initialize variables for spiral traversal
            int top = 0, bottom = pocetRiadkov - 1, left = 0, right = columns - 1;
            int index = 0;

            // Perform the spiral pattern filling of the matrix
            while (index < value) {
            	// Fill the top row of the matrix
                for (int i = left; i <= right && index < value; i++) {
                    matrix[top][i] = cisla[index++];
                }
                top++;

                // Fill the rightmost column of the matrix
                for (int i = top; i <= bottom && index < value; i++) {
                    matrix[i][right] = cisla[index++];
                }
                right--;

                // Fill the bottom row of the matrix
                for (int i = right; i >= left && index < value; i--) {
                    matrix[bottom][i] = cisla[index++];
                }
                bottom--;

                // Fill the leftmost column of the matrix
                for (int i = bottom; i >= top && index < value; i--) {
                    matrix[i][left] = cisla[index++];
                }
                left++;
            }

         	// Write the matrix into the file using FileWriter
            for (int i = 0; i < pocetRiadkov; i++) {
                for (int j = 0; j < columns; j++) {
                	// Write each element of the matrix followed by a space
                    writer.write(matrix[i][j] + " ");
                }
                // Move to the next line after writing each row
                writer.write("\n");
            }
        } catch (IOException e) {
        	// Handle IOException if encountered during file write operation
            e.printStackTrace();
        }
    }
}

package sk.paz1a.dominik.chrobak.a4;

import sk.upjs.jpaz2.Turtle;

public class HomeTurtle extends Turtle {

	public String toEmailAddress(String name) {
		// Initialize an empty string to store the email address
		String email = "";

		// Check if the input 'name' is null
		if (name == null) {
			// Return null if 'name' is null
			return null;
		}

		// Iterate through each character in the 'name' string
		for (int i = 0; i < name.length(); i++) {
			// Check if the current character is a space
			if (name.charAt(i) == ' ') {
				// Replace spaces with dots in the email address
				email += ".";
			} else {
				// Keep other characters as they are in the email
				email += name.charAt(i);
			}
		}
		// Append the domain part of the email address
		email += "@upjs.sk";
		// Convert the entire email address to lowercase and return it
		return email.toLowerCase();
	}

	public int countAcronyms(String r) {
		// Initialize a counter for consecutive uppercase letters
		int counter = 0;
		// Initialize a counter for acronyms found
		int ans = 0;

		// Check if the input 'r' is null
		if (r == null) {
			// Return 0 if 'r' is null
			return 0;
		}

		// Iterate through each character in the input string 'r'
		for (int i = 0; i < r.length(); i++) {
			// Check if the current character is an uppercase letter (A-Z) based on ASCII values
			if (r.charAt(i) >= 65 && r.charAt(i) <= 90) {
				// Increment the consecutive uppercase letter counter
				counter++;
			} else {
				// Reset the counter if an uppercase letter is not encountered
				counter = 0;
			}

			// Check if three consecutive uppercase letters have been found (forming an acronym)
			if (counter == 3) {
				// Increment the acronym counter
				ans++;
			}
		}
		// Return the total count of acronyms found in the string 'r'
		return ans;
	}

	public String replaceNumbers(String s, String replacement) {
		// Initialize a string to store the result
		String ans = "";
		
		// Check if the input 's' is null
		if (s == null) {
			// Return null if 's' is null
			return null;
		}
		
		// Iterate through each character in the input string 's'
		for (int i = 0; i < s.length(); i++) {
			// Check if the current character is a digit (0-9) based on ASCII values
			if (s.charAt(i) >= 48 && s.charAt(i) <= 57) {
				// Replace the digit with the corresponding character from 'replacement'
				ans += replacement.charAt(i);
			} else {
				// Keep other characters as they are
				ans += s.charAt(i);
			}
		}
		// Return the resulting string after replacing numbers
		return ans;
	}

	public String sanitize(String s) {
		// Initialize a variable to store the current character
		char currentChar = ' ';
		// Initialize a string to store the sanitized result
		String answer = "";
		
		// Check if the input 's' is null
		if (s == null) {
			// Return null if 's' is null
			return null;
		}

		// Iterate through each character in the input string 's'
		for (int i = 0; i < s.length(); i++) {
			// Check if the current character is the same as the previous character
			if (s.charAt(i) == currentChar) {
				// Skip and continue if the current character is the same as the previous character
				continue;
			} else {
				// Append the character to the sanitized result
				answer += s.charAt(i);
				// Update the current character
				currentChar = s.charAt(i);
			}
		}
		// Return the resulting string after removing consecutive duplicate characters
		return answer;
	}

}

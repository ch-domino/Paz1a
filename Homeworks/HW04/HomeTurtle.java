package sk.upjs.paz;

import sk.upjs.jpaz2.Turtle;

public class HomeTurtle extends Turtle {

    // Converts a given name into an email adress format
    public String toEmailAddress(String name) {
        // If the name is null, return null
        if (name == null) return null;

        // Replace spaces (' ') with dots ('.') in the name
        String email = name.replace((char) 32, (char) 46);

        // Append the domain "@upjs.sk" to the email
        email += "@upjs.sk";

        // Convert the email to lowercase and return it
        return email.toLowerCase();
    }

    // Counts the number of 3-letter acronyms in a string
    public int countAcronyms(String r) {
        // If the input string is null, return 0
        if (r == null) return 0;

        int counter = 0; // Keeps track of consecutive uppercase letters
        int ans = 0; // Stores the number of acronyms found

        // Iterate through each character of the string
        for (int i = 0; i < r.length(); i++) {
            // Check if the character is an uppercase letter (A-Z)
            if (r.charAt(i) >= 65 && r.charAt(i) <= 90) {
                counter++; // Increment the counter if it's uppercase
            } else {
                counter = 0; // Reset the counter if it's not uppercase
            }

            // If there are 3 consecutive uppercase letters, count it as an acronym
            if (counter == 3) ans++;
        }

        // Return the total number of acronyms found
        return ans;

    }

    // Replaces digits (0-9) in a string with corresponding characters from the replacement string
    public String replaceNumbers(String s, String replacement) {
        // If the input string is null, return null
        if (s == null) return null;

        String ans = ""; // Stores the result string

        // Iterate through each character of the input string
        for (int i = 0; i < s.length(); i++) {
            // Check if the character is a digit (0-9)
            if (s.charAt(i) >= 48 && s.charAt(i) <= 57) {
                // Replace the digit with the corresponding character from the replacement string
                ans += replacement.charAt(i);
            } else {
                // If not a digit, keep the original character
                ans += s.charAt(i);
            }
        }

        // Return the modified string
        return ans;
    }

    // Removes consecutive duplicates from a string
    public String sanitize(String s) {
        // If the input string is null, return null
        if (s == null) return null;

        // If the string has only one character, return it as is
        if (s.length() == 1) return s;

        char currentChar = ' '; // Tracks the last unique character
        String ans = ""; // Stores the sanitized string

        // Iterate through each character of the input string
        for (int i = 0; i < s.length(); i++) {
            // IF the current character is the same as the last unique one, skip it
            if (s.charAt(i) == currentChar) {
                continue;
            } else {
                // Add the new unique character to the result and update currentChar
                ans += s.charAt(i);
                currentChar = s.charAt(i);
            }
        }

        // Return the sanitized string without consecutive duplicates
        return ans;
    }

}

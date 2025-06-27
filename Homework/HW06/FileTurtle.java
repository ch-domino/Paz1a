package sk.upjs.paz;

import sk.upjs.jpaz2.Turtle;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class FileTurtle extends Turtle {

    public boolean isWinner(String name, String resultsFilename) {
        if (name == null || resultsFilename == null) return false;

        // Create instance variables for tracking votes
        int votesTotal = 0;
        int votes = 0;
        int num = 0;

        // Convert filePath to File
        File file = new File(resultsFilename);

        // Try to open the file, catch exception
        try (Scanner sc = new Scanner(file)) {
            // While nextLine exists continue
            while (sc.hasNextLine()) {
                // Convert current line toLowerCase
                String line = sc.nextLine().toLowerCase();

                // Split line based on " " for easy finding of the number of votes
                String[] parts = line.split(" ");

                // Iterate through parts to find the number
                for (String part : parts) {
                    // Try to parse the current part to Int, catch exception and move to next part
                    try {
                        // Save current part as int if it can be parsed as int
                        num = Integer.parseInt(part);
                    } catch (NumberFormatException e) {
                        // Ignore if not a number
                    }
                }

                // Check if current line contains the inputted name
                if (line.contains(name.toLowerCase())) {
                    // Add the number of votes if the line contains the inputted name
                    votes += num;
                }
                // Add number of votes to total and reset the number
                votesTotal += num;
                num = 0;

            }

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred while checking the winner.\n " + e);
        }

        // Return true if votes are bigger than half the Total
        return (votesTotal / 2) < votes;
    }

    public String[] vycitanka(int n, int k, String priebeh) {
        if (n == 0 || k == 0 || priebeh == null) return null;

        // Convert file path to file
        File file = new File(priebeh);

        // Open the file with a Scanner
        try (Scanner sc = new Scanner(file)) {
            // Read line from file
            String line = sc.nextLine();
            // Split names with space
            String[] failed = line.split(" ");
            // Create an Array for the original sequence with the size of 'n'
            String[] originalSequence = new String[n];

            // Loop through names
            for (int i = 0; i < failed.length; i++) {
                // Check the position when the name was eliminated
                int eliminatedAt = (i + k) % n;
                // Save the name to the originalSequence on the position it was before failing
                originalSequence[eliminatedAt] = failed[i];
            }

            return originalSequence;

        } catch (FileNotFoundException e) {
            System.out.println("Error when reading file.");
            return null;
        }

    }

    public void renameVariables(String inputFilename, String outputFilename) {
        // Convert input and output paths to files
        File inputFile = new File(inputFilename);
        File outputFile = new File(outputFilename);

        // Create a 2D array for storing variables & an array for storing arguments of first line
        String[][] vars = new String[5][2];
        String[] args = new String[0];

        // Loop through the vars array and assign letters from 'a' to 'e' for each position starting from 0
        for (int i = 0; i < vars.length; i++) {
            vars[i][0] = String.valueOf((char) ('a' + i));
        }

        // Open the input file using the Scanner
        try (Scanner sc = new Scanner(inputFile)) {
            // Create the firstLine boolean for allowing code to be run only on the first line
            boolean firstLine = true;

            // Open the output file using the PrintWriter
            try (PrintWriter pw = new PrintWriter(outputFile)) {
                // Loop until scanner doesn't have any more lines
                while (sc.hasNextLine()) {
                    // Save the current line to the 'line' variable
                    String line = sc.nextLine();

                    // Check if we are on the first line
                    if (firstLine) {
                        // Check the index of the '(' % ')' so we can get rid of everything which is not an inputted variable
                        int startPos = line.indexOf('(');
                        int endPos = line.indexOf(')');

                        // Check if the starting and end positions are not negative and if the end position is bigger than the start position
                        if (startPos != -1 && endPos != -1 && startPos < endPos) {
                            // Remove all text before the '(' and after the ')' bracket
                            String argumentsNoBrackets = line.substring(startPos + 1, endPos).trim();
                            // Remove the ',' from the text
                            String argumentsNoCommas = argumentsNoBrackets.replaceAll(",", "");
                            // Split the text with spaces
                            args = argumentsNoCommas.split(" ");
                        } else {
                            System.out.println("There are no inputted variables in the first line.");
                            return;
                        }

                        // Create an argument counter
                        int argCunter = 0;
                        // Loop through the arguments
                        for (int i = 0; i < args.length; i++) {
                            // Add every second argument to the variables
                            // We do this because the first argument is always a type
                            if (i % 2 != 0) {
                                vars[argCunter][1] = args[i];
                                // Increase the argument counter
                                argCunter++;
                            }
                        }

                        // Loop through all variables in the vars array
                        for (String[] var : vars) {
                            // If the array is not null replace variable with letter from 'a' to 'e' based on the variable number
                            if (var[1] != null) {
                                line = line.replace(var[1], var[0]);
                            }
                        }

                        // Write the line to the output file
                        pw.println(line);

                    } else {
                        // Loop through all variables in the vars array
                        for (String[] var : vars) {
                            // If the array is not null replace variable with letter from 'a' to 'e' based on the variable number
                            if (var[1] != null) {
                                line = line.replace(var[1], var[0]);
                            }
                        }

                        // Write the line to the output file
                        pw.println(line);
                    }
                    // Change the firstLine boolean to false, so we skip the splitting and looking for variables on other lines, as it's not needed
                    firstLine = false;
                }
            } catch (FileNotFoundException e) {
                System.out.println("Error while trying to write to `" + inputFilename + "`\n" + e);
            }
        } catch (
                FileNotFoundException e) {
            System.out.println("Error while trying to read `" + inputFilename + "`\n" + e);
        }
    }
}

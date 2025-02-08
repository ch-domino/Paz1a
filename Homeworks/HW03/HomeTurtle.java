package sk.upjs.paz;

import sk.upjs.jpaz2.Turtle;

import java.awt.*;

public class HomeTurtle extends Turtle {

    public void squardot(double size) {
        // Get the starting position and direction while setting currentSize to size
        double currentSize = size;
        double initX = this.getX();
        double initY = this.getY();
        double initDir = this.getDirection();

        // Loop to make progressively smaller and smaller squares and circles
        while (currentSize > 1) {

            // Position the pen for drawing
            this.setFillColor(Color.GRAY);
            this.penUp();
            this.turn(-90);
            this.step(currentSize / 2);
            this.turn(90);
            this.step(-currentSize / 2);

            // Draw the square
            this.openPolygon();
            for (int i = 0; i < 4; i++) {
                this.step(currentSize);
                this.turn(90);
            }
            this.closePolygon();

            // Move back to the initial position
            this.setPosition(initX, initY);

            // Draw the circle inside the square
            this.setFillColor(Color.LIGHT_GRAY);
            this.dot((currentSize / 2));

            // Reduce the size for the next loop
            currentSize /= Math.sqrt(2);
        }

        // Finish drawing
        this.penDown();
    }

    public int divergence(double c) {
        double num = 0.0; // Tracks the running total
        int ans = 1; // The current number in the series

        // Loop to incrementally add terms until the sum is greater than or equal to "c"
        while (num < c) {
            if (ans % 2 == 0) { // For even numbers
                num += 1.0 / ans;
            } else { // For odd numbers
                num += (ans - 1.0) / ans;
            }
            if (num >= c) {
                break; // Stop if the sum exceeds "c"
            }
            ans++;
        }
        return ans; // Return the smallest "ans" that makes the sum greater than or equal to "c"
    }

    public boolean isUniDigitNumber(int n) {
        boolean ans = true;
        n = Math.abs(n); // Work with the absolute value
        int lastDigit = -1; // Track the last digit
        int currentDigit;

        // Loop through all digits of the number
        while (n > 0) {
            currentDigit = n % 10; // Get the current last digit

            if (lastDigit != -1) {
                if (currentDigit != lastDigit) { // Check if the current digit is different from the last digit
                    ans = false;
                    break; // If different, the number is not a uniDigit number
                }
            }
            lastDigit = currentDigit; // Update the last digit
            n /= 10; // Remove the last digit

        }
        return ans; // Return true if all digits are the same, false otherwise
    }

    public int countDigits(int n) {
        int ans = 0;
        while (n > 0) {
            n /= 10; // Remove the last digit
            ans++; // Increment the digit count
        }
        return ans; // Return the total digit count
    }

    public int countHammingDistance(int number1, int number2) {
        int ans = 0;
        int digit1 = -1;
        int digit2 = -1;
        number1 = Math.abs(number1);
        number2 = Math.abs(number2);

        // Get the number of digits in both numbers
        int count1 = countDigits(number1);
        int count2 = countDigits(number2);

        if (count1 > count2) { // If the first number has more digits

            for (int i = 0; i < count1; i++) {
                if (i < count2) {
                    // Compare digits of both numbers
                    digit1 = number1 % 10;
                    digit2 = number2 % 10;
                } else {
                    digit1 = number1 % 10;
                    digit2 = 0; // Artificially add a 0 to the number
                }

                if (digit1 != digit2) {
                    ans++; // Increment if digits differ
                }

                // Remove the last digits in both numbers
                number1 /= 10;
                number2 /= 10;

            }

        } else if (count2 > count1) { // If the second number has more digits

            for (int i = 0; i < count2; i++) {
                // Compare digits of both numbers
                if (i < count1) {
                    digit1 = number1 % 10;
                    digit2 = number2 % 10;
                } else {
                    digit1 = 0; // Artificially add a 0 to the number
                    digit2 = number2 % 10;
                }

                if (digit1 != digit2) {
                    ans++; // Increment if digits differ
                }

                // Remove the last digits in both numbers
                number1 /= 10;
                number2 /= 10;
            }
        } else { // If the numbers have the same number of digits

            while (number1 > 0) {
                digit1 = number1 % 10;
                digit2 = number2 % 10;

                if (digit1 != digit2) {
                    ans++; // Increment if digits differ
                }

                // Remove the last digits in both numbers
                number1 /= 10;
                number2 /= 10;
            }
        }

        return ans; // Return the total Hamming distance
    }

}

package sk.paz1a.dominik.chrobak.a3;

import java.awt.Color;

import sk.upjs.jpaz2.*;

public class HomeTurtle extends Turtle {

	public void square(double size) {
		// Store the initial position and direction of the turtle
		double initialX = this.getX();
		double initialY = this.getY();
		double initialDir = this.getDirection();

		// Lift the pen to prevent drawing while repositioning the turtle
		this.penUp();
		// Move half of the specified size forward
		this.step(size / 2);
		// Turn the turtle anti-clockwise by 90 degrees
		this.turn(-90);
		// Move half of the specified size forward
		this.step(size / 2);
		// Turn the turtle clockwise by 90 degrees
		this.turn(90);

		// Begin drawing a polygon
		this.openPolygon();
		// Draw a square by making four right turns and moving forward by the specified
		// size
		for (int i = 0; i < 4; i++) {
			this.turn(90);
			this.step(size);
		}
		// Finish the polygon
		this.closePolygon();

		// Restore the turtle's initial position and direction
		this.setPosition(initialX, initialY);
		this.setDirection(initialDir);
	}

	public void squares(double size) {
		// Store the initial direction of the turtle
		double initialDir = this.getDirection();

		// Initialize a counter to keep track of square drawing and set the initial size
		double counter = 0;
		double newSize = size;

		// Loop to draw a sequence of squares with decreasing sizes
		while (newSize > 1) {
			// Change the fill color based on whether the counter is even or odd
			if (counter % 2 == 0) {
				this.setFillColor(Color.RED);
			} else {
				this.setFillColor(Color.BLACK);
			}
			// Draw a square with the current size
			this.square(newSize);
			// Turn the turtle by 45 degrees
			this.turn(45);
			// Increment the counter to keep track of the number of squares drawn
			counter++;
			// Reduce the size for the next square
			newSize = newSize * 0.70;
		}
		this.setDirection(initialDir);
	}

	public int countDivisors(int n) {
		// Take the absolute value of n to ensure we work with positive integers
		int a = Math.abs(n);
		// Initialize a counter to 1 since all numbers have at least one divisor, which
		// is itself
		int counter = 1;

		// Iterate from 1 to half of 'a' to check for divisors
		for (int i = 1; i <= a / 2; i++) {
			// Check if 'i' is a divisor of 'a' by checking the remainder
			if (a % i == 0) {
				// If 'i' is a divisor, increment the counter
				counter++;
			}
		}

		// Return the total count of divisors for 'n'
		return counter;
	}

	public boolean isPrime(int n) {
		// A number is prime if it has exactly two divisors (1 and itself)
		// We determine this by checking the count of divisors using the countDivisors
		// method
		if (this.countDivisors(n) == 2) {
			// If it has exactly 2 divisors, it's prime
			return true;
		} else {
			// If it has more than 2 divisors, it's not prime
			return false;
		}
	}

	public int goldbach(int n) {
		// Iterate through possible values of 'p' to check for prime numbers
		for (int p = 0; p <= n; p++) {
			// Check if 'p' is a prime number using the isPrime method
			if (this.isPrime(p) == true) {
				// If 'p' is prime, explore possible values of 'q'
				for (int q = 0; q <= n; q++) {
					// Calculate the square of 'q'
					double a = Math.pow(q, 2);
					// Calculate the proposed Goldbach formula
					double formula = p + 2 * a;
					// If the formula matches 'n', it means 'n' can be expressed as p + 2*q^2
					if (formula == n) {
						// Return 'p' as part of the Goldbach formula
						return p;
					}
				}
			}
		}
		// If no combination of prime 'p' and 'q' is found to satisfy Goldbach's
		// conjecture, return -1
		return -1;
	}

	public int combinedNumber(int n, int m) {
		// Initialize variables to store the digits, result, and a counter
		
		// Store the first number
		int a = n;
		// Store the second number
		int b = m;
		// Store the combined result
		int ans = 0;
		// Counter for digit position
		int counter = 0;

		// Continue looping as long as there are digits left in either 'a' or 'b'
		while (a > 0 || b > 0) {

			// Extract the last digit of 'a'
			int digitA = a % 10;
			// Extract the last digit of 'b'
			int digitB = b % 10;

			// Compare the digits and select the larger one to add to the result
			if (digitA < digitB) {
				// Add the larger digit by adding the digit times ten to the power of the number in the counter 
				ans = ans + (digitB * (int) Math.pow(10, counter));
			} else {
				// Add the larger digit by adding the digit times ten to the power of the number in the counter
				ans = ans + (digitA * (int) Math.pow(10, counter));
			}

			// Remove the last digit from 'a' and 'b'
			a = a / 10;
			b = b / 10;
			// Increment the counter to move to the next digit position
			counter++;
		}
		// The result 'ans' now contains the combined number with larger digits from 'n' and 'm'
		return ans;
	}
}

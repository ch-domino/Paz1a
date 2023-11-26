package sk.paz1a.dominik.chrobak.a5;

import java.awt.Color;
import java.awt.event.MouseEvent;

import sk.upjs.jpaz2.Turtle;
import sk.upjs.jpaz2.WinPane;

public class KorytnaciSvet extends WinPane {

	/**
	 * Referencia na pole korytnaciek
	 */
	private Turtle[] korytnacky = null;

	/**
	 * Inicializacna metoda (konstruktor)
	 */
	public KorytnaciSvet() {
		this.korytnacky = new Turtle[0];
	}

	/**
	 * Metoda na pridanie korytnacky na zadanych suradniciach
	 */
	public void pridajKorytnacku(int x, int y) {
		Turtle novaKorytnacka = new Turtle();
		this.add(novaKorytnacka);
		novaKorytnacka.setPosition(x, y);

		Turtle[] noveKorytnacky = new Turtle[this.korytnacky.length + 1];
		System.arraycopy(this.korytnacky, 0, noveKorytnacky, 0, this.korytnacky.length);
		noveKorytnacky[noveKorytnacky.length - 1] = novaKorytnacka;

		this.korytnacky = noveKorytnacky;
	}

	public void vystrelNaTazisko() {
		double sumX = 0; // Initialize a variable to store the sum of X-coordinates
		double sumY = 0; // Initialize a variable to store the sum of Y-coordinates

		// Calculate the sum of X and Y coordinates of all turtles in the array
		for (int i = 0; i < this.korytnacky.length; i++) {
			sumX += this.korytnacky[i].getX(); // Add the X-coordinate of the current turtle
			sumY += this.korytnacky[i].getY(); // Add the Y-coordinate of the current turtle
		}

		double centroidX = sumX / this.korytnacky.length; // Calculate the X-coordinate of the centroid
		double centroidY = sumY / this.korytnacky.length; // Calculate the Y-coordinate of the centroid

		// Iterate through all turtles in the array
		for (int j = 0; j < this.korytnacky.length; j++) {
			double turtleX = this.korytnacky[j].getX(); // Get the X-coordinate of the current turtle
			double turtleY = this.korytnacky[j].getY(); // Get the Y-coordinate of the current turtle

			// Move the turtle to the calculated centroid
			this.korytnacky[j].moveTo(centroidX, centroidY);

			// Move the turtle back to its original X and Y coordinates
			this.korytnacky[j].moveTo(turtleX, turtleY);
		}
	}

	public double explozia(double x, double y, double sila) {
		double biggest = 0; // Initialize a variable to store the biggest movement factor

		// Iterate through all turtles in the array
		for (int i = 0; i < korytnacky.length; i++) {
			double distance = this.korytnacky[i].distanceTo(x, y); // Calculate the distance from the turtle to the
																	// explosion point
			// Calculate the movement factor based on the explosion strength and distance
			double movementFactor = (sila * sila) / (distance * distance * distance * distance);

			this.korytnacky[i].penUp();
			this.korytnacky[i].setDirectionTowards(x, y); // Set the direction towards the explosion point
			this.korytnacky[i].step(-movementFactor); // Move the turtle away from the explosion point
			this.korytnacky[i].turn(180); // Turn the turtle around to face the opposite direction
			this.korytnacky[i].penDown();

			// Check if the current movement factor is the biggest so far
			if (movementFactor > biggest) {
				biggest = movementFactor; // Update the biggest movement factor
			}
		}
		return biggest; // Return the biggest movement factor
	}

	public double casDoPrichodu(double x, double y) {
		double rotation = 0; // Initialize a variable to store the rotation angle
		double distance = 0; // Initialize a variable to store the distance to the target point
		double fastestTime = Double.MAX_VALUE; // Initialize a variable to store the fastest time

		// Iterate through all turtles in the array
		for (int i = 0; i < korytnacky.length; i++) {
			double initialDir = this.korytnacky[i].getDirection(); // Get the initial direction of the turtle

			// Set the direction of the turtle towards the target point (x, y)
			this.korytnacky[i].setDirectionTowards(x, y);

			// Get the new direction after setting it towards the target
			double newDir = this.korytnacky[i].getDirection();

			// Restore the initial direction
			this.korytnacky[i].setDirection(initialDir);

			// Calculate the distance from the turtle to the target point
			distance = this.korytnacky[i].distanceTo(x, y);

			// Calculate the absolute rotation angle between the initial and new directions
			rotation = Math.abs(newDir - initialDir);

			// If the rotation angle is greater than 180 degrees, use the complementary angle
			if (rotation > 180) {
				rotation = 360 - rotation;
			}

			// Calculate the time it would take for the turtle to reach the target point
			double time = rotation + distance;

			// Check if the calculated time is faster than the current fastest time
			if (fastestTime > time) {
				fastestTime = time; // Update the fastest time
			}
		}
		return fastestTime; // Return the fastest time to reach the target point
	}

	public int[] histogram(double x, double y, double d) {

		return null;
	}

	public void doStvorca(double dlzkaStrany) {
		// Calculate the number of turtles for each side of the square
		int countOfTurtles = this.korytnacky.length / 4;

		// Calculate the coordinates of the center of the drawing area
		double xCoordinateOfTheCenter = this.getWidth() / 2;
		double yCoordinateOfTheCenter = this.getHeight() / 2;

		// Calculate the distance between turtles along one side of the square
		double separatedParts = dlzkaStrany / (countOfTurtles + 1);

		// Place turtles on the first side of the square
		for (int i = 0; i < countOfTurtles; i++) {
			this.korytnacky[i].penUp();

			// Set the position of the turtle along the top side of the square
			this.korytnacky[i].setPosition(xCoordinateOfTheCenter + (separatedParts * (i + 1)) - dlzkaStrany / 2,
					yCoordinateOfTheCenter - dlzkaStrany / 2);
		}

		// Place turtles on the second side of the square
		for (int k = 2 * countOfTurtles; k < 3 * countOfTurtles; k++) {
			this.korytnacky[k].penUp();

			// Set the position of the turtle along the bottom side of the square
			this.korytnacky[k].setPosition(xCoordinateOfTheCenter - (dlzkaStrany / 2),
					yCoordinateOfTheCenter + (separatedParts * ((k - countOfTurtles * 2) + 1)) - (dlzkaStrany / 2));
		}

		// Place turtles on the third side of the square
		for (int j = countOfTurtles; j < 2 * countOfTurtles; j++) {
			this.korytnacky[j].penUp();

			// Set the position of the turtle along the right side of the square
			this.korytnacky[j].setPosition(
					xCoordinateOfTheCenter + (separatedParts * ((j - countOfTurtles) + 1)) - dlzkaStrany / 2,
					yCoordinateOfTheCenter + dlzkaStrany / 2);
		}

		// Place turtles on the fourth side of the square
		for (int l = 3 * countOfTurtles; l < 4 * countOfTurtles; l++) {
			this.korytnacky[l].penUp();

			// Set the position of the turtle along the left side of the square
			this.korytnacky[l].setPosition(xCoordinateOfTheCenter + (dlzkaStrany / 2),
					yCoordinateOfTheCenter + (separatedParts * ((l - countOfTurtles * 3) + 1)) - (dlzkaStrany / 2));
		}
	}

	public void prestrelka(int idxPrvehoStrelca, Color farbaStriel) {
		// Create an array to keep track of hit status for each turtle
		boolean[] hitStatus = new boolean[korytnacky.length];

		// Initialize the index of the selected shooter
		int selectedTurtle = idxPrvehoStrelca;

		// Start an infinite loop (it will break when no non-hit turtles are left)
		while (true) {
			// Initialize variables to find the nearest non-hit turtle
			int nearestNonHitTurtle = -1;
			double shortestDistance = Double.MAX_VALUE;

			// Iterate through all turtles to find the nearest non-hit turtle
			for (int i = 0; i < korytnacky.length; i++) {
				// Skip the current shooter and already hit turtles
				if (i == selectedTurtle || hitStatus[i]) {
					continue;
				}

				// Calculate the distance between the selected turtle and the current turtle
				double distance = this.korytnacky[selectedTurtle].distanceTo(this.korytnacky[i].getX(),
						this.korytnacky[i].getY());

				// Update the nearest non-hit turtle and the shortest distance
				if (distance < shortestDistance) {
					shortestDistance = distance;
					nearestNonHitTurtle = i;
				}
			}

			// If a nearest non-hit turtle is found, prepare to "shoot"
			if (nearestNonHitTurtle != -1) {
				// Store the current position of the selected turtle
				double currentPositionX = this.korytnacky[selectedTurtle].getX();
				double currentPositionY = this.korytnacky[selectedTurtle].getY();
				Color currentPenColor = this.korytnacky[selectedTurtle].getPenColor();

				// Set the pen color for the selected turtle (color of the "shot")
				this.korytnacky[selectedTurtle].setPenColor(farbaStriel);

				// Turn the selected turtle towards the nearest non-hit turtle
				this.korytnacky[selectedTurtle].turnTowards(this.korytnacky[nearestNonHitTurtle].getX(),
						this.korytnacky[nearestNonHitTurtle].getY());

				// Move the selected turtle to the position of the nearest non-hit turtle
				this.korytnacky[selectedTurtle].moveTo(this.korytnacky[nearestNonHitTurtle].getX(),
						this.korytnacky[nearestNonHitTurtle].getY());

				// Reset the position of the selected turtle to its original position
				this.korytnacky[selectedTurtle].setPosition(currentPositionX, currentPositionY);

				// Reset the pen color of the selected turtle to its original color
				this.korytnacky[selectedTurtle].setPenColor(currentPenColor);

				// Mark the nearest non-hit turtle as "hit"
				hitStatus[nearestNonHitTurtle] = true;
			} else {
				// If no nearest non-hit turtle is found, exit the loop
				break;
			}
			// Update the selected turtle to be the nearest non-hit turtle for the next
			// "shot"
			selectedTurtle = nearestNonHitTurtle;
		}
	}

	@Override
	protected void onMouseClicked(int x, int y, MouseEvent detail) {
		if (!(detail.isAltDown() || detail.isControlDown() || detail.isShiftDown())) {
			this.pridajKorytnacku(x, y);
		}
	}
}

package sk.paz1a.dominik.chrobak.a4;

import java.awt.Color;
import java.awt.event.MouseEvent;

import sk.upjs.jpaz2.*;

public class ClickPane extends WinPane {

	public void kresliKotviaceBody() {
		Turtle kreslic = new Turtle();
		this.add(kreslic);

		int rozostup = 10;
		int pocetHorizontalnych = this.getWidth() / rozostup;
		int pocetVertikalnych = this.getHeight() / rozostup;

		kreslic.setFillColor(Color.gray);
		for (int j = 0; j <= pocetVertikalnych; j++) {
			for (int i = 0; i <= pocetHorizontalnych; i++) {
				kreslic.setPosition(i * rozostup + rozostup / 2, j * rozostup + rozostup / 2);
				kreslic.dot(1);
			}
		}

		this.remove(kreslic);
	}

	// A private variable to keep track of the number of clicks
	private int clickCount = 0;

	public void createDot(int column, int row, Color c) {
		// Create a new 'Turtle' object for dot creation
		Turtle dotCreator = new Turtle();
		// Add the 'dotCreator' to the current pane
		this.add(dotCreator);

		// Calculate the exact pixel position for the dot based on the column and row
		// Calculate the X-coordinate for the dot
		int xPosition = column * 10 + 5;
		// Calculate the Y-coordinate for the dot
		int yPosition = row * 10 + 5;

		// Set the fill color of the dot using the specified color 'c'
		dotCreator.setFillColor(c);

		// Position the 'dotCreator' turtle at the calculated coordinates
		dotCreator.setPosition(xPosition, yPosition);
		// Draw a small dot with a radius of 3 pixels
		dotCreator.dot(3);

		// Remove the 'dotCreator' turtle from the pane
		this.remove(dotCreator);
	}

	public void createLineConnection(int x1, int y1, int x2, int y2, Color c) {
		// Create a new 'Turtle' object for drawing lines
		Turtle lineCreator = new Turtle();
		// Add the 'lineCreator' to the current pane
		this.add(lineCreator);

		// Set the pen color of the 'lineCreator' turtle to the specified color 'c'
		lineCreator.setPenColor(c);

		// Check the color 'c' to determine the line drawing direction and position
		if (c == Color.red) {
			// Check if the endpoint 'x2' is to the right of the starting point 'x1'
			if (x2 > x1) {
				// Set the starting position and direction for the red line (left to right)
				lineCreator.setPosition(x2 * 10 + 5, y2 * 10 + 5);
				lineCreator.setDirection(-90);
				// Draw the horizontal segment of the line
				lineCreator.step((x2 * 10 + 5) - (x1 * 10 + 5));
				lineCreator.setDirection(180);
				// Draw the vertical segment of the line
				lineCreator.step((y1 * 10 + 5) - (y2 * 10 + 5));
			} else {
				// Set the starting position and direction for the red line (left to right)
				lineCreator.setPosition(x1 * 10 + 5, y1 * 10 + 5);
				lineCreator.setDirection(90);
				// Draw the horizontal segment of the line
				lineCreator.step((x2 * 10 + 5) - (x1 * 10 + 5));
				lineCreator.setDirection(0);
				// Draw the vertical segment of the line
				lineCreator.step((y1 * 10 + 5) - (y2 * 10 + 5));
			}
		} else {
			// Check if the endpoint 'x2' is to the right of the starting point 'x1'
			if (x2 > x1) {
				// Set the starting position and direction for the non-red line (left to right)
				lineCreator.setPosition(x1 * 10 + 5, y1 * 10 + 5);
				lineCreator.setDirection(90);
				// Draw the horizontal segment of the line
				lineCreator.step((x2 * 10 + 5) - (x1 * 10 + 5));
				lineCreator.setDirection(0);
				// Draw the vertical segment of the line
				lineCreator.step((y1 * 10 + 5) - (y2 * 10 + 5));
			} else {
				// Set the starting position and direction for the non-red line (left to right)
				lineCreator.setPosition(x2 * 10 + 5, y2 * 10 + 5);
				lineCreator.setDirection(-90);
				// Draw the horizontal segment of the line
				lineCreator.step((x2 * 10 + 5) - (x1 * 10 + 5));
				lineCreator.setDirection(180);
				// Draw the vertical segment of the line
				lineCreator.step((y1 * 10 + 5) - (y2 * 10 + 5));
			}
		}

		// Remove the 'lineCreator' turtle from the pane
		this.remove(lineCreator);
	}

	// X-coordinate of the first dot
	private int firstDotX = 0;
	// Y-coordinate of the first dot
	private int firstDotY = 0;
	// X-coordinate of the second dot
	private int secondDotX = 0;
	// Y-coordinate of the second dot
	private int secondDotY = 0;

	@Override
	protected void onMouseClicked(int x, int y, MouseEvent detail) {

		// Check if the click count is even (starting with 0)
		if (this.clickCount % 2 == 0) {
			// Calculate the grid coordinates for the first dot
			// Convert the x-coordinate to grid units
			// Convert the y-coordinate to grid units
			firstDotX = x / 10;
			firstDotY = y / 10;
			// Create a blue dot at the first clicked position
			this.createDot(x / 10, y / 10, Color.blue);
		} else {
			// Check if the 'Alt' key is pressed during the click
			if (detail.isAltDown()) {
				// Calculate the grid coordinates for the second dot
				// Convert the x-coordinate to grid units
				secondDotX = x / 10;
				// Convert the y-coordinate to grid units
				secondDotY = y / 10;

				// Create a red line connection between the first and second dots
				this.createLineConnection(firstDotX, firstDotY, secondDotX, secondDotY, Color.red);
				// Create a red dot at the second clicked position
				this.createDot(x / 10, y / 10, Color.red);
				// Create a red dot at the first dot's position
				this.createDot(firstDotX, firstDotY, Color.red);
			} else {
				// Calculate the grid coordinates for the second dot
				// Convert the x-coordinate to grid units
				secondDotX = x / 10;
				// Convert the y-coordinate to grid units
				secondDotY = y / 10;

				// Create a black line connection between the first and second dots in
				this.createLineConnection(firstDotX, firstDotY, secondDotX, secondDotY, Color.black);
				// Create a black dot at the second clicked position
				this.createDot(x / 10, y / 10, Color.black);
				// Create a black dot at the first dot's position
				this.createDot(firstDotX, firstDotY, Color.black);
			}
		}
		// Increment the click count for tracking even/odd clicks
		clickCount++;

	}
}

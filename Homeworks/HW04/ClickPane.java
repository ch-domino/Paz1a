package sk.upjs.paz;

import sk.upjs.jpaz2.Turtle;
import sk.upjs.jpaz2.WinPane;

import java.awt.event.MouseEvent;

public class ClickPane extends WinPane {

    // Class variables to track the turtle's drawing state and positions
    private Turtle dotter; // The turtle object used for drawing
    private int count = 1; // Keeps track of the current dot number
    private double firstX; // Stores the x-coordinate of the first dot
    private double firstY; // Stores the y-coordinate of the first dot
    private double currentX; // Stores the current x-coordinate of the last dot
    private double currentY; // Stores the current y-coordinate of the last dot

    @Override
    protected void onMousePressed(int x, int y, MouseEvent detail) {
        // Call the superclass method to handle basic mouse click event
        super.onMouseClicked(x, y, detail);

        // Create a new turtle for drawing and add it to the canvas
        this.dotter = new Turtle();
        this.add(this.dotter);

        // Set the direction for the turtle (for printing text upwards)
        this.dotter.setDirection(90);

        // Convert the current dot count and previous dot count to strings
        String s = Integer.toString(count);
        String ps = Integer.toString(count - 1);

        // Check if this is the first dot to be drawn
        if (count == 1) {
            // Place the turtle at the mouse click position and draw the first dot
            this.dotter.setPosition(x, y);
            this.dotter.dot(10); // Draws a dot with a size of 10
            this.dotter.printCenter(s); // Prints the count number in the center of the dot

            // Increment the count for the next dot
            this.count++;

            // Update position variavbes to store the first dot's coordinates
            this.firstX = x;
            this.firstY = y;
            this.currentX = x;
            this.currentY = y;

            // Remove the turtle object after drawing
            this.remove(this.dotter);

        } else {
            // If this is not the first dot, move the turtle to the current mouse position
            this.dotter.setPosition(x, y);

            // Check if the turtle is within 10 units of the first dot's position
            if (this.dotter.distanceTo(firstX, firstY) <= 10) {
                // Close the loop by moving back to the first dot and completing the shape
                this.dotter.setPosition(firstX, firstY);
                this.dotter.moveTo(currentX, currentY); // Draws a line to the previous dot
                this.dotter.dot(10); // Redraws the previous dot
                this.dotter.printCenter(ps); // Print the previous dot number
                this.dotter.setPosition(firstX, firstY); // Teleport to first dot position
                this.dotter.dot(10); // Redraw the first dot
                this.dotter.printCenter("1"); // Print "1" on the first dot

                // Reset the count to 1 for the next series of dots
                this.count = 1;

                // Remove the turtle object after drawing
                this.remove(this.dotter);
            } else {
                // If the loop shouldn't close, draw a line from the last dot position to the current dot position
                if (count == 2) {
                    this.dotter.moveTo(firstX, firstY); // Move to the first dot if it's the second dot
                } else {
                    this.dotter.moveTo(currentX, currentY); // Move to the last dot otherwise
                }

                // Redraw the previous dot and print its number
                this.dotter.dot(10);
                this.dotter.printCenter(ps);

                // Draw the current dot and print its number
                this.dotter.setPosition(x, y);
                this.dotter.dot(10);
                this.dotter.printCenter(s);

                // Increment the count for the next dot
                this.count++;

                // Update the current dot's position
                this.currentX = x;
                this.currentY = y;

                // Remove the turtle object after drawing
                this.remove(this.dotter);
            }

        }

    }

}

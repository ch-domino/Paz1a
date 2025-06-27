package sk.upjs.paz;

import sk.upjs.jpaz2.Turtle;

import java.awt.*;

public class HomeTurtle extends Turtle {

    // Draws the flag of South Korea using random walk
    public void flagOfSouthKorea(int stepCount, double height) {
        // Store initial position and direction
        double initX = this.getX();
        double initY = this.getY();
        double initDir = this.getDirection();

        // Add random movements while changing color depending on position
        for (int i = 0; i < stepCount; i++) {
            this.turn(Math.random() * 360); // Random turn in any direction
            this.penUp();
            this.step(5); // Move forward

            // Check if position is outside the flag's boundary and reverse if necessary
            if (this.getX() >= initX + (1.5 * height / 2) || this.getX() <= initX - (1.5 * height / 2)) {
                this.step(-5); // Reverse the step if outside the boundary
            } else if (this.getY() >= initY + (height / 2) || this.getY() <= initY - (height / 2)) {
                this.step(-5); // Reverse step if outside Y boundary
            } else {
                // Set color based on position relative to the flag's center and smaller circles
                if (this.distanceTo(initX, initY) >= height / 4) {
                    this.step(-5); // Reverse step if far from center
                    this.setPenColor(Color.WHITE); // Set color to white
                    this.penDown();
                    this.step(5); // Move forward with the pen down
                } else if (this.getY() >= initY) {
                    // Set color based on proximity to the upper small circles
                    if (this.distanceTo(initX + (0.125 * height), initY) <= 0.125 * height) {
                        this.step(-5);
                        this.setPenColor(Color.BLUE); // Blue for upper right
                        this.penDown();
                        this.step(5);
                    } else if (this.distanceTo(initX - (0.125 * height), initY) <= 0.125 * height) {
                        this.step(-5);
                        this.setPenColor(Color.RED); // Red for upper left
                        this.penDown();
                        this.step(5);
                    } else {
                        this.step(-5);
                        this.setPenColor(Color.BLUE); // Default to blue
                        this.penDown();
                        this.step(5);
                    }
                } else if (this.getY() <= initY) {
                    // Set color based on proximity to the lower small circles
                    if (this.distanceTo(initX + (0.15 * height), initY) <= 0.125 * height) {
                        this.step(-5);
                        this.setPenColor(Color.BLUE); // Blue for lower right
                        this.penDown();
                        this.step(5);
                    } else if (this.distanceTo(initX - (0.125 * height), initY) <= 0.125 * height) {
                        this.step(-5);
                        this.setPenColor(Color.RED); // Red for lower left
                        this.penDown();
                        this.step(5);
                    } else {
                        this.step(-5);
                        this.setPenColor(Color.RED); // Default to red
                        this.penDown();
                        this.step(5);
                    }
                }
            }
        }

        // Return to initial position and direction
        this.setPosition(initX, initY);
        this.setDirection(initDir);
    }


    // Draws a single arrow with a polygon base
    public void sipka(double sirka, double dlzka) {
        double initX = this.getX(); // Store initial positiona nd direction
        double initY = this.getY();
        double initDir = this.getDirection();

        this.penUp();

        // Position the pen to start drawing the arrow
        this.turn(-90);
        this.step(sirka / 2);
        this.turn(90);

        this.openPolygon(); // Begin drawing the arrow shape

        // Draw the arrow tail
        this.turn(30);
        this.step(sirka);
        this.turn(120);
        this.step(sirka);
        this.turn(-150);

        // Draw the arrow head
        this.step(dlzka);
        this.turn(-30);
        this.step(sirka);
        this.turn(-120);
        this.step(sirka);
        this.turn(-30);
        this.step(dlzka);

        this.closePolygon(); // Close the arrow shape

        // Return to initial position and direction
        this.setPosition(initX, initY);
        this.setDirection(initDir);
    }

    // Draws a sequence of arrows decreasing in size
    public void sipkovnica(int pocetSipok, double sirka, double dlzkaPrvej, double medzera) {
        // Store initial position and direction
        double initX = this.getX();
        double initY = this.getY();
        double initDir = this.getDirection();

        // Initialize with the size of the first arrow
        double size = dlzkaPrvej;

        // Loop through the number of arrows
        for (int i = pocetSipok; i > 0; i--) {
            // Alternate colors for each arrow
            if (i == pocetSipok) {
                this.setFillColor(Color.ORANGE); // First arrow color
            } else if (i % 2 == 0) {
                this.setFillColor(Color.BLACK); // Even arrows
            } else {
                this.setFillColor(Color.ORANGE); // Odd arrows
            }

            // Draw the arrow and adjust spacing
            if (i == pocetSipok) {
                this.sipka(sirka, dlzkaPrvej);
                this.step(dlzkaPrvej + medzera);
            } else {
                this.sipka(sirka, size);
                this.step(size + medzera);
            }
            size *= 0.7; // Reduce size for each subsequent arrow
        }

        // Return to initial position and direction
        this.setPosition(initX, initY);
        this.setDirection(initDir);
    }
}

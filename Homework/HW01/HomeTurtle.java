package sk.upjs.paz;

import sk.upjs.jpaz2.*;

import java.awt.*;

public class HomeTurtle extends Turtle {

    // Draws a flower with red petals and yellow center
    public void flower(double radius) {
        this.penUp(); // Prepare for drawing
        this.setFillColor(Color.RED); // Petal color

        // Loop to draw 10 petals
        for (int i = 0; i < 10; i++) {
            this.step(radius);
            this.dot(radius / 2); // Draw petal
            this.step(-radius);
            this.turn(360 / 10); // Position for next petal
        }

        this.setFillColor(Color.YELLOW); // Center color
        this.dot(radius); // Draw center
        this.penDown(); // Finish drawing
    }

    // Draws a triangular arrow shape
    public void arrow(double size) {
        this.penUp(); // Prepare for drawing
        this.setFillColor(Color.ORANGE); // Arrow color

        this.turn(-90);
        this.openPolygon();
        this.step(size / 2);

        // Draw two legs of the arrow
        for (int i = 0; i < 2; i++) {
            this.turn(120);
            this.step(size);
        }

        this.turn(120);
        this.step(size / 2);
        this.closePolygon();
        this.turn(90);
        this.penDown(); // Finish drawing
    }

    // Draws multipe arrows in a row
    public void navigationArrow(double size) {
        // Draw 5 arrows in a row
        for (int i = 0; i < 5; i++) {
            this.arrow(size); // Draw individual arrow
            this.penUp();
            this.step(size / 2); // Move to next position
        }

        this.step(-(5 * size / 2)); // Return to start
        this.penDown();
    }

    // Draws a hexagon
    public void hexagon(double size) {
        this.penUp(); // Prepare for drawing
        this.step(size);
        this.penDown();
        this.turn(120);

        // Draw 6 sides of the hexagon
        for (int i = 0; i < 6; i++) {
            this.step(size);
            this.turn(360 / 6);
        }

        this.turn(-120); // Reorient back to the original direction
        this.penUp();
        this.step(-size); // Move back to starting point
        this.penDown(); // Finish drawing
    }

    // Draw a beehive pattern using hexagons
    public void beehive(double size) {
        this.penUp(); // Prepare for drawing
        this.setPenColor(Color.GREEN); // set hexagon color

        // Draw 6 hexagons arranged in a circular pattern
        for (int i = 0; i < 6; i++) {
            this.step(size);
            this.turn(60);
            this.step(size);
            this.turn(-60);
            this.hexagon(size); // Draw a hexagon
            this.penUp();

            // Move to the next position for the next hexagon
            this.turn(60);
            this.step(-size);
            this.turn(-60);
            this.step(-size);
            this.turn(360 / 6); // Rotate to next hexagon position

        }

        this.penDown(); // Finish drawing
    }

    // Draws a smartwatch face with hour and minute hands
    public void smartWatch(double radius, int hh, int mm) {
        this.setPenColor(Color.BLACK); // Set pen color for the watch

        this.setFillColor(Color.BLUE); // Set face color
        this.dot(radius); // Draw the outer watch face

        this.setFillColor(Color.LIGHT_GRAY); // Inner watch face color
        this.dot((radius / 4) * 3); // Draw the inner watch face

        // Draw 12 tick marks around the face
        for (int i = 0; i < 12; i++) {
            this.penUp();
            this.step((radius / 4) * 2); // Move to tick position
            this.penDown();
            this.step(radius / 4); // Draw tick
            this.penUp();
            this.step(-(radius / 4) * 3); // Move back to center
            this.turn(360 / 12); // Rotate to next tick
        }

        this.penDown(); // Ready for hands

        // Draw the hour hand
        this.setPenColor(Color.RED);
        this.setPenWidth(5);
        this.setDirection((360 / 12) * hh + (30 * mm / 60)); // Set direction for the hour hand
        this.step(radius / 3); // Draw hour hand
        this.step(-radius / 3); // Move back to center
        this.setDirection(0);

        // Draw the minute hand
        this.setPenWidth(3);
        this.setDirection((360 / 60) * mm); // Set direction for the minute hand
        this.step(2 * radius / 3); // Draw minute hand
        this.step(-2 * radius / 3); // move back to center
        this.setDirection(0);


        this.setFillColor(Color.BLUE); // Center dot color
        this.dot(radius / 10); // Draw center dot

    }
}

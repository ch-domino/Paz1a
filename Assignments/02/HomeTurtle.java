package sk.paz1a.dominik.chrobak.a2;

import java.awt.Color;

import sk.upjs.jpaz2.*;

public class HomeTurtle extends Turtle {

	// Please code review

	public void flagOfMalawi(int stepCount, double height) {

		/*
		 * 
		 * Beginning of the code
		 * 
		 */

		// Create a variable called "startPosX" which saves the initial "X" position of
		// the Turtle.
		double startPosX = this.getX();
		// Create a variable called "startPosY" which saves the initial "Y" position of
		// the Turtle.
		double startPosY = this.getY();

		// Create a variable called "startDirection" which saves the initial orientation
		// of the Turtle.
		double startDirection = this.getDirection();

		// Create a variable called "width" which saves the calculation of the flag
		// width
		// by multiplying the height by 2.
		double width = height * 2;

		// Create a variable called "heightOfRectangles" which saves the calculated
		// height
		// of each rectangle by dividing the input height by 3.
		double heightOfRectangles = height / 3;

		// Make a variable called "radiusOfCircle" which saves the calculated radius of
		// the circle by dividing the "heightOfRectangles" by 2.
		double radiusOfCircle = heightOfRectangles / 2;

		// Create a variable called "centerOfCircleY" which saves the calculated "Y"
		// position of the centre of the circle by subtracting the "startPosY" by the
		// "heightOfRectangles" divided by 2.
		double centreOfCircleY = startPosY - (heightOfRectangles / 2);

		/*
		 * 
		 * Creating the Flag
		 * 
		 */

		// Create a for loop where the "i" starts on 0 and adds 1 after each step.
		// The loop continues while the "i" is lower than the inputed "stepCount".
		// Once the "i" is equal to "stepCount" the for loop stops/breaks.
		for (int i = 0; i < stepCount; i++) {

			// Create a boolean called "redRectangle" which compares the Turtle's position.
			// The boolean checks if the "Y" position of the Turtle is HIGHER than the
			// "startPosY" minus the ( "heightOfRectangles" minus the "heightOfRectangles"
			// divided by two ).
			// The boolean also checks if the "Y" position of the Turtle is LOWER than the
			// "startPosY" plus the ( "heightOfRectangles" minus the "heightOfRectangles"
			// divided by two ).
			// Both checks have to be TRUE for the boolean to be TRUE.
			boolean redRectangle = (this.getY() > startPosY - (heightOfRectangles - (heightOfRectangles / 2)))
					&& (this.getY() < startPosY + (heightOfRectangles - (heightOfRectangles / 2)));

			/*
			 * 
			 * Changing Colors depending on position
			 * 
			 */

			// Create an If statement which checks if the boolean called "redRectangle" is
			// TRUE.
			// If the boolean is TRUE change the Turtle's pen color to RED.
			if (redRectangle) {
				this.setPenColor(Color.RED);
			}

			// Create a boolean called "greenRectangle" which compares the Turtle's
			// position.
			// The boolean checks if the "Y" position of the Turtle is HIGHER than the
			// "startPosY" plus the "heightOfRectangles" divided by two.
			boolean greenRectangle = (this.getY() > startPosY + (heightOfRectangles / 2));

			// Create an If statement which checks if the boolean called "greenRectangle" is
			// TRUE.
			// If the boolean is TRUE change the Turtle's pen color to GREEN.
			if (greenRectangle) {
				this.setPenColor(Color.GREEN);
			}

			// Create a boolean called "greenRectangle" which compares the Turtle's
			// position.
			// The boolean checks if the "Y" position of the Turtle is LOWER than the
			// "startPosY" minus the "heightOfRectangles" divided by two.
			boolean blackRectangle = (this.getY() < startPosY - (heightOfRectangles / 2));

			// Create an If statement which checks if the boolean called "blackRectangle" is
			// TRUE.
			// If the boolean is TRUE change the Turtle's pen color to BLACK.
			if (blackRectangle) {
				this.setPenColor(Color.BLACK);
			}

			// Create a boolean called "redCircle" which compares the Turtle's position.
			// The boolean checks if the distance between the "X & Y" position of the Turtle
			// and the "x: startPosX" & "y: centreOfCircleY" is LOWER than the
			// "radiusOfCircle".
			boolean redCircle = this.distanceTo(startPosX, centreOfCircleY) < radiusOfCircle;

			// Create an If statement which checks if the boolean called "redCircle" is
			// TRUE.
			// If the boolean is TRUE change the Turtle's pen color to RED.
			if (redCircle) {
				this.setPenColor(Color.RED);
			}

			/*
			 * 
			 * Random movement for the Turtle
			 * 
			 */

			// Make the Turtle turn by a random value from 0 to 360.
			this.turn(Math.random() * 360);
			// Make the Turtle move by 5
			this.step(5);

			/*
			 * 
			 * Checking for the bounding box
			 * 
			 */

			// Create a boolean called "outsideX" which compares the Turtle's position.
			// The boolean checks if the "X" position of the Turtle is HIGHER than the
			// "startPosX" plus the "width" divided by two.
			// The boolean also checks if the "X" position of the Turtle is LOWER than the
			// "startPosX" minus the "width" divided by two.
			// The boolean returns TRUE if the Turtle's X position is HIGHER or LOWER than
			// the Bounding Box.
			boolean outsideX = (this.getX() > startPosX + width / 2) || (this.getX() < startPosX - width / 2);

			// Create a boolean called "outsideY" which compares the Turtle's position.
			// The boolean checks if the "Y" position of the Turtle is HIGHER than the
			// "startPosY" plus the "width" divided by two.
			// The boolean also checks if the "Y" position of the Turtle is LOWER than the
			// "startPosY" minus the "width" divided by two.
			// The boolean returns TRUE if the Turtle's Y position is HIGHER or LOWER than
			// the Bounding Box.
			boolean outsideY = (this.getY() > startPosY + height / 2) || (this.getY() < startPosY - height / 2);

			// Create a boolean called "isOutsideBounds" which compares the Turtle's
			// position.
			// The boolean checks if the "outsideX" is TRUE or False
			// The boolean also checks if the "outsideY" is TRUE or False
			// The boolean returns TRUE if either of the outside booleans are TRUE.
			boolean isOutsideBounds = outsideX || outsideY;

			// Create an If statement which checks if the boolean called "isOutsideBounds"
			// is
			// TRUE.
			// If the boolean is TRUE make the turtle move back by 5.
			if (isOutsideBounds) {
				this.step(-5);
			}
		}

		/*
		 * 
		 * Making the Turtle move back to the initial postition
		 * 
		 */

		// Create a chain of commands which moves the Turtle back to it's starting
		// position
		// Make the Turtle lift up the pen to not make any lines.
		this.penUp();
		// Make the Turtle move to the saved "startPosX" & "startPosY"
		this.moveTo(startPosX, startPosY);
		// Make the Turtle rotate to the saved "startDirection"
		this.setDirection(startDirection);
		// Make the Turtle put the pen down so it can draw again
		this.penDown();
	}

	public void triangle(double width) {

		/*
		 * 
		 * Beginning of the code
		 * 
		 */

		// Make the Turtle lift up the pen to not make any lines.
		this.penUp();
		// Make the Turtle turn by 90 degrees anti-clockwise.
		this.turn(-90);
		// Make the Turtle move by half the size of the inputed "width".
		this.step(width / 2);
		// Make the Turtle open a Polygon and start collecting the polygon points.
		this.openPolygon();

		/*
		 * 
		 * Creating the Triangle
		 * 
		 */

		// Create a for loop where the "i" starts on 0 and adds 1 after each step.
		// The loop continues while the "i" is lower than "3".
		// Once the "i" is equal to "3" the for loop stops/breaks.
		for (int i = 0; i < 3; i++) {
			// Make the Turtle turn by 120 degrees clockwise.
			this.turn(120);
			// Make the Turtle move by the inputed "width".
			this.step(width);
		}

		/*
		 * 
		 * Creating a few commands to move the Turtle to the Top of the Triangle
		 * 
		 */

		// Make the Turtle close the Polygon and fill in the polygon shape from the
		// gathered points
		this.closePolygon();
		// Make the Turtle turn by 120 degrees clockwise.
		this.turn(120);
		// Make the Turtle move by the inputed "width".
		this.step(width);
		// Make the Turtle turn by 30 degrees anti-clockwise.
		this.turn(-30);
		// Make the Turtle put the pen down so it can draw again.
		this.penDown();
	}

	public void littleTree(int levelCount, double width) {

		/*
		 * 
		 * Beginning of the code
		 * 
		 */

		// Create a variable called "startPosX" which saves the initial "X" position of
		// the Turtle.
		double startPosX = this.getX();
		// Create a variable called "startPosY" which saves the initial "Y" position of
		// the Turtle.
		double startPosY = this.getY();

		// Create a variable called "startDirection" which saves the initial orientation
		// of the Turtle.
		double startDirection = this.getDirection();

		// Create a variable called "counter" which starts at "0".
		int counter = 0;
		// Create a variable called "decreasedWidth" which begins equal to width.
		double decreasedWidth = width;

		// Create a variable called "a" which calculates the height of the Tree Trunk by
		// dividing the "width" by 8.
		double a = width / 8;
		// Create a variable called "b" which calculates the width of the Tree Trunk by
		// dividing the "width" by 4.
		double b = width / 4;

		// Make the Turtle lift up the pen to not make any lines.
		this.penUp();
		// Make the Turtle change it's color to the (R: 153,G: 76,B: 0) values.
		this.setFillColor(new Color(153, 76, 0));
		// Make the Turtle Turn by 90 degrees anti-clockwise.
		this.turn(-90);
		// Make the Turtle move by half the Tree Trunk width which is "(width/8)/2"
		// which equals to "a".
		this.step(a);
		// Make the Turtle open a Polygon and start collecting the polygon points.
		this.openPolygon();

		/*
		 * 
		 * Creating the Tree Trunk
		 * 
		 */

		// Create a for loop where the "j" starts on 0 and adds 1 after each step.
		// The loop continues while the "j" is lower than "2".
		// Once the "j" is equal to "2" the for loop stops/breaks.
		for (int j = 0; j < 2; j++) {
			// Make the Turtle Turn by 90 degrees clockwise.
			this.turn(90);
			// Make the Turtle move by "a".
			this.step(a);
			// Make the Turtle Turn by 90 degrees clockwise.
			this.turn(90);
			// Make the Turtle move by "b".
			this.step(b);
		}
		// Make the Turtle close the Polygon and fill in the polygon shape from the
		// gathered points
		this.closePolygon();

		/*
		 * 
		 * Move to the Top center of the Tree Trunk
		 * 
		 */

		// Make the turtle move back by "a".
		this.step(-a);
		// Make the turtle turn by 90 degrees clockwise.
		this.turn(90);
		// make the turtle move by "a".
		this.step(a);

		/*
		 * 
		 * Creating the Tree Levels
		 * 
		 */

		// Create a for loop where the "i" starts on 0 and adds 1 after each step.
		// The loop continues while the "i" is lower than "levelCount".
		// Once the "i" is equal to "levelCount" the for loop stops/breaks.
		for (int i = 0; i < levelCount; i++) {

			// Create an If statement which checks if the remainder after dividing the
			// "counter" by two is equal to 0.
			if (counter % 2 == 0) {
				// Make the Turtle change it's color to the (R: 0,G: 153,B: 0) values.
				this.setFillColor(new Color(0, 153, 0));
			}

			// Create an If statement which checks if the remainder after dividing the
			// "counter" by two is equal to 1.
			if (counter % 2 == 1) {
				// Make the Turtle change it's color to the (R: 0,G: 130,B: 0) values.
				this.setFillColor(new Color(0, 130, 0));
			}

			/*
			 * 
			 * Making the Levels one by one
			 * 
			 */

			// Make the Turtle use the previously made function called "triangle" while
			// using the "decreasedWidth" variable as input.
			this.triangle(decreasedWidth);
			// Make the Turtle lift up the pen to not make any lines.
			this.penUp();
			// Add +1 to the Counter variable.
			counter++;
			// Make the Turtle move back by the "decreasedWidth" divided by 2
			this.step(-decreasedWidth / 2);
			// Make the "decreasedWidth" smaller by 20 percent for the next run of the loop
			// by multiplying the saved "decreasedWidth" by 0.80 and overwriting the
			// previously saved value.
			decreasedWidth = decreasedWidth * 0.80;
		}

		/*
		 * 
		 * Making the Turtle move back to the initial postition
		 * 
		 */

		// Create a chain of commands which moves the Turtle back to it's starting
		// position
		// Make the Turtle lift up the pen to not make any lines.
		this.penUp();
		// Make the Turtle move to the saved "startPosX" & "startPosY"
		this.moveTo(startPosX, startPosY);
		// Make the Turtle rotate to the saved "startDirection"
		this.setDirection(startDirection);
		// Make the Turtle put the pen down so it can draw again
		this.penDown();
	}
}

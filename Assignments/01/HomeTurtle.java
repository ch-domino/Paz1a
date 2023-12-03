package sk.paz1a.dominik.chrobak.a1;

import java.awt.Color;

import sk.upjs.jpaz2.*;

public class HomeTurtle extends Turtle {

	public void triangle(double size) {
		this.turn(30);
		for (int i = 0; i < 3; i++) {
			this.step(size);
			this.turn(120);
		}
		this.turn(-30);
	}

	public void arrows(double size) {
		double halfSize = size / 2;

		for (int i = 0; i < 4; i++) {
			this.penUp();
			this.step(size);
			this.turn(-90);
			this.step(halfSize);
			this.turn(90);
			this.penDown();
			this.triangle(size);
			this.penUp();
			this.turn(90);
			this.step(halfSize);
			this.turn(90);
			this.step(size);
			this.turn(180);
			this.penDown();
			this.turn(90);
		}
	}

	public void cog(double size) {
		this.penUp();
		this.openPolygon();
		this.turn(-90);
		this.step(size);
		this.turn(120);

		for (int i = 0; i < 3; i++) {
			this.step(size);
			this.turn(60);
		}
		this.turn(60);
		this.step(size);
		this.turn(90);
		this.closePolygon();
		this.penDown();
	}

	public void tripleCog(double size) {
		this.setFillColor(Color.RED);

		this.cog(size);

		for (int i = 0; i < 2; i++) {
			this.penUp();
			this.turn(150);
			this.step(size);
			this.turn(-30);
			this.penDown();
			this.cog(size);
		}
		this.penUp();
		this.turn(150);
		this.step(size);
		this.turn(-30);
		this.penDown();
	}

	public void hexagon(double size) {
		this.penUp();
		this.step(size);
		this.penDown();
		this.turn(120);
		for (int i = 0; i < 6; i++) {
			this.step(size);
			this.turn(360 / 6);
		}
		this.turn(-120);
		this.penUp();
		this.step(-size);
		this.penDown();
	}

	public void beehive(double size) {
		this.setPenColor(Color.GREEN);
		double doubleSize = size * 2;

		this.hexagon(size);

		for (int i = 0; i < 6; i++) {
			this.penUp();
			this.turn(60);
			this.step(doubleSize);
			this.turn(-60);
			this.step(-size);
			this.penDown();
			this.hexagon(size);
			this.penUp();
			this.step(size);
			this.turn(60);
			this.step(-doubleSize);
			this.penDown();
		}

	}
}

package sk.upjs.midterm;

import sk.upjs.jpaz2.*;

import java.awt.*;

public class MidtermPane extends WinPane {

    private Turtle[] turtles;

    public MidtermPane() {
        this.turtles = new Turtle[12];
        for (int i = 0; i < turtles.length; i++) {
            double rx = Math.random() * this.getWidth();
            double ry = Math.random() * this.getHeight();
            int rd = (int) (Math.random() * 2);
            if (rd == 1) {
                rd = 90;
            } else {
                rd = 270;
            }

            this.turtles[i] = new Turtle();
            this.add(this.turtles[i]);
            this.turtles[i].setPosition(rx, ry);
            this.turtles[i].setDirection(rd);
        }
    }

    public int bestSeeker() {

        int count = 0;
        double leftMost = Double.MAX_VALUE;
        int leftMostIdx = 0;

        for (int i = 0; i < turtles.length; i++) {
            if (this.turtles[i].getX() < leftMost && this.turtles[i].getDirection() == 270) leftMostIdx = i;
        }

        for (int i = 0; i < turtles.length; i++) {
            if (i == leftMostIdx) {
                this.turtles[i].setPenColor(Color.BLACK);
                count--;
                continue;
            }
            if (this.turtles[i].getDirection() == 270) {
                this.turtles[i].setPenColor(Color.RED);
                count++;
                continue;
            }

            this.turtles[i].setPenColor(Color.BLACK);

        }

        return count;
    }

}

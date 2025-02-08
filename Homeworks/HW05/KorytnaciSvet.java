package sk.upjs.paz;

import sk.upjs.jpaz2.Turtle;
import sk.upjs.jpaz2.WinPane;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.Arrays;

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

    // Shoots at the centroid by moving each turtle to the center and back
    public void vystrelNaTazisko() {
        // Initialize sums for x and y coordinates of all turtles
        double sumX = 0;
        double sumY = 0;

        // Sum up all x and y coordinates of the turtles
        for (int i = 0; i < this.korytnacky.length; i++) {
            sumX += this.korytnacky[i].getX();
            sumY += this.korytnacky[i].getY();
        }

        // Calculate centroid coordinates
        double centroidX = sumX / this.korytnacky.length;
        double centroidY = sumY / this.korytnacky.length;

        // Move each turtle to the centroid and then back to its original position
        for (int j = 0; j < this.korytnacky.length; j++) {
            double turtleX = this.korytnacky[j].getX();
            double turtleY = this.korytnacky[j].getY();

            this.korytnacky[j].moveTo(centroidX, centroidY);

            this.korytnacky[j].setPosition(turtleX, turtleY);
        }
    }

    // Applies an explosion effect, moving turtles away from a specified point with a given force
    public double explozia(double x, double y, double sila) {
        double biggest = 0;

        // Iterate through each turtle to calculate and apply movement due to explosion
        for (int i = 0; i < korytnacky.length; i++) {
            double distance = this.korytnacky[i].distanceTo(x, y);

            // Calculate movement factor based on inverse fourth power of distance
            double movementFactor = (sila * sila) / (distance * distance * distance * distance);

            this.korytnacky[i].penUp();
            this.korytnacky[i].setDirectionTowards(x, y);
            this.korytnacky[i].step(-movementFactor);
            this.korytnacky[i].turn(180);
            this.korytnacky[i].penDown();

            // Track the largest movement factor for return
            if (movementFactor > biggest) {
                biggest = movementFactor;
            }
        }
        return biggest;
    }

    // Calculates the fastest time for any turtle to reach a specified point
    public double casDoPrichodu(double x, double y) {
        double rotation = 0;
        double distance = 0;
        double fastestTime = Double.MAX_VALUE;

        // Iterate through each turtle to determine the time to reach the target point
        for (int i = 0; i < korytnacky.length; i++) {
            double initialDir = this.korytnacky[i].getDirection();

            this.korytnacky[i].setDirectionTowards(x, y);

            double newDir = this.korytnacky[i].getDirection();
            this.korytnacky[i].setDirection(initialDir);

            distance = this.korytnacky[i].distanceTo(x, y);

            // Calculate rotation needed to face the target
            rotation = Math.abs(newDir - initialDir);
            if (rotation > 180) {
                rotation = 360 - rotation;
            }

            double time = rotation + distance;

            // Update the fastest time if this turtle is quicker
            if (fastestTime > time) {
                fastestTime = time;
            }
        }
        return fastestTime;
    }

    // Creates a histogram of turtle distances from a point, based on zones of distance d
    public int[] histogram(double x, double y, double d) {
        double maxDistance = 0;

        // Find the maximum distance from the point to any turtle
        for (Turtle turtle : korytnacky) {
            double distance = Math.sqrt(Math.pow(turtle.getX() - x, 2) + Math.pow(turtle.getY() - y, 2));
            if (distance > maxDistance) {
                maxDistance = distance;
            }
        }

        // Calculate the number of zones based on maxDistance and d
        int numZones = (int) -Math.floor(-(maxDistance / d));
        int[] histogram = new int[numZones];

        // Count turtles in each distance zone
        for (Turtle turtle : korytnacky) {
            double distance = Math.sqrt(Math.pow(turtle.getX() - x, 2) + Math.pow(turtle.getY() - y, 2));
            int zone = (int) (distance / d);
            histogram[zone]++;
        }

        return histogram;
    }

    // Testing method for the histogram function, prints the histogram to console
    public void testHistogram(double x, double y, double d) {
        int[] p = this.histogram(x, y, d);
        System.out.print("histogram(" + x + ", " + y + ", " + d + "): ");
        System.out.println(Arrays.toString(p));
    }

    // Arranges turtles in a square formation if their count is divisible by 4
    public void doStvorca(double dlzkaStrany) {
        if (this.korytnacky.length % 4 != 0) {
            System.out.println("Number of turtles needs to be divisible by 4");
            return;
        }

        int countOfTurtles = this.korytnacky.length / 4;
        double xCoordinateOfTheCenter = (double) this.getWidth() / 2;
        double yCoordinateOfTheCenter = (double) this.getHeight() / 2;
        double separatedParts = dlzkaStrany / (countOfTurtles + 1);

        // Arrange turtles on each side of square
        for (int i = 0; i < countOfTurtles; i++) {
            this.korytnacky[i].penUp();

            this.korytnacky[i].setPosition(xCoordinateOfTheCenter + (separatedParts * (i + 1)) - dlzkaStrany / 2,
                    yCoordinateOfTheCenter - dlzkaStrany / 2);
            this.korytnacky[i].penDown();
        }

        for (int k = 2 * countOfTurtles; k < 3 * countOfTurtles; k++) {
            this.korytnacky[k].penUp();

            this.korytnacky[k].setPosition(xCoordinateOfTheCenter - (dlzkaStrany / 2),
                    yCoordinateOfTheCenter + (separatedParts * ((k - countOfTurtles * 2) + 1)) - (dlzkaStrany / 2));
            this.korytnacky[k].penDown();
        }

        for (int j = countOfTurtles; j < 2 * countOfTurtles; j++) {
            this.korytnacky[j].penUp();

            this.korytnacky[j].setPosition(
                    xCoordinateOfTheCenter + (separatedParts * ((j - countOfTurtles) + 1)) - dlzkaStrany / 2,
                    yCoordinateOfTheCenter + dlzkaStrany / 2);
            this.korytnacky[j].penDown();
        }

        for (int l = 3 * countOfTurtles; l < 4 * countOfTurtles; l++) {
            this.korytnacky[l].penUp();

            this.korytnacky[l].setPosition(xCoordinateOfTheCenter + (dlzkaStrany / 2),
                    yCoordinateOfTheCenter + (separatedParts * ((l - countOfTurtles * 3) + 1)) - (dlzkaStrany / 2));
            this.korytnacky[l].penDown();
        }

    }

    // Conducts a shooting game between turtles, where each targets the closest unhit turtle
    public void prestrelka(int idxPrvehoStrelca, Color farbaStriel) {
        boolean[] hitStatus = new boolean[korytnacky.length];
        int selectedTurtle = idxPrvehoStrelca;

        // Loop until there are no more unhit turtles to target
        while (true) {
            int nearestNonHitTurtle = -1;
            double shortestDistance = Double.MAX_VALUE;

            // Find the nearest unhit turtle to target
            for (int i = 0; i < korytnacky.length; i++) {
                if (i == selectedTurtle || hitStatus[i]) {
                    continue;
                }

                double distance = this.korytnacky[selectedTurtle].distanceTo(this.korytnacky[i].getX(),
                        this.korytnacky[i].getY());

                if (distance < shortestDistance) {
                    shortestDistance = distance;
                    nearestNonHitTurtle = i;
                }
            }

            // If a target is found, shoot at it, otherwise end the loop
            if (nearestNonHitTurtle != -1) {
                double currentPositionX = this.korytnacky[selectedTurtle].getX();
                double currentPositionY = this.korytnacky[selectedTurtle].getY();
                Color currentPenColor = this.korytnacky[selectedTurtle].getPenColor();

                this.korytnacky[selectedTurtle].setPenColor(farbaStriel);

                this.korytnacky[selectedTurtle].turnTowards(this.korytnacky[nearestNonHitTurtle].getX(),
                        this.korytnacky[nearestNonHitTurtle].getY());

                this.korytnacky[selectedTurtle].moveTo(this.korytnacky[nearestNonHitTurtle].getX(),
                        this.korytnacky[nearestNonHitTurtle].getY());

                this.korytnacky[selectedTurtle].setPosition(currentPositionX, currentPositionY);

                this.korytnacky[selectedTurtle].setPenColor(currentPenColor);

                hitStatus[nearestNonHitTurtle] = true;
            } else {
                break;
            }
            selectedTurtle = nearestNonHitTurtle;
        }
    }

    protected void onMouseClicked(int x, int y, MouseEvent detail) {
        if (!(detail.isAltDown() || detail.isControlDown() || detail.isShiftDown())) {
            this.pridajKorytnacku(x, y);
        }
    }
}
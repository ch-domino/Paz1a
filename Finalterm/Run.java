package sk.upjs.paz;

import java.util.Scanner;

public class Run {

    private int day;
    private double length;
    private boolean lonelySkier;
    private String skiType;
    private int numberOfRuns;
    private String slopeName;

    public Run(int day, double length, boolean lonelySkier, String skiType, int numberOfRuns) {
        this.day = day;
        this.length = length;
        this.lonelySkier = lonelySkier;
        this.skiType = skiType;
        this.numberOfRuns = numberOfRuns;
    }

    public Run(int day, double length, boolean lonelySkier, String skiType, int numberOfRuns, String slopeName) {
        this.day = day;
        this.length = length;
        this.lonelySkier = lonelySkier;
        this.skiType = skiType;
        this.numberOfRuns = numberOfRuns;
        this.slopeName = slopeName;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public boolean isLonelySkier() {
        return lonelySkier;
    }

    public void setLonelySkier(boolean lonelySkier) {
        this.lonelySkier = lonelySkier;
    }

    public String getSkiType() {
        return skiType;
    }

    public void setSkiType(String skiType) {
        this.skiType = skiType;
    }

    public int getNumberOfRuns() {
        return numberOfRuns;
    }

    public void setNumberOfRuns(int numberOfRuns) {
        this.numberOfRuns = numberOfRuns;
    }

    public String getSlopeName() {
        return slopeName;
    }

    public void setSlopeName(String slopeName) {
        this.slopeName = slopeName;
    }

    public static Run fromString(String input) {
        Scanner sc = new Scanner(input);
        sc.useDelimiter("\t");

        int day = Integer.parseInt(sc.next());
        double length = Double.parseDouble(sc.next());
        boolean lonelySkier = Boolean.parseBoolean(sc.next());
        String skiType = sc.next();
        int numberOfRuns = Integer.parseInt(sc.next());

        if (sc.hasNext()) return new Run(day, length, lonelySkier, skiType, numberOfRuns, sc.next());

        return new Run(day, length, lonelySkier, skiType, numberOfRuns);

    }

    @Override
    public String toString() {
        if (slopeName == null) return day + "\t" + length + "\t" + lonelySkier + "\t" + skiType + "\t" + numberOfRuns;
        return day + "\t" + length + "\t" + lonelySkier + "\t" + skiType + "\t" + numberOfRuns + "\t" + slopeName;

    }
}

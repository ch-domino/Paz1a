package sk.upjs.paz;

import sk.upjs.jpaz2.*;

import java.io.File;
import java.nio.file.Files;
import java.util.logging.FileHandler;

public class Launcher {

    public static void main(String[] args) {
        Diary d = new Diary();

        Run run1 = new Run(12, 13.567, true, "freeride", 2);
        Run run2 = new Run(13, 5.0, false, "downhill", 2, "Jurgow");
        Run run3 = new Run(14, 1405.025, false, "downhill", 1, "Jurgow");
        Run run4 = new Run(15, 13.5, true, "freeride", 6);
        d.addRun(run1);
        d.addRun(run2);
        d.addRun(run3);
        d.addRun(run4);

        Run run5 = Run.fromString("6\t10.5\ttrue\tsnowboard\t6\tAlps");
        d.addRun(run5);

        Run run6 = Run.fromString("6\t25.5\ttrue\tfreeride\t6");
        d.addRun(run6);

        Run run7 = Run.fromString("6\t25.5\ttrue\tdownhill\t6\tAntarctica");
        d.addRun(run7);

        Run run8 = Run.fromString("6\t25.5\ttrue\tsnowboard\t6\tAbced");
        d.addRun(run8);

        d.saveRuns("runs.txt");

        Diary a = Diary.loadRuns("runs.txt");
        a.saveRuns("runs1.txt");

//        System.out.println("To String:\n[" + a.toString() + "]");

        System.out.println("Total Distance: " + a.getTotalDistance());
        System.out.println("Longest Lonely Run: " + a.longestLonelyRun());
        System.out.println("Slopes by day 6: " + a.slopesByDay(6));
        System.out.println("Skiing days: " + a.skiingDays());
        System.out.println("Favourite Slope: " + a.getFavouriteSlope());
        System.out.println("Get Runs on Slopes:\n" + a.getRunsOnSlopes());
        System.out.println("Best Day: " + a.bestDay());
        System.out.println("usedMultipleSkis on day 6: " + a.usedMultipleSkis(6));

    }
}
package sk.upjs.paz;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

public class Diary {

    private List<Run> runs;

    public Diary() {
        runs = new ArrayList<>();
    }

    public void addRun(Run run) {
        runs.add(run);
    }

    public void saveRuns(String filename) {
        try (PrintWriter pw = new PrintWriter(new File(filename))) {
            for (Run run : runs) {
                pw.write(run.toString() + "\n");
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found\n" + e);
        }
    }

    public static Diary loadRuns(String filename) {
        Diary d = new Diary();
        try (Scanner sc = new Scanner(new File(filename))) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] run = line.split("\t");
                if (run.length < 6) {
                    d.addRun(new Run(Integer.parseInt(run[0]), Double.parseDouble(run[1]), Boolean.parseBoolean(run[2]), run[3], Integer.parseInt(run[4])));
                } else {
                    d.addRun(new Run(Integer.parseInt(run[0]), Double.parseDouble(run[1]), Boolean.parseBoolean(run[2]), run[3], Integer.parseInt(run[4]), run[5]));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found\n" + e);
        }
        return d;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Run run : runs) {
            sb.append(run.toString());
            sb.append("\n");
        }
        return sb.toString();
    }

    public double getTotalDistance() {
        double total = 0.0;
        for (Run run : runs) {
            total += run.getLength() * run.getNumberOfRuns();
        }
        total = total * 100;
        String t = String.valueOf(total);
        String[] n = t.split("\\.");
        total = Double.parseDouble(n[0]) / 100;
        return total;
    }

    public String longestLonelyRun() {
        String name = "";
        double longestDist = 0.0;

        for (Run run : runs) {
            if (run.isLonelySkier() && run.getLength() >= longestDist) {
                if (run.getSlopeName() == null) {
                    name = "freeride";
                } else {
                    name = run.getSlopeName();
                }
            }
        }

        return name;

    }

    public List<String> slopesByDay(int Day) {
        List<String> slopes = new ArrayList<>();
        for (Run run : runs) {
            if (run.getDay() == Day && run.getSlopeName() != null) {
                slopes.add(run.getSlopeName());
            }
        }
        Collections.sort(slopes);
        return slopes;
    }

    public int skiingDays() {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (Run run : runs) {
            if (map.containsKey(run.getDay())) continue;
            map.put(run.getDay(), 1);
        }
        return map.size();
    }

    public String getFavouriteSlope() {
        HashMap<String, Integer> map = new HashMap<>();
        for (Run run : runs) {
            if (map.containsKey(run.getSlopeName())) {
                map.put(run.getSlopeName(), map.get(run.getSlopeName()) + run.getNumberOfRuns());
            } else {
                if (run.getSlopeName() == null) continue;
                map.put(run.getSlopeName(), run.getNumberOfRuns());
            }
        }
        int max = Collections.max(map.values());

        String name = "";
        for (String string : map.keySet()) {
            if (map.get(string).equals(max)) name = string;
        }

        return name;
    }

    public Map<String, Integer> getRunsOnSlopes() {
        Map<String, Integer> map = new HashMap<>();
        for (Run run : runs) {
            if (run.getSlopeName() != null) {
                if (map.containsKey(run.getSlopeName())) {
                    map.put(run.getSlopeName(), map.get(run.getSlopeName()) + run.getNumberOfRuns());
                } else {
                    map.put(run.getSlopeName(), run.getNumberOfRuns());
                }
            }
        }
        return map;
    }

    public int bestDay() {
        HashMap<Integer, Double> map = new HashMap<>();
        for (Run run : runs) {
            if (map.containsKey(run.getDay())) {
                map.put(run.getDay(), map.get(run.getDay()) + run.getLength() * run.getNumberOfRuns());
            } else {
                map.put(run.getDay(), run.getLength() * run.getNumberOfRuns());
            }

        }

        double max = Collections.max(map.values());
        int pos = 0;
        for (Integer i : map.keySet()) {
            if (map.get(i).equals(max)) pos = i;
        }
        return pos;
    }

    public boolean usedMultipleSkis(int day) {
        HashMap<String, Integer> map = new HashMap<>();
        for (Run run : runs) {
            if (run.getDay() != day) continue;
            if (map.containsKey(run.getSkiType())) {
                map.put(run.getSkiType(), map.get(run.getSkiType()) + 1);
            } else {
                map.put(run.getSkiType(), 1);
            }
        }
        return map.size() > 1;
    }


}

package sk.upjs.paz;

import java.util.*;

public class ZoznamOdberov {

    // List representing the collection of test records
    private List<Odber> odbery;

    // Constructor initializes an empty list of test records
    public ZoznamOdberov() {
        odbery = new ArrayList<>();
    }

    // Method to add a record to the list
    public void pridaj(Odber o) {
        if (o == null) {
            System.out.println("Error: Cannot add a null object.");
        } else {
            odbery.add(o);
        }
    }

    // Method to count the number of tests conducted at a specific location
    public int pocetOdberovNaMieste(int odberneMiesto) {
        int count = 0;
        for (Odber o : odbery) {
            if (o != null && o.getOdberneMiesto() == odberneMiesto) {
                count++;
            }
        }
        return count;
    }

    // Method do return a list of employees who tested positive at least once
    public List<String> pozitivneTestovani() {
        Set<String> pozitivneTestovani = new HashSet<>();
        for (Odber o : odbery) {
            if (o != null && o.getVysledok()) {
                pozitivneTestovani.add(o.getMenoZamestnanca());
            }
        }
        return new ArrayList<>(pozitivneTestovani);
    }

    // Method to calculate the average number of tests conducted per day
    public double priemernyPocetTestovZaDenTestovania() {
        if (odbery.isEmpty()) return 0;

        Set<String> dniTestovania = new HashSet<>();
        for (Odber o : odbery) {
            dniTestovania.add(o.getDatumOdberu());
        }
        return (double) odbery.size() / dniTestovania.size();
    }

    // Method to return a list of employees who were tested between two given dates
    public List<String> testovaniOdDo(String odDatum, String doDatum) {
        List<String> testovani = new ArrayList<>();
        try {
            int odDatumInt = convertToDateInt(odDatum);
            int doDatumInt = convertToDateInt(doDatum);

            for (Odber o : odbery) {
                int oDatumInt = convertToDateInt(o.getDatumOdberu());
                if (oDatumInt >= odDatumInt && oDatumInt <= doDatumInt) {
                    testovani.add(o.getMenoZamestnanca());
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid date format or empty input.");
        }
        return new ArrayList<>(new HashSet<>(testovani));
    }

    // Mehod to convert a date string in "DD.MM.YYY" format to an integer for comparison
    private int convertToDateInt(String date) {
        String[] parts = date.split("\\.");

        int day = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int year = Integer.parseInt(parts[2]);

        return day + month * 100 + year * 10000;
    }

    // Method to return a map of employees and the number of their test records
    public Map<String, Integer> pocetTestovOsoby() {
        Map<String, Integer> pocetTestovMap = new HashMap<>();
        for (Odber o : odbery) {
            if (o != null && o.getMenoZamestnanca() != null) {
                pocetTestovMap.put(o.getMenoZamestnanca(), pocetTestovMap.getOrDefault(o.getMenoZamestnanca(), 0) + 1);
            }
        }
        return pocetTestovMap;
    }

    // Method to return a list of employees wo were tested positive multiple times
    public List<String> opakovanePozitivny() {
        Map<String, Integer> pocetPozitivnych = new HashMap<>();
        for (Odber o : odbery) {
            if (o != null && o.getVysledok() && o.getMenoZamestnanca() != null) {
                pocetPozitivnych.put(o.getMenoZamestnanca(),
                        pocetPozitivnych.getOrDefault(o.getMenoZamestnanca(), 0) + 1);
            }
        }
        List<String> opakovanePozitivne = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : pocetPozitivnych.entrySet()) {
            if (entry.getValue() >= 2) {
                opakovanePozitivne.add(entry.getKey());
            }
        }
        return opakovanePozitivne;
    }

    // Method which checks if an employee recovered after testing positive
    public boolean prekonalNakazu(String menoZamestnanca) {
        if (menoZamestnanca == null) return false;

        List<Odber> tests = new ArrayList<>();

        for (Odber o : odbery) {
            if (o != null && menoZamestnanca.equals(o.getMenoZamestnanca())) {
                tests.add(o);
            }
        }

        tests.sort(Comparator.comparing(Odber::getDatumOdberu));

        boolean hadPositive = false;

        for (Odber test : tests) {
            if (test.getVysledok()) {
                hadPositive = true;
            } else if (hadPositive) {
                return true;
            }
        }

        return false;
    }



    // Method to return the employees which weren't tested yet
    public List<String> bezOdberu(List<String> vyberZamestnancov) {
        if (vyberZamestnancov == null) {
            return new ArrayList<>();
        }

        List<String> zamestnanciBezOdberu = new ArrayList<>(vyberZamestnancov);
        for (Odber o : odbery) {
            if (o != null && o.getMenoZamestnanca() != null) {
                zamestnanciBezOdberu.remove(o.getMenoZamestnanca());
            }
        }
        return zamestnanciBezOdberu;
    }

    // Method to return a list of employees who had both positive and negative results on the same day
    public List<String> podozrivoTestovani() {
        Map<String, Set<Boolean>> resultsPerDay = new HashMap<>();

        // Group results by employee name and date
        for (Odber o : odbery) {
            if (o != null && o.getMenoZamestnanca() != null && o.getDatumOdberu() != null) {
                String key = o.getMenoZamestnanca() + " " + o.getDatumOdberu();
                resultsPerDay.computeIfAbsent(key, k -> new HashSet<>()).add(o.getVysledok());
            }
        }

        Set<String> suspiciousEmployees = new HashSet<>();


        for (Map.Entry<String, Set<Boolean>> entry : resultsPerDay.entrySet()) {
            if (entry.getValue().size() > 1) {
                String fullName = entry.getKey().substring(0, entry.getKey().lastIndexOf(" "));
                suspiciousEmployees.add(fullName);
            }
        }

        return new ArrayList<>(suspiciousEmployees);
    }

    // Method to return the busiest testing location
    public int najvytazenejsiePracovisko() {
        Map<Integer, Integer> pocetOdberovNaMieste = new HashMap<>();
        int maxCount = 0;
        int najvytazenejsiePracovisko = 0;
        for (Odber o : odbery) {
            if (o != null) {
                int miesto = o.getOdberneMiesto();
                pocetOdberovNaMieste.put(miesto, pocetOdberovNaMieste.getOrDefault(miesto, 0) + 1);
                if (pocetOdberovNaMieste.get(miesto) > maxCount) {
                    maxCount = pocetOdberovNaMieste.get(miesto);
                    najvytazenejsiePracovisko = miesto;
                }
            }
        }
        return najvytazenejsiePracovisko;
    }
}

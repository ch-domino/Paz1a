package paz1a.dominik.chrobak.a8;

import java.util.*;

public class ZoznamOdberov {
	/**
	 * Please code review I tried coding in this format,
	 * using @param & @return as well
	 * 
	 * I didn't manage to figure out what I did wrong for
	 * prekonalNakazu & podozrivoTestovani
	 */

	private List<Odber> odbery;

	// Constructor to initialize the list of collections
	public ZoznamOdberov() {
		odbery = new ArrayList<>();
	}

	// Method to add a collection to the list
	public void pridaj(Odber o) {
		if (o != null) {
			odbery.add(o);
		} else {
			System.out.println("Error: Cannot add a null object.");
		}
	}

	/**
	 * Counts the number of collections at a specific place.
	 *
	 * @param odberneMiesto The place to count collections.
	 * @return The count of collections at the specified place.
	 */
	public int pocetOdberovNaMieste(int odberneMiesto) {
		int count = 0;
		for (Odber o : odbery) {
			if (o != null && o.getOdberneMiesto() == odberneMiesto) {
				count++;
			}
		}
		return count;
	}

	/**
	 * Retrieves names of employees who had positive tests (without duplicates).
	 *
	 * @return A list of names of employees with positive tests.
	 */
	public List<String> pozitivneTestovani() {
		Set<String> pozitivniTestovani = new HashSet<>();
		for (Odber o : odbery) {
			if (o != null && o.getVysledok()) {
				pozitivniTestovani.add(o.getMenoZamestnanca());
			}
		}
		return new ArrayList<>(pozitivniTestovani);
	}

	/**
	 * Calculates the average number of tests per testing day.
	 *
	 * @return The average number of tests per testing day.
	 */
	public double priemernyPocetTestovZaDenTestovania() {
		if (odbery.isEmpty()) {
			return 0;
		}

		Set<String> dniTestovania = new HashSet<>();
		for (Odber o : odbery) {
			dniTestovania.add(o.getDatumOdberu());
		}
		return (double) odbery.size() / dniTestovania.size();
	}

	/**
	 * Retrieves names of tested employees within a date range.
	 *
	 * @param odDatum Starting date of the range.
	 * @param doDatum Ending date of the range.
	 * @return A list of names of employees tested within the specified date range.
	 */
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

	/**
	 * Converts a date string in the format "DD.MM.YYYY" to an integer
	 * representation in "DDMMYYYY" format.
	 *
	 * @param date The date string in "DD.MM.YYYY" format to be converted.
	 * @return An integer representation of the date in "DDMMYYYY" format.
	 */
	private int convertToDateInt(String date) {
		// Split the date string into day, month, and year parts based on the dot
		// separator
		String[] parts = date.split("\\.");

		// Extract day, month, and year as integers from the string array parts
		int day = Integer.parseInt(parts[0]);
		int month = Integer.parseInt(parts[1]);
		int year = Integer.parseInt(parts[2]);

		// Calculate the date integer in "DDMMYYYY" format and return it
		return day + month * 100 + year * 10000;
	}

	/**
	 * Counts the number of tests for each employee.
	 *
	 * @return A map containing the count of tests for each employee.
	 */
	public Map<String, Integer> pocetTestovOsoby() {
		Map<String, Integer> pocetTestovMap = new HashMap<>();
		for (Odber o : odbery) {
			if (o != null && o.getMenoZamestnanca() != null) {
				pocetTestovMap.put(o.getMenoZamestnanca(), pocetTestovMap.getOrDefault(o.getMenoZamestnanca(), 0) + 1);
			}
		}
		return pocetTestovMap;
	}

	/**
	 * Retrieves names of employees who had positive tests at least twice.
	 *
	 * @return A list of names of employees with positive tests at least twice.
	 */
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

	public boolean prekonalNakazu(String menoZamestnanca) {
		if (menoZamestnanca == null) {
			return false;
		}

		Map<String, List<Odber>> employeeTests = new HashMap<>();

		for (Odber o : odbery) {
			if (o != null && o.getMenoZamestnanca() != null) {
				employeeTests.computeIfAbsent(o.getMenoZamestnanca(), k -> new ArrayList<>()).add(o);
			}
		}

		List<Odber> tests = employeeTests.get(menoZamestnanca);

		if (tests != null && tests.size() >= 2) {
			// Sort tests chronologically by date
			Collections.sort(tests, Comparator.comparing(Odber::getDatumOdberu));

			for (int i = 0; i < tests.size() - 1; i++) {
				Odber current = tests.get(i);
				Odber next = tests.get(i + 1);

				int currentDate = convertToDateInt(current.getDatumOdberu());
				int nextDate = convertToDateInt(next.getDatumOdberu());

				if (currentDate == nextDate) {
					if (current.getVysledok() && next.getVysledok()) {
						return false; // Positive and negative on the same day, return false
					} else if (!current.getVysledok() && next.getVysledok()) {
						return true; // Only negative test on the same day, return true
					} else if (current.getVysledok() && !next.getVysledok()) {
						return false; // Only positive test on the same day, return false
					}
				} else {
					int currentTrueCount = 0;
					int currentFalseCount = 0;

					for (int j = 0; j <= i; j++) {
						Odber entry = tests.get(j);
						int entryDate = convertToDateInt(entry.getDatumOdberu());
						if (entryDate == currentDate) {
							if (entry.getVysledok()) {
								currentTrueCount++;
							} else {
								currentFalseCount++;
							}
						}
					}

					if (currentTrueCount == 1 && currentFalseCount == 1) {
						// Check the previous day
						int previousDate = currentDate - 1;
						int previousTrueCount = 0;
						int previousFalseCount = 0;

						for (Odber entry : tests) {
							int entryDate = convertToDateInt(entry.getDatumOdberu());
							if (entryDate == previousDate) {
								if (entry.getVysledok()) {
									previousTrueCount++;
								} else {
									previousFalseCount++;
								}
							}
						}

						if (previousTrueCount > previousFalseCount) {
							return true;
						}
					}
				}
			}
		}

		return false; // No relevant test sequence found
	}

	// Method to retrieve names of employees who haven't had any collections
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

	/**
	 * Retrieves names of employees who haven't had any collections.
	 *
	 * @param vyberZamestnancov List of selected employees.
	 * @return A list of names of employees who haven't had any collections.
	 */
	public List<String> podozrivoTestovani() {
		Map<String, Map<String, Integer>> employeeEntriesPerDay = new HashMap<>();

		// Count entries per employee per day
		for (Odber o : odbery) {
			if (o != null && o.getMenoZamestnanca() != null && o.getDatumOdberu() != null) {
				String employeeDateKey = o.getMenoZamestnanca() + " " + o.getDatumOdberu();
				employeeEntriesPerDay.putIfAbsent(employeeDateKey, new HashMap<>());

				Map<String, Integer> entriesForDay = employeeEntriesPerDay.get(employeeDateKey);
				entriesForDay.put(employeeDateKey, entriesForDay.getOrDefault(employeeDateKey, 0) + 1);
			}
		}

		Set<String> podozrivi = new HashSet<>();

		// Check for employees with multiple entries on the same day having both true
		// and false
		for (Map.Entry<String, Map<String, Integer>> employee : employeeEntriesPerDay.entrySet()) {
			boolean containsFalse = false;
			boolean containsTrue = false;

			for (Map.Entry<String, Integer> entry : employee.getValue().entrySet()) {
				if (entry.getValue() > 1) {
					String[] parts = entry.getKey().split(" ");
					String employeeName = parts[0];

					for (Odber o : odbery) {
						if (o != null && o.getMenoZamestnanca() != null && o.getDatumOdberu() != null
								&& o.getMenoZamestnanca().equals(employeeName) && o.getDatumOdberu().equals(parts[1])) {
							if (o.getVysledok()) {
								containsTrue = true;
							} else {
								containsFalse = true;
							}
						}
					}

					if (containsTrue && containsFalse) {
						podozrivi.add(employeeName);
						break;
					}
				}
			}
		}

		return new ArrayList<>(podozrivi);
	}

	/**
	 * Retrieves names of employees who had positive and negative tests on the same
	 * day.
	 *
	 * @return A list of names of employees who had positive and negative tests on
	 *         the same day.
	 */
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

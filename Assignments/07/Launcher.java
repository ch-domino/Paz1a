package paz1a.dominik.chrobak.a7;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Launcher {

	public static void main(String[] args) {

		int[] numbers = new int[] { 4, 6, 2, 1, 3, 8 };
		// Vypise: [4, 6, 2, 1, 3, 8]
		System.out.println(Arrays.toString(numbers));

		NumberList z1 = new NumberList(numbers);
		z1.set(1, 8);
		// Vypise: [4, 8, 2, 1, 3, 8]
		System.out.println(z1.toString());

		// Vypise: [4, 6, 2, 1, 3, 8], t.j. pole sa nezmenilo
		System.out.println(Arrays.toString(numbers));

		NumberList z2 = new NumberList(z1);
		// Vypise: [4, 8, 2, 1, 3, 8]
		System.out.println(z1.toString());
		// Vypise: [4, 8, 2, 1, 3, 8]
		System.out.println(z2.toString());

		z2.set(1, 10);

		// Vypise: [4, 8, 2, 1, 3, 8]
		System.out.println(z1.toString());
		// Vypise: [4, 10, 2, 1, 3, 8]
		System.out.println(z2.toString());

		FrequencyTable ft = new FrequencyTable();

		Scanner sc = null;
		try {
			sc = new Scanner(new File("C:\\Users\\satis\\Desktop\\word.txt"));
			while (sc.hasNext())
				ft.addOccurrence(sc.next());
		} catch (FileNotFoundException e) {
			System.err.println("Subor neexistuje.");
		} finally {
			if (sc != null)
				sc.close();
		}

		System.out.println("Pocet roznych precitanych slov: " + ft.getWordCount());

		// Vypis po slovach
		String[] words = ft.getWords();
		System.out.println("Vyskyty slov: ");
		for (int i = 0; i < words.length; i++)
			System.out.println(words[i] + ": " + ft.getNumberOfOccurrences(words[i]));

		// test "hacknutia" objektu
		for (int i = 0; i < words.length; i++) {
			words[i] = "???";
		}

		// Vypis cez toString
		System.out.println("Vyskyty slov inak: " + ft.toString());
	}

}

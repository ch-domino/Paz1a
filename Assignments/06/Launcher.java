package paz1a.dominik.chrobak.a6;

import java.io.File;

public class Launcher {

	public static void main(String[] args) {

		HomeTurtle HT = new HomeTurtle();

		System.out.println(HT.najproduktivnejsiHrac("C:\\Users\\satis\\Desktop\\test.txt"));
		System.out.println(HT.pocetNulVSucine("C:\\Users\\satis\\Desktop\\cisla(1).txt"));
		
		int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
		File example = new File("C:\\Users\\satis\\Desktop\\spiral.txt");
        
		HT.spirala(example,numbers, 5);
	}
}

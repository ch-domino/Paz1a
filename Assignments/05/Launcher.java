package sk.paz1a.practicals.a5;

import sk.upjs.jpaz2.*;

public class Launcher {

	public static void main(String[] args) {

		KorytnaciSvet world = new KorytnaciSvet();

		ObjectInspector oi = new ObjectInspector();
		
		oi.inspect(world);
	}
}

package sk.paz1a.dominik.chrobak.a4;

import sk.upjs.jpaz2.*;

public class Launcher {

	public static void main(String[] args) {
		
		HomeTurtle ht = new HomeTurtle();

		// create new object inspector
		ObjectInspector oi = new ObjectInspector();
		oi.inspect(ht);

//		System.out.println(ht.toEmailAddress("Janko Hrasko"));
//		System.out.println(ht.toEmailAddress("Peter Chemik"));
//		System.out.println(ht.toEmailAddress("ALBERT MATEMATIK"));
//		
//		System.out.println(ht.countAcronyms("Čo vieš o TCP a UDP?"));
//		System.out.println(ht.countAcronyms("Neskôr boli CORBA a RMI vytlačené SOAPom."));
//		System.out.println(ht.countAcronyms("self-verifying-DFA"));
//		
//		System.out.println(ht.sanitize("Jaaavvaaa"));
//		System.out.println(ht.sanitize("IIIssiel"));
//		System.out.println(ht.sanitize("IIissiel"));
//		System.out.println(ht.sanitize("Odddstran duuuupllicitu"));
//		
//		System.out.println(ht.replaceNumbers("a5h","gci"));
//		System.out.println(ht.replaceNumbers("246","abc"));
//		System.out.println(ht.replaceNumbers("ahoj svet","qwertyuio"));
//		System.out.println(ht.replaceNumbers("1mn4op78","ABCDEFGH"));
		
		ClickPane d = new ClickPane();
        d.kresliKotviaceBody();

	}
}

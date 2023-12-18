package paz1a.dominik.chrobak.a8;

public class Launcher {

	public static void main(String[] args) {
        // Creating an instance of ZoznamOdberov
        ZoznamOdberov zoznamOdberov = new ZoznamOdberov();

        // Adding some sample data (Odber instances)
        zoznamOdberov.pridaj(new Odber("Emil Kovacik", "5.12.2020",1, false));
        zoznamOdberov.pridaj(new Odber("Mikulas Toth", "18.11.2020",2, false));
        zoznamOdberov.pridaj(new Odber("Emil Kovacik", "3.11.2020",1, true));
        zoznamOdberov.pridaj(new Odber("Mikulas Toth", "9.11.2020",2, true));
        zoznamOdberov.pridaj(new Odber("Oto Kovac", "30.12.2020",1, false));
        zoznamOdberov.pridaj(new Odber("Oto Kovac", "9.11.2020",1, false));
        zoznamOdberov.pridaj(new Odber("Mikulas Toth", "18.11.2020",2, true));
        // ... add more sample data as needed

        // Testing various methods and printing the results
        System.out.println("Number of collections at place 1: " + zoznamOdberov.pocetOdberovNaMieste(1));

        System.out.println("Names of positively tested employees: " + zoznamOdberov.pozitivneTestovani());

        System.out.println("Average number of tests per testing day: " + zoznamOdberov.priemernyPocetTestovZaDenTestovania());

        System.out.println("Employees tested between 15.11.2020 and 4.12.2020: " +
                zoznamOdberov.testovaniOdDo("15.11.2020", "4.12.2020"));

        System.out.println("Number of tests per employee: " + zoznamOdberov.pocetTestovOsoby());

        System.out.println("Employees tested positive at least twice: " + zoznamOdberov.opakovanePozitivny());

        System.out.println("Did Raduz Gajdos overcome the infection? " + zoznamOdberov.prekonalNakazu("Raduz Gajdos"));

        // ... test other methods

        System.out.println("Most busy collection place: " + zoznamOdberov.najvytazenejsiePracovisko());
    }
}

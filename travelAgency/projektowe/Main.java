package zadanie.projektowe;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;

public class Main {

	public static void main(String[] args) throws Exception {

		// wczytanie ekstensji
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("BazaDanych"))) {
			Ekstensja.load(ois);
		} catch (Exception e) {
			System.out.println("Brak ekstensji");
		}
//
//		for (KlientZarejestrowany k : Ekstensja.getEkstensja(KlientZarejestrowany.class)) {
//			System.out.println(k);
//		}

	
		KlientZarejestrowany kz1 = new KlientZarejestrowany("Jan", "Kowalski", "454432256");
		KlientZarejestrowany kz2 = new KlientZarejestrowany("Andrzej", "Dwroski", "687854");
		KlientZarejestrowany kz3 = new KlientZarejestrowany("Wieslaw", "Duda", "45352456");
		
		WycieczkaZagraniczna w1 = new WycieczkaZagraniczna("Afryka", Ocena.SREDNIA, new BigDecimal("500"), Motyw.ZWIEDZANIE, "Kenia",SrodekTransportu.SAMOLOT );
		WycieczkaZagraniczna w2 = new WycieczkaZagraniczna ("Piekna Grecja", Ocena.SLABA, new BigDecimal("3200"), Motyw.WYPOCZYNEK,"Grecja", SrodekTransportu.POCIAG );
		WycieczkaZagraniczna w3 = new WycieczkaZagraniczna ("Piekna Malezja", Ocena.SREDNIA, new BigDecimal("8000"), Motyw.ZWIEDZANIE,"Malezja", SrodekTransportu.SAMOLOT);
		WycieczkaZagraniczna w4 = new WycieczkaZagraniczna ("Piekna Malezja", Ocena.SREDNIA, new BigDecimal("8000"), Motyw.ZWIEDZANIE,"Malezja", SrodekTransportu.SAMOLOT);
//		
		
		kz1.dodajWycieczke(w1);
		kz1.dodajWycieczke(w2);
		kz1.dodajWycieczke(w3);
		kz2.dodajWycieczke(w2);
		kz2.dodajWycieczke(w4);
//		kz3.dodajWycieczke(w1);
//		kz3.dodajWycieczke(w3);
//	
		System.out.println(KlientZarejestrowany.najdrozszyKlient(Ekstensja.getEkstensja(KlientZarejestrowany.class)));
		System.out.println(WycieczkaZagraniczna.najpopularniejszyKraj(Ekstensja.getEkstensja(WycieczkaZagraniczna.class)));
		
//
//		System.out.println(kz1.getIdKlienta());
//		System.out.println(kz2.getIdKlienta());
//		System.out.println(kz3.getIdKlienta());
//
//		Przewodnik p1 = new Przewodnik("Andrzek", "Kowalsk", "5643643", 400);
//		System.out.println(p1.wyliczPensje());
//
//		Menager m1 = new Menager("Andrzek", "Kowalsk", "5643643", 400, 2000);
//		// String nazwa, int ocena, BigDecimal cena, Motyw motyw, String kraj, String
//		// srodekTransportu
//		System.out.println(m1.wyliczPensje());
//		WycieczkaZagraniczna w1 = new WycieczkaZagraniczna("Odkryj Kenie", 4, new BigDecimal("3000"), Motyw.WYPOCZYNEK,"Kenia", "Samolot");
//

		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("BazaDanych"));

		Ekstensja.save(oos);
		oos.close();

		System.out.println("\nEkstensja wczytana z pliku: ");

		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("BazaDanych"));
			System.out.println(ois.readObject());
			ois.close();
		} catch (IOException e) {
		} catch (ClassNotFoundException e) {
		}
	}

}

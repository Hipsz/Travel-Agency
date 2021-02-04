package zadanie.projektowe;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class KlientZarejestrowany extends Klient {

	private int idKlienta;
	private static int generator = 1;

	private static List<KlientZarejestrowany> ekstensja = new ArrayList<>();
	
	List<Wycieczka> wycieczki = new ArrayList<>(); // alt shift r do zmiany wszystkich nazw


	List<KlientObsluga> klientObsluga = new ArrayList<>();

	public KlientZarejestrowany(String imie, String nazwisko, String telefon) throws Exception {
		super(imie, nazwisko, telefon);
		setIDKlienta(generator);
		generator++;

		ekstensja.add(this);
	}

	public void dodajKlinetObsluga(KlientObsluga k1) {
		if (!klientObsluga.contains(k1)) {
			klientObsluga.add(k1);
		}
	}
	
	
	// metoda, asocjacja sluzaca dod doawania wycieczki, wiele do wiele
	public void dodajWycieczke(Wycieczka wycieczka) {
			wycieczki.add(wycieczka);
			wycieczka.dodajKlienta(this); // inromacja zwrotna		
	}

	public void usunWycieczke(Wycieczka wycieczka) {
		wycieczki.remove(wycieczka);
		wycieczka.usunKlienta(this);// info zwrotne
	}
	

	public void wyswietlWszystkichKlientow(Klient klient) {
		for (Klient element : ekstensja) {
			System.out.println(element + " ");
		}
	}
	public void wyswietlWycieczki(Wycieczka wycieczka) {
		for (Wycieczka element : wycieczki) {
			System.out.print(element + " ");
		}
	}
	public BigDecimal policzKosztWycieczek() {
		BigDecimal suma = BigDecimal.ZERO;
		for (Wycieczka wycieczka : wycieczki) {
			 suma.add(wycieczka.getCena());
		}
		return suma;

	}
	
	public List<Wycieczka> getWycieczki() {
		return wycieczki;
	}
	
	
	//metoda liczaca ile wydal dany klient
	public BigDecimal ileWydal() {

		BigDecimal suma = BigDecimal.ZERO;

		for (Wycieczka w : this.getWycieczki()) {
			suma = suma.add(w.getCena());
		}
		return suma;
	}
		
	//metoda zwracajaca klienta ktory wydal najwicej
	public static KlientZarejestrowany najdrozszyKlient(List<KlientZarejestrowany> klienci) {
		KlientZarejestrowany max = klienci.get(0);
		for (KlientZarejestrowany kz : klienci) {
			if (kz.ileWydal().compareTo(max.ileWydal()) == 1) {
				kz = max;
			}

		}
		return max;
	}

	public static List<KlientZarejestrowany> getEkstensja() {
		return ekstensja;
	}

	public int getIdKlienta() {
		return idKlienta;
	}

	public void setIDKlienta(int idKlienta) throws Exception {
		for (KlientZarejestrowany element : ekstensja) {
			if (idKlienta == element.getIdKlienta()) {
				throw new Exception("Isteniej juz klient z takim ID");
			}
		}

		this.idKlienta = idKlienta;
	}

	public static int getGenerator() {
		return generator;
	}

}

package zadanie.projektowe;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public abstract class Klient extends Osoba {
	List<Klient> ekstensja = new ArrayList<>();

	public Klient(String imie, String nazwisko, String telefon) {
		super(imie, nazwisko, telefon);
		ekstensja.add(this);
	}

	// metoda statyczna jest wywolywana tylko na rzecz klasy
	public static Klient najlepszyKlient(List<Klient> lista) {
		return null;
	}

}

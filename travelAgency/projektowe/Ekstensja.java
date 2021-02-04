package zadanie.projektowe;

import java.io.*;
import java.util.*;

public class Ekstensja implements Serializable {

	// HashTable ktory przechowuje wszystkie ekstensje w postaci
	// klucz- Wartosc, gdzie kkluczem jest dana klasa a wartoscia lista obiekt�w
	// typu ObjecPlust
	private static Map<Class, List<Class>> ekstensje = new HashMap<>();

	// kazda nasza klasa w projekcie dziedziczy po OP,
	// za kazdym razem jak tworzymy obiekt to bedziemy dodawaca go do danej
	// ekstensji
	public <T> Ekstensja() {
		List<T> lista = new ArrayList<T>();
		Class klasa = this.getClass();

		// jesli hashtable zawiera nasza klase o ktorej mowimy
		if (ekstensje.containsKey(klasa)) {
			// do listy wprzypisujemy obiekty danej klasy
			lista = (List<T>) ekstensje.get(klasa);
		} else {
			lista = new ArrayList();
			// jesli nie to ja dodajemy
			ekstensje.put(klasa, (List<Class>) lista);
		}

		lista.add((T) this);
	}

	// metody do zapisywania i odczytu ekstensji

	public static void save(ObjectOutputStream stream) throws IOException {
		stream.writeObject(ekstensje);
	}

	public static void load(ObjectInputStream stream) throws IOException, ClassNotFoundException {
		ekstensje = (Map<Class, List<Class>>) stream.readObject();
	}

	// metoda zwracajaca ekstensje klasy
	public static <T> List<T> show(Class klasa) throws Exception {

		ArrayList<T> ekstensja = null;

		// jesli hashtable zawiera klase to zwracamy jej ekstensje
		if (ekstensje.containsKey(klasa)) {
			ekstensja = (ArrayList<T>) ekstensje.get(klasa);
		} else {
			throw new Exception("Brak klasy " + klasa);
		}

		return ekstensja;
	}

	// sparametryzowana (typ generyczny) metoda zwracajaca do listy wszystkie
	// obiekty danej klasy
	public static <T> List<T> getEkstensja(Class<T> klasa) {
		return (List<T>) ekstensje.get(klasa);
	}

}
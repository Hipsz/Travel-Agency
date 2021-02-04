package zadanie.projektowe;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Przewodnik extends Pracownik {

	private List<String> jezyki = new ArrayList<>();
	private List <Wycieczka> wycieczki = new ArrayList<>();

	public Przewodnik(String imie, String nazwisko,  String telefon ) {
		super(imie, nazwisko,  telefon );
	}
	
	public void dodajWycieczke (Wycieczka wycieczka) throws Exception {
		//czy wycieczka nie ma przypadkiem juz przypisanego przewodnika
		if(wycieczka.getPrzewodnik() != null) {
			throw new Exception("Wycieczka ma ju¿ przewodnika");			
		}
		
		if(!wycieczki.contains(wycieczka)) {
			wycieczki.add(wycieczka);
			//przypisanie wycieczki do przewodnika
			wycieczka.setPrzewodnik(this);
		}
	
	}
	
	//Wycieczka * ---- 1 Przedowdnik
	
	public void usunWycieczke(Wycieczka wycieczka) {
		wycieczki.remove(wycieczka);
		wycieczka.setPrzewodnik(this);
	}

	public List<String> getJezyki() {
		return jezyki;
	}

	

	public void dodajJezyk(String jezyk) {
		if (!jezyki.contains(jezyk)) {
			jezyki.add(jezyk);
		}
	}

}

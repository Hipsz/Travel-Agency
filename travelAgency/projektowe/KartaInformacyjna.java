package zadanie.projektowe;

import java.io.Serializable;

public class KartaInformacyjna extends Ekstensja implements Serializable{

	private String informacje, ograniczenia, uwagi;
	private Wycieczka wycieczka;

	// kompozycja z wycieczka, karta nie moze istniec jesli nie ma wycieczki
	public KartaInformacyjna(String informacje, String ograniczenia, String uwagi, Wycieczka wycieczka)
			throws Exception {

		if (wycieczka == null) {
			throw new Exception("Wycieczka nie moze byc nullem");
		}

		this.informacje = informacje;
		this.ograniczenia = ograniczenia;
		this.uwagi = uwagi;
		this.wycieczka = wycieczka;
		
		//informacja zwrotna 
		wycieczka.setKartaInformacyjna(this);
	}

	public String getInformacje() {
		return informacje;
	}

	public void setInformacje(String informacje) {
		this.informacje = informacje;
	}

	public String getOgraniczenia() {
		return ograniczenia;
	}

	public void setOgraniczenia(String ograniczenia) {
		this.ograniczenia = ograniczenia;
	}

	public String getUwagi() {
		return uwagi;
	}

	public void setUwagi(String uwagi) {
		this.uwagi = uwagi;
	}

	public Wycieczka getWycieczka() {
		return wycieczka;
	}

}

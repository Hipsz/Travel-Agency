package zadanie.projektowe;

import java.math.BigDecimal;

public class WycieczkaKrajowa extends Wycieczka{

	private String miasto;

	public WycieczkaKrajowa(String nazwa, Ocena ocena, BigDecimal cena, Motyw motyw, String miasto) {
		super(nazwa, ocena, cena, motyw);
		this.miasto = miasto;
	}

	public String getMiasto() {
		return miasto;
	}

	@Override
	public String toString() {
		return "Wycieczka Krajowa: " + getNazwa() + " Do " + miasto;
	}
	
	
}

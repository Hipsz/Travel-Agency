package zadanie.projektowe;

import java.math.BigDecimal;

public class Menager extends Pracownik {

	private double premia;

	public Menager(String imie, String nazwisko,  String telefon,
			double premia) {
		super(imie, nazwisko,  telefon);
		this.premia = premia;
	}

	public double getPremia() {
		return premia;
	}

	public void setPremia(double premia) {
		this.premia = premia;
	}

	
	
	
	
	
}

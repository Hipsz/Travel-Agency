package zadanie.projektowe;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class PracownikObslugi extends Pracownik {

	private int miejsceRankingowe;
	
	List<KlientObsluga> KlientObsluga = new ArrayList<>(); 

	public PracownikObslugi(String imie, String nazwisko,  String telefon,
			double liczbaPrzepracowanychGodzin, int miejsceRankingowe) {
		super(imie, nazwisko, telefon);
		this.miejsceRankingowe = miejsceRankingowe;
	}
	public void dodajKlientObsluga (KlientObsluga k1) {
		if(!KlientObsluga.contains(k1)) {
			KlientObsluga.add(k1);
		}
	}
	

	public int getMiejsceRankingowe() {
		return miejsceRankingowe;
	}

	public void setMiejsceRankingowe(int miejsceRankingowe) {
		this.miejsceRankingowe = miejsceRankingowe;
	}

	
	
	
	
}

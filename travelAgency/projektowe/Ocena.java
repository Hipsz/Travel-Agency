package zadanie.projektowe;

public enum Ocena {

	SLABA(1), SREDNIA(2), DOBRA(3), WSPANIALA(4);
	private int wartosc;

	private Ocena(int wartosc) {
		this.wartosc = wartosc;
	}

	public int getWartosc() {
		return wartosc;
	}
	
	
	
}

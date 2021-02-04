package zadanie.projektowe;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public abstract class Pracownik extends Osoba {

	private static List<Pracownik> ekstensja = new ArrayList<>();

	private static BigDecimal minimalnaPensja = new BigDecimal("1500");
	
	private String rodzajUmowy;

	public Pracownik(String imie, String nazwisko, String telefon) {
		super(imie, nazwisko, telefon);
		

		ekstensja.add(this);
	}

	// dziedziczenie wieloaspektowe, kosntruktor do pracowniak etatowego
	public Pracownik(String imie, String nazwisko, String email, String telefon, 
			String rodzajUmowy) {
		super(imie, nazwisko, telefon);
		
		this.rodzajUmowy = rodzajUmowy;

		ekstensja.add(this);
	}

	public static BigDecimal getMinimalnaPensja() {
		return minimalnaPensja;
	}

	public static void setMinimalnaPensja(BigDecimal minimalnaPensja) {
		Pracownik.minimalnaPensja = minimalnaPensja;
	}

	public static List<Pracownik> getEkstensja() {
		return ekstensja;
	}

	

	public String getRodzajUmowy() {
		return rodzajUmowy;
	}

	public void setRodzajUmowy(String rodzajUmowy) {
		this.rodzajUmowy = rodzajUmowy;
	}

	

//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = super.hashCode();
//		result = prime * result + ((pensja == null) ? 0 : pensja.hashCode());
//		return result;
//	}

//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (!super.equals(obj))
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		Pracownik other = (Pracownik) obj;
//		if (pensja == null) {
//			if (other.pensja != null)
//				return false;
//		} else if (!pensja.equals(other.pensja))
//			return false;
//		return true;
//	}

}

package zadanie.projektowe;

import java.io.Serializable;
import java.util.*;

public abstract class Osoba extends Ekstensja implements Serializable {

	private String imie, nazwisko, telefon;
	private List<String> emaile = new ArrayList<>();

	public Osoba(String imie, String nazwisko, String telefon) {
		this.imie = imie;
		this.nazwisko = nazwisko;
		this.telefon = telefon;
	}

	public void dodajEmail(String mail) {
		if (!emaile.contains(mail)) {
			emaile.add(mail);
		}
	}

	public void usunEmail(String mail) {
		emaile.remove(mail);
	}

	public String getImie() {
		return imie;
	}

	public void setImie(String imie) {
		this.imie = imie;
	}

	public String getNazwisko() {
		return nazwisko;
	}

	public void setNazwisko(String nazwisko) {
		this.nazwisko = nazwisko;
	}

	

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((emaile == null) ? 0 : emaile.hashCode());
		result = prime * result + ((imie == null) ? 0 : imie.hashCode());
		result = prime * result + ((nazwisko == null) ? 0 : nazwisko.hashCode());
		result = prime * result + ((telefon == null) ? 0 : telefon.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Osoba other = (Osoba) obj;
		if (emaile == null) {
			if (other.emaile != null)
				return false;
		} else if (!emaile.equals(other.emaile))
			return false;
		if (imie == null) {
			if (other.imie != null)
				return false;
		} else if (!imie.equals(other.imie))
			return false;
		if (nazwisko == null) {
			if (other.nazwisko != null)
				return false;
		} else if (!nazwisko.equals(other.nazwisko))
			return false;
		if (telefon == null) {
			if (other.telefon != null)
				return false;
		} else if (!telefon.equals(other.telefon))
			return false;
		return true;
	}

	public String toString() {
		return imie + " " + nazwisko;
	}

}

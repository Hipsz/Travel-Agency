package zadanie.projektowe;

import java.time.LocalDate;

public class KlientObsluga {

	private final LocalDate dataObslugi;
	private final KlientZarejestrowany klient;
	private final PracownikObslugi pracownik;

	public KlientObsluga(LocalDate dataObslugi, KlientZarejestrowany klient, PracownikObslugi pracownik) {

		this.dataObslugi = dataObslugi;
		this.klient = klient;
		klient.dodajKlinetObsluga(this);
		this.pracownik = pracownik;
		pracownik.dodajKlientObsluga(this);

	}

	public LocalDate getDataObslugi() {
		return dataObslugi;
	}

	public KlientZarejestrowany getKlient() {
		return klient;
	}

	public PracownikObslugi getPracownik() {
		return pracownik;
	}

}

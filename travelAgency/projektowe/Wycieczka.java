package zadanie.projektowe;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public abstract class Wycieczka extends Ekstensja implements Serializable{

	private String nazwa;
	private int id;
	private static int generator = 1;
	private BigDecimal cena;
	private Motyw motyw;
	private Ocena ocena;
	// ekstensje robimy zawsze
	private static List<Wycieczka> ekstensja = new ArrayList<>();

	private List<KlientZarejestrowany> klienci = new ArrayList<>();

	private Przewodnik przewodnik;

	private KartaInformacyjna kartaInformacyjna;

	public Wycieczka(String nazwa, Ocena ocena, BigDecimal cena, Motyw motyw) {
		setId(generator);
		generator++;
		this.ocena = ocena;
		this.cena = cena;
		this.motyw = motyw;
		this.nazwa = nazwa;
		ekstensja.add(this);

	}
	public static Wycieczka najdorzszaWycieczka (List <Wycieczka> wycieczki) {
		if(wycieczki.isEmpty() || wycieczki==null) {
			throw new IllegalArgumentException("Lista nie moze byc pusta ani byc nullem");
		}
		Wycieczka max = wycieczki.get(0);
		for (Wycieczka element : wycieczki) {
			if(element.getCena().compareTo(max.getCena())==1) {
				element = max;
			}
		}
		return max;
	}
	
	

	public void dodajKlienta(KlientZarejestrowany klient) {
		klienci.add(klient);
	}

	public void usunKlienta(KlientZarejestrowany klient) {
		klienci.remove(klient);
	}

	public KartaInformacyjna getKartaInformacyjna() {
		return kartaInformacyjna;
	}

	public void setKartaInformacyjna(KartaInformacyjna kartaInformacyjna) {
		this.kartaInformacyjna = kartaInformacyjna;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public List<KlientZarejestrowany> getKlienci() {
		return klienci;
	}

	public Ocena getOcena() {
		return ocena;
	}

	public void setOcena(Ocena ocena) {
		this.ocena = ocena;
	}

	public BigDecimal getCena() {
		return cena;
	}

	public void setCena(BigDecimal cena) {
		this.cena = cena;
	}

	public Motyw getMotyw() {
		return motyw;
	}

	public void setMotyw(Motyw motyw) {
		this.motyw = motyw;
	}

	public String getNazwa() {
		return nazwa;
	}

	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}

	public Przewodnik getPrzewodnik() {
		return przewodnik;
	}

	public void setPrzewodnik(Przewodnik przewodnik) {
		this.przewodnik = przewodnik;
	}

}

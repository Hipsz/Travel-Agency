package zadanie.projektowe;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class WycieczkaZagraniczna extends Wycieczka {

	private String kraj;
	private SrodekTransportu srodekTransportu;

	public WycieczkaZagraniczna(String nazwa, Ocena ocena, BigDecimal cena, Motyw motyw, String kraj, SrodekTransportu srodekTransportu) {
		super(nazwa, ocena, cena, motyw);
		this.kraj = kraj;
		this.srodekTransportu = srodekTransportu;
	}

	public static String najpopularniejszyKraj(List<WycieczkaZagraniczna> wycieczki) {
		if (wycieczki.isEmpty() || wycieczki == null) {
			throw new IllegalArgumentException("Lista nie moze byc pusta ani byc nullem");
		}

		Map<String, Integer> mapaKrajow = new HashMap<>();
		
		for (WycieczkaZagraniczna element : wycieczki) {
			if (!mapaKrajow.containsKey(element.getKraj())) {
				mapaKrajow.put(element.getKraj(), 1);
			}else {
				int value = mapaKrajow.get(element.getKraj());
				value ++;
				mapaKrajow.put(element.getKraj(), value);
			}
		}
		
		String max = (String) mapaKrajow.keySet().toArray()[0];
		int licznik = (int) mapaKrajow.values().toArray()[0];
		
		for (Entry <String, Integer> element : mapaKrajow.entrySet()){
			
			if(element.getValue() > licznik) {
				max = element.getKey();
				licznik = element.getValue();
			}			
		}

		return max;
	}

	public void setKraj(String kraj) {
		this.kraj = kraj;
	}

//	public String getSrodekTransportu() {
//		return srodekTransportu;
//	}

//	public void setSrodekTransportu(String srodekTransportu) {
//		this.srodekTransportu = srodekTransportu;
//	}



	@Override
	public String toString() {
		return "Wycieczka Zagraniczna: " + getNazwa()+ " Do " + kraj ;
	}

	public SrodekTransportu getSrodekTransportu() {
		return srodekTransportu;
	}

	public void setSrodekTransportu(SrodekTransportu srodekTransportu) {
		this.srodekTransportu = srodekTransportu;
	}

	public String getKraj() {
		return kraj;
	}
	
	
}

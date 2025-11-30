package taksi.firma;

import java.util.GregorianCalendar;

import taksi.TaksiVozilo;

public class TaksiFirma {
	
	private TaksiVozilo[] vozila = new TaksiVozilo[1000];
	
	
	public void unesiTaksistu(TaksiVozilo tv) {
		for (int i=vozila.length-1; i>=0;i--)
			if (vozila[i] == null) {
				vozila[i] = tv;
				vozila[i].setBrojPoziva(0);
				return;
			}
		
		System.out.println("NEMA MESTA");
	}
	
	public void primiPoziv(int taksiBroj, String ime, String prezime) {
		for (int i=0; i<vozila.length;i++)
			if (vozila[i] != null &&
				vozila[i].getId().equals(""+taksiBroj+" "+ime+" "+prezime)) {
				
				if (vozila[i].getKrajSmene().before(new GregorianCalendar())) {
					System.out.println("KRAJ SMENE");
					return;
				}
				
				vozila[i].setBrojPoziva(vozila[i].getBrojPoziva()+1);
				vozila[i].setSlobodan(false);
				return;
			}
		
		System.out.println("NE POSTOJI");	
	}
	
	public void uredi() {
		//Pomocni niz u koji ce biti uneta vozila u zeljenom redosledu		
		TaksiVozilo[] pom = new TaksiVozilo[1000];
		
		int brojacSlobodnih = 0;
		int brojacZauzetih = 0;
		
		for (int i=0; i<vozila.length; i++)
			if (vozila[i] != null)
				if (vozila[i].isSlobodan()) {
					pom[brojacSlobodnih] = vozila[i];
					brojacSlobodnih++;
				}
				else {
					pom[999-brojacZauzetih] = vozila[i];
					brojacZauzetih++;
				}
		
		//Atribut vozila dobija vrednost pomocnog niza, radi se preusmerenje 
		vozila = pom;
	}

}

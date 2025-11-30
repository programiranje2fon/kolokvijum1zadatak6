package taksi;

import java.util.GregorianCalendar;

public class TaksiVozilo {
	
	private String id;
	
	private int brojPoziva;
	
	private boolean slobodan;
	
	private GregorianCalendar krajSmene;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		if (id == null || id.length() < 10 || id.length() > 30)
			System.out.println("GRESKA");
		else
			this.id = id;
	}

	public int getBrojPoziva() {
		return brojPoziva;
	}

	public void setBrojPoziva(int brojPoziva) {
		if (brojPoziva < 0)
			System.out.println("GRESKA");
		else				
			this.brojPoziva = brojPoziva;
	}

	public boolean isSlobodan() {
		return slobodan;
	}

	public void setSlobodan(boolean slobodan) {
		this.slobodan = slobodan;
	}

	public GregorianCalendar getKrajSmene() {
		return krajSmene;
	}

	public void setKrajSmene(GregorianCalendar krajSmene) {
		if (krajSmene == null || krajSmene.before(new GregorianCalendar()))
				System.out.println("GRESKA");
			else				
				this.krajSmene = krajSmene;
	}

	@Override
	public String toString() {
		return "TaksiVozilo [id=" + id + ", brojPoziva=" + brojPoziva + ", slobodan=" + slobodan + ", krajSmene="
				+ krajSmene + "]";
	}

}

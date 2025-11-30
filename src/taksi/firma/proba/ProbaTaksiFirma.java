package taksi.firma.proba;

import java.util.GregorianCalendar;

import taksi.TaksiVozilo;
import taksi.firma.TaksiFirma;

public class ProbaTaksiFirma {

	public static void main(String[] args) {
		TaksiFirma tf = new TaksiFirma();

		TaksiVozilo tv1 = new TaksiVozilo();
		tv1.setId("122 Marko Markovic");
		tv1.setBrojPoziva(0);
		tv1.setSlobodan(true);
		
		GregorianCalendar dat = new GregorianCalendar();
		dat.set(dat.get(GregorianCalendar.YEAR), 
				dat.get(GregorianCalendar.MONTH),
				dat.get(GregorianCalendar.DAY_OF_MONTH), 
				23, 59, 0);
		tv1.setKrajSmene(dat);
		
		tf.unesiTaksistu(tv1);
		
		TaksiVozilo tv2 = new TaksiVozilo();
		tv2.setId("333 Zika Petrovic");
		tv2.setBrojPoziva(7);
		tv2.setSlobodan(false);
		
		dat = new GregorianCalendar();
		dat.set(dat.get(GregorianCalendar.YEAR), 
				dat.get(GregorianCalendar.MONTH),
				dat.get(GregorianCalendar.DAY_OF_MONTH), 
				22, 22, 0);
		tv2.setKrajSmene(dat);
		
		tf.unesiTaksistu(tv2);
	}

}

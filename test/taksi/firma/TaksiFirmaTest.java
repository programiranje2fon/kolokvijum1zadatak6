package taksi.firma;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Modifier;
import java.util.GregorianCalendar;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import taksi.TaksiVozilo;
import test.TestUtil;

public class TaksiFirmaTest {

	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;
	private final PrintStream originalErr = System.err;

	TaksiFirma instance;
	
	@Before
	public void setUp() throws Exception {
		instance = new TaksiFirma();
		System.setOut(new PrintStream(outContent));
	    System.setErr(new PrintStream(errContent));
	}

	@After
	public void tearDown() throws Exception {
		instance = null;
		System.setOut(originalOut);
	    System.setErr(originalErr);
	}

	@Test
	public void atribut_vozila() {
		assertTrue("U klasi nije definisan atribut vozila", TestUtil.doesFieldExist(TaksiFirma.class, "vozila"));
	}
	
	@Test
	public void atribut_vozila_vidljivost() {
		assertTrue("Atribut vozila nije privatan", TestUtil.hasFieldModifier(TaksiFirma.class, "vozila", Modifier.PRIVATE));
	}

	@Test (timeout = 2000)
	public void metoda_unesiTaksistu() {
		TaksiVozilo tv1 = new TaksiVozilo();
		tv1.setBrojPoziva(30);
		tv1.setSlobodan(false);
		
		instance.unesiTaksistu(tv1);
		
		TaksiVozilo tv2 = new TaksiVozilo();
		tv2.setBrojPoziva(7);
		tv2.setSlobodan(false);
		
		instance.unesiTaksistu(tv2);
		
		TaksiVozilo[] niz = (TaksiVozilo[])TestUtil.getFieldValue(instance, "vozila");
		
		assertTrue("Ako se unesu dva vozila, metoda ne unosi prvo vozilo na poslednje mesto", tv1 == niz[niz.length-1]);
		assertTrue("Ako se unesu dva vozila, metoda ne unosi drugo vozilo na pretposlednje mesto", tv2 == niz[niz.length-2]);
		
		for (int i=niz.length-3; i>=0;i--)
			assertEquals("Metoda greskom unosi nova vozila i na sva preostala prazna mesta", null, niz[i]);
	
		assertEquals("Metoda ne postavlja da je broj poziva unetog taksija nula", 0, tv1.getBrojPoziva());
		
	}
	
	@Test (timeout = 2000)
	public void metoda_unesiTaksistu_PunNiz() {
		
		for (int i=1;i<=1000;i++)
			instance.unesiTaksistu(new TaksiVozilo());
		
		TaksiVozilo tv1 = new TaksiVozilo();
		tv1.setBrojPoziva(30);
		tv1.setSlobodan(false);
		
		instance.unesiTaksistu(tv1);
		
		
		assertEquals("NE ispisuje se 'NEMA MESTA' u slucaju unosa u potpuno pun niz", "NEMA MESTA", outContent.toString().trim().toUpperCase());		

		TaksiVozilo[] niz = (TaksiVozilo[])TestUtil.getFieldValue(instance, "vozila");
		
		for (int i=0; i<niz.length;i++)
			assertFalse("Metoda greskom unosi novo vozilo iako je niz prepun", tv1==niz[i]);		
	}

	@Test (timeout = 2000)
	public void metoda_primiPoziv() {
		TaksiVozilo tv1 = new TaksiVozilo();
		tv1.setId("122 Pera Peric");
		tv1.setSlobodan(true);
		GregorianCalendar dat = new GregorianCalendar();
		dat.set(dat.get(GregorianCalendar.YEAR), 
				dat.get(GregorianCalendar.MONTH),
				dat.get(GregorianCalendar.DAY_OF_MONTH), 
				23, 59, 0);
		tv1.setKrajSmene(dat);
		
		instance.unesiTaksistu(tv1);
		
		TaksiVozilo tv2 = new TaksiVozilo();
		tv2.setId("333 Mika Zikic");
		tv2.setSlobodan(true);
		tv2.setKrajSmene(dat);
		
		instance.unesiTaksistu(tv2);
		
		instance.primiPoziv(333, "Mika", "Zikic");
		
		assertEquals("Ako su uneti taksi vozila "+tv1+" i " 
					+ tv2 + " i trazi se 333 Mika Zikic, metoda ne povecava njegov broj poziva", 1, tv2.getBrojPoziva());
		assertEquals("Ako su uneti taksi vozila "+tv1+" i " 
				+ tv2 + " i trazi se 333 Mika Zikic, metoda ne postavlja da je zauzet", false, tv2.isSlobodan());
		
	}
	
	@Test (timeout = 2000)
	public void metoda_primiPoziv_NePostoji() {
		TaksiVozilo tv1 = new TaksiVozilo();
		tv1.setId("122 Pera Peric");
		tv1.setSlobodan(true);
		GregorianCalendar dat = new GregorianCalendar();
		dat.set(dat.get(GregorianCalendar.YEAR), 
				dat.get(GregorianCalendar.MONTH),
				dat.get(GregorianCalendar.DAY_OF_MONTH), 
				23, 59, 0);
		tv1.setKrajSmene(dat);
		
		instance.unesiTaksistu(tv1);
		
		TaksiVozilo tv2 = new TaksiVozilo();
		tv2.setId("333 Mika Zikic");
		tv2.setSlobodan(true);
		tv2.setKrajSmene(dat);
		
		instance.unesiTaksistu(tv2);
		
		instance.primiPoziv(121, "Mika", "Zikic");
		
		assertEquals("NE ispisuje se 'NE POSTOJI' u slucaju unosa nepostojeceg ID-a", "NE POSTOJI", outContent.toString().trim().toUpperCase());		
				
	}
	
	@Test (timeout = 2000)
	public void metoda_primiPoziv_KrajSmene() {
		TaksiVozilo tv1 = new TaksiVozilo();
		tv1.setId("122 Pera Peric");
		tv1.setSlobodan(true);
		GregorianCalendar dat = new GregorianCalendar();
		dat.set(dat.get(GregorianCalendar.YEAR), 
				dat.get(GregorianCalendar.MONTH),
				dat.get(GregorianCalendar.DAY_OF_MONTH), 
				23, 59, 0);
		tv1.setKrajSmene(dat);
		
		instance.unesiTaksistu(tv1);
		
		TaksiVozilo tv2 = new TaksiVozilo();
		tv2.setId("333 Mika Zikic");
		tv2.setSlobodan(true);
		tv2.setKrajSmene(dat);
		dat.set(GregorianCalendar.YEAR, 2017);
		
		instance.unesiTaksistu(tv2);
		
		instance.primiPoziv(333, "Mika", "Zikic");
		
		assertEquals("NE ispisuje se 'KRAJ_SMENE' u slucaju poziva za taksistu kojem je vec gotova smena", "KRAJ SMENE", outContent.toString().trim().toUpperCase());		
				
	}

	@Test (timeout = 2000)
	public void metoda_uredi() {
		TaksiVozilo tv1 = new TaksiVozilo();
		tv1.setId("122 Pera Peric");
		tv1.setSlobodan(true);
		
		instance.unesiTaksistu(tv1);
		
		TaksiVozilo tv2 = new TaksiVozilo();
		tv2.setId("333 Mika Zikic");
		tv2.setSlobodan(false);
		
		instance.unesiTaksistu(tv2);
		
		TaksiVozilo tv3 = new TaksiVozilo();
		tv3.setId("200 Mika Peric");
		tv3.setSlobodan(false);
		
		instance.unesiTaksistu(tv3);
		
		TaksiVozilo tv4 = new TaksiVozilo();
		tv4.setId("543 Bogdan Pejic");
		tv4.setSlobodan(true);
		
		instance.unesiTaksistu(tv4);
		
		TaksiVozilo tv5 = new TaksiVozilo();
		tv5.setId("534 Borisav Zivanovic");
		tv5.setSlobodan(true);
		
		instance.unesiTaksistu(tv5);
		
		instance.uredi();
		
		TaksiVozilo[] niz = (TaksiVozilo[])TestUtil.getFieldValue(instance, "vozila");
		
		assertTrue("Ako se u niz vozila unese pet taksista "+System.lineSeparator()+
					tv1+System.lineSeparator()+
					tv2+System.lineSeparator()+
					tv3+System.lineSeparator()+
					tv4+System.lineSeparator()+
					tv5+System.lineSeparator()+
					"Metoda ne premesta slobodnog taksistu 122 Pera Peric na pocetak", tv1==niz[0] || tv1==niz[1] || tv1==niz[2]);
		
		assertTrue("Ako se u niz vozila unese pet taksista "+System.lineSeparator()+
				tv1+System.lineSeparator()+
				tv2+System.lineSeparator()+
				tv3+System.lineSeparator()+
				tv4+System.lineSeparator()+
				tv5+System.lineSeparator()+
				"Metoda ne premesta slobodnog taksistu 543 Bogdan Pejic na pocetak", tv4==niz[0] || tv4==niz[1] || tv4==niz[2]);
	
		assertTrue("Ako se u niz vozila unese pet taksista "+System.lineSeparator()+
				tv1+System.lineSeparator()+
				tv2+System.lineSeparator()+
				tv3+System.lineSeparator()+
				tv4+System.lineSeparator()+
				tv5+System.lineSeparator()+
				"Metoda ne premesta slobodnog taksistu 534 Borisav Zivanovic na pocetak", tv5==niz[0] || tv5==niz[1] || tv5==niz[2]);
		
		assertTrue("Ako se u niz vozila unese pet taksista "+System.lineSeparator()+
				tv1+System.lineSeparator()+
				tv2+System.lineSeparator()+
				tv3+System.lineSeparator()+
				tv4+System.lineSeparator()+
				tv5+System.lineSeparator()+
				"Metoda ne premesta zauzetog taksistu 333 Mika Zikic na kraj", tv2==niz[niz.length-1] || tv2==niz[niz.length-2]);

		assertTrue("Ako se u niz vozila unese pet taksista "+System.lineSeparator()+
				tv1+System.lineSeparator()+
				tv2+System.lineSeparator()+
				tv3+System.lineSeparator()+
				tv4+System.lineSeparator()+
				tv5+System.lineSeparator()+
				"Metoda ne premesta zauzetog taksistu 200 Mika Peric na kraj", tv3==niz[niz.length-1] || tv3==niz[niz.length-2]);
		
		
	}

}

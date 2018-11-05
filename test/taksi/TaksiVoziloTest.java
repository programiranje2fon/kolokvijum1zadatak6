package taksi;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Modifier;
import java.util.GregorianCalendar;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import test.TestUtil;

public class TaksiVoziloTest {

	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;
	private final PrintStream originalErr = System.err;

	TaksiVozilo instance;
	
	@Before
	public void setUp() throws Exception {
		instance = new TaksiVozilo();
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
	public void atribut_id() {
		assertTrue("U klasi nije definisan atribut id", TestUtil.doesFieldExist(TaksiVozilo.class, "id"));
	}
	
	@Test
	public void atribut_id_vidljivost() {
		assertTrue("Atribut id nije privatan", TestUtil.hasFieldModifier(TaksiVozilo.class, "id", Modifier.PRIVATE));
	}
	
	@Test
	public void atribut_brojPoziva() {
		assertTrue("U klasi nije definisan atribut brojPoziva", TestUtil.doesFieldExist(TaksiVozilo.class, "brojPoziva"));
	}
	
	@Test
	public void atribut_brojPoziva_vidljivost() {
		assertTrue("Atribut brojPoziva nije privatan", TestUtil.hasFieldModifier(TaksiVozilo.class, "brojPoziva", Modifier.PRIVATE));
	}
	
	@Test
	public void atribut_slobodan() {
		assertTrue("U klasi nije definisan atribut slobodan", TestUtil.doesFieldExist(TaksiVozilo.class, "slobodan"));
	}
	
	@Test
	public void atribut_slobodan_vidljivost() {
		assertTrue("Atribut slobodan nije privatan", TestUtil.hasFieldModifier(TaksiVozilo.class, "slobodan", Modifier.PRIVATE));
	}
	
	@Test
	public void atribut_krajSmene() {
		assertTrue("U klasi nije definisan atribut krajSmene", TestUtil.doesFieldExist(TaksiVozilo.class, "krajSmene"));
	}
	
	@Test
	public void atribut_krajSmene_vidljivost() {
		assertTrue("Atribut krajSmene nije privatan", TestUtil.hasFieldModifier(TaksiVozilo.class, "krajSmene", Modifier.PRIVATE));
	}
	
	@Test
	public void metoda_setId() {
		instance.setId("212 Pera Lazic");
		
		assertEquals("Ako se kao id prosledi '212 Pera Lazic', atribut ne dobija tu vrednost", "212 Pera Lazic", instance.getId());
	}
	
	@Test
	public void metoda_setId_Null() {
		instance.setId(null);
		
		assertEquals("NE ispisuje se rec GRESKA u slucaju unosa NULL vrednosti", "GRESKA", outContent.toString().trim().toUpperCase());		
	}
	
	@Test
	public void metoda_setId_Prekratak() {
		instance.setId("Pera");
		
		assertEquals("NE ispisuje se rec GRESKA u slucaju unosa prekratke id vrednosti", "GRESKA", outContent.toString().trim().toUpperCase());		
		assertEquals("Uneta je nedozvoljena vrednost (String kraci od 10 znakova) u atribut id", null, instance.getId());
	}
	
	@Test
	public void metoda_setId_Predugacak() {
		instance.setId("PeraPeraPeraPeraPeraPeraPeraPera");
		
		assertEquals("NE ispisuje se rec GRESKA u slucaju unosa preduge id vrednosti", "GRESKA", outContent.toString().trim().toUpperCase());		
		assertEquals("Uneta je nedozvoljena vrednost (String duzi od 30 znakova) u atribut id", null, instance.getId());
	}

	@Test
	public void metoda_setBrojPoziva() {
		instance.setBrojPoziva(12);
		
		assertEquals("Ako se unese 12 poziva, atribut ne dobija tu vrednost", 12, instance.getBrojPoziva());
	}
	
	@Test
	public void metoda_setBrojPoziva_Negativan() {
		instance.setBrojPoziva(-1);
		
		assertEquals("NE ispisuje se rec GRESKA u slucaju unosa negativne vrednosti", "GRESKA", outContent.toString().trim().toUpperCase());		
		assertEquals("Uneta je greskom nedozvoljena vrednost (-1) u atribut brojPoziva", 0, instance.getBrojPoziva());
	}

	@Test
	public void metoda_setSlobodan() {
		instance.setSlobodan(true);
		
		assertEquals("Ako se unese true, atribut ne dobija tu vrednost", true, instance.isSlobodan());
	}

	@Test
	public void metoda_setKrajSmene() {
		GregorianCalendar ks = new GregorianCalendar();
		
		ks.set(GregorianCalendar.YEAR, ks.get(GregorianCalendar.YEAR)+1);
		
		instance.setKrajSmene(ks);
		
		assertEquals("Ako se unese kraj smene koji je u buducnosti, atribut ne dobija tu vrednost", ks, instance.getKrajSmene());
	}
	
	@Test
	public void metoda_setKrajSmene_Null() {
		instance.setKrajSmene(null);
		
		assertEquals("NE ispisuje se rec GRESKA u slucaju unosa NULL vrednosti", "GRESKA", outContent.toString().trim().toUpperCase());		
	}
	
	@Test
	public void metoda_setKrajSmene_Proslost() {
		GregorianCalendar ks = new GregorianCalendar();
		
		ks.set(GregorianCalendar.YEAR, ks.get(GregorianCalendar.YEAR)-1);
		
		instance.setKrajSmene(ks);
		
		assertEquals("NE ispisuje se rec GRESKA u slucaju unosa datuma iz proslosti", "GRESKA", outContent.toString().trim().toUpperCase());		
		assertEquals("Ako se unese kraj smene koji je u proslosti, atribut greskom dobija tu vrednost", null, instance.getKrajSmene());
	}

	@Test
	public void metoda_toString() {
		GregorianCalendar ks = new GregorianCalendar();
		
		ks.set(GregorianCalendar.YEAR, ks.get(GregorianCalendar.YEAR)+1);
		
		instance.setId("100 Pera Mikic");
		instance.setBrojPoziva(23);
		instance.setSlobodan(true);
		instance.setKrajSmene(ks);
		
		String s = instance.toString();
		
		assertTrue("NE vraca se id", s.contains("100 Pera Mikic"));
		assertTrue("NE vraca se brojPoziva", s.contains("23"));
		assertTrue("NE vraca se da li je slobodan", s.contains("true"));
		assertTrue("NE vraca se kraj smene"+s, s.contains(""+ks.get(GregorianCalendar.YEAR)));	
	}

}

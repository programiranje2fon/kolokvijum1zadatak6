# Zadatak 1

** NAPOMENA: PO ZAVRŠETKU ZADATKA OBAVEZNO TESTIRATI REŠENJE POZIVANJEM AUTOMATIZOVANIH TESTOVA (desnim tasterom na naziv projekta, Run as - Java Application - PokreniTestove)**

Napraviti javnu klasu **TaksiVozilo** u paketu **taksi** koja ima:

* Privatni atribut **id** koji sadrži taksi broj i ime i prezime vozača (npr. „314 Pera Perić“).

* Privatni atribut **brojPoziva** koji predstavlja broj vožnji koje je taj taksi imao.

* Privatni atribut **slobodan** koji ima vrednost TRUE ako je taksi trenutno slobodan a FALSE ako je zauzet.

* Privatni atribut **krajSmene** koji predstavlja datum i vreme kad taksi završava sa smenom (GregorianCalendar).

* Odgovarajuće javne get i set metode za ove atribute. Nedozvoljene vrednosti za atribut id su null i svaki String kraći od 10 ili duži od 30 znakova. Kraj smene mora biti različit od null i mora da se odnosi na neki vremenski trenutak u budućnosti, a broj poziva mora da bude nula ili veći. U slučaju 
unosa ovih nedozvoljenih vrednosti potrebno je ispisati poruku o grešci.

* Redefinisanu metodu **toString** klase Object koja vraća String u kome se nalaze svi podaci o taksiju uz odgovarajući tekst.

Napraviti javnu klasu **TaksiFirma** u paketu **taksi.firma** koja ima:

* Privatni atribut **vozila** koji predstavlja niz objekata klase TaksiVozilo. Odmah inicijalizovati niz na kapacitet 1000.

* Javnu metodu **unesiTaksistu** koja kao parametre prima objekat klase TaksiVozilo i unosi ga na kraj niza, odnosno na ono slobodno mesto koje je najbliže kraju niza (mesto je slobodno ako element niza ima NULL vrednost). Ako u nizu nema slobodnih mesta, ispisati na ekranu "NEMA MESTA". Posle uspešnog unosa, potrebno je postaviti da brojPoziva bude nula.

* Javnu metodu **primiPoziv** koja kao ima tri parametra: taksi broj (ceo broj), ime vozača (String) i prezime vozača (String). Metoda na osnovu unetih parametara pronalazi traženog taksistu, povećava njegov broj poziva za jedan, i postavlja da nije slobodan. Obratiti pažnju na prazna mesta u nizu i na to da je atribut id klase taksista u formatu „TAKSI_BROJ IME PREZIME“. Ako je taksisti prošao kraj smene, ispisati poruku "KRAJ SMENE" i ne vršiti izmene. Ako taksista sa datim podacima ne postoji, ispisati poruku "NE POSTOJI".

* Javnu metodu **uredi** koja preuređuje redosled taksi vozila iz niza tako da se na početku nalaze sva slobodna taksi vozila, a na kraju sva zauzeta taksi vozila.

Napraviti javnu klasu **ProbaTaksiFirma** u paketu **taksi.firma.proba** koja u main metodi pravi jedan objekat klase TaksiFirma i unosi dva objekta klase TaksiVozila: "122 Marko Markovic", broj poziva 0, trenutno slobodan, koji zavrsava smenu u 23:59h trenutnog dana (dana kad je pokrenut program), i "333 Zika Petrovic", broj poziva 7, trenutno nije slobodan, koji zavrsava smenu u 22:22h trenutnog dana.

# Zadatak 2 (ispravka koda)

** NAPOMENA: PO ZAVRŠETKU ZADATKA OBAVEZNO TESTIRATI REŠENJE POZIVANJEM AUTOMATIZOVANIH TESTOVA (desnim tasterom na naziv projekta, Run as - Java Application - PokreniTestove)**

U produžetku teksta je dat kod klase sa metodom koja bi trebalo da ispisuje piramidu (od znakova 0) na ekranu. Ideja je da se u prvom redu na ekranu ispiše jedna nula (i 4 znaka za prazno mesto pre nje), u drugom redu tri nule (i tri znaka za prazno mesto pre njih)... a u poslednjem (petom) redu devet nula, tako da bi konačan izlaz na ekranu trebalo da izgleda ovako:

	    0
	   000
	  00000
	 0000000
	000000000
	
Dati kod se kompajlira, ali ne radi to šta treba. Napraviti klasu **Ispisivac4** u paketu **ispravka_koda**, prekucati u nju kod koji je dat  i, uz minimalne izmene ga ispraviti tako da funkcioniše kako treba. Koristeći main metodu iz test klase, pozvati metodu ispisiPiramidu() i proveriti njen rad.

	package ispravka_koda;
	class Ispisivac4 {
	static void ispisiPiramidu() {
		int prazno=4, nula = 1;
		for (int i = 1; i <= 5; i--){
			for (int j=1;j<prazno+nula;j++)
				if (j<=prazno)System.out.print(' ');
				else System.out.print('0');
			System.out.println();
			prazno--;nula++;
		}
	   }
	}

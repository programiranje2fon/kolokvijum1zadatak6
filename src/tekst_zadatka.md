# Zadatak 1

** NAPOMENA: PO ZAVRŠETKU ZADATKA OBAVEZNO TESTIRATI REŠENJE POZIVANJEM AUTOMATIZOVANIH TESTOVA (desnim tasterom na naziv projekta, Run as - Java Application - PokreniTestove)**



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
		for (int i = 1; i >= 5; i++){
			for (int j=1;j<prazno+nula;j++)
				if (j<=prazno)System.out.print(' ');
				else System.out.print('0');
			System.out.println();
			prazno--;nula++;
		}
	   }
	}

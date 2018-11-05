package ispravka_koda;

class Ispisivac4 {
	static void ispisiPiramidu() {
		int prazno = 4, nula = 1;
		for (int i = 1; i <= 5; i++) {
			for (int j = 1; j < prazno + nula*2; j++)
				if (j <= prazno)
					System.out.print(' ');
				else
					System.out.print('0');
			System.out.println();
			prazno--;
			nula++;
		}
	}
}

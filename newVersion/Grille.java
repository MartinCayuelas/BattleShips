public class Grille {

	private String[][] grille;

	public Grille(int longueurM, int largeurM) {
		String[][] maGrille = new String[longueurM][largeurM];
		for (int i = 0; i < maGrille.length; i++) {
			for (int j = 0; j < maGrille[i].length; j++) {
				maGrille[i][j] = "〰️";
			}
		}
		this.grille = maGrille;
	}

	public String[][] getGrille() {
		return grille;
	}

	public void setGrille(int a, int b, String typeCase) {
		grille[b][a] = typeCase;
	}

	public void restartGrille(int longueurM, int largeurM) {
		String[][] maGrille = new String[longueurM][largeurM];
		for (int i = 0; i < maGrille.length; i++) {
			for (int j = 0; j < maGrille[i].length; j++) {
				maGrille[i][j] = "〰️";
			}
		}
		this.grille = maGrille;
	}

	public String toString() {
		String str = "";
		String lettres = "	";
		String letter = "A";
		char l = letter.charAt(0);
		int cpt = 0;
		while (cpt < 10) {
			lettres += "" + l + "\t";
			l++; // On change la lettre
			cpt++;
		}

		str += lettres + "\n";

		int u = 0;
		int w;
		while (u < grille.length) {
			w = 0;
			str += (u + 1) + "	";
			while (w < grille[u].length) {
				str += "" + grille[u][w] + "\t";
				w++;
			}
			str += "\n";
			u++;
		}
		return str;
	}

	/*** Vérification grille ***/
	boolean verifInt(int l) {
		boolean ok = false;
		if (l < 5) {
			ok = false;
		} else if (l > 15) {
			ok = false;
		} else {
			ok = true;
		}
		return ok;
	}

	/********** Verification coord coordonnées *************/

	boolean coordCorrect(String coord) {
		boolean coordOk = false;
		String tab[] = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J" };
		String partOne;
		String partTwo;

		partOne = coord.substring(0, 1); // Debut
		partTwo = coord.substring(1, 2); // end

		if (coord.length() == 3) {
			String partThree;
			partThree = coord.substring(2, 3);
			partTwo += partThree;
		}
		int i = 0;
		boolean partOneOk = false;
		while (i < tab.length && !partOneOk) {
			if (tab[i].equals(partOne)) {
				partOneOk = true;
			}
			i++;
		}
		int right;
		boolean partTwoOk = false;
		try {
			right = Integer.parseInt(partTwo);
			if (right > 0 && right <= 10) {
				partTwoOk = true;
			}
		} catch (Exception e) {
			System.out.println("Entrez une coordonnée valide LettreChiffre (Ex: B2)");
		}
		if (partOneOk && partTwoOk) {
			coordOk = true;
		}
		return coordOk;
	}
}


public class Grille {

	private int[][] grille;

	public Grille() {

	}

	public Grille(int longueurM, int largeurM) {
		int[][] maGrille = new int[longueurM][largeurM];

		for (int i = 0; i < maGrille.length; i++) {
			for (int j = 0; j < maGrille[i].length; j++) {

				maGrille[i][j] = 0;

			}
		} /*
			 * int[][] grille = { { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0,
			 * 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
			 * }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0,
			 * 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0,
			 * 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 } };
			 */
		this.grille = maGrille;
	}

	public int[][] getGrille() {
		return grille;
	}

	public void setGrille(int a, int b) {
		grille[b][a] = 1;
	}

	public void restartGrille(int longueurM, int largeurM) {

		int[][] maGrille = new int[longueurM][largeurM];

		for (int i = 0; i < maGrille.length; i++) {
			for (int j = 0; j < maGrille[i].length; j++) {

				maGrille[i][j] = 0;

			}
		} /*
			 * int[][] grille = { { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0,
			 * 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
			 * }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0,
			 * 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0,
			 * 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 } };
			 */

		this.grille = maGrille;
	}

	public String toString() {
		String str = "";

		int u = 0;
		int w;
		while (u < grille.length)

		{

			w = 0;

			while (w < grille[u].length)

			{

				str += "" + grille[u][w] + "";

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

	/********** Verification Tir coordonnées *************/

	boolean tirCorrect(String tir) {
		boolean tirOk = false;

		String tab[] = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O" };

		String partOne;
		String partTwo;

		partOne = tir.substring(0, 1); // Debut
		partTwo = tir.substring(1, 2); // end

		if (tir.length() == 3) {
			String partThree;
			partThree = tir.substring(2, 3);
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

			if (right > 0 && right <= 15) {
				partTwoOk = true;

			}
		} catch (Exception e) {
			System.out.println("Entrez une coordonnée valide LettreChiffre (Ex: B2)");
		}

		if (partOneOk && partTwoOk) {
			tirOk = true;
		}

		return tirOk;

	}

}

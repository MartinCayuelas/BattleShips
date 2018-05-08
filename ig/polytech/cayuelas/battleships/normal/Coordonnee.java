package ig.polytech.cayuelas.battleships.normal;

import java.util.ArrayList;
import java.util.Random;

public class Coordonnee {

	private char partOne;
	private int partTwo;
	private int hit;

	public Coordonnee() {};
	public Coordonnee(String coord){

		if (coord.length() == 3) {
			coord = coord.substring(0, 1).toUpperCase() + coord.substring(1, 2) + coord.substring(2, 3);

			partOne = coord.charAt(0);
			String partTwo1 = coord.substring(1, 2);
			String partTwo2 = coord.substring(2, 3);
			partTwo1 += partTwo2;
			try {
				partTwo = Integer.parseInt(partTwo1);
			} catch (Exception e) {
				System.out.println("Problème dans le format");
			}
			hit = 0;
		} else {
			coord = coord.substring(0, 1).toUpperCase() + coord.substring(1, 2);

			partOne = coord.charAt(0);
			try {
				partTwo = Integer.parseInt(coord.substring(1, 2));
			} catch (Exception e) {
				System.out.println("Problème dans le format");
			}
			hit = 0;
		}

	}

	public char getPartOne() {
		return partOne;
	}

	public void setPartOne(char partOne) {
		this.partOne = partOne;
	}

	public int getPartTwo() {
		return partTwo;
	}

	public void setPartTwo(char partTwo) {
		this.partTwo = partTwo;
	}

	public int getHit() {
		return hit;
	}

	public void setHit() {
		this.hit = 1;
	}

	public String getCoordonnee() {
		String coord = "";
		coord = Character.toString(this.getPartOne()) + this.getPartTwo();
		return coord;
	}

	public boolean coordCorrect(String coord) {
		boolean coordOk = false;
		String tab[] = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J" };
		String partOne;
		String partTwo;

		partOne = coord.substring(0, 1).toUpperCase(); // Debut
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

	public boolean compareCoord(Coordonnee start, Coordonnee end) {
		boolean superieur = false;
		if (start.getPartOne() > end.getPartOne()) {
			superieur = true;
		}
		if (start.getPartTwo() > end.getPartTwo()) {
			superieur = true;
		}
		return superieur;
	}
	
	
	
	/***************** Pour le robot **************/

	public ArrayList<Coordonnee> getPossibilities(int sizeBoat) {
		ArrayList<Coordonnee> shipCoords = new ArrayList<Coordonnee>();

		int i = 0;
		char lettre = this.getPartOne();
		char lSup = lettre;
		char lInf = lettre;
		while (i < sizeBoat - 1) {
			i++;
			lSup++;
			lInf--;

		}
		// Chiffre change
		if ((this.getPartTwo() + i) <= 10) {
			int v = this.getPartTwo() + i;
			String c = this.getPartOne() + "" + v;
			Coordonnee maCoord = new Coordonnee(c);
			shipCoords.add(maCoord);
		}
		if ((this.getPartTwo() - i) > 0) {
			int v = this.getPartTwo() - i;
			String c = this.getPartOne() + "" + v;
			Coordonnee maCoord = new Coordonnee(c);
			shipCoords.add(maCoord);
		}

		// lettre change

		if (lSup <= 'J') {
			String c = lSup + "" + this.getPartTwo();
			Coordonnee maCoord = new Coordonnee(c);
			shipCoords.add(maCoord);
		}
		if (lInf >= 'A') {
			String c = lInf + "" + this.getPartTwo();
			Coordonnee maCoord = new Coordonnee(c);
			shipCoords.add(maCoord);
		}

		return shipCoords;
	}

	public static Coordonnee coordRandom() {
		Random rdL = new Random();
		int valeurL = 1 + rdL.nextInt(11 - 1);
		Random rdC = new Random();
		int valeurC = 1 + rdC.nextInt(11 - 1);

		String tab[] = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J" };
		String partOne = tab[valeurL - 1];
		String coord = partOne + "" + valeurC;
		Coordonnee start = new Coordonnee(coord);

		return start;
	}

	public static Coordonnee coordRandomMiddle() {
		Random rdL = new Random();
		int valeurL = 1 + rdL.nextInt(6 - 1);
		Random rdC = new Random();
		int valeurC = 3 + rdC.nextInt(8 - 3);

		String tab[] = { "C", "D", "E", "F", "G" };
		String partOne = tab[valeurL - 1];
		String coord = partOne + "" + valeurC;
		Coordonnee start = new Coordonnee(coord);

		return start;
	}

	
	
}

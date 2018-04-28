import java.util.Scanner;

public class Ship {
	Coordonnee[] tabCoord; // tableau des coordonnées entières du bateau
	String name;

	public Ship() {

	}

	public Ship(String start, String end) {
		int sizeTab = 0;

		if (!start.equals(end)) {

			Coordonnee startC = new Coordonnee(start);
			Coordonnee endC = new Coordonnee(end);

			boolean vertical;
			if (startC.getPartOne() == (endC.getPartOne())) {
				vertical = true;
				sizeTab = endC.getPartTwo() - startC.getPartTwo() + 1;
			} else {
				vertical = false;
				sizeTab = endC.getPartOne() - startC.getPartOne() + 1;
			}
			tabCoord = new Coordonnee[sizeTab];

			for (int z = 0; z < sizeTab; z++) {
				if (vertical) {
					int pos = startC.getPartTwo() + z;
					String newCoordS = Character.toString(startC.getPartOne()) + pos;
					Coordonnee newCoord = new Coordonnee(newCoordS);
					tabCoord[z] = newCoord;
				} else {
					String newCoordS = Character.toString(((char) (startC.getPartOne() + z))) + startC.getPartTwo();
					Coordonnee newCoord = new Coordonnee(newCoordS);
					tabCoord[z] = newCoord;
				}
			}

			switch (this.getSize()) {
			case 2:
				this.name = "Destroyer";
				break;
			case 4:
				name = "Battleship";
				break;
			case 5:
				name = "Carrier";
				break;
			default:
				this.name = "None";
			}
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSize() {

		return this.tabCoord.length;
	}

	public Coordonnee[] getTabCoord() {
		return tabCoord;
	}

	public boolean isDiagonal(String start, String end) {
		Coordonnee startC = new Coordonnee(start);
		Coordonnee endC = new Coordonnee(end);
		boolean diagonal = false;
		if (!(startC.getPartOne() == endC.getPartOne()) && !(startC.getPartTwo() == endC.getPartTwo())) { // Bateau
																											// impossible
			diagonal = true; // à placer

		}
		return diagonal;
	}

	public void nameShip3() {
		boolean chiffreBon = false;
		while (!chiffreBon) {
			System.out.println("Vous voulez un Submarine (Tapez 1) ou un Cruiser(Tapez 2)?");
			Scanner type = new Scanner(System.in);
			int bateau = 0;
			try {
				bateau = type.nextInt();

				if (bateau == 1) {
					this.setName("Submarine");
					chiffreBon = true;
				} else if (bateau == 2) {
					this.setName("Cruiser");
					chiffreBon = true;
				} else {

					chiffreBon = false;
					System.out.println("Entrez un chiffre entre 1 et 2");
				}
			} catch (Exception e) {
				chiffreBon = false;
				System.out.println("Entrez un chiffre entre 1 et 2, pas autre chose");
			}
		}
	}

	/***************** Code pour gérer le touché/coulé *******************/

	public boolean isHit(String missileCoord) {
		int i = 0;
		boolean touche = false;
		while (i < tabCoord.length && touche == false) {
			if (tabCoord[i] == null) {
				touche = false;
			} else {
				if (tabCoord[i].getCoordonnee().equals(missileCoord)) {
					tabCoord[i].setHit();
					touche = true;

				}
			}
			i++;
		}

		return touche;

	}

	public int coordLeft(char h) {
		int left = 0;

		left = Character.getNumericValue(h) - 10;

		return left;
	}

	public int coordRight(int h) {
		int right = 0;
		right = h - 1; // Car le tableau commence à 0

		return right;
	}

	public boolean isDestroyed() {

		int cpt = 0;
		for (int i = 0; i < this.getSize(); i++) {
			System.out.println(this.getTabCoord()[i]+ " hit: "+this.getTabCoord()[i].getHit());
			if (this.getTabCoord()[i].getHit() == 1) {
				cpt++;
			}

		}

		return cpt == this.getSize();
	}

	/***************************************************************/

	@Override
	public String toString() {
		return "[" + name + "]";
	}

	/*****************************************************************/

}
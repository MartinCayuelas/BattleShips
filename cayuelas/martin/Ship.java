package cayuelas.martin;

import java.util.Scanner;


public class Ship {
	Coordonnee[] tabCoord; // tableau des coordonnées entières du bateau
	

	public Ship() {

	}

	public Ship(String start, String end){
		int sizeTab = 0;

		if (!start.equals(end)) { //IF == then sizeShip == 1, not possible

			Coordonnee startC = new Coordonnee(start);
			Coordonnee endC = new Coordonnee(end);
			if(startC.compareCoord(startC, endC)) {
				Coordonnee tmp = startC;
				startC = endC;
				endC = tmp;
			}

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

			
		}
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
		if (!(startC.getPartOne() == endC.getPartOne()) && !(startC.getPartTwo() == endC.getPartTwo())) { //Bateau impossible à placer																					
			diagonal = true; 
		}
		return diagonal;
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

	public boolean isDestroyed() {
		int cpt = 0;
		for (int i = 0; i < this.getSize(); i++) {
			if (this.getTabCoord()[i].getHit() == 1) {
				cpt++;
			}
		}
		return cpt == this.getSize();
	}

}

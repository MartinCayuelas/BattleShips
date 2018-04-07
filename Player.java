import java.util.ArrayList;

public class Player {

	private String name;
	private int score;
	private ArrayList<Ship> flote;
	private Grille maGrille;
	private Grille maGrilleBateau;

	int nbCarrier;
	int nbCruiser;
	int nbBattleship;
	int nbSubmarine;
	int nbDestroyer;

	public Player(String nom) {
		name = nom;
		score = 0;
		flote = new ArrayList<Ship>();
		maGrille = new Grille(0, 0);
		nbCarrier = 0;
		nbCruiser = 0;
		nbBattleship = 0;
		nbSubmarine = 0;
		nbDestroyer = 0;

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public ArrayList<Ship> getFlote() {
		return flote;
	}

	public Grille getMaGrille() {
		return maGrille;
	}

	public Grille getMaGrilleBat() {
		return maGrilleBateau;
	}

	/*************** Incrémentation nb Bateaux et vérifications ***********/

	void incrementeTypeBateau(int size) {
		if (size == 2) {
			this.setNbDestroyer();
		} else if (size == 4) {
			this.setNbBattleship();
		} else { // Taille == 5
			this.setNbCarrier();
		}

	}

	void incrementeTypeBateauSize3(int size) {
		if (size == 1) {
			this.setNbSubmarine();
		} else {
			this.setNbCruiser();
		}
	}

	public boolean verificationAjout(String name) {
		// retourne vrai si ajout Possible
		boolean ajout = false;

		if (name.equals("Destroyer")) {
			if (this.getNbDestroyer() < 1) {
				ajout = true;
			}
		} else if (name.equals("Carrier")) {
			if (this.getNbCarrier() < 1) {
				ajout = true;
			}
		} else if (name.equals("Battleship")) {
			if (this.getNbBattleship() < 1) {
				ajout = true;
			}
		} else if (name.equals("Cruiser")) {
			if (this.getNbCruiser() < 1) {
				ajout = true;

			}
		} else if (name.equals("Submarine")) {
			if (this.getNbSubmarine() < 1) {
				ajout = true;
			}
		}

		return ajout;

	}

	public boolean verificationChevauchement(Ship shipTraite) {
		boolean coordEgale = false;
		for (Ship s : this.flote) {
			for (int i = 0; i < s.getSize(); i++) {
				for (int j = 0; j < shipTraite.getSize(); j++) {

					if (s.getTabCoord()[i].equals(shipTraite.getTabCoord()[j])) {
						coordEgale = true;

					}
				}
			}
		}

		return coordEgale;
	}

	@Override
	public String toString() {
		String st = "";
		st += this.getName() + ": \n";
		for (Ship s : this.flote) {
			st += s;
		}
		return st;
	}

	public int getNbCarrier() {
		return nbCarrier;
	}

	public void setNbCarrier() {
		this.nbCarrier += 1;
	}

	public int getNbCruiser() {
		return nbCruiser;
	}

	public void setNbCruiser() {
		this.nbCruiser += 1;
	}

	public int getNbBattleship() {
		return nbBattleship;
	}

	public void setNbBattleship() {
		this.nbBattleship += 1;
	}

	public int getNbSubmarine() {
		return nbSubmarine;
	}

	public void setNbSubmarine() {
		this.nbSubmarine += 1;
	}

	public int getNbDestroyer() {
		return nbDestroyer;
	}

	public void setNbDestroyer() {
		this.nbDestroyer += 1;
	}

}

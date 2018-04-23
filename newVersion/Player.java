import java.util.ArrayList;
import java.util.Scanner;

public class Player {

	private String name;
	private int score;
	private ArrayList<Ship> flote;
	private Grille maGrille;

	int nbCarrier;
	int nbCruiser;
	int nbBattleship;
	int nbSubmarine;
	int nbDestroyer;

	public Player(String nom) {
		name = nom;
		score = 0;
		flote = new ArrayList<Ship>();
		maGrille = new Grille(10, 10);
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


	/*************** Creation / Incrémentation nb Bateaux et vérifications ***********/

	void creationFlote() {
		int i = 0;
		while (i < 5) {
		boolean ajoute = false;
		while (!ajoute) {
			String start = "";
			String end = "";
			boolean diagonal = true; // Bonne forme de coordonnée, pas en diagonale
			Ship ship = new Ship(); // Bateau fictif pour tester
			while (diagonal) { // Tant que le bateau est en diagonale on loop
				boolean coordOk = false;
				while (!coordOk) {
					Scanner sc3 = new Scanner(System.in);
					System.out.println("Veuillez saisir une coordonnée de début :");
					start = sc3.nextLine();
					if (start.length() > 1) {
						coordOk = this.getMaGrille().coordCorrect(start);
						System.out.println("Vous avez saisi : " + start);
					}

				} // Fin Coordonnée de Début
				coordOk = false;
				while (!coordOk) {
					Scanner sc4 = new Scanner(System.in);
					System.out.println("Veuillez saisir une coordonnée de fin :");
					end = sc4.nextLine();
					if (end.length() > 1) {
						coordOk = this.getMaGrille().coordCorrect(end);
						System.out.println("Vous avez saisi : " + end);
					}
				} // Fin Coordonnée de Fin
				diagonal = ship.isDiagonal(start, end);
				if (diagonal) {
					System.out.println("Bateau en diagonale!");
				}
			}

			Ship s = new Ship(start, end);

			System.out.println("Vous avez créé un bateau de taille : " + s.getSize());

			if (s.getSize() == 3) {
				boolean chiffreBon = false;
				while (!chiffreBon) {
					System.out.println("Vous voulez un Submarine (Tapez 1) ou un Cruiser(Tapez 2)?");
					Scanner type = new Scanner(System.in);
					int bateau = 0;
					try {
						bateau = type.nextInt();

						if (bateau == 1) {
							s.setName("Submarine");
							chiffreBon = true;
						} else if (bateau == 2) {
							s.setName("Cruiser");
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
			System.out.println("Vous avez choisi : " + s.getName());
			boolean chevauchement = this.verificationChevauchement(s);
			boolean okAjout = this.verificationAjout(s.getName());

			if (okAjout && !chevauchement) {
				this.getFlote().add(s); // Ajout du Bateau à la flotte du joueur

				/********** Partie Incrémentation nombre de Bateaux du Joueur **********/
				if (s.getSize() == 3) {
					int size;
					if (s.getName().equals("Submarine")) {
						size = 1;
					} else {
						size = 2;
					}
					this.incrementeTypeBateauSize3(size);

				} else {
					this.incrementeTypeBateau(s.getSize());
				}

				ajoute = true; // On a ajouté le Bateau
				i++;

				/**** Partie Affichage Données du Joueur ******/
				System.out.println("Bateau n° " + i);

				afficheFloteDetails();
				/**** Partie Affichage Grille du Joueur ******/
				for (int z = 0; z < s.getSize(); z++) {

					int a = s.coordLeft(s.getTabCoord()[z]);
					int b = s.coordRight(s.getTabCoord()[z]);

					this.getMaGrille().setGrille(a, b, "⛴");

				}
				System.out.println(this.getMaGrille().toString());
				/**************************************************/
			} else {
				if (chevauchement) {
					System.out.println("Le bateau va chevaucher une position déjà occupée");
				} else {
					System.out.println("Vous avez atteint le nombre limite de " + s.getName() + "s");
					System.out.println("Choississez un autre type de bateau --------------");
				}
				afficheFloteDetails();
				ajoute = false;
			}

		}
		}
	
	}
	
	void afficheFloteDetails() {
		System.out.println("Carriers (Taille 5): " + this.getNbCarrier());
		System.out.println("Cruisers (Taille 3): " + this.getNbCruiser());
		System.out.println("Battleships (Taille 4): " + this.getNbBattleship());
		System.out.println("Destroyers (Taille 2): " + this.getNbDestroyer());
		System.out.println("Submarines (Taille 3): " + this.getNbSubmarine());
		
	}
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
		// retourne vrai si ajout Possible en fonction du nombre de bateaux du même type
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

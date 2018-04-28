import java.util.ArrayList;
import java.util.Scanner;

public class Player {

	private String name;
	private int score;
	private ArrayList<Ship> Flotte;
	private ArrayList<Coordonnee> myCoords;

	private int nbCarrier;
	private int nbCruiser;
	private int nbBattleship;
	private int nbSubmarine;
	private int nbDestroyer;

	public Player(String nom) {
		name = nom;
		score = 0;
		Flotte = new ArrayList<Ship>();
		myCoords = new ArrayList<Coordonnee>();
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

	public ArrayList<Ship> getFlotte() {
		return Flotte;
	}

	
	public ArrayList<Coordonnee> getMyCoords() {
		return myCoords;
	}


	/***************
	 * Creation / Incrémentation nb Bateaux et vérifications
	 ***********/

	public void creationFlotte() {
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
							Coordonnee coord = new Coordonnee(start);
							coordOk = coord.coordCorrect(start);
							System.out.println("Vous avez saisi : " + start);
						}

					} // Fin Coordonnée de Début
					coordOk = false;
					while (!coordOk) {
						Scanner sc4 = new Scanner(System.in);
						System.out.println("Veuillez saisir une coordonnée de fin :");
						end = sc4.nextLine();
						if (end.length() > 1) {
							Coordonnee coord = new Coordonnee(end);
							coordOk = coord.coordCorrect(end);
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
					s.nameShip3();
				}
				System.out.println("Vous avez choisi : " + s.getName());
				boolean chevauchement = this.verificationChevauchement(s);
				boolean okAjout = this.verificationAjout(s.getName());

				if (okAjout && !chevauchement) {
					this.getFlotte().add(s); // Ajout du Bateau à la flotte du joueur

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
					afficheFlotteDetails();
					/**** Partie Affichage Grille du Joueur ******/
					for (int z = 0; z < s.getSize(); z++) {
						this.getMyCoords().add(s.getTabCoord()[z]);
					}
					
					String grille = this.myCoordString();
					System.out.println(grille);
					/**************************************************/
				} else {
					if (chevauchement) {
						System.out.println("Le bateau va chevaucher une position déjà occupée");
					} else {
						System.out.println("Vous avez atteint le nombre limite de " + s.getName() + "s");
						System.out.println("Choississez un autre type de bateau --------------");
					}
					afficheFlotteDetails();
					ajoute = false;
				}

			}
		}

	}

	void afficheFlotteDetails() {
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

		for (Ship s : this.Flotte) {
			int i = 0;
			while (!coordEgale && i < s.getSize()) {
				int j = 0;
				while (j < shipTraite.getSize() && !coordEgale) {
					if (s.getTabCoord()[i].getCoordonnee().equals(shipTraite.getTabCoord()[j].getCoordonnee())) {
						coordEgale = true;
					}
					j++;
				}
				i++;
			}
		}
		return coordEgale;
	}

	@Override
	public String toString() {
		String st = "";
		st += this.getName() + ": \n";
		for (Ship s : this.Flotte) {
			st += s;
		}
		return st;
	}
	
	public String myCoordString() {
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
		letter = "A";
		 l = letter.charAt(0);
	
		int u = 0;
		int w;
		while (u < 10) {
			w = 0;
			str += (u + 1) + "	";
			int nb = u+1;
			while (w < 10) {
				
				String maCoord = l+""+nb;
				boolean equal =false;
				boolean touche = false;
				Coordonnee c = new Coordonnee(maCoord);
				for(Coordonnee coord : this.getMyCoords()) {
					//System.out.println("c:"+coord.getCoordonnee()+" hit: "+coord.getHit());
					if(coord.getCoordonnee().equals(c.getCoordonnee())) {
						equal = true;
						if(coord.getHit() == 1) {
							touche = true;
							
						}
					}
				}
				if(equal) {
					if(touche) {
						str += "" + "⛴" + "\t";
					}else {
						str += "" + "X" + "\t";
					}
				}else {
					str += "" + "〰️" + "\t";
				}
				
				
				w++;
				l++;
			}
			str += "\n";
			letter = "A";
			l = letter.charAt(0);
			u++;
		}
		return str;
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

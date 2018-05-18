package cayuelas.martin;



import java.util.ArrayList;
import java.util.Random;

public class IAhard implements Iia {
	private String name;
	private int score;
	private ArrayList<Ship> Flotte;
	private ArrayList<Coordonnee> myCoordsShooted;

	private ArrayList<Coordonnee> coordHitedShip; // Liste de coordonnée touchée (Hit == 1 )

	public IAhard(String nom) {
		name = nom;
		score = 0;
		Flotte = new ArrayList<Ship>();
		myCoordsShooted = new ArrayList<Coordonnee>();

		coordHitedShip = new ArrayList<Coordonnee>();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createFleet() {
		// TODO Auto-generated method stub

		this.createDestroyer();
		this.createCruiser();

		this.createSubmarine();

		this.createBattleship();

		this.createCarrier();

	}

	
	@Override
	public void shoot(Iplayer monPlayer, Iplayer monAdversaire) {
		Coordonnee c = new Coordonnee();

		// System.out.println("Je vais tirer");
		if (monPlayer.getmyCoordsShooted().size() == 0) {

			boolean tirPossible = false;

			while (!tirPossible) {
				c = Coordonnee.coordRandomMiddle();
				if (c.isPair()) {
					tirPossible = true;
				}
			}
		} else {
			if (coordHitedShip.size() > 0) { // Si l'IA a déjà touché une coordonnée

				Coordonnee last = coordHitedShip.get(coordHitedShip.size() - 1);
				boolean available = false;
				System.out.println("LastHited: " + last.getCoordonnee()); // Dernière coordonnée touchée (hit == 1)
				int i = 0;
				while (!available && i < coordHitedShip.size()) {
					Coordonnee co = coordHitedShip.get(i);
					c = co.getNextCoordUp();
					// System.out.println(co.getCoordonnee() + " :" + c.getCoordonnee());
					if (monPlayer.isShooted(c.getCoordonnee()) || !c.coordCorrect(c.getCoordonnee())) {
						// System.out.println("UP hit");
						c = co.getNextCoordDown();
						// System.out.println(co.getCoordonnee() + " :" + c.getCoordonnee());
						if (monPlayer.isShooted(c.getCoordonnee()) || !c.coordCorrect(c.getCoordonnee())) {
							// System.out.println("Down hit");
							c = co.getNextCoordLeft();
							// System.out.println(co.getCoordonnee() + " :" + c.getCoordonnee());
							if (monPlayer.isShooted(c.getCoordonnee()) || !c.coordCorrect(c.getCoordonnee())) {
								// System.out.println("left hit");
								c = co.getNextCoordRight();
								// System.out.println(co.getCoordonnee() + " :" + c.getCoordonnee());
								if (monPlayer.isShooted(c.getCoordonnee()) || !c.coordCorrect(c.getCoordonnee())) {
									// System.out.println("Right hit");
								} else {
									available = true;
								}
							} else {
								available = true;
							}
						} else {
							available = true;
						}
					} else {
						available = true;
					}
					i++;
				} // END While
				
				
				if (!available) {
					
					c = Coordonnee.huntMode(monPlayer);
				
				}

			} else {// Sinon on passe en mode Chasse
				c = Coordonnee.huntMode(monPlayer);
			} // End IF
		} // END IF

		System.out.println("Tir: " + c.getCoordonnee());

		/************ Partie 2 *************/

		boolean touche = false;
		boolean destroyed = false;// variable pour supprimer un bateau si jamais il est coulé
		Ship shipDelete = new Ship();// Bateau fictif pour les vérifications +
		// Si jamais aucun bateau n'est touché on peut dire que la position de tir est
		// ratée
		for (Ship s1 : monAdversaire.getFlotte()) {
			if (s1.isHit(c.getCoordonnee())) {
				touche = true;
				c.setHit();
				monPlayer.getmyCoordsShooted().add(c);
				coordHitedShip.add(c);
				if (s1.isDestroyed()) {
					shipDelete = s1;
					destroyed = true;
				}
			}
		}
		if (touche) {
			System.out.println("Touché");
		} else {
			monPlayer.getmyCoordsShooted().add(c);
			System.out.println("Raté");
		}

		if (destroyed) {
			System.out.println("Coulé");
			monAdversaire.getFlotte().remove(shipDelete);
			System.out.println("Il reste " + monAdversaire.getFlotte().size() + " bateaux à couler");
			int score = monPlayer.getScore() + 1;
			monPlayer.setScore(score);
		}

	}

	public void createDestroyer() { // size 2

		boolean ajoute = false;
		while (!ajoute) {
			Random rdL = new Random();
			int valeurL = 1 + rdL.nextInt(4 - 1);
			Random rdC = new Random();
			int valeurC = 1 + rdC.nextInt(5 - 1);

			String tab[] = { "A", "B", "C" };
			String partOne = tab[valeurL - 1];
			String coord = partOne + "" + valeurC;
			Coordonnee start = new Coordonnee(coord);
			System.out.println("Start: " + start.getCoordonnee());
			ArrayList<Coordonnee> tabCoords;

			tabCoords = start.getPossibilities(2);

			Random endV = new Random();
			int valEnd = 0 + endV.nextInt(tabCoords.size() - 0);

			Ship s = new Ship(start.getCoordonnee(), tabCoords.get(valEnd).getCoordonnee());

			boolean chevauchement = this.verificationChevauchement(s);
			if (!chevauchement) {
				this.getFlotte().add(s); // Ajout du Bateau à la flotte
				ajoute = true; // On a ajouté le Bateau
				System.out.println("Ship Size: " + s.getSize());
				for (Coordonnee c : s.getTabCoord()) {
					System.out.println(c.getCoordonnee());
				}
			}

		}

	}

	public void createSubmarine() { // 3
		boolean ajoute = false;
		while (!ajoute) {
			Random rdL = new Random();
			int valeurL = 1 + rdL.nextInt(5 - 1);
			Random rdC = new Random();
			int valeurC = 4 + rdC.nextInt(9 - 4);

			String tab[] = { "A", "B", "C", "D" };
			String partOne = tab[valeurL - 1];
			String coord = partOne + "" + valeurC;
			Coordonnee start = new Coordonnee(coord);
			System.out.println("Start: " + start.getCoordonnee());
			ArrayList<Coordonnee> tabCoords;

			tabCoords = start.getPossibilities(3);

			Random endV = new Random();
			int valEnd = 0 + endV.nextInt(tabCoords.size() - 0);

			Ship s = new Ship(start.getCoordonnee(), tabCoords.get(valEnd).getCoordonnee());

			boolean chevauchement = this.verificationChevauchement(s);
			if (!chevauchement) {
				this.getFlotte().add(s); // Ajout du Bateau à la flotte
				ajoute = true; // On a ajouté le Bateau
				System.out.println("Ship Size: " + s.getSize());
				for (Coordonnee c : s.getTabCoord()) {
					System.out.println(c.getCoordonnee());
				}
			}

		}
	}

	public void createCarrier() { // 5
		boolean ajoute = false;
		while (!ajoute) {
			Random rdL = new Random();
			int valeurL = 1 + rdL.nextInt(6 - 1);
			Random rdC = new Random();
			int valeurC = 6 + rdC.nextInt(11 - 6);

			String tab[] = { "C", "D", "E", "F", "G" };
			String partOne = tab[valeurL - 1];
			String coord = partOne + "" + valeurC;
			Coordonnee start = new Coordonnee(coord);
			System.out.println("Start: " + start.getCoordonnee());
			ArrayList<Coordonnee> tabCoords;

			tabCoords = start.getPossibilities(5);

			Random endV = new Random();
			int valEnd = 0 + endV.nextInt(tabCoords.size() - 0);

			Ship s = new Ship(start.getCoordonnee(), tabCoords.get(valEnd).getCoordonnee());

			boolean chevauchement = this.verificationChevauchement(s);
			if (!chevauchement) {
				this.getFlotte().add(s); // Ajout du Bateau à la flotte
				ajoute = true; // On a ajouté le Bateau
				System.out.println("Ship Size: " + s.getSize());
				for (Coordonnee c : s.getTabCoord()) {
					System.out.println(c.getCoordonnee());
				}
			}

		}
	}

	public void createBattleship() { // 4
		boolean ajoute = false;
		while (!ajoute) {
			Random rdL = new Random();
			int valeurL = 1 + rdL.nextInt(5 - 1);
			Random rdC = new Random();
			int valeurC = 1 + rdC.nextInt(6 - 1);

			String tab[] = { "G", "H", "I", "J" };
			String partOne = tab[valeurL - 1];
			String coord = partOne + "" + valeurC;
			Coordonnee start = new Coordonnee(coord);
			System.out.println("Start: " + start.getCoordonnee());
			ArrayList<Coordonnee> tabCoords;

			tabCoords = start.getPossibilities(4);

			Random endV = new Random();
			int valEnd = 0 + endV.nextInt(tabCoords.size() - 0);

			Ship s = new Ship(start.getCoordonnee(), tabCoords.get(valEnd).getCoordonnee());

			boolean chevauchement = this.verificationChevauchement(s);
			if (!chevauchement) {
				this.getFlotte().add(s); // Ajout du Bateau à la flotte
				ajoute = true; // On a ajouté le Bateau
				System.out.println("Ship Size: " + s.getSize());
				for (Coordonnee c : s.getTabCoord()) {
					System.out.println(c.getCoordonnee());
				}
			}

		}
	}

	public void createCruiser() { // 3
		boolean ajoute = false;
		while (!ajoute) {
			Random rdL = new Random();
			int valeurL = 1 + rdL.nextInt(4 - 1);
			Random rdC = new Random();
			int valeurC = 6 + rdC.nextInt(11 - 6);

			String tab[] = { "H", "I", "J" };
			String partOne = tab[valeurL - 1];
			String coord = partOne + "" + valeurC;
			Coordonnee start = new Coordonnee(coord);
			System.out.println("Start: " + start.getCoordonnee());
			ArrayList<Coordonnee> tabCoords;

			tabCoords = start.getPossibilities(3);

			Random endV = new Random();
			int valEnd = 0 + endV.nextInt(tabCoords.size() - 0);

			Ship s = new Ship(start.getCoordonnee(), tabCoords.get(valEnd).getCoordonnee());

			boolean chevauchement = this.verificationChevauchement(s);
			if (!chevauchement) {
				this.getFlotte().add(s); // Ajout du Bateau à la flotte du Robot
				ajoute = true; // On a ajouté le Bateau
				System.out.println("Ship Size: " + s.getSize());
				for (Coordonnee c : s.getTabCoord()) {
					System.out.println(c.getCoordonnee());
				}
			}

		}
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

	public ArrayList<Coordonnee> getmyCoordsShooted() {
		return myCoordsShooted;
	}


	@Override
	public boolean verificationChevauchement(Ship shipTraite) {
		// TODO Auto-generated method stub
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
		String st = "[";
		st += this.getName() + ": ";
		for (Ship s : this.Flotte) {
			st += s.getSize()+", ";
		}
		st+="]";
		return st;
	}

	@Override
	public boolean isShooted(String shoot) {
		boolean inList = false;
		int i = 0;
		for (Coordonnee coord : myCoordsShooted) {
			if (coord.equals(shoot)) {
				inList = true;
			}
		}
		return inList;
	}

	@Override
	public String myCoordsShootedString() {
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
			int nb = u + 1;
			while (w < 10) {

				String maCoord = l + "" + nb;
				boolean equal = false;
				boolean touche = false;
				Coordonnee c = new Coordonnee(maCoord);
				for (Coordonnee coord : this.getmyCoordsShooted()) {
					// System.out.println("c:"+coord.getCoordonnee()+" hit: "+coord.getHit());
					if (coord.getCoordonnee().equals(c.getCoordonnee())) {
						equal = true;
						if (coord.getHit() == 1) {
							touche = true;

						}
					}
				}
				if (equal) {
					if (touche) {
						str += "" + "⛴" + "\t";
					} else {
						str += "" + "X" + "\t";
					}
				} else {
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

	

	@Override
	public void resetPlayer() {
		// TODO Auto-generated method stub
		this.getmyCoordsShooted().clear(); //On nettoie les tirs
		this.getFlotte().clear(); // On nettoie la flotte
		this.coordHitedShip.clear();
		this.score= 0;
	}


	
}

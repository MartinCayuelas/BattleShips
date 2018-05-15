package fr.polytech.cayuelas.battleships.ia;

import fr.polytech.cayuelas.battleships.normal.Player;
import fr.polytech.cayuelas.battleships.normal.Coordonnee;
import fr.polytech.cayuelas.battleships.normal.Ship;

import java.util.ArrayList;
import java.util.Random;

public class IAhard extends Player implements IA {

	private ArrayList<Coordonnee> coordHitedShip; //Liste de coordonnée touchée (Hit == 1 )

	public IAhard(String nom) {
		super(nom);
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
	public void shoot(Player monPlayer, Player monAdversaire) {
		Coordonnee c = new Coordonnee();

		// System.out.println("Je vais tirer");
		if(monPlayer.getmyCoordsShooted().size() == 0) {
		
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
				System.out.println("LastHited: " + last.getCoordonnee()); //Dernière coordonnée touchée (hit == 1)
				int i = 0;
				while(!available && i < coordHitedShip.size()) {
				Coordonnee co = coordHitedShip.get(i);
					c = co.getNextCoordUp();
					//System.out.println(co.getCoordonnee() + "  :" + c.getCoordonnee());
					if (monPlayer.isShooted(c.getCoordonnee()) || !c.coordCorrect(c.getCoordonnee())) {
					//	System.out.println("UP hit");
						c = co.getNextCoordDown();
						//System.out.println(co.getCoordonnee() + "  :" + c.getCoordonnee());
						if (monPlayer.isShooted(c.getCoordonnee()) || !c.coordCorrect(c.getCoordonnee())) {
							//	System.out.println("Down hit");
							c = co.getNextCoordLeft();
							//System.out.println(co.getCoordonnee() + "  :" + c.getCoordonnee());
							if (monPlayer.isShooted(c.getCoordonnee()) || !c.coordCorrect(c.getCoordonnee())) {
								//	System.out.println("left hit");
								c = co.getNextCoordRight();
								//System.out.println(co.getCoordonnee() + "  :" + c.getCoordonnee());
								if (monPlayer.isShooted(c.getCoordonnee()) || !c.coordCorrect(c.getCoordonnee())) {
									//	System.out.println("Right hit");
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
				} //END While
				
				if(!available) {
					c = Coordonnee.huntMode(monPlayer);
				}

			} else {//Sinon on passe en mode Chasse
				c = Coordonnee.huntMode(monPlayer); 
			}//End IF
		}//END IF

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

}

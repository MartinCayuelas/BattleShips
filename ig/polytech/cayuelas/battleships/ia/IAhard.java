package ig.polytech.cayuelas.battleships.ia;

import ig.polytech.cayuelas.battleships.normal.Player;
import ig.polytech.cayuelas.battleships.normal.Coordonnee;
import ig.polytech.cayuelas.battleships.normal.Ship;

import java.util.ArrayList;
import java.util.Random;

public class IAhard extends Player implements IA {

	private Coordonnee lastShoot;
	private boolean firstShoot;

	public IAhard(String nom) {
		super(nom);
		lastShoot = new Coordonnee();
		firstShoot = true;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createFleet() {
		// TODO Auto-generated method stub

		this.createCruiser();

		this.createDestroyer();

		this.createSubmarine();

		this.createBattleship();

		this.createCarrier();

	}

	@Override
	public void shoot(Player monPlayer, Player monAdversaire) {
		Coordonnee c = new Coordonnee();

		System.out.println("LastShoot: " + lastShoot.getCoordonnee());
		boolean okTir = false;
		boolean okCoord = false;
		boolean contains = false;
		/************* Partie 1 **************/

		if (firstShoot) {
			c = Coordonnee.coordRandomMiddle();
			firstShoot = false;
		} else {

			if (lastShoot.getHit() == 1) {
				// On prend la taille la plus petite de bateau
				ArrayList<Coordonnee> tabCoords = lastShoot.getPossibilities(2);
				Random rdC = new Random();
				int valeurC = 0 + rdC.nextInt(2 - 0);
				
					c = tabCoords.get(valeurC);
					

				System.out.println("Je peux tirer ===========================  == 1");
			} else {
				boolean ok = false;
				while (!ok) {
					c = Coordonnee.coordRandom();
					if (IAhard.isContained(c, monPlayer)) {
						ok = false;
					} else {
						ok = true;
					}
				}

				System.out.println("Je peux tirer ===============================  normal");

			}

		}
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
				monPlayer.getMyCoords().add(c);
				if (s1.isDestroyed()) {
					shipDelete = s1;
					destroyed = true;
				}
			}
		}
		if (touche) {
			System.out.println("Touché");
		} else {
			monPlayer.getMyCoords().add(c);
			System.out.println("Raté");
		}

		if (destroyed) {
			System.out.println("Coulé");
			monAdversaire.getFlotte().remove(shipDelete);
			System.out.println("Il reste " + monAdversaire.getFlotte().size() + " bateaux à couler");
			int score = monPlayer.getScore() + 1;
			monPlayer.setScore(score);
		}

		// Analyse du dernier tir
		// Si touché, comparer avec les bateaux restants pour voir où tirer
		// prendre en compte la taille et l'orientation

		// Si non touché, faire un pseudo random en regardant les bateaux restants

		lastShoot = c;
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

			System.out.println("Ship Size: " + s.getSize());
			for (Coordonnee c : s.getTabCoord()) {
				System.out.println(c.getCoordonnee());
			}
			boolean chevauchement = this.verificationChevauchement(s);
			if (!chevauchement) {
				this.getFlotte().add(s); // Ajout du Bateau à la flotte
				ajoute = true; // On a ajouté le Bateau

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

			System.out.println("Ship Size: " + s.getSize());
			for (Coordonnee c : s.getTabCoord()) {
				System.out.println(c.getCoordonnee());
			}
			boolean chevauchement = this.verificationChevauchement(s);
			if (!chevauchement) {
				this.getFlotte().add(s); // Ajout du Bateau à la flotte
				ajoute = true; // On a ajouté le Bateau

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

			System.out.println("Ship Size: " + s.getSize());
			for (Coordonnee c : s.getTabCoord()) {
				System.out.println(c.getCoordonnee());
			}
			boolean chevauchement = this.verificationChevauchement(s);
			if (!chevauchement) {
				this.getFlotte().add(s); // Ajout du Bateau à la flotte
				ajoute = true; // On a ajouté le Bateau

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

			System.out.println("Ship Size: " + s.getSize());
			for (Coordonnee c : s.getTabCoord()) {
				System.out.println(c.getCoordonnee());
			}
			boolean chevauchement = this.verificationChevauchement(s);
			if (!chevauchement) {
				this.getFlotte().add(s); // Ajout du Bateau à la flotte
				ajoute = true; // On a ajouté le Bateau

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

			System.out.println("Ship Size: " + s.getSize());
			for (Coordonnee c : s.getTabCoord()) {
				System.out.println(c.getCoordonnee());
			}
			boolean chevauchement = this.verificationChevauchement(s);
			if (!chevauchement) {
				this.getFlotte().add(s); // Ajout du Bateau à la flotte du Robot
				ajoute = true; // On a ajouté le Bateau

			}

		}
	}

	public static boolean isContained(Coordonnee c, Player p) {
		boolean equal = false;

		for (Coordonnee coord : p.getMyCoords()) {
			if (coord.getCoordonnee().equals(c.getCoordonnee())) {
				equal = true;
			}
		}
		return equal;
	}

}

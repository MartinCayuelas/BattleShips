package fr.polytech.cayuelas.battleships.ia;

import fr.polytech.cayuelas.battleships.normal.Player;
import fr.polytech.cayuelas.battleships.normal.Coordonnee;
import fr.polytech.cayuelas.battleships.normal.Ship;

import java.util.ArrayList;
import java.util.Random;

public class IAmedium extends Player implements IA {

	public IAmedium(String nom) {
		super(nom);

		// TODO Auto-generated constructor stub
	}

	@Override
	public void createFleet() {
		int i = 2;
		int nb = 0;
		while (nb < 5) {
			boolean ajoute = false;
			while (!ajoute) {
				Random rdL = new Random();
				int valeurL = 1 + rdL.nextInt(11 - 1);
				Random rdC = new Random();
				int valeurC = 1 + rdC.nextInt(11 - 1);

				String tab[] = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J" };
				String partOne = tab[valeurL - 1];
				String coord = partOne + "" + valeurC;
				Coordonnee start = new Coordonnee(coord);
				System.out.println("Start: " + start.getCoordonnee());
				ArrayList<Coordonnee> tabCoords;
				if (i == 6) {
					tabCoords = start.getPossibilities(i - 3);
				} else {
					tabCoords = start.getPossibilities(i);
				}

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
					i++;
					nb++;
				}

			}
		}

	}

	@Override
	public void shoot(Player monPlayer, Player monAdversaire) {

		Coordonnee c = new Coordonnee();
		boolean tirOk = false;
		while (!tirOk) {

			c = Coordonnee.coordRandom();
			if (monPlayer.isShooted(c.getCoordonnee())) {
				tirOk = false;
				

			} else {
				tirOk = true;
				
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
				monPlayer.getmyCoordsShooted().add(c);
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

		// Analyse du dernier tir
		// Si touché, comparer avec les bateaux restants pour voir où tirer
		// prendre en compte la taille et l'orientation

		// Si non touché, faire un pseudo random en regardant les bateaux restants

	}

}

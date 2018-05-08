package ig.polytech.cayuelas.battleships.main;
import ig.polytech.cayuelas.battleships.normal.Human;
import ig.polytech.cayuelas.battleships.normal.Coordonnee;
import ig.polytech.cayuelas.battleships.normal.Ship;
import ig.polytech.cayuelas.battleships.ia.IAbeginner;
import ig.polytech.cayuelas.battleships.ia.IAmedium;
import ig.polytech.cayuelas.battleships.ia.IAhard;

import java.util.ArrayList;
import java.util.Random;


import java.util.Scanner;

public class MainPlayerIA {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Entrez votre nom (J1)");
		Scanner sc = new Scanner(System.in);
		String name = sc.nextLine();
		Human player1 = new Human(name);

		/********** Init Player *******/
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
						System.out.println("Veuillez saisir une coordonnée de début :");
						Scanner sc3 = new Scanner(System.in);
						start = sc3.nextLine();
						if (start.length() > 1) {
							Coordonnee coord = new Coordonnee(start);
							coordOk = coord.coordCorrect(start);
							System.out.println("Vous avez saisi : " + start);
						}
					} // Fin Coordonnée de Début
					coordOk = false;
					while (!coordOk) {
						System.out.println("Veuillez saisir une coordonnée de fin :");
						Scanner sc4 = new Scanner(System.in);
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
				try {
					Ship s = new Ship(start, end);
					System.out.println("Vous avez créé un bateau de taille : " + s.getSize());
					boolean chevauchement = player1.verificationChevauchement(s);
					boolean okAjout = player1.verificationAjout(s.getSize());

					if (okAjout && !chevauchement) {
						player1.getFlotte().add(s); // Ajout du Bateau à la flotte du joueur
						/********** Partie Incrémentation nombre de Bateaux du Joueur **********/
						player1.incrementeTypeBateau(s.getSize());
						ajoute = true; // On a ajouté le Bateau
						i++;
						/**** Partie Affichage Données du Joueur ******/
						System.out.println("Bateau n° " + i);
						System.out.println(player1.afficheFlotteDetails());
						/**** Partie Affichage "Grille" du Joueur ******/
						for (int z = 0; z < s.getSize(); z++) {
							player1.getMyCoords().add(s.getTabCoord()[z]);
						}
						System.out.println(player1.myCoordString());
						/**************************************************/
					} else {
						if (chevauchement) {
							System.out.println("Le bateau va chevaucher une position déjà occupée");
						} else {
							if (s.getSize() > 5) {
								System.out.println("Size Ship > 5 -- Try Again");
							} else {
								System.out.println("Vous ne pouvez plus ajouetr de bateaux de taille: " + s.getSize());
								System.out.println("Choississez un autre type de bateau --------------");
							}
						}
						System.out.println(player1.afficheFlotteDetails());
						ajoute = false;
					}
				} catch (Exception e) {
					System.out.println("Size Ship == 1 --> Try again");
				}
			}
		}
		System.out.println(player1.getFlotte());

		/**************** Init IA ***********/
		IAhard ia = new IAhard("Gandalf");
		ia.createFleet();
		System.out.println(ia.getFlotte());

		System.out.println("---------Début de la partie-----------");
		System.out.println("Les indications : ");
		System.out.println(" 〰️ -> Eau (possiblement)");
		System.out.println(" ⛴ -> Touché");
		System.out.println(" X -> Raté");
		player1.getMyCoords().clear();
		ia.getMyCoords().clear();

		
		boolean end = false;
		while ((player1.getFlotte().size() != 0) && (ia.getFlotte().size() != 0)) {
			// Tant que un des deux joueurs n'a pas perdu tous ses bateaux la partie
			// continue

			System.out.println("Player  : " + player1.getName());
			System.out.println("Grille : -------------------------------");
			System.out.println(player1.myCoordString());
			boolean tirOk = false;
			String tir1 = "";
			Coordonnee c = null;
			while (!tirOk) {
				Scanner tir = new Scanner(System.in);
				System.out.println("Veuillez saisir une coordonnée de tir ( " + player1.getName() + " ) :");
				tir1 = tir.nextLine();
				if (tir1.length() > 1) {
					c = new Coordonnee(tir1);
					tirOk = c.coordCorrect(tir1);
				}
			}
			System.out.println("missile  -------------" + c.getCoordonnee());
			boolean touche = false;
			boolean destroyed = false;// variable pour supprimer un bateau si jamais il est coulé
			Ship shipDelete = new Ship();// Bateau fictif pour les vérifications +
			// Si jamais aucun bateau n'est touché on peut dire que la position de tir est
			// ratée
			for (Ship s1 : ia.getFlotte()) {
				if (s1.isHit(c.getCoordonnee())) {
					touche = true;
					c.setHit();
					player1.getMyCoords().add(c);
					if (s1.isDestroyed()) {
						shipDelete = s1;
						destroyed = true;
					}
				}
			}
			if (touche) {
				System.out.println("Touché");
			} else {
				player1.getMyCoords().add(c);
				System.out.println("Raté");
			}

			if (destroyed) {
				System.out.println("Coulé");
				ia.getFlotte().remove(shipDelete);
				System.out.println("Il reste " + ia.getFlotte().size() + " bateaux à couler");
				int score = player1.getScore() + 1;
				player1.setScore(score);
			}
			System.out.println("------------Changement de tour--------------");

			
			if ((ia.getFlotte().size() == 0)) {
				end = true;
			}
			if(!end) {
			System.out.println("Player  : " + ia.getName());
			System.out.println("Grille : -------------------------------");
			System.out.println(ia.myCoordString());
			ia.shoot(ia, player1);
			}
		}
		System.out.println("------------Partie Terminée-----------");
		if ((player1.getFlotte().size() == 0)) {
			System.out.println("Gagnant : " + ia.getName());
		} else {
			System.out.println("Gagnant : " + player1.getName());
		}
		System.out.println("Score de " + player1.getName() + " : " + player1.getScore());
		System.out.println("Score de " + ia.getName() + " : " + ia.getScore());
	}
}

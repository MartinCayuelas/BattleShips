package cayuelas.martin;



import java.util.ArrayList;
import java.util.Random;

import java.util.Scanner;

public class MainPlayer {

	public static void main(String[] args) {

		System.out.println("Entrez votre nom (J1)");
		Scanner sc = new Scanner(System.in);
		String name = sc.nextLine();
		Human player1 = new Human(name);

		System.out.println("Entrez votre nom (J2)");
		Scanner sc2 = new Scanner(System.in);
		String name2 = sc2.nextLine();
		Human player2 = new Human(name2);

		System.out.println("Nom J1 : " + player1.getName());
		System.out.println("Nom J2 : " + player2.getName());
		boolean nouvellePartie = true;
		int scoreCumuleJ1 = 0;
		int scoreCumuleJ2 = 0;
		Human monPlayer = player1;
		Human monAdversaire = player2;
		while (nouvellePartie) {
			System.out.println("---------------------------Initialisation---------------------");
			Human p = monPlayer;
			for (int t = 1; t < 3; t++) {
				System.out.println("--J" + t + "--" + p.getName());
				int i = 0;
				while (i < 1) {
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
								if (start.length() > 1 && start.length() <= 3) {
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
								if (end.length() > 1 && end.length() <= 3) {
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
							boolean chevauchement = p.verificationChevauchement(s);
							boolean okAjout = p.verificationAjout(s.getSize());

							if (okAjout && !chevauchement && s.getSize() < 6) {
								p.getFlotte().add(s); // Ajout du Bateau à la flotte du joueur
								/********** Partie Incrémentation nombre de Bateaux du Joueur **********/
								p.incrementeTypeBateau(s.getSize());
								ajoute = true; // On a ajouté le Bateau
								i++;
								/**** Partie Affichage Données du Joueur ******/
								System.out.println("Bateau n° " + i);
								System.out.println(p.afficheFlotteDetails());
								/**** Partie Affichage "Grille" du Joueur ******/
								for (int z = 0; z < s.getSize(); z++) {
									p.getmyCoordsShooted().add(s.getTabCoord()[z]);
								}
								System.out.println(p.myCoordsShootedString());
								/**************************************************/
							} else {
								if (chevauchement) {
									System.out.println("Le bateau va chevaucher une position déjà occupée");
								} else {

									System.out.println(
											"Vous ne pouvez plus ajouter de bateaux de taille: " + s.getSize());
									System.out.println("Choississez un autre type de bateau --------------");

								}
								System.out.println(p.afficheFlotteDetails());
								ajoute = false;
							}
						} catch (Exception e) {
							System.out.println("Size Ship == 1 --> Try again");
						}
					}
				}
				System.out.println(p.getFlotte());
				p = monAdversaire;
			}
			System.out.println("---------Début de la partie-----------");
			System.out.println("Les indications : ");
			System.out.println(" 〰️ -> Eau (possiblement)");
			System.out.println(" ⛴ -> Touché");
			System.out.println(" X -> Raté");
			player1.getmyCoordsShooted().clear();
			player2.getmyCoordsShooted().clear();
			

			while ((monPlayer.getFlotte().size() != 0) && (monAdversaire.getFlotte().size() != 0)) {
				// Tant que un des deux joueurs n'a pas perdu tous ses bateaux la partie
				// continue

				System.out.println("Player  : " + monPlayer.getName());
				System.out.println("Grilles : -------------------------------");
				System.out.println("Mes tirs: ");
				System.out.println(monPlayer.myCoordsShootedString());
				System.out.println("Ma grille: (Positions que mon adversaire touche )");
				System.out.println(monAdversaire.myCoordsShootedString());
				boolean tirOk = false;
				String tir1 = "";
				Coordonnee c = null;
				while (!tirOk) {
					Scanner tir = new Scanner(System.in);
					System.out.println("Veuillez saisir une coordonnée de tir ( " + monPlayer.getName() + " ) :");
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
				System.out.println("------------Changement de tour--------------");
				// changement de joueur
				Human tmp = monPlayer;
				monPlayer = monAdversaire;
				monAdversaire = tmp;

			}

			System.out.println("------------Partie Terminée-----------");
			if (player2.getFlotte().size() == 0) {
				System.out.println("Gagnant : " + player1.getName());
				scoreCumuleJ1 += 1;
			} else {
				System.out.println("Gagnant : " + player2.getName());
				scoreCumuleJ2 += 1;
			}
			System.out.println("Score de " + player1.getName() + " : " + player1.getScore());
			System.out.println("Score de " + player2.getName() + " : " + player2.getScore());

			System.out.println("Relancer une nouvelle partie? Tapez 1 (oui), Tapez 2 (non)");
			Scanner scPartie = new Scanner(System.in);
			int newPartie = scPartie.nextInt();

			if (newPartie == 1) {
				player1.resetPlayer();
				player2.resetPlayer();

				nouvellePartie = true;
			} else {
				nouvellePartie = false;
			}
		}

		System.out.println("Score total de " + player1.getName() + " : " + scoreCumuleJ1 + " parties gagnées");
		System.out.println("Score total de " + player2.getName() + " : " + scoreCumuleJ2 + " parties gagnées");

	}

}

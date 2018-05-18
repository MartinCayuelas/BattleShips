package cayuelas.martin;

import java.util.Scanner;

public class Battleship {

	public static void main(String[] args) {
		System.out
				.println("Choix de Mode de Jeu? 1 - Human vs Human, 2 - Human vs AI");
		int choix = 1;
		boolean choixOk = false;
		while (!choixOk) {
			Scanner sc = new Scanner(System.in);
			try {
				choix = sc.nextInt();
				if (choix >= 1 && choix <= 2) {
					choixOk = true;
				}else{
					System.out.println("Il faut un entier compris entre 1 et 2");
				}
			} catch (Exception e) {
				System.out.println("Mauvais Format");
			}
		}
		if (choix == 1) {
			System.out.println("Vous avez choisi le mode 1 vs 1");
			
			//init names
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
					initPlayer(p);
					
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
					shootProcessPlayer(monPlayer, monAdversaire);
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
		} else if (choix == 2) {
			System.out.println("Vous avez choisi le mode Human vs IA");
			
			System.out.println("Entrez votre nom (J1)");
			Scanner sc = new Scanner(System.in);
			String name = sc.nextLine();
			Human player1 = new Human(name);

			System.out.println("Entrez le niveau de l'IA ( 1 = beginner, 2 = medium, 3 = hard)");
			Scanner scAi = new Scanner(System.in);
			int choixIa = scAi.nextInt();
			/**************** Init IA ***********/

			Iia ia;
			if (choixIa == 1) {
				ia = new IAbeginner("Frodon");
				System.out.println("Je suis beginner");
			} else if (choixIa == 2) {
				ia = new IAmedium("Aragorn");
				System.out.println("Je suis medium");
			} else {
				ia = new IAhard("Gandalf");
				System.out.println("Je suis hard");
			}
			boolean nouvellePartie = true;
			int scoreCumuleJ1 = 0;
			int scoreCumuleIA = 0;

			boolean tour = true;
			while (nouvellePartie) {
				ia.createFleet();
				System.out.println(ia.toString());

				/********** Init Player *******/
				initPlayer(player1);
				System.out.println(player1.toString());

				System.out.println("---------Début de la partie-----------");
				System.out.println("Les indications : ");
				System.out.println(" 〰️ -> Eau (possiblement)");
				System.out.println(" ⛴ -> Touché");
				System.out.println(" X -> Raté");
				player1.getmyCoordsShooted().clear();
				ia.getmyCoordsShooted().clear();

				boolean end = false;
				while ((player1.getFlotte().size() != 0) && (ia.getFlotte().size() != 0)) {
					// Tant que un des deux joueurs n'a pas perdu tous ses bateaux la partie
					// continue
					if (tour) {
						shootProcessPlayer(player1, ia);

						if ((ia.getFlotte().size() == 0)) {
							end = true;
						}
						if (!end) {
							System.out.println("Player  : " + ia.getName());
							System.out.println("Grille : -------------------------------");
							System.out.println(ia.myCoordsShootedString());
							ia.shoot(ia, player1);
						}
					} else {
						System.out.println("Player  : " + ia.getName());
						System.out.println("Grille : -------------------------------");
						System.out.println(ia.myCoordsShootedString());
						ia.shoot(ia, player1);
						System.out.println("------------Changement de tour--------------");
						if ((player1.getFlotte().size() == 0)) {
							end = true;
						}
						if (!end) {
							shootProcessPlayer(player1, ia);
						}
					}
				}
				System.out.println("------------Partie Terminée-----------");
				if ((player1.getFlotte().size() == 0)) {
					System.out.println("Gagnant : " + ia.getName());
					scoreCumuleIA += 1;
				} else {
					System.out.println("Gagnant : " + player1.getName());
					scoreCumuleJ1 += 1;
				}
				System.out.println("Score de " + player1.getName() + " : " + player1.getScore());
				System.out.println("Score de " + ia.getName() + " : " + ia.getScore());

				System.out.println("Relancer une nouvelle partie? Tapez 1 (oui), Tapez 2 (non)");
				Scanner scPartie = new Scanner(System.in);
				int newPartie = scPartie.nextInt();

				if (newPartie == 1) {
					player1.resetPlayer();
					ia.resetPlayer();
					nouvellePartie = true;
					if (tour) {
						tour = false;
					} else {
						tour = true;
					}
				} else {
					nouvellePartie = false;

				}
			} // END While neouvellePartie
			System.out.println("Score total de " + player1.getName() + " : " + scoreCumuleJ1 + " parties gagnées");
			System.out.println("Score total de l'IA " + ia.getName() + " : " + scoreCumuleIA + " parties gagnées");

		} 
	}
	
	
	
	
	public static void shootProcessPlayer(Iplayer player1, Iplayer ia) {
		System.out.println("Player  : " + player1.getName());
		System.out.println("Grille : -------------------------------");
		System.out.println("Mes tirs: ");
		System.out.println(player1.myCoordsShootedString());
		System.out.println("Ma grille: (Positions que mon adversaire touche )");
		System.out.println(ia.myCoordsShootedString());
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
				player1.getmyCoordsShooted().add(c);
				if (s1.isDestroyed()) {
					shipDelete = s1;
					destroyed = true;
				}
			}
		}
		if (touche) {
			System.out.println("Touché");
		} else {
			player1.getmyCoordsShooted().add(c);
			System.out.println("Raté");
		}

		if (destroyed) {
			System.out.println("Coulé");
			ia.getFlotte().remove(shipDelete);
			System.out.println("Il reste " + ia.getFlotte().size() + " bateaux à couler");
			int score = player1.getScore() + 1;
			player1.setScore(score);
		}
		
	}
	
	public static void initPlayer(Human player1){
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
					boolean chevauchement = player1.verificationChevauchement(s);
					boolean okAjout = player1.verificationAjout(s.getSize());

					if (okAjout && !chevauchement && s.getSize() < 6) {
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
							player1.getmyCoordsShooted().add(s.getTabCoord()[z]);
						}
						System.out.println(player1.myCoordsShootedString());
						/**************************************************/
					} else {
						if (chevauchement) {
							System.out.println("Le bateau va chevaucher une position déjà occupée");
						} else {
							System.out.println("Vous ne pouvez pas ajouter de bateaux de taille: " + s.getSize());
							System.out.println("Choississez un autre type de bateau --------------");

						}
						System.out.println(player1.afficheFlotteDetails());
						ajoute = false;
					}
				} catch (Exception e) {
					System.out.println("Size Ship == 1 --> Try again");
				}
			}
		}
		
	}
	
	

	

}

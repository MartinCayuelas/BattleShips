import java.util.Scanner;

public class MainPlayer {

	public static void main(String[] args) {

		boolean lgOk = false;
		boolean lrgOk = false;
		int longueur = 10;
		int largeur = 10;

		Grille grilleVerif = new Grille();
		while (!lgOk) {
			System.out.println("Entrez une longueur de matrice");
			Scanner lg = new Scanner(System.in);

			try {
				longueur = lg.nextInt();
				boolean autorise = grilleVerif.verifInt(longueur);
				if (autorise) {
					lgOk = true;
				}

			} catch (Exception e) {
				System.out.println("Entrez un chiffre");
			}
		}
		while (!lrgOk) {
			System.out.println("Entrez une largeur de matrice");
			Scanner lrg = new Scanner(System.in);

			try {
				largeur = lrg.nextInt();
				boolean autorise = grilleVerif.verifInt(largeur);
				if (autorise) {
					lrgOk = true;
				}

			} catch (Exception e) {
				System.out.println("Entrez un chiffre");
			}
		}

		System.out.println("Entrez votre nom (J1)");
		Scanner sc = new Scanner(System.in);
		String name = sc.nextLine();

		Player player1 = new Player(name);

		System.out.println("Entrez votre nom (J2)");
		Scanner sc2 = new Scanner(System.in);
		String name2 = sc2.nextLine();

		Player player2 = new Player(name2);

		System.out.println("Nom J1 : " + player1.getName());
		System.out.println("Nom J2 : " + player2.getName());
		player1.getMaGrille().restartGrille(longueur, largeur);
		player2.getMaGrille().restartGrille(longueur, largeur);

		System.out.println("---------------------------Initialisation---------------------");
		System.out.println("--J1--" + player1.getName());
		int i = 0;
		while (i < 5) {
			boolean ajoute = false;
			while (!ajoute) {
				String start = "";
				String end = "";
				boolean diagonal = true; // Bonne forme de coordonnée, pas en diagonale
				Ship ship = new Ship("A1", "A2"); // Bateau fictif pour tester
				while (diagonal) { // Tant que le bateau est en diagonale on loop
					boolean coordOk = false;
					while (!coordOk) {
						Scanner sc3 = new Scanner(System.in);
						System.out.println("Veuillez saisir une coordonnée de début :");
						start = sc3.nextLine();
						if (start.length() > 1) {
							coordOk = grilleVerif.coordCorrect(start);
							System.out.println("Vous avez saisi : " + start);
						}

					} // Fin Coordonnée de Début
					coordOk = false;
					while (!coordOk) {
						Scanner sc4 = new Scanner(System.in);
						System.out.println("Veuillez saisir une coordonnée de fin :");
						end = sc4.nextLine();
						if (end.length() > 1) {
							coordOk = grilleVerif.coordCorrect(end);
							System.out.println("Vous avez saisi : " + end);
						}
					} // Fin Coordonnée de Fin
					diagonal = ship.isDiagonal(start, end);
					if (diagonal) {
						System.out.println("Bateau en diagonale!");
					}
				}

				Ship s = new Ship(start, end);

				System.out.println("Vous avez crée un bateau de taille : " + s.getSize());

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
				System.out.println("Vous avez choisi :" + s.getName());
				boolean chevauchement = player1.verificationChevauchement(s);

				boolean okAjout = player1.verificationAjout(s.getName());

				if (okAjout && !chevauchement) {
					player1.getFlote().add(s); // Ajout du Bateau à la flotte du joueur

					/********** Partie Incrémentation nombre de Bateaux du Joueur **********/
					if (s.getSize() == 3) {
						int size;
						if (s.getName().equals("Submarine")) {
							size = 1;
						} else {
							size = 2;
						}
						player1.incrementeTypeBateauSize3(size);

					} else {
						player1.incrementeTypeBateau(s.getSize());
					}

					ajoute = true; // On a ajouté le Bateau
					i++;

					/**** Partie Affichage Données du Joueur ******/
					System.out.println("Bateau n° " + i);

					System.out.println("Carriers (Taille 5): " + player1.getNbCarrier());
					System.out.println("Cruisers (Taille 3): " + player1.getNbCruiser());
					System.out.println("Battleships (Taille 4): " + player1.getNbBattleship());
					System.out.println("Destroyers (Taille 2): " + player1.getNbDestroyer());
					System.out.println("Submarines (Taille 3): " + player1.getNbSubmarine());

					/**** Partie Affichage Grille du Joueur ******/
					for (int z = 0; z < s.getSize(); z++) {

						int a = s.coordLeft(s.getTabCoord()[z]);
						int b = s.coordRight(s.getTabCoord()[z]);

						player1.getMaGrille().setGrille(a, b);

					}

					System.out.println("A" + "" + "B" + "" + "C" + "" + "D" + "" + "E" + "" + "F" + "" + "G" + "" + "H"
							+ "" + "I" + "" + "J");
					System.out.println(player1.getMaGrille().toString());
				} else {
					if (chevauchement) {
						System.out.println("Le bateau va chevaucher une position déjà occupée");
					} else {
						System.out.println("Vous avez atteint le nombre limite de " + s.getName() + "s");
						System.out.println("Choississez un autre type de bateau --------------");
					}
					System.out.println("Carriers (Taille 5): " + player1.getNbCarrier());
					System.out.println("Cruisers (Taille 3): " + player1.getNbCruiser());
					System.out.println("Battleships (Taille 4): " + player1.getNbBattleship());
					System.out.println("Destroyers (Taille 2): " + player1.getNbDestroyer());
					System.out.println("Submarines (Taille 3): " + player1.getNbSubmarine());
					ajoute = false;
				}

			}
		}
		System.out.println(player1.getFlote());
		System.out.println("--J2--" + player2.getName());
		int j = 0;
		while (j < 5) {
			boolean ajoute = false;
			while (!ajoute) {
				String start = "";
				String end = "";

				boolean diagonal = true; // Bonne forme de coordonnée, pas en diagonale
				Ship ship = new Ship("A1", "A2");

				while (diagonal) { // Tant que le bateau est en diagonale on loop
					boolean coordOk = false;
					while (!coordOk) {
						Scanner sc3 = new Scanner(System.in);
						System.out.println("Veuillez saisir une coordonnée de début :");
						start = sc3.nextLine();
						if (start.length() > 1) {
							coordOk = grilleVerif.coordCorrect(start);
							System.out.println("Vous avez saisi : " + start);
						}

					} // Fin Coordonnée de Début
					coordOk = false;
					while (!coordOk) {
						Scanner sc4 = new Scanner(System.in);
						System.out.println("Veuillez saisir une coordonnée de fin :");
						end = sc4.nextLine();
						if (end.length() > 1) {
							coordOk = grilleVerif.coordCorrect(end);
							System.out.println("Vous avez saisi : " + end);
						}
					} // Fin Coordonnée de Fin

					diagonal = ship.isDiagonal(start, end);
					if (diagonal) {
						System.out.println("Bateau en diagonale!");
					}
				}

				Ship s = new Ship(start, end);
				System.out.println("Vous avez crée un bateau de taille : " + s.getSize());

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
				System.out.println("Vous avez choisi :" + s.getName());
				boolean okAjout = player2.verificationAjout(s.getName());
				boolean chevauchement = player2.verificationChevauchement(s);

				if (okAjout && !chevauchement) {
					player2.getFlote().add(s); // Ajout du Bateau à la flotte du joueur

					/********** Partie Incrémentation nombre de Bateaux du Joueur **********/
					if (s.getSize() == 3) {
						int size;
						if (s.getName().equals("Submarine")) {
							size = 1;
						} else {
							size = 2;
						}
						player2.incrementeTypeBateauSize3(size);

					} else {
						player2.incrementeTypeBateau(s.getSize());
					}

					ajoute = true; // On a ajouté le Bateau
					j++;

					/**** Partie Affichage Données du Joueur ******/
					System.out.println("Bateau n° " + j);

					System.out.println("Carriers (Taille 5): " + player2.getNbCarrier());
					System.out.println("Cruisers (Taille 3): " + player2.getNbCruiser());
					System.out.println("Battleships (Taille 4): " + player2.getNbBattleship());
					System.out.println("Destroyers (Taille 2): " + player2.getNbDestroyer());
					System.out.println("Submarines (Taille 3): " + player2.getNbSubmarine());

					/**** Partie Affichage Grille du Joueur ******/
					for (int z = 0; z < s.getSize(); z++) {

						int a = s.coordLeft(s.getTabCoord()[z]);
						int b = s.coordRight(s.getTabCoord()[z]);

						player2.getMaGrille().setGrille(a, b);

					}

					System.out.println("A" + "" + "B" + "" + "C" + "" + "D" + "" + "E" + "" + "F" + "" + "G" + "" + "H"
							+ "" + "I" + "" + "J");
					System.out.println(player2.getMaGrille().toString());
				} else {
					if (chevauchement) {
						System.out.println("Le bateau va chevaucher une position déjà occupée");
					} else {
						System.out.println("Vous avez atteint le nombre limite de " + s.getName() + "s");
						System.out.println("Choississez un autre type de bateau --------------");
					}
					System.out.println("Carriers (Taille 5): " + player2.getNbCarrier());
					System.out.println("Cruisers (Taille 3): " + player2.getNbCruiser());
					System.out.println("Battleships (Taille 4): " + player2.getNbBattleship());
					System.out.println("Destroyers (Taille 2): " + player2.getNbDestroyer());
					System.out.println("Submarines (Taille 3): " + player2.getNbSubmarine());
					ajoute = false;
				}

			}

		}

		System.out.println(player2.getFlote());

		System.out.println("---------Début de la partie-----------");

		player1.getMaGrille().restartGrille(longueur, largeur);// remise à zero de la grille
		player2.getMaGrille().restartGrille(longueur, largeur);

		boolean tour = true;
		while ((player1.getFlote().size() != 0) && (player2.getFlote().size() != 0)) {
			// Tant que un des deux joueurs n'a pas perdu tous ses bateaux la partie
			// continue

			if (tour == true) {
				// Tour du Joueur 1

				System.out.println("Player (J1) : " + player1.getName());
				System.out.println("Grille : -------------------------------");
				// Grille pour voir là où l'on a tiré et les bateaux que l'on a touché/coulé
				System.out.println("A" + "" + "B" + "" + "C" + "" + "D" + "" + "E" + "" + "F" + "" + "G" + "" + "H" + ""
						+ "I" + "" + "J");
				System.out.println(player1.getMaGrille().toString());

				boolean tirOk = false;
				String tir1 = "";
				while (!tirOk) {
					Scanner tir = new Scanner(System.in);
					System.out.println("Veuillez saisir une coordonnée de tir (J1) :");
					tir1 = tir.nextLine();
					tirOk = grilleVerif.coordCorrect(tir1);
				}

				System.out.println("missile  -------------" + tir1);
				boolean touche = false;
				boolean destroyed = false;
				Ship shipDelete = null;
				for (Ship s1 : player2.getFlote()) {

					if (s1.isHit(tir1)) {
						touche = true;
						// System.out.println("Touché");
						int a = s1.coordLeft(tir1);
						int b = s1.coordRight(tir1);
						player1.getMaGrille().setGrille(a, b);
						if (s1.isDestroyed()) {
							// System.out.println("Coulé");
							shipDelete = s1;
							destroyed = true;
							// player2.getFlote().remove(s1);
							// System.out.println("Il reste " + player2.getFlote().size() + " bateaux à
							// couler");
							// int score = player1.getScore() + 1;
							// player1.setScore(score);
							// System.out.println("Porbleme ici");

						} else {
							System.out.println("Pas coulé");
						}
					}
				}
				if (touche) {
					System.out.println("Touché");
				} else {
					System.out.println("Raté");
				}
				if (destroyed) {
					System.out.println("Coulé");
					player2.getFlote().remove(shipDelete);
					System.out.println("Il reste " + player2.getFlote().size() + " bateaux à couler");
					int score = player1.getScore() + 1;
					player1.setScore(score);

				}

				System.out.println("------------Changement de tour--------------");
				tour = false; // changement de joueur
			} else {
				// Tour du Joueur 2
				System.out.println("Player (J2) : " + player2.getName());
				System.out.println("Grille : -------------------------------");
				System.out.println("A" + "" + "B" + "" + "C" + "" + "D" + "" + "E" + "" + "F" + "" + "G" + "" + "H" + ""
						+ "I" + "" + "J");
				System.out.println(player2.getMaGrille().toString());

				boolean tirOk = false;
				String tir1 = "";
				while (!tirOk) {
					Scanner tir = new Scanner(System.in);
					System.out.println("Veuillez saisir une coordonnée de tir (J2) :");
					tir1 = tir.nextLine();
					tirOk = grilleVerif.coordCorrect(tir1);
				}
				System.out.println("missile -------------" + tir1);
				boolean touche = false;
				Ship shipDelete = null;

				boolean destroyed = false;
				for (Ship s1 : player1.getFlote()) {

					if (s1.isHit(tir1)) {
						touche = true;
						// System.out.println("Touché");
						int a = s1.coordLeft(tir1);
						int b = s1.coordRight(tir1);
						player2.getMaGrille().setGrille(a, b);
						if (s1.isDestroyed()) {
							// System.out.println("Coulé");
							shipDelete = s1;
							destroyed = true;
							// player1.getFlote().remove(s1);
							// System.out.println("Il reste " + player1.getFlote().size() + " bateaux à
							// couler");
							// int score = player2.getScore() + 1;
							// player2.setScore(score);

						} else {
							System.out.println("Pas coulé");
						}

					}
				}
				if (touche) {
					System.out.println("Touché");
				} else {
					System.out.println("Raté");
				}
				if (destroyed) {
					System.out.println("Coulé");
					player1.getFlote().remove(shipDelete);
					System.out.println("Il reste " + player1.getFlote().size() + " bateaux à couler");
					int score = player2.getScore() + 1;
					player2.setScore(score);
				}

				System.out.println("------------Changement de tour--------------");
				tour = true; // changement de joueur
			}

		}

		System.out.println("------------Partie Terminée-----------");
		if (!tour) {
			System.out.println("Gagnant : " + player1.getName());
		} else {
			System.out.println("Gagnant : " + player2.getName());
		}

	}

}

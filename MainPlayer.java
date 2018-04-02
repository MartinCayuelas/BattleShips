import java.util.Scanner;

public class MainPlayer {

	public static void main(String[] args) {

		System.out.println("Entrez votre nom (J1)");
		Scanner sc = new Scanner(System.in);
		String name = sc.nextLine();

		Player player1 = new Player(name);

		System.out.println("Entrez votre nom (J2)");
		Scanner sc2 = new Scanner(System.in);
		String name2 = sc.nextLine();

		Player player2 = new Player(name2);

		System.out.println("Nom J1 : " + player1.getName());
		System.out.println("Nom J2 : " + player2.getName());

		System.out.println("---------------------------Initialisation---------------------");
		System.out.println("--J1--" + player1.getName());
		int i = 0;
		while (i < 5) {
			Scanner sc3 = new Scanner(System.in);
			System.out.println("Veuillez saisir une coordonnée de début :");
			String start = sc3.nextLine();
			System.out.println("Vous avez saisi : " + start);

			Scanner sc4 = new Scanner(System.in);
			System.out.println("Veuillez saisir une coordonnée de fin :");
			String end = sc4.nextLine();
			System.out.println("Vous avez saisi : " + end);

			Ship s = new Ship(start, end);
			System.out.println("Vous avez crée un bateau de taille : " + s.getSize());
			player1.getFlote().add(s);
			i++;
			System.out.println("Bateau n° " + i);

			for (int z = 0; z < s.getSize(); z++) {

				int a = s.coordLeft(s.getTabCoord()[z]);
				int b = s.coordRight(s.getTabCoord()[z]);
				
				player1.getMaGrille().setGrille(a, b);


			}

			System.out.println("A" + "" + "B" + "" + "C" + "" + "D" + "" + "E" + "" + "F" + "" + "G" + "" + "H" + ""
					+ "I" + "" + "J");
			System.out.println(player1.getMaGrille().toString());
		}

		System.out.println(player1.getFlote());
		System.out.println("--J2--" + player2.getName());
		int j = 0;
		while (j < 5) {
			Scanner sc3 = new Scanner(System.in);
			System.out.println("Veuillez saisir une coordonnée de début :");
			String start = sc3.nextLine();
			System.out.println("Vous avez saisi : " + start);

			Scanner sc4 = new Scanner(System.in);
			System.out.println("Veuillez saisir une coordonnée de fin :");
			String end = sc4.nextLine();
			System.out.println("Vous avez saisi : " + end);

			Ship s = new Ship(start, end);
			System.out.println("Vous avez crée un bateau de taille : " + s.getSize());
			player2.getFlote().add(s);
			j++;
			System.out.println("Bateau n° " + j);

			for (int z = 0; z < s.getSize(); z++) {

				int a = s.coordLeft(s.getTabCoord()[z]);
				int b = s.coordRight(s.getTabCoord()[z]);
				player2.getMaGrille().setGrille(a, b);

				System.out.println("1 ajouté!");

			}

			System.out.println("A" + "" + "B" + "" + "C" + "" + "D" + "" + "E" + "" + "F" + "" + "G" + "" + "H" + ""
					+ "I" + "" + "J");
			System.out.println(player2.getMaGrille().toString());

		}

		System.out.println(player2.getFlote());

		System.out.println("---------Début de la partie-----------");

		player1.getMaGrille().restartGrille();// remise à zero de la grille
		player2.getMaGrille().restartGrille();

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

				Scanner tir = new Scanner(System.in);
				System.out.println("Veuillez saisir une coordonnée de tir (J1) :");
				String tir1 = tir.nextLine();

				System.out.println("missile  -------------" + tir1);
				for (Ship s1 : player2.getFlote()) {

					if (s1.isHit(tir1)) {
						System.out.println("Touché");
						int a = s1.coordLeft(tir1);
						int b = s1.coordRight(tir1);
						player1.getMaGrille().setGrille(a, b);
						if (s1.isDestroyed()) {
							System.out.println("Coulé");
							player2.getFlote().remove(s1);
							System.out.println(player2.getFlote().size());
							int score = player1.getScore() + 1;
							player1.setScore(score);

						} else {
							System.out.println("Pas coulé");
						}
						break;
					} else {
						System.out.println("Raté");
					}
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

				Scanner tir = new Scanner(System.in);
				System.out.println("Veuillez saisir une coordonnée de tir (J2):");
				String tir1 = tir.nextLine();

				System.out.println("missile -------------" + tir1);
				for (Ship s1 : player1.getFlote()) {

					if (s1.isHit(tir1)) {
						System.out.println("Touché");
						int a = s1.coordLeft(tir1);
						int b = s1.coordRight(tir1);
						player2.getMaGrille().setGrille(a, b);
						if (s1.isDestroyed()) {
							System.out.println("Coulé");
							player1.getFlote().remove(s1);
							System.out.println(player1.getFlote().size());
							int score = player2.getScore() + 1;
							player2.setScore(score);

						} else {
							System.out.println("Pas coulé");
						}
						break;
					} else {
						System.out.println("Raté");
					}
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

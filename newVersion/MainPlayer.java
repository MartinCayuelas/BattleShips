import java.util.Scanner;

public class MainPlayer {

	public static void main(String[] args) {

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

		System.out.println("---------------------------Initialisation---------------------");
		System.out.println("--J1--" + player1.getName());
		player1.creationFlote();
		System.out.println(player1.getFlote());
		System.out.println("--J2--" + player2.getName());
		player2.creationFlote();
		System.out.println(player2.getFlote());

		System.out.println("---------Début de la partie-----------");

		System.out.println("Les indications : ");
		System.out.println(" 〰️ -> Eau (possiblement)");
		System.out.println(" ⛴ -> Touché");
		System.out.println(" X -> Raté");

		player1.getMaGrille().restartGrille(10, 10);// remise à zero de la grille
		player2.getMaGrille().restartGrille(10, 10);// Pour "contenir" les positions tirées

		boolean tour = true;
		Player monPlayer;
		Player monAdversaire;
		while ((player1.getFlote().size() != 0) && (player2.getFlote().size() != 0)) {
			// Tant que un des deux joueurs n'a pas perdu tous ses bateaux la partie
			// continue
			if (tour == true) {
				monPlayer = player1;
				monAdversaire = player2;
			} else {
				monPlayer = player2;
				monAdversaire = player1;
			}
			// Tour du Joueur 1
			System.out.println("Player  : " + monPlayer.getName());
			System.out.println("Grille : -------------------------------");
			// Grille pour voir là où l'on a tiré et les bateaux que l'on a touché/coulé
			System.out.println(monPlayer.getMaGrille().toString());
			boolean tirOk = false;
			String tir1 = "";
			while (!tirOk) {
				Scanner tir = new Scanner(System.in);
				System.out.println("Veuillez saisir une coordonnée de tir ( " + monPlayer.getName() + " ) :");
				tir1 = tir.nextLine();
				tirOk = monPlayer.getMaGrille().coordCorrect(tir1);
			}
			System.out.println("missile  -------------" + tir1);
			boolean touche = false;
			boolean destroyed = false;// variable pour supprimer un bateau si jamais il est coulé
			Ship shipDelete = new Ship();// Bateau fictif pour les vérifications +
			int a = shipDelete.coordLeft(tir1);
			int b = shipDelete.coordRight(tir1);// On enregistre les coordonnées du tir
			// Si jamais aucun bateau n'est touché on peut dire que la position de tir est
			// ratée
			for (Ship s1 : monAdversaire.getFlote()) {
				if (s1.isHit(tir1)) {
					touche = true;
					monPlayer.getMaGrille().setGrille(a, b, "⛴");
					if (s1.isDestroyed()) {
						shipDelete = s1;
						destroyed = true;
					} else {
						System.out.println("Pas coulé");
					}
				}
			}
			if (touche) {
				System.out.println("Touché");
			} else {
				System.out.println("Raté");
				monPlayer.getMaGrille().setGrille(a, b, "X");
			}
			if (destroyed) {
				System.out.println("Coulé");
				monAdversaire.getFlote().remove(shipDelete);
				System.out.println("Il reste " + monAdversaire.getFlote().size() + " bateaux à couler");
				int score = monPlayer.getScore() + 1;
				monPlayer.setScore(score);
			}
			System.out.println("------------Changement de tour--------------");
			if (tour == true) {
				tour = false; // changement de joueur
			} else {
				tour = true; // changement de joueur
			}
		}
		System.out.println("------------Partie Terminée-----------");
		if (!tour) {
			System.out.println("Gagnant : " + player1.getName());
		} else {
			System.out.println("Gagnant : " + player2.getName());
		}
		System.out.println("Score de " + player1.getName() + " : " + player1.getScore());
		System.out.println("Score de " + player2.getName() + " : " + player2.getScore());
	}
}

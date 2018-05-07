
		System.out.println("--J2--" + player2.getName());
		player2.creationFlotte();
		System.out.println(player2.getFlotte());

		System.out.println("---------Début de la partie-----------");

		System.out.println("Les indications : ");
		System.out.println(" 〰️ -> Eau (possiblement)");
		System.out.println(" ⛴ -> Touché");
		System.out.println(" X -> Raté");

		player1.getMyCoords().clear();
		player2.getMyCoords().clear();
		boolean tour = true;
		Player monPlayer;
		Player monAdversaire;
		while ((player1.getFlotte().size() != 0) && (player2.getFlotte().size() != 0)) {
			// Tant que un des deux joueurs n'a pas perdu tous ses bateaux la partie
			// continue
			if (tour == true) {
				monPlayer = player1;
				monAdversaire = player2;
			} else {
				monPlayer = player2;
				monAdversaire = player1;
			}
			System.out.println("Player  : " + monPlayer.getName());
			System.out.println("Grille : -------------------------------");
			System.out.println(monPlayer.myCoordString()); //List of Shoots = Display kind of Grid
			monPlayer.shootProcess(monPlayer, monAdversaire);
			System.out.println("-------------------------------");
			System.out.println();
			if (tour == true) {
				tour = false;
			} else {
				tour = true;
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

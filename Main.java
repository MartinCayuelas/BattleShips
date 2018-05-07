import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		System.out.println("Entrez votre nom (J1)");
		Scanner sc = new Scanner(System.in);
		String name = sc.nextLine();
		Player player1 = new Player(name);
		System.out.println("Entrez votre nom (J2)");
		Scanner sc2 = new Scanner(System.in);
		String name2 = sc2.nextLine();
		Player  player2 = new Player(name2);

		System.out.println("Nom J1 : " + player1.getName());
		System.out.println("Nom J2 : " + player2.getName());

		System.out.println("---------------------------Initialisation---------------------");
		//System.out.println("--Joueur--" + player1.getName());
	//	player1.creationFlotte();
		
		Player p = player1;
		int nb = 0;
		while(nb < 2){
			System.out.println("--Joueur--" + p.getName());
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
				}try {
					Ship s = new Ship(start, end);

					System.out.println("Vous avez créé un bateau de taille : " + s.getSize());

					if (s.getSize() == 3) {
						s.nameShip3();
					}
					System.out.println("Vous avez choisi : " + s.getName());
					boolean chevauchement = p.verificationChevauchement(s);
					boolean okAjout = p.verificationAjout(s.getName());

					if (okAjout && !chevauchement) {
						p.getFlotte().add(s); // Ajout du Bateau à la flotte du joueur

						/********** Partie Incrémentation nombre de Bateaux du Joueur **********/
						if (s.getSize() == 3) {
							int size;
							if (s.getName().equals("Submarine")) {
								size = 1;
							} else {
								size = 2;
							}
							p.incrementeTypeBateauSize3(size);

						} else {
							p.incrementeTypeBateau(s.getSize());
						}
						ajoute = true; // On a ajouté le Bateau
						i++;
						/**** Partie Affichage Données du Joueur ******/
						System.out.println("Bateau n° " + i);
						/**** Partie Affichage "Grille" du Joueur ******/
						for (int z = 0; z < s.getSize(); z++) {
							p.getMyCoords().add(s.getTabCoord()[z]);
						}
						System.out.println(this.myCoordString());
						/**************************************************/
					}else {
						if (chevauchement) {
							System.out.println("Le bateau va chevaucher une position déjà occupée");
						} else {
							if (s.getSize() > 5) {
								System.out.println("Size Ship > 5 -- Try Again");
							} else {
								System.out.println("Vous avez atteint le nombre limite de " + s.getName() + "s");
								System.out.println("Choississez un autre type de bateau --------------");
							}
						}
						ajoute = false;
					}
				afficheFlotteDetails();
				} catch (Exception e) {
					System.out.println("Size Ship == 1 --> Try again");
				}
			}
		}
			
			
			
			
			
			System.out.println(p.getFlotte());
			p = player2;
			nb++;
		}
		
		
		
		
		
		
		//System.out.println(player1.getFlotte());
		//System.out.println("--J2--" + player2.getName());
		//player2.creationFlotte();
		//System.out.println(player2.getFlotte());

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
			//monPlayer.shootProcess(monPlayer, monAdversaire);Coordonnee c = new Coordonnee();
			boolean tirOk = false;
		String tir1 = "";
		Coordonnee c = new Coordonnee();
		while (!tirOk) {
			Scanner tir = new Scanner(System.in);
			System.out.println("Veuillez saisir une coordonnée de tir ( " + monPlayer.getName() + " ) :");
			tir1 = tir.nextLine();
			if(tirOk = c.coordCorrect(tir1)) {
			 c = new Coordonnee(tir1);
			}
		}
		System.out.println("missile  -------------" + tir1);
		
		//String tir1 = c.getCoordonnee();
		boolean touche = false;
		boolean destroyed = false;// variable pour supprimer un bateau si jamais il est coulé
		Ship shipDelete = new Ship();// Bateau fictif pour les vérifications +
		// Si jamais aucun bateau n'est touché on peut dire que la position de tir est
		// ratée
		for (Ship s1 : monAdversaire.getFlotte()) {
			if (s1.isHit(tir1)) {
				touche = true;
				c.setHit();
				monPlayer.getMyCoords().add(c);
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

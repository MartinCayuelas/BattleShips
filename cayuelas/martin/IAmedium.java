package cayuelas.martin;


import java.util.ArrayList;
import java.util.Random;

public class IAmedium  implements Iia {
	private String name;
	private int score;
	private ArrayList<Ship> Flotte;
	private ArrayList<Coordonnee> myCoordsShooted;
	public IAmedium(String nom) {

		name = nom;
		score = 0;
		Flotte = new ArrayList<Ship>();
		myCoordsShooted = new ArrayList<Coordonnee>();
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
	public void shoot(Iplayer monPlayer, Iplayer monAdversaire) {

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
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public ArrayList<Ship> getFlotte() {
		return Flotte;
	}

	public ArrayList<Coordonnee> getmyCoordsShooted() {
		return myCoordsShooted;
	}

	


	@Override
	public boolean verificationChevauchement(Ship shipTraite) {
		// TODO Auto-generated method stub
		boolean coordEgale = false;

		for (Ship s : this.Flotte) {
			int i = 0;
			while (!coordEgale && i < s.getSize()) {
				int j = 0;
				while (j < shipTraite.getSize() && !coordEgale) {
					if (s.getTabCoord()[i].getCoordonnee().equals(shipTraite.getTabCoord()[j].getCoordonnee())) {
						coordEgale = true;
					}
					j++;
				}
				i++;
			}
		}
		return coordEgale;
	}

	@Override
	public String toString() {
		String st = "";
		st += this.getName() + ": \n";
		for (Ship s : this.Flotte) {
			st += s.getSize();
		}
		return st;
	}

	@Override
	public boolean isShooted(String shoot) {
		boolean inList = false;
		int i = 0;
		for (Coordonnee coord : myCoordsShooted) {
			if (coord.equals(shoot)) {
				inList = true;
			}
		}
		return inList;
	}

	@Override
	public String myCoordsShootedString() {
		String str = "";
		String lettres = "	";
		String letter = "A";
		char l = letter.charAt(0);
		int cpt = 0;
		while (cpt < 10) {
			lettres += "" + l + "\t";
			l++; // On change la lettre
			cpt++;
		}

		str += lettres + "\n";
		letter = "A";
		l = letter.charAt(0);

		int u = 0;
		int w;
		while (u < 10) {
			w = 0;
			str += (u + 1) + "	";
			int nb = u + 1;
			while (w < 10) {

				String maCoord = l + "" + nb;
				boolean equal = false;
				boolean touche = false;
				Coordonnee c = new Coordonnee(maCoord);
				for (Coordonnee coord : this.getmyCoordsShooted()) {
					// System.out.println("c:"+coord.getCoordonnee()+" hit: "+coord.getHit());
					if (coord.getCoordonnee().equals(c.getCoordonnee())) {
						equal = true;
						if (coord.getHit() == 1) {
							touche = true;

						}
					}
				}
				if (equal) {
					if (touche) {
						str += "" + "⛴" + "\t";
					} else {
						str += "" + "X" + "\t";
					}
				} else {
					str += "" + "〰️" + "\t";
				}

				w++;
				l++;
			}
			str += "\n";
			letter = "A";
			l = letter.charAt(0);
			u++;
		}
		return str;
	}

	

	@Override
	public void resetPlayer() {
		// TODO Auto-generated method stub
		this.getmyCoordsShooted().clear(); //On nettoie les tirs
		this.getFlotte().clear(); // On nettoie la flotte
		
		this.score= 0;
	}


	
}

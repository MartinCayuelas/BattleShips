import java.util.ArrayList;
import java.util.Scanner;

public class Player {

	private String name;
	private int score;
	private ArrayList<Ship> Flotte;
	private ArrayList<Coordonnee> myCoords;

	/***Avec L'IA***/
	//Cela passera dans Human 
	

	public Player(String nom) {
		name = nom;
		score = 0;
		Flotte = new ArrayList<Ship>();
		myCoords = new ArrayList<Coordonnee>();
		

	}

	/*************Getters/setters******************/
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

	public ArrayList<Coordonnee> getMyCoords() {
		return myCoords;
	}

	/***************
	 * Creation / Incrémentation nb Bateaux et vérifications
	 ***********/

	
	
	/*********Gestion du Tir***************/
	
	public void shootProcess(Player monPlayer, Player monAdversaire) {
		Coordonnee c = new Coordonnee();
		c = c.tir(monPlayer);
		String tir1 = c.getCoordonnee();
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
	}
	



	

	

	public boolean verificationChevauchement(Ship shipTraite) {
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
			st += s;
		}
		return st;
	}

	public String myCoordString() {
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
				for (Coordonnee coord : this.getMyCoords()) {
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

	

}

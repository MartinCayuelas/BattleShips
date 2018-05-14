package fr.polytech.cayuelas.battleships.normal;

import java.util.ArrayList;
import java.util.Scanner;

public class Player {

	private String name;
	private int score;
	private ArrayList<Ship> Flotte;
	private ArrayList<Coordonnee> myCoords;

	private int nbCarrier;
	private int nbCruiser;
	private int nbBattleship;
	private int nbSubmarine;
	private int nbDestroyer;

	public Player() {}
	public Player(String nom) {
		name = nom;
		score = 0;
		Flotte = new ArrayList<Ship>();
		myCoords = new ArrayList<Coordonnee>();
		nbCarrier = 0;
		nbCruiser = 0;
		nbBattleship = 0;
		nbSubmarine = 0;
		nbDestroyer = 0;

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

	public ArrayList<Coordonnee> getMyCoords() {
		return myCoords;
	}

	/***************
	 * Creation / Incrémentation nb Bateaux et vérifications
	 ***********/

	public String afficheFlotteDetails() {
		String str = "Carriers (Taille 5): " + this.getNbCarrier() + "\n";
		str += "Cruisers (Taille 3): " + this.getNbCruiser() + "\n";
		str += "Battleships (Taille 4): " + this.getNbBattleship() + "\n";
		str += "Destroyers (Taille 2): " + this.getNbDestroyer() + "\n";
		str += "Submarines (Taille 3): " + this.getNbSubmarine() + "\n";

		return str;

	}

	public void incrementeTypeBateau(int size) {
		if (size == 2) {
			this.setNbDestroyer();
		} else if (size == 4) {
			this.setNbBattleship();
		} else if ((size == 3) && (this.getNbCruiser() < 1)) {
			this.setNbCruiser();
		} else if ((size == 3)) {
			this.setNbSubmarine();
			;
		} else { // Taille == 5
			this.setNbCarrier();
		}

	}

	public boolean verificationAjout(int size) {
		// retourne vrai si ajout Possible en fonction du nombre de bateaux du même type
		boolean ajout = false;

		if (size == 2) {
			if (this.getNbDestroyer() < 1) {
				ajout = true;
			}
		} else if (size == 5) {
			if (this.getNbCarrier() < 1) {
				ajout = true;
			}
		} else if (size == 4) {
			if (this.getNbBattleship() < 1) {
				ajout = true;
			}
		} else {
			if (this.getNbCruiser() < 1) {
				ajout = true;
			} else if (this.getNbSubmarine() < 1) {
				ajout = true;
			}
		}
		return ajout;

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

	public String mYcoordsString() {
		String str ="[";
		int i = 0;
		while(i < this.myCoords.size()) {
			str+=this.myCoords.get(i).getCoordonnee()+" ";
		}
		return str;
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

	public boolean isShooted(String shoot) {
		boolean inList = false;
		int i = 0;
		for(Coordonnee coord : myCoords) {
			if(coord.equals(shoot)) {
				inList=true;
			}
		}
		return inList;
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

	public void createFleet() {
		
	}
	public void shoot(Player p, Player p2) {}
	public int getNbCarrier() {
		return nbCarrier;
	}

	public void setNbCarrier() {
		this.nbCarrier += 1;
	}

	public int getNbCruiser() {
		return nbCruiser;
	}

	public void setNbCruiser() {
		this.nbCruiser += 1;
	}

	public int getNbBattleship() {
		return nbBattleship;
	}

	public void setNbBattleship() {
		this.nbBattleship += 1;
	}

	public int getNbSubmarine() {
		return nbSubmarine;
	}

	public void setNbSubmarine() {
		this.nbSubmarine += 1;
	}

	public int getNbDestroyer() {
		return nbDestroyer;
	}

	public void setNbDestroyer() {
		this.nbDestroyer += 1;
	}

}

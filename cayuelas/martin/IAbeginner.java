package cayuelas.martin;


import java.util.ArrayList;
import java.util.Random;

public class IAbeginner  implements Iia {
	private String name;
	private int score;
	private ArrayList<Ship> Flotte;
	private ArrayList<Coordonnee> myCoordsShooted;

	public IAbeginner(String nom) {
		// TODO Auto-generated constructor stub
		name = nom;
		score = 0;
		Flotte = new ArrayList<Ship>();
		myCoordsShooted = new ArrayList<Coordonnee>();
	}

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
					i++;
					nb++;
					System.out.println("Ship Size: " + s.getSize());
					
				}
			}
		}
	}

	public void shoot(Iplayer monPlayer, Iplayer monAdversaire) {
		Coordonnee c = Coordonnee.coordRandom();
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
	public boolean isShooted(String shoot) {
		// TODO Auto-generated method stub
		return false;
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

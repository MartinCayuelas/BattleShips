import java.util.ArrayList;
import java.util.Scanner;

public class Coordonnee  {

	private char partOne;
	private int partTwo;
	private int hit; //Touché = 1, Non touché = 0
	
	public Coordonnee() {}

	public Coordonnee(String coord) {

		if (coord.length() == 3) {
			coord = coord.substring(0, 1).toUpperCase() + coord.substring(1, 2) + coord.substring(2, 3);
			partOne = coord.charAt(0);
			String partTwo1 = coord.substring(1, 2);
			String partTwo2 = coord.substring(2, 3);
			partTwo1 += partTwo2;
			try {
				partTwo = Integer.parseInt(partTwo1);
			} catch (Exception e) {
				System.out.println("Problème dans le format");
			}
			
		} else {
			coord = coord.substring(0, 1).toUpperCase() + coord.substring(1, 2);
			//UPPER CASE pour la cas où l'on rentre a1 par exemple
			partOne = coord.charAt(0);
			try {
				partTwo = Integer.parseInt(coord.substring(1, 2));
			} catch (Exception e) {
				System.out.println("Problème dans le format");
			}
			
		}
		hit = 0;

	}

	public char getPartOne() {
		return partOne;
	}

	public void setPartOne(char partOne){
		this.partOne = partOne;
	}

	public int getPartTwo() {
		return partTwo;
	}

	public void setPartTwo(char partTwo) {
		this.partTwo = partTwo;
	}

	public int getHit() {
		return hit;
	}

	public void setHit() {
		this.hit = 1;
	}

	public String getCoordonnee() {
		String coord = "";
		coord = Character.toString(this.getPartOne()) + this.getPartTwo();
		return coord;
	}

	boolean coordCorrect(String coord) {
		boolean coordOk = false;
		String tab[] = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J" };
		String partOne="";
		String partTwo="";
		try{
		partOne = coord.substring(0, 1).toUpperCase(); // Debut
		}catch(Exception e) {
			System.out.println("Partie 1 manquante");
		}
		try{
			partTwo = coord.substring(1, 2); // end
		}catch(Exception e) {
			System.out.println("Partie 2 manquante");
		}

		if (coord.length() == 3) {
			String partThree;
			partThree = coord.substring(2, 3);
			partTwo += partThree;
		}
		int i = 0;
		boolean partOneOk = false;
		while (i < tab.length && !partOneOk) {
			if (tab[i].equals(partOne)) {
				partOneOk = true;
			}
			i++;
		}
		int right;
		boolean partTwoOk = false;
		try {
			right = Integer.parseInt(partTwo);
			if (right > 0 && right <= 10) {
				partTwoOk = true;
			}
		} catch (Exception e) {
			System.out.println("Entrez une coordonnée valide LettreChiffre (Ex: B2)");
		}
		if (partOneOk && partTwoOk) {
			coordOk = true;
		}
		return coordOk;
	}

	public boolean compareCoord(Coordonnee start, Coordonnee end) { // permet de savoir quelle est la coordonnée la "plus" grande
		boolean superieur = false;
		if (start.getPartOne() > end.getPartOne()) {
			superieur = true;
		}
		if (start.getPartTwo() > end.getPartTwo()) {
			superieur = true;
		}
		return superieur;
	}
	
	public Coordonnee tir(Player p) {
		boolean tirOk = false;
		String tir1 = "";
		Coordonnee c = new Coordonnee();
		while (!tirOk) {
			Scanner tir = new Scanner(System.in);
			System.out.println("Veuillez saisir une coordonnée de tir ( " + p.getName() + " ) :");
			tir1 = tir.nextLine();
			if(tirOk = c.coordCorrect(tir1)) {
			 c = new Coordonnee(tir1);
			}
		}
		System.out.println("missile  -------------" + tir1);
		
		return c;
	}
}

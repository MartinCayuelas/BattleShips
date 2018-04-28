
public class Coordonnee {

	private char partOne;
	private int partTwo;
	private int hit;
	
	public Coordonnee(String coord) {
		if(coord.length() == 3) {
			partOne = coord.charAt(0);
			String partTwo1 = coord.substring(1, 2);
			String partTwo2 = coord.substring(2, 3);
			partTwo1 += partTwo2;
			try {
			partTwo = Integer.parseInt(partTwo1);
			}catch(Exception e){
				System.out.println("Problème dans le format");
			}
			hit = 0;
		}else {
		partOne = coord.charAt(0);
		try {
		partTwo = Integer.parseInt(coord.substring(1, 2));
		}catch(Exception e) {
			System.out.println("Problème dans le format");
		}
		hit = 0;
		}
		
	}

	public char getPartOne() {
		return partOne;
	}

	public void setPartOne(char partOne) {
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
		String partOne;
		String partTwo;

		partOne = coord.substring(0, 1); // Debut
		partTwo = coord.substring(1, 2); // end

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
	

}

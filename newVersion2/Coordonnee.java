
public class Coordonnee {

	private char partOne;
	private int partTwo;
	private int hit;
	
	public Coordonnee(String coord) {
		partOne = coord.charAt(0);
		partTwo = Integer.parseInt(coord.substring(1, 2));
		hit = 0;
		
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
		this.hit += 1;
	}
	
	public String getCoordonnee() {
		String coord = "";
		coord = Character.toString(this.getPartOne()) + this.getPartTwo();
		return coord;
	}
	

}

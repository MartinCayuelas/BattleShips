import java.util.ArrayList;


public class Player {

	private String name;
	private int score;
	private ArrayList<Ship> flote;
	private Grille maGrille;
	private Grille maGrilleBateau;
	
	public Player(String nom){
		name = nom;
		score = 0;
		flote = new ArrayList<Ship>();
		maGrille = new Grille();
			
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
	
	

	public ArrayList<Ship> getFlote() {
		return flote;
	}
	
	

	public Grille getMaGrille() {
		return maGrille;
	}
	
	public Grille getMaGrilleBat() {
		return maGrilleBateau;
	}

	@Override
	public String toString() {
		String st ="";
		st += this.getName()+": \n";
		for(Ship s : this.flote) {
			st += s;
		}
	return st;
	}
	
	

	
	
}

import java.util.ArrayList;


public class Player {

	private String name;
	private int score;
	private ArrayList<Ship> flote;
	
	public Player(String nom){
		name = nom;
		score = 0;
		flote = new ArrayList<Ship>();
			
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

	
	
}


public class Grille {

	private int[][] grille;

	public Grille() {

		int[][] grille = { 
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{  0, 0, 0, 0, 0, 0, 0, 0, 0, 0 } };

		this.grille = grille;

	}

	public int[][] getGrille() {
		return grille;
	}
	
	public void setGrille(int a, int b) {
		grille[b][a] = 1;
	}

	public String toString() {
		String str ="";
		
		int u = 0;
		int w;
		while (u < 10)

		{

		  w = 0;

		  while(w < 10)

		  {

		    str += ""+grille[u][w]+"";

		    w++;

		  }

		 str+="\n";

		  u++;

		}
		
		return str;
	}
	
	
	
}

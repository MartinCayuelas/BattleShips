
public class Grille {

	private int[][] grille;

	public Grille(int longueurM, int largeurM) {
		int[][] maGrille = new int[longueurM][largeurM];
		
		for(int i = 0; i<maGrille.length; i++) {
			for(int j = 0; j<maGrille[i].length;j++) {
				
				
				maGrille[i][j] = 0;
				
			}
		}/*
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
	*/
		this.grille = maGrille;
	}

	public int[][] getGrille() {
		return grille;
	}
	
	public void setGrille(int a, int b) {
		grille[b][a] = 1;
	}
	
	public void restartGrille(int longueurM, int largeurM) {
		
		int[][] maGrille = new int[longueurM][largeurM];
		
		for(int i = 0; i<maGrille.length; i++) {
			for(int j = 0; j<maGrille[i].length;j++) {
				
				
				maGrille[i][j] = 0;
				
			}
		}/*
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
				{  0, 0, 0, 0, 0, 0, 0, 0, 0, 0 } };*/

		this.grille = maGrille;
	}
	

	public String toString() {
		String str ="";
		
		int u = 0;
		int w;
		while (u < grille.length)

		{

		  w = 0;

		  while(w < grille[u].length)

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

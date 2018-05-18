package cayuelas.martin;

import java.util.ArrayList;
import java.util.Scanner;

public interface Iplayer {

	

	public String getName();

	public void setName(String name);

	public int getScore();

	public void setScore(int score);

	public ArrayList<Ship> getFlotte();

	public ArrayList<Coordonnee> getmyCoordsShooted();

	/***************
	 * Creation / Incrémentation nb Bateaux et vérifications
	 ***********/


	

	

	public boolean verificationChevauchement(Ship shipTraite);

	

	public boolean isShooted(String shoot);

	public String myCoordsShootedString();

	public void resetPlayer();

	
}

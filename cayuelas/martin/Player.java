package cayuelas.martin;

import java.util.ArrayList;
import java.util.Scanner;

public interface Player {

	String name = null;
	int score = 0; 
	ArrayList<Ship> Flotte = null;
	ArrayList<Coordonnee> myCoordsShooted = null ;

	int nbCarrier = 0;
	int nbCruiser= 0;
	int nbBattleship= 0;
	int nbSubmarine= 0;
	int nbDestroyer= 0;

	public String getName();

	public void setName(String name);

	public int getScore();

	public void setScore(int score);

	public ArrayList<Ship> getFlotte();

	public ArrayList<Coordonnee> getmyCoordsShooted();

	/***************
	 * Creation / Incrémentation nb Bateaux et vérifications
	 ***********/

	public String afficheFlotteDetails();

	public void incrementeTypeBateau(int size);

	public boolean verificationAjout(int size);

	public boolean verificationChevauchement(Ship shipTraite);

	@Override
	public String toString();

	public boolean isShooted(String shoot);

	public String myCoordsShootedString();

	public void resetPlayer();

	public int getNbCarrier();

	public void setNbCarrier();

	public int getNbCruiser();

	public void setNbCruiser();

	public int getNbBattleship();

	public void setNbBattleship();

	public int getNbSubmarine();

	public void setNbSubmarine();

	public int getNbDestroyer();

	public void setNbDestroyer();

}

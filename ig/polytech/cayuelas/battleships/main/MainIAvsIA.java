package ig.polytech.cayuelas.battleships.main;

import ig.polytech.cayuelas.battleships.ia.IAbeginner;
import ig.polytech.cayuelas.battleships.ia.IAmedium;
import ig.polytech.cayuelas.battleships.ia.IAhard;

public class MainIAvsIA {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		/**************** Init IA 1 ***********/
		IAmedium ia = new IAmedium("Gandalf");
		ia.createFleet();
		System.out.println(ia.getFlotte());

		/**************** Init I2 ***********/
		IAhard ia2 = new IAhard("Saroumane");
		ia2.createFleet();
		System.out.println(ia2.getFlotte());

		System.out.println("---------Début de la partie-----------");
		System.out.println("Les indications : ");
		System.out.println(" 〰️ -> Eau (possiblement)");
		System.out.println(" ⛴ -> Touché");
		System.out.println(" X -> Raté");
		
		System.out.println("avant: "+ia.getMyCoords().size());
		ia2.getMyCoords().clear();
		ia.getMyCoords().clear();
		System.out.println("Apres: "+ia.getMyCoords().size());

		int cpt = 0;
		while ((ia.getFlotte().size() != 0) && (ia2.getFlotte().size() != 0)) {
			System.out.println("Player  : " + ia.getName());
			System.out.println("Grille : -------------------------------");
			System.out.println(ia.myCoordString());
			ia.shoot(ia, ia2);

			System.out.println("Player  : " + ia2.getName());
			System.out.println("Grille : -------------------------------");
			System.out.println(ia2.myCoordString());
			ia2.shoot(ia2, ia);
			cpt++;
		}
		System.out.println("------------Partie Terminée-----------");
		if ((ia.getFlotte().size() == 0)) {
			System.out.println("Gagnant : " + ia2.getName());
		} else {
			System.out.println("Gagnant : " + ia.getName());
		}
		System.out.println("Score de " + ia2.getName() + " : " + ia2.getScore());
		System.out.println("Score de " + ia.getName() + " : " + ia.getScore());
		
		System.out.println("Cpt : " + cpt);
		int t = cpt/2;
		System.out.println("NB tour : " + t);
	}

}

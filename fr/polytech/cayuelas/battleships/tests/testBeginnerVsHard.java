package fr.polytech.cayuelas.battleships.tests;

import fr.polytech.cayuelas.battleships.ia.IAbeginner;
import fr.polytech.cayuelas.battleships.ia.IAhard;

public class testBeginnerVsHard {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int scoreB = 0;
		int scoreM = 0;

		for (int i = 0; i < 100; i++) {

			/**************** Init IA 1 ***********/
			IAbeginner beginner = new IAbeginner("Frodon");
			beginner.createFleet();
			System.out.println(beginner.getFlotte());

			/**************** Init I2 ***********/
			IAhard hard = new IAhard("Aragorn");
			hard.createFleet();
			System.out.println(hard.getFlotte());

			boolean end = false;
			while ((beginner.getFlotte().size() != 0) && (hard.getFlotte().size() != 0)) {
				System.out.println("Player  : " + beginner.getName());
				System.out.println("Grille : -------------------------------");
				//System.out.println(beginner.myCoordString());
				beginner.shoot(beginner, hard);
				
				if ((hard.getFlotte().size() == 0)) {
					end = true;
				}
				if(!end) {
				System.out.println("Player  : " +hard.getName());
				System.out.println("Grille : -------------------------------");
				//System.out.println(hard.myCoordString());
				hard.shoot(hard, beginner);
				}
				System.out.println("Partie : "+ (i+1));
				//System.out.println(medium.mYcoordsString());
			}
			if ((beginner.getFlotte().size() == 0)) {
				scoreM += 1;
			} else {
				scoreB += 1;
			}
		}

		System.out.println("Score de IA Beginner: " + scoreB);
		System.out.println("Score de IA Hard: " + scoreM);

	
	}

}

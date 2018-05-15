package fr.polytech.cayuelas.battleships.tests;

import fr.polytech.cayuelas.battleships.ia.IAhard;
import fr.polytech.cayuelas.battleships.ia.IAmedium;

public class testMediumVsHard {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int scoreB = 0;
		int scoreM = 0;
		int tourMoyen = 0;

		for (int i = 0; i < 100; i++) {

			/**************** Init IA 1 ***********/
			IAmedium medium = new IAmedium("Frodon");
			medium.createFleet();
			System.out.println(medium.getFlotte());

			/**************** Init I2 ***********/
			IAhard hard = new IAhard("Gandalf");
			hard.createFleet();
			System.out.println(hard.getFlotte());

			int j = 1;
			boolean end = false;
			while ((medium.getFlotte().size() != 0) && (hard.getFlotte().size() != 0)) {
				
				System.out.println("Medium_-----------------------------");
				medium.shoot(medium, hard);
				
				if ((hard.getFlotte().size() == 0)) {
					end = true;
				}
				if(!end) {
				System.out.println("Hard_-----------------------------Tour: " + j);
				hard.shoot(hard, medium);
				j++;
				}
			}
			if ((medium.getFlotte().size() == 0)) {
				scoreM += 1;
			} else {
				scoreB += 1;
			}
			tourMoyen += j;
		}

		System.out.println("Score de IA Medium: " + scoreB);
		System.out.println("Score de IA Hard: " + scoreM);
		System.out.println("IA Hard coule la flotte en : " + (tourMoyen/100)+" coups en moyenne");
		

	
	}

	

}

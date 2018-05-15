package fr.polytech.cayuelas.battleships.tests;

import fr.polytech.cayuelas.battleships.ia.IAbeginner;
import fr.polytech.cayuelas.battleships.ia.IAmedium;

public class testBeginnerVsMedium {

	public static void main(String[] args) {
		// TODO Auto-generated constructor stub

		int scoreB = 0;
		int scoreM = 0;

		for (int i = 0; i < 100; i++) {

			/**************** Init IA 1 ***********/
			IAbeginner beginner = new IAbeginner("Frodon");
			beginner.createFleet();
			System.out.println(beginner.getFlotte());

			/**************** Init I2 ***********/
			IAmedium medium = new IAmedium("Aragorn");
			medium.createFleet();
			System.out.println(medium.getFlotte());

			boolean end = false;
			while ((beginner.getFlotte().size() != 0) && (medium.getFlotte().size() != 0)) {
				System.out.println("Beginner_-----------------------------");
				beginner.shoot(beginner, medium);
				
				if ((medium.getFlotte().size() == 0)) {
					end = true;
				}
				if(!end) {
				System.out.println("Medium_-----------------------------");
				medium.shoot(medium, beginner);
				}
			}
			if ((beginner.getFlotte().size() == 0)) {
				scoreM += 1;
			} else {
				scoreB += 1;
			}
		}

		System.out.println("Score de IA Beginner: " + scoreB);
		System.out.println("Score de IA Medium: " + scoreM);

	}

}

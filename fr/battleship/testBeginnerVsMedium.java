package fr.battleship;

import cayuelas.martin.IAbeginner;
import cayuelas.martin.IAmedium;

public class testBeginnerVsMedium {

	public static void main(String[] args) {
		// TODO Auto-generated constructor stub

		int scoreB = 0;
		int scoreM = 0;
		int tourMoyen = 0;
		boolean tour = true;
		for (int i = 0; i < 100; i++) {

			/**************** Init IA 1 ***********/
			IAbeginner beginner = new IAbeginner("Frodon");
			beginner.createFleet();
			System.out.println(beginner.getFlotte());

			/**************** Init I2 ***********/
			IAmedium medium = new IAmedium("Aragorn");
			medium.createFleet();
			System.out.println(medium.getFlotte());
			int j = 0;
			boolean end = false;
			while ((beginner.getFlotte().size() != 0) && (medium.getFlotte().size() != 0)) {

				if (tour) {
					System.out.println("Beginner_-----------------------------");
					beginner.shoot(beginner, medium);

					if ((medium.getFlotte().size() == 0)) {
						end = true;
					}
					if (!end) {
						System.out.println("Medium_-----------------------------");
						medium.shoot(medium, beginner);
						j++;
					}
				} else {
					System.out.println("Medium_-----------------------------");
					medium.shoot(medium, beginner);

					if ((beginner.getFlotte().size() == 0)) {
						end = true;
					}
					if (!end) {

						System.out.println("Player  : " + beginner.getName());
						System.out.println("Grille : -------------------------------");
						// System.out.println(beginner.myCoordString());
						beginner.shoot(beginner, medium);
						j++;

					}

				}

			}
			if ((beginner.getFlotte().size() == 0)) {
				scoreM += 1;
			} else {
				scoreB += 1;
			}
			tourMoyen += j;
			if (tour) {
				tour = false;
			} else {
				tour = true;
			}
		}

		System.out.println("Score de IA Beginner: " + scoreB);
		System.out.println("Score de IA Medium: " + scoreM);
		System.out.println("IA Medium coule la flotte en : " + (tourMoyen / 100) + " coups en moyenne");

	}

}

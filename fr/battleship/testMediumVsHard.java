package fr.battleship;

import cayuelas.martin.IAhard;
import cayuelas.martin.IAmedium;

public class testMediumVsHard {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int scoreHard = 0;
		int scoreMed = 0;
		int tourMoyen = 0;

		boolean tour = true;

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
				
				
				
				if (tour) {
					System.out.println("Medium_-----------------------------");
					medium.shoot(medium, hard);
					System.out.println(medium.myCoordsShootedString());
					if ((hard.getFlotte().size() == 0)) {
						end = true;
					}
					if(!end) {
					System.out.println("Hard_-----------------------------Tour: " + j);
					hard.shoot(hard, medium);
					System.out.println(hard.myCoordsShootedString());
					j++;
					}
				} else {
					System.out.println("Hard_-----------------------------Tour: " + j);
					hard.shoot(hard, medium);
					System.out.println(hard.myCoordsShootedString());

					if ((medium.getFlotte().size() == 0)) {
						end = true;
					}
					if (!end) {

						System.out.println("Medium_-----------------------------");
						medium.shoot(medium, hard);
						System.out.println(medium.myCoordsShootedString());
						j++;

					}

				}
				
			}
			if ((medium.getFlotte().size() == 0)) {
				scoreHard += 1;
			} else {
				scoreMed += 1;
			}
			tourMoyen += j;
			if (tour) {
				tour = false;
			} else {
				tour = true;
			}
		}

		System.out.println("Score de IA Medium: " + scoreMed);
		System.out.println("Score de IA Hard: " + scoreHard);
		System.out.println("IA Hard coule la flotte en : " + (tourMoyen/100)+" coups en moyenne");
		

	
	}

	

}

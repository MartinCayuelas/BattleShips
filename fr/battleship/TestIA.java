package fr.battleship;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import cayuelas.martin.IAbeginner;
import cayuelas.martin.IAhard;
import cayuelas.martin.IAmedium;

public class TestIA {
	public static void main(String[] args) {

		// Beginenr versus Medium

		int scoreB = 0;
		int scoreM = 0;
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

			if (tour) {
				tour = false;
			} else {
				tour = true;
			}
		}

		System.out.println("Score de IA Beginner: " + scoreB);
		System.out.println("Score de IA Medium: " + scoreM);

		// beginner vs Hard

		int scoreBeg = 0;
		int scoreH = 0;
		tour = true;
		for (int i = 0; i < 100; i++) {

			/**************** Init IA 1 ***********/
			IAbeginner beginner = new IAbeginner("Frodon");
			beginner.createFleet();
			System.out.println(beginner.toString());

			/**************** Init I2 ***********/
			IAhard hard = new IAhard("Aragorn");
			hard.createFleet();
			System.out.println(hard.toString());
			int j = 1;

			boolean end = false;

			while ((beginner.getFlotte().size() != 0) && (hard.getFlotte().size() != 0)) {

				if (tour) {

					beginner.shoot(beginner, hard);

					if ((hard.getFlotte().size() == 0)) {
						end = true;
					}
					if (!end) {

						hard.shoot(hard, beginner);
						j++;

					}
				} else {

					hard.shoot(hard, beginner);

					if ((beginner.getFlotte().size() == 0)) {
						end = true;
					}
					if (!end) {

						beginner.shoot(beginner, hard);
						j++;

					}

				}
			}

			if ((beginner.getFlotte().size() == 0)) {
				scoreH += 1;
			} else {
				scoreB += 1;
			}

			// System.out.println("Partie : "+ (i+1));
			if (tour) {
				tour = false;
			} else {
				tour = true;
			}
		}

		// Medium VS Hard

		int scoreHard = 0;
		int scoreMed = 0;
		tour = true;

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

					if ((hard.getFlotte().size() == 0)) {
						end = true;
					}
					if (!end) {
						System.out.println("Hard_-----------------------------Tour: " + j);
						hard.shoot(hard, medium);

						j++;
					}
				} else {
					System.out.println("Hard_-----------------------------Tour: " + j);
					hard.shoot(hard, medium);

					if ((medium.getFlotte().size() == 0)) {
						end = true;
					}
					if (!end) {

						System.out.println("Medium_-----------------------------");
						medium.shoot(medium, hard);
						j++;

					}

				}

			}
			if ((medium.getFlotte().size() == 0)) {
				scoreHard += 1;
			} else {
				scoreMed += 1;
			}
			if (tour) {
				tour = false;
			} else {
				tour = true;
			}
		}

		System.out.println("Tests terminés -- Score enregistrés dans le fichier ai_proof.csv ");

		// partie csv

		// creates the file
		FileWriter writer = null;
		try {
			
			File f;
			//System.out.println("sep: "+File.separator+"ai_proof.csv");
			writer = new FileWriter(new File("."+File.separator+"ai_proof.csv"));
			writer.write("AI Name;score;AI Name2;score2;");
			writer.append("\n");

			writer.write("AI Beginner;" + scoreB + ";AI Medium;" + scoreM + ";\nAI Beginner;" + scoreBeg + ";AI hard;"
					+ scoreH + "\nAI Medium;" + scoreMed + ";AI hard;" + scoreHard + ";");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}

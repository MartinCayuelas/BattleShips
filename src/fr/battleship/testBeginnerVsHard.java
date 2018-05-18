package fr.battleship;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import cayuelas.martin.IAbeginner;
import cayuelas.martin.IAhard;

public class testBeginnerVsHard {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int scoreB = 0;
		int scoreH = 0;
		int tourMoyen = 0;
		boolean tour = true;
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
				
				//System.out.println("Partie : "+ (i+1));
			tourMoyen += j;
			if(tour) {
				tour = false;
			}else {
				tour = true;
			}
		}

		System.out.println("Score de IA Beginner: " + scoreB);
		System.out.println("Score de IA Hard: " + scoreH);
		System.out.println("IA Hard coule la flotte en : " + (tourMoyen/100)+" coups en moyenne");
		
		try {
			FileReader fr = new FileReader("ai_proof.csv");
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

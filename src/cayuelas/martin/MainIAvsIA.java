package cayuelas.martin;

import java.util.Scanner;



public class MainIAvsIA {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		/**************** Init IA 1 ***********/
		
		System.out.println("Entrez le niveau de l'IA numéro 1  ( 1 = beginner, 2 = medium, 3 = hard)");
		Scanner scAi = new Scanner(System.in);
		int choix = scAi.nextInt();
		/**************** Init IA ***********/
		 
		IA ia ;
		if(choix == 1){
			ia = new IAbeginner("Frodon");
			System.out.println("Je suis beginner");
		}else if(choix == 2){
			ia = new IAmedium("Aragorn");
			System.out.println("Je suis medium");
		}else {
			ia = new IAhard("Gandalf");
			System.out.println("Je suis hard");
		}
		//IAbeginner ia = new IAbeginner("Gandalf");
		ia.createFleet();
		System.out.println(ia.getFlotte());

		/**************** Init I2 ***********/
		System.out.println("Entrez le niveau de l'IA numéro 2 ( 1 = beginner, 2 = medium, 3 = hard)");
		Scanner scAi2 = new Scanner(System.in);
		int choix2 = scAi2.nextInt();
			 
		IA ia2;
		if(choix2 == 1){
			ia2 = new IAbeginner("Frodon");
			System.out.println("Je suis une IA Beginner");
		}else if(choix2 == 2){
			ia2 = new IAmedium("Aragorn");
			System.out.println("Je suis une IA Medium");
		}else {
			ia2 = new IAhard("Gandalf");
			System.out.println("Je suis une IA Hard");
		}
		
		ia2.createFleet();
		System.out.println(ia2.getFlotte());

		System.out.println("---------Début de la partie-----------");
		System.out.println("Les indications : ");
		System.out.println(" 〰️ -> Eau (possiblement)");
		System.out.println(" ⛴ -> Touché");
		System.out.println(" X -> Raté");
		
		System.out.println("avant: "+ia.getmyCoordsShooted().size());
		ia2.getmyCoordsShooted().clear();
		ia.getmyCoordsShooted().clear();
		System.out.println("Apres: "+ia.getmyCoordsShooted().size());
		boolean end = false;
		int cpt = 0;
		while ((ia.getFlotte().size() != 0) && (ia2.getFlotte().size() != 0)) {
			System.out.println("Player  : " + ia.getName());
			System.out.println("Grille : -------------------------------");
			System.out.println(ia.myCoordsShootedString());
			ia.shoot(ia, ia2);

			if ((ia2.getFlotte().size() == 0)) {
				end = true;
			}
			if(!end) {
			System.out.println("Player  : " + ia2.getName());
			System.out.println("Grille : -------------------------------");
			System.out.println(ia2.myCoordsShootedString());
			ia2.shoot(ia2, ia);
			cpt++;
			}
			
		}
		System.out.println("------------Partie Terminée-----------");
		if ((ia.getFlotte().size() == 0)) {
			System.out.println("Gagnant : " + ia2.getName());
		} else {
			System.out.println("Gagnant : " + ia.getName());
		}
		
		System.out.println("Score de IA 1 " + ia.getName() + " : " + ia.getScore()); // IA 1
		System.out.println("Score de IA 2 " + ia2.getName() + " : " + ia2.getScore());
		
		System.out.println("NB tour : " + cpt);
	}

}


public class MainIAvsIA {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		/**************** Init IA 1 ***********/
		IAbeginner ia = new IAbeginner("Gandalf");
		ia.createFleet();
		System.out.println(ia.getFlotte());

		/**************** Init I2 ***********/
		IAbeginner ia2 = new IAbeginner("Saroumane");
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

		while ((ia.getFlotte().size() != 0) && (ia2.getFlotte().size() != 0)) {
			System.out.println("Player  : " + ia.getName());
			System.out.println("Grille : -------------------------------");
			System.out.println(ia.myCoordString());
			ia.shoot(ia, ia2);

			System.out.println("Player  : " + ia2.getName());
			System.out.println("Grille : -------------------------------");
			System.out.println(ia2.myCoordString());
			ia2.shoot(ia2, ia);
		}
		System.out.println("------------Partie Terminée-----------");
		if ((ia.getFlotte().size() == 0)) {
			System.out.println("Gagnant : " + ia2.getName());
		} else {
			System.out.println("Gagnant : " + ia.getName());
		}
		System.out.println("Score de " + ia2.getName() + " : " + ia2.getScore());
		System.out.println("Score de " + ia.getName() + " : " + ia.getScore());
	}

}

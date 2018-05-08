package ig.polytech.cayuelas.battleships.ia;

public class testHardVsHard {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int scoreB = 0;
		int scoreM = 0;

		for (int i = 0; i < 100; i++) {

			/**************** Init IA 1 ***********/
			IAhard medium = new IAhard("Frodon");
			medium.createFleet();
			System.out.println(medium.getFlotte());

			/**************** Init I2 ***********/
			IAhard hard = new IAhard("Aragorn");
			hard.createFleet();
			System.out.println(hard.getFlotte());

	
			while ((medium.getFlotte().size() != 0) && (hard.getFlotte().size() != 0)) {

				medium.shoot(medium, hard);
				hard.shoot(hard, medium);
				//System.out.println(medium.mYcoordsString());
			}
			if ((medium.getFlotte().size() == 0)) {
				scoreM += 1;
			} else {
				scoreB += 1;
			}
		}

		System.out.println("Score de IA HardMed: " + scoreB);
		System.out.println("Score de IA Hard: " + scoreM);

	
	}

	

}

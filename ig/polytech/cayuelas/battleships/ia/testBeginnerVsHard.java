package ig.polytech.cayuelas.battleships.ia;

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

	
			while ((beginner.getFlotte().size() != 0) && (hard.getFlotte().size() != 0)) {

				beginner.shoot(beginner, hard);
				hard.shoot(hard, beginner);
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


public class testBeginnerVsMedium {

	public static void main(String[] args) {
		// TODO Auto-generated constructor stub

		int scoreB = 0;
		int scoreM = 0;

		for (int i = 0; i < 1; i++) {

			/**************** Init IA 1 ***********/
			IAbeginner beginner = new IAbeginner("Frodon");
			beginner.createFleet();
			System.out.println(beginner.getFlotte());

			/**************** Init I2 ***********/
			IAmedium medium = new IAmedium("Aragorn");
			medium.createFleet();
			System.out.println(medium.getFlotte());

			System.out.println("Mycoords Med: " + medium.getMyCoords().size());

			while ((beginner.getFlotte().size() != 0) && (medium.getFlotte().size() != 0)) {

				beginner.shoot(beginner, medium);
				medium.shoot(medium, beginner);
				System.out.println(medium.mYcoordsString());
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

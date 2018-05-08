package ig.polytech.cayuelas.battleships.ia;
import ig.polytech.cayuelas.battleships.normal.Player;
import ig.polytech.cayuelas.battleships.normal.Coordonnee;
import ig.polytech.cayuelas.battleships.normal.Ship;

public class testBeginnerVsBeginner {

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
			IAbeginner beg = new IAbeginner("Aragorn");
			beg.createFleet();
			System.out.println(beg.getFlotte());

			System.out.println("Mycoords Med: " + beg.getMyCoords().size());

			while ((beginner.getFlotte().size() != 0) && (beg.getFlotte().size() != 0)) {

				beginner.shoot(beginner, beg);
				beg.shoot(beg, beginner);
				//System.out.println(medium.mYcoordsString());
			}
			if ((beginner.getFlotte().size() == 0)) {
				scoreM += 1;
			} else {
				scoreB += 1;
			}
		}

		System.out.println("Score de IA Beginner: " + scoreB);
		System.out.println("Score de IA Beginner2: " + scoreM);

	}

}

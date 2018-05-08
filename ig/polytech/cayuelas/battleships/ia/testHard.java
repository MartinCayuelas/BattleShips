package ig.polytech.cayuelas.battleships.ia;

import ig.polytech.cayuelas.battleships.normal.Coordonnee;

public class testHard {

	public static void main(String[] args) {
		IAhard hard = new IAhard("jeje");
		hard.createFleet();
		IAbeginner beginner = new IAbeginner("Frodon");
		beginner.createFleet();
		

		for (int i = 0; i < 30; i++) {
			System.out.println("Beginner-----------------------");
			beginner.shoot(beginner, hard);
			System.out.println("hard-----------------------");
			hard.shoot(hard, beginner);
		}
		
		
	}

}

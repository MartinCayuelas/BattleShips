package ig.polytech.cayuelas.battleships.main;

import java.util.Scanner;

public class Launcher {

	public static void main(String[] args) {
		System.out.println("Choix de Mode de Jeu? 1 - Human vs Human, 2 - Human vs AI, 3 - AI vs AI");

		Scanner sc = new Scanner(System.in);
		int choix = sc.nextInt();
		if (choix == 1) {
			System.out.println("Vous avez choisi le mode 1 vs 1");
			initModeHvsH();
		} else if (choix == 2) {

			System.out.println("Vous avez choisi le mode Human vs IA");
			initModeHvsIa();
		} else {
			System.out.println("Vous avez choisi le mode IA vs IA");
			initModeIaVsIa();
		}
	}

	public static void initModeHvsH() {
		MainPlayer.main(null);
	}

	public static void initModeHvsIa() {
		MainPlayerIA.main(null);
	}

	public static void initModeIaVsIa() {
		 MainIAvsIA.main(null);
	}

}

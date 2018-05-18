package cayuelas.martin;

import java.util.Scanner;

public class Battleship {

	public static void main(String[] args) {
		System.out
				.println("Choix de Mode de Jeu? 1 - Human vs Human, 2 - Human vs AI, 3 - AI vs AI");
		int choix = 1;
		boolean choixOk = false;
		while (!choixOk) {
			Scanner sc = new Scanner(System.in);
			try {
				choix = sc.nextInt();
				if (choix >= 1 && choix <= 3) {
					choixOk = true;
				}else{
					System.out.println("Il faut un entier compris entre 1 et 3");
				}
			} catch (Exception e) {
				System.out.println("Mauvais Format");
			}
		}
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

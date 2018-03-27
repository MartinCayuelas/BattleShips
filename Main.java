import java.util.Scanner;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		/*Saisi manuelle*/
		Scanner sc = new Scanner(System.in);
		System.out.println("Veuillez saisir une coordonnée de début :");
		String start = sc.nextLine();
		System.out.println("Vous avez saisi : " + start);
		
		Scanner sc2 = new Scanner(System.in);
		System.out.println("Veuillez saisir une coordonnée de fin :");
		String end = sc.nextLine();
		System.out.println("Vous avez saisi : " + end);
		
		
		Ship s1 = new Ship(start, end);
		System.out.println("Vous avez crée un bateau de taille : "+s1.getSize());

		
		String missile2 = "A2";
		String missile3 = "A3";
		String missile4 = "B3";
		
		
		Scanner tir = new Scanner(System.in);
		System.out.println("Veuillez saisir une coordonnée de tir :");
		String tir1 = tir.nextLine();
		

		System.out.println("missile 1 -------------" + tir1);
		if (s1.isHit(tir1)) {
			System.out.println("Touché");
		} else {
			System.out.println("Raté");
		}

		System.out.println("hitNumber: " + s1.getHitNumber());
		if (s1.isDestroyed()) {

			System.out.println("Coulé");
		} else {
			System.out.println("Pas coulé");
		}

		System.out.println("missile 2 -------------" + missile2);
		if (s1.isHit(missile2)) {
			System.out.println("Touché");
		} else {
			System.out.println("Raté");
		}
		System.out.println("hitNumber: " + s1.getHitNumber());
		if (s1.isDestroyed()) {

			System.out.println("Coulé");
		} else {
			System.out.println("Pas coulé");
		}

		System.out.println("missile 3 -------------" + missile3);
		if (s1.isHit(missile3)) {
			System.out.println("Touché");
		} else {
			System.out.println("Raté");
		}
		System.out.println("hitNumber: " + s1.getHitNumber());
		if (s1.isDestroyed()) {

			System.out.println("Coulé");
		} else {
			System.out.println("Pas coulé");
		}
		System.out.println("missile 4 -------------" + missile4);
		if (s1.isHit(missile4)) {
			System.out.println("Touché");
		} else {
			System.out.println("Raté");
		}
		System.out.println("hitNumber: " + s1.getHitNumber());
		if (s1.isDestroyed()) {

			System.out.println("Coulé");
		} else {
			System.out.println("Pas coulé");
		}

	}

}

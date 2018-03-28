import java.util.Scanner;


public class MainPlayer {

	
	public static void main(String[] args) {
		
		System.out.println("Entrez votre nom (J1)");
		Scanner sc = new Scanner(System.in);
		String name = sc.nextLine();
		
		Player player1 = new Player(name);
		
		System.out.println("Entrez votre nom (J2)");
		Scanner sc2 = new Scanner(System.in);
		String name2 = sc.nextLine();
		
		Player player2 = new Player(name2);
		
		System.out.println("Nom J1 : "+player1.getName());
		System.out.println("Nom J2 : "+player2.getName());
	}

}

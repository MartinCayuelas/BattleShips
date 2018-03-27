
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Ship s1 = new Ship("A2","A6");
		
		String missile = "A1";
		String missile2 = "A2";
		String missile3 = "A4";
		String missile4 = "B3";
		
		System.out.println("missile 1 -------------"+missile);
		if(s1.isHit(missile)){
			System.out.println("Touché");
		}else{
			System.out.println("Raté");
		}
		System.out.println("missile 2 -------------"+ missile2);
		if(s1.isHit(missile2)){
			System.out.println("Touché");
		}else{
			System.out.println("Raté");
		}
		System.out.println("missile 3 -------------"+ missile3);
		if(s1.isHit(missile3)){
			System.out.println("Touché");
		}else{
			System.out.println("Raté");
		}
		System.out.println("missile 4 -------------"+ missile4);
		if(s1.isHit(missile4)){
			System.out.println("Touché");
		}else{
			System.out.println("Raté");
		}

	}

}

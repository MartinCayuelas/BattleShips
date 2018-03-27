public class Ship {
	String startCoord;
	String endCoord;
	int hitNumber;
	String[] tabCoord; // tableau des coordonnées entières du bateau
	boolean vertical;

	public Ship(String start, String end) {

		if (!start.equals(end)) {
			String partOneStart;
			String partTwoStart;
			String partOneEnd;
			String partTwoEnd;
			partOneStart = start.substring(0, 1); // Start
			partTwoStart = start.substring(1, 2); // Start
			partOneEnd = end.substring(0, 1); // End
			partTwoEnd = end.substring(1, 2); // End

			if (!partOneStart.equals(partOneEnd)
					&& !partTwoStart.equals(partTwoEnd)) { // Bateau impossible
															// à placer

			} else {

				startCoord = start;
				endCoord = end;
				hitNumber = 0;

				if (partOneStart.equals(partOneEnd)) {
					vertical = true;

				} else {
					vertical = false;

				}

				tabCoord = new String[5];
				if (vertical == true) {
					int j = Integer.parseInt(partTwoStart) + 1;
					int i = 1;
					tabCoord[0] = startCoord;
					while (i < tabCoord.length
							&& !tabCoord[i - 1].equals(endCoord)) {

						tabCoord[i] = partOneStart + j;
						j++;
						i++;

					}
				} else {
					char l = partOneStart.charAt(0);
					l++;
					int i = 1;
					tabCoord[0] = startCoord;
					while (i < tabCoord.length
							&& !tabCoord[i - 1].equals(endCoord)) {

						tabCoord[i] = l + partTwoStart;
						l++;
						i++;

					}
				}
				

			}
			/*
			 * Pour savoir si le bateau est en position verticale ou en position
			 * Horizontale
			 */

		}
	}

	public String getStartCoord() {
		return startCoord;
	}

	public String getEndCoord() {
		return endCoord;
	}

	public int getHitNumber() {
		return hitNumber;
	}

	public void setHitNumber(int hitNumber) {
		this.hitNumber = hitNumber;
	}

	public boolean isVertical() {
		return vertical;
	}

	public int getSize() {
		int size = 0;
		int i = 0;
		while (tabCoord[i] != null) {
			size++;
			i++;
		}
		return size;
	}

	/*****************Code pour gérer le touché/coulé*******************/

	public boolean isHit(String missileCoord) {
		int i = 0;
		boolean touche = false;
		while (i < tabCoord.length && touche == false) {
			if (tabCoord[i] == null) {
				touche = false;
			} else {

				if (tabCoord[i].equals(missileCoord)) {
					
					int n = this.getHitNumber();
					n += 1;
					this.setHitNumber(n);
					touche = true;
				}
			}

			i++;
		}
	
		return touche;

		
	}

	public boolean isDestroyed() {
		return hitNumber == this.getSize();
	}
	
	/*****************************************************************/
	
	

}

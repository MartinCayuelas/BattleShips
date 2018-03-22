public class Ship {
	String startCoord;
	String endCoord;
	int hitNumber;
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
					&& !partTwoStart.equals(partTwoEnd)) { //Bateau impossible à placer
				
			} else {

				startCoord = start;
				endCoord = end;
				hitNumber = 0;

				if (partOneStart.equals(partOneEnd)) {
					vertical = true;

				} else {
					vertical = false;

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

	public boolean isHit(String missileCoor) {

		return true;
	}
}

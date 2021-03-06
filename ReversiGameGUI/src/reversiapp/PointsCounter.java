package reversiapp;

public class PointsCounter {
	//class members:
	int white_count;
	int black_count;
	
	/**
	 * constructor
	 */
	public PointsCounter() {
		black_count = 0;
		white_count = 0;
	}


	/**
	 * increase the counter
	 * @param sign
	 */
	public void add_one(char sign) {
		if (sign == 'X') {
			black_count++;
		}
		if (sign == 'O') {
			white_count++;
		}
	}

	/**
	 * decrease the counter
	 * @param sign
	 */
	public void minus_one(char sign) {
		if (sign == 'X') {
			black_count--;
		}
		if (sign == 'O') {
			white_count--;
		}
	}

	/**
	 * add several points
	 * @param how_much
	 * @param sign
	 */
	public void add(int how_much, char sign) {
		for (int i = 0; i < how_much; i++) {
			add_one(sign);
		}
	}

	/**
	 * delete several points
	 * @param how_much
	 * @param sign
	 */
	public void minus(int how_much, char sign) {
		for (int i = 0; i < how_much; i++) {
			minus_one(sign);
		}
	}
	/**
	 * get the first player counter
	 * @return
	 */
	public int getBlackCount() {
		return black_count;
	}
	/**
	 * get the second player counter
	 * @return
	 */
	public int getWhiteCount() {
		return white_count;
	}
}

package reversiapp;
public class Point {
	private int row,col;
	private char sign; 
	public Point() {
		this.row = 0;
		this.col = 0;
		this.sign = 'N'; // not initiaized
	}
	public Point(int row, int col, char sign) {
		this.row = row;
		this.col = col;
		this.sign = sign;
	}


//	public void printRealValues() {
//		cout << "(" << row << "," << col << ")";
//	}
//
//	void Point::printValuesPlusOne() {
//		cout << "(" << row + 1 << "," << col + 1 << ")";
//	}

//	void Point::printSign() {
//		cout << this->sign;
//	}

	public int get_row() {
		return this.row;
	}

	public int get_col() {
		return this.col;
	}

	public char get_sign() {
		return this.sign;
	}

	public boolean isSamePoint(Point other) { // delete the & dont remember how to do it right
		if (other.get_row() == row && other.get_col() == col) {
			return true;
		}
		return false;
	}

	public void set_sign(char sign) {
		this.sign = sign;
	}
}

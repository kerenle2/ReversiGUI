package reversiapp;

import java.util.ArrayList;

/**
 * 
 * Fliper
 *
 */
public class Fliper {
	private ArrayList<Point> points_to_flip = new ArrayList<Point>();
	
	/**
	 * constructor
	 */
	public Fliper() {
		
	}
	
	
	/**
	 * flips all the points needs to be flipped according to the game logic,
	 * using all the "helper functions"
	 * @return - the number of points flipped
	 * @flip_to - the sign to change to.
	*/
	public int flip(Board board, Point chosen_point, char flip_to) {
		this.points_to_flip.removeAll(this.points_to_flip);
		ArrayList<Point> temp;
		checkAndUpdateDown(chosen_point, board, flip_to);
		checkAndUpdateUp(chosen_point, board, flip_to);
		checkAndUpdateRight(chosen_point, board, flip_to);
		checkAndUpdateLeft(chosen_point, board, flip_to);
		checkAndUpdateDownRight(chosen_point, board, flip_to);
		checkAndUpdateDownLeft(chosen_point, board, flip_to);
		checkAndUpdateUpLeft(chosen_point, board, flip_to);
		checkAndUpdateUpRight(chosen_point, board, flip_to);
	
		for (int i = 0; i < this.points_to_flip.size(); i++) {
			this.points_to_flip.get(i).set_sign(flip_to);
			board.setPoint(this.points_to_flip.get(i));
		}
	
		int num_fliped = this.points_to_flip.size();
		//the board.setPoint added to the counter everything needed, but fliper needs to distract from the sign-flipped counter:
		board.getCounter().minus(num_fliped, FindOpponentSign(flip_to));
		return num_fliped;
	
	}


	/**
	 * gets a player sign and returns the opponent sign.
	 * @param my_sign - current player sign
	 * @return opponent sign
	 */
	private char FindOpponentSign(char my_sign) {
		char opp_sign = ' ';
		if (my_sign == 'X') {
			opp_sign = 'O';
		}
		if (my_sign == 'O') {
			opp_sign = 'X';
		}
		return opp_sign;
	}
	
	/**
	 * helper-functions:
	 * gets the board and the chosen point, and each function checks
	 * the direction it responsible of.
	 * @return - vector of points need to be flipped.
	 */
	private void checkAndUpdateUp(Point chosen_point, Board board, char flip_to) {
		char sign = flip_to;
		char opp_sign = FindOpponentSign(sign);
		ArrayList<Point> temp = new ArrayList<Point>();
	//	vector<Point> to_flip;
		int row = chosen_point.get_row();
		int col = chosen_point.get_col();
		int i = 1;
		while (true) {
		if (row - i >= 0 && board.getAllPointsList()[row - i][col].get_sign() == opp_sign) {
			temp.add(board.getAllPointsList()[row - i][col]);
			i++;
		}
		else
			break;
		}
		if (row - i >=0 && board.getAllPointsList()[row - i][col].get_sign() == sign) {
			if (temp.size() > 0) {
			this.points_to_flip.addAll(temp);
			}
		}
	
	}
	
	
	private void checkAndUpdateDown(Point chosen_point, Board board, char flip_to) {
		char sign = flip_to;
		char opp_sign = FindOpponentSign(sign);
		ArrayList<Point> temp = new ArrayList<Point>();
		int row = chosen_point.get_row();
		int col = chosen_point.get_col();
		int i = 1;
		while (true) {
		if (row + i < board.getBoardSize() && board.getAllPointsList()[row + i][col].get_sign() == opp_sign) {
			temp.add(board.getAllPointsList()[row + i][col]);
			i++;
		}
		else
			break;
			}
		if (row + i < board.getBoardSize() && board.getAllPointsList()[row + i][col].get_sign() == sign) {
			if (temp.size() > 0) {
				this.points_to_flip.addAll(temp);
			}
		}
	}
	
	private void checkAndUpdateRight(Point chosen_point, Board board, char flip_to) {
		char sign = flip_to;
		char opp_sign = FindOpponentSign(sign);
		ArrayList<Point> temp = new ArrayList<Point>();
		int row = chosen_point.get_row();
		int col = chosen_point.get_col();
		int i = 1;
		while (true) {
		if (col + i < board.getBoardSize() && board.getAllPointsList()[row][col + i].get_sign() == opp_sign) {
			temp.add(board.getAllPointsList()[row][col + i]);
			i++;
		}
		else
			break;
			}
		if (col + i < board.getBoardSize() && board.getAllPointsList()[row][col + i].get_sign() == sign) {
			if (temp.size() > 0) {
				this.points_to_flip.addAll(temp);
			}
		}
	}
	
	private void checkAndUpdateLeft(Point chosen_point, Board board, char flip_to) {
		char opp_sign = FindOpponentSign(flip_to);
		ArrayList<Point> temp = new ArrayList<Point>();
		int row = chosen_point.get_row();
		int col = chosen_point.get_col();
		int i = 1;
		while (true) {
		if (col - i >= 0 && board.getAllPointsList()[row][col - i].get_sign() == opp_sign) {
			temp.add(board.getAllPointsList()[row][col - i]);
			i++;
		}
		else
			break;
			}
		if (col - i >= 0 && board.getAllPointsList()[row][col - i].get_sign() == flip_to) {
			if (temp.size() > 0) {
				this.points_to_flip.addAll(temp);
			}
		}
	}
	
	private void checkAndUpdateUpRight(Point chosen_point, Board board, char flip_to) {
		char opp_sign = FindOpponentSign(flip_to);
		ArrayList<Point> temp = new ArrayList<Point>();
		int row = chosen_point.get_row();
		int col = chosen_point.get_col();
		int i = 1;
		while (true) {
		if (row - i >= 0 && col + i < board.getBoardSize() && board.getAllPointsList()[row - i][col + i].get_sign() == opp_sign) {
			temp.add(board.getAllPointsList()[row - i][col + i]);
			i++;
		}
		else
			break;
			}
		if (row - i >= 0 && col + i < board.getBoardSize()
				&& board.getAllPointsList()[row - i][col + i].get_sign() == flip_to) {
			if (temp.size() > 0) {
				this.points_to_flip.addAll(temp);
			}
		}
	}
	
	private void checkAndUpdateUpLeft(Point chosen_point, Board board, char flip_to) {
		char opp_sign = FindOpponentSign(flip_to);
		ArrayList<Point> temp = new ArrayList<Point>();
		int row = chosen_point.get_row();
		int col = chosen_point.get_col();
		int i = 1;
		while (true) {
		if (row - i >= 0 && col - i >= 0 && board.getAllPointsList()[row - i][col - i].get_sign() == opp_sign) {
			temp.add(board.getAllPointsList()[row - i][col - i]);
			i++;
		}
		else
			break;
			}
		if (row - i >= 0 && col - i >= 0 && board.getAllPointsList()[row - i][col - i].get_sign() == flip_to) {
			if (temp.size() > 0) {
				this.points_to_flip.addAll(temp);
			}
		}
	}
	
	private void checkAndUpdateDownRight(Point chosen_point, Board board, char flip_to) {
		char opp_sign = FindOpponentSign(flip_to);
		ArrayList<Point> temp = new ArrayList<Point>();
		int row = chosen_point.get_row();
		int col = chosen_point.get_col();
		int i = 1;
		while (true) {
		if (row + i < board.getBoardSize() && col + i < board.getBoardSize() &&
				board.getAllPointsList()[row + i][col + i].get_sign() == opp_sign) {
			temp.add(board.getAllPointsList()[row + i][col + i]);
			i++;
		}
		else
			break;
			}
		if (row + i < board.getBoardSize() && col + i < board.getBoardSize() &&
				board.getAllPointsList()[row + i][col + i].get_sign() == flip_to) {
			if (temp.size() > 0) {
				this.points_to_flip.addAll(temp);
			}
		}
	}
	
	private void checkAndUpdateDownLeft(Point chosen_point, Board board, char flip_to) {
		char opp_sign = FindOpponentSign(flip_to);
		ArrayList<Point> temp = new ArrayList<Point>();
		int row = chosen_point.get_row();
		int col = chosen_point.get_col();
		int i = 1;
		while (true) {
		if (row + i < board.getBoardSize() && col - i >= 0 &&
				board.getAllPointsList()[row + i][col - i].get_sign() == opp_sign) {
			temp.add(board.getAllPointsList()[row + i][col - i]);
			i++;
		}
		else
			break;
			}
		if (row + i < board.getBoardSize() && col - i >= 0 &&
				board.getAllPointsList()[row + i][col - i].get_sign() == flip_to) {
			if (temp.size() > 0) {
				this.points_to_flip.addAll(temp);
			}
		}
	}


}

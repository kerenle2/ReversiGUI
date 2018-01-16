
package reversiapp;
import java.util.ArrayList;

public class MovesCalculator {
	private ArrayList<Point> options_list;
	/**
	 * constructor
	 */
	public MovesCalculator() {
		this.options_list = new ArrayList<Point>();
	}
	
	/**
	 * check if the move is inside the list of the possible moves.
	 */
	private boolean allreadyInList(int row, int col) {
		//check if the move is inside the list of the possible moves.
		for (int i = 0; i < options_list.size(); i++) {
			Point point = options_list.get(i);
			if (row == point.get_row()  && col == point.get_col()) {
				return true;
			}
		}
		return false;
	}


	/**
	 * gets a board and a player sign and calculates all his
	 * possible moves, using all the "check directions" functions.
	 * @param board
	 * @param this_player_sign
	 * @return list of points - options of moves
	 */
	public ArrayList<Point> calc_moves(Board board, char this_player_sign) {
		//initialize the list, erase all the last options
		this.options_list.removeAll(this.options_list);
		for (int i = 0; i < board.getBoardSize(); i++) {
			for (int j = 0; j < board.getBoardSize(); j++) {
				Point p = board.getAllPointsList()[i][j];
				//if p is the opponent sign - check around her for options:
				if (p.get_sign() != this_player_sign && p.get_sign() != board.getBlank()) {
					checkDown(board, p, this_player_sign, options_list);
					checkUp(board, p, this_player_sign, options_list);
					checkRight(board, p, this_player_sign, options_list);
					checkLeft(board, p, this_player_sign, options_list);
					checkDownLeftDiagonal(board, p, this_player_sign, options_list);
					checkDownRightDiagonal(board, p, this_player_sign, options_list);
					checkUpLeftDiagonal(board, p, this_player_sign, options_list);
					checkUpRightDiagonal(board, p, this_player_sign, options_list);
				}
			}
		}
		return options_list;
	}
	


	/**
	 * for all the check_direction functions - assume they get the opponent point as current point,
	 * and check around it for possible moves.
	 * adds the moves found to 'options'
	 * @param board - the game board
	 * @param current_point - the opponent point to check around.
	 * @param this_playr_sign- char
	 * @param options- a list of options to add to.
	 */
	private void checkRight(Board board, Point current_point,
			char this_playr_sign, ArrayList<Point> options) {
		if (current_point.get_sign() != this_playr_sign && current_point.get_sign()
			!= board.getBlank()) {
			int row = current_point.get_row();
			int col = current_point.get_col();
			int i = 1;
			//if the LEFT point from this current point is this player sign -
			//continue to check RIGHT
			if (col - 1 >= 0 && board.getAllPointsList()[row][col - 1].get_sign() == this_playr_sign) {
				//keep moving RIGHT as long as its the opponent sign || board borders
				while (col + i < board.getBoardSize() && board.getAllPointsList()[row][col + i].get_sign() == current_point.get_sign()) {
					i++;
				}
				//if this point is outside the boards, return
				if (!(col + i < board.getBoardSize())){
					return;
				}
				if (board.getAllPointsList()[row][col + i].get_sign() == board.getBlank()) {
					if (!allreadyInList(row, col + i)){
						options.add(board.getAllPointsList()[row][col + i]);
					}
				}
			}
		}
	}
	
	
	/**
	 * for all the check_direction functions - assume they get the opponent point as current point,
	 * and check around it for possible moves.
	 * adds the moves found to 'options'
	 * @param board - the game board
	 * @param current_point - the opponent point to check around.
	 * @param this_playr_sign- char
	 * @param options- a list of options to add to.
	 */
	private void checkLeft(Board board, Point current_point,
		char this_playr_sign, ArrayList<Point> options) {
	if (current_point.get_sign() != this_playr_sign && current_point.get_sign() != board.getBlank()) {
		int row = current_point.get_row();
		int col = current_point.get_col();
		int i = 1;
		//if the RIGHT point from this current point is this player sign - continue to check LEFT
		if (col + 1 < board.getBoardSize() && board.getAllPointsList()[row][col + 1].get_sign() == this_playr_sign) {
			//keep moving LEFT as long as its the opponent sign || board borders
			while (col - i >= 0 && board.getAllPointsList()[row][col - i].get_sign() == current_point.get_sign()) {
				i++;
			}
			//if this point is outside the boards, return
			if (!(col - i >= 0 )){
				return;
			}
			if (board.getAllPointsList()[row][col - i].get_sign() == board.getBlank()) {
				if (!allreadyInList(row, col - i)){
					options.add(board.getAllPointsList()[row][col - i]);
				}


			}
		}
	}
}

	
	/**
	 * for all the check_direction functions - assume they get the opponent point as current point,
	 * and check around it for possible moves.
	 * adds the moves found to 'options'
	 * @param board - the game board
	 * @param current_point - the opponent point to check around.
	 * @param this_playr_sign- char
	 * @param options- a list of options to add to.
	 */
	private void checkDown(Board board, Point current_point,
		char this_playr_sign, ArrayList<Point> options) {
	if (current_point.get_sign() != this_playr_sign && current_point.get_sign() != board.getBlank()) {
		int row = current_point.get_row();
		int col = current_point.get_col();
		int i = 1;
		//if the UP point from this current point is this player sign - continue to check DOWN
		if (row - 1 >= 0 && board.getAllPointsList()[row - 1][col].get_sign() == this_playr_sign) {
			//keep moving DOWN as long as its the opponent sign || board borders
			while (row + i < board.getBoardSize() && board.getAllPointsList()[row + i][col].get_sign() == current_point.get_sign()) {
				i++;
			}
			//if this point is outside the boards, return
			if (!(row + i < board.getBoardSize())){
				return;
			}
			if (board.getAllPointsList()[row + i][col].get_sign() == board.getBlank()) {
				if (!allreadyInList(row + i, col)){
					options.add(board.getAllPointsList()[row + i][col]);
				}
			}
		}
	}
}

	/**
	 * for all the check_direction functions - assume they get the opponent point as current point,
	 * and check around it for possible moves.
	 * adds the moves found to 'options'
	 * @param board - the game board
	 * @param current_point - the opponent point to check around.
	 * @param this_playr_sign- char
	 * @param options- a list of options to add to.
	 */
	private void checkUp(Board board, Point current_point,
		char this_playr_sign, ArrayList<Point> options) {
	if (current_point.get_sign() != this_playr_sign && current_point.get_sign() != board.getBlank()) {
		int row = current_point.get_row();
		int col = current_point.get_col();
		int i = 1;
		//if the DOWN point from this current point is this player sign - continue to check UP
		if (row + 1 < board.getBoardSize() && board.getAllPointsList()[row + 1][col].get_sign() == this_playr_sign) {
			//keep moving UP as long as its the opponent sign || board borders
			while (row - i >= 0 && board.getAllPointsList()[row - i][col].get_sign() == current_point.get_sign()) {
				i++;
			}
			//if this point is outside the boards, return
			if (!(row - i >= 0)){
				return;
			}
			if (board.getAllPointsList()[row - i][col].get_sign() == board.getBlank()) {
				if (!allreadyInList(row - i, col)){
					options.add(board.getAllPointsList()[row - i][col]);
				}
			}
		}
	}
}

	/**
	 * for all the check_direction functions - assume they get the opponent point as current point,
	 * and check around it for possible moves.
	 * adds the moves found to 'options'
	 * @param board - the game board
	 * @param current_point - the opponent point to check around.
	 * @param this_playr_sign- char
	 * @param options- a list of options to add to.
	 */
	private void checkUpRightDiagonal(Board board, Point current_point,
		char this_playr_sign, ArrayList<Point> options) {
	if (current_point.get_sign() != this_playr_sign && current_point.get_sign() != board.getBlank()) {
		int row = current_point.get_row();
		int col = current_point.get_col();
		int i = 1;
		//if the DOWN-LEFT point from this current point is this player sign - continue to check UP-RIGHT
		if (row + 1 < board.getBoardSize() && col - 1 >= 0 && board.getAllPointsList()[row + 1][col - 1].get_sign() == this_playr_sign) {
			//keep moving UP-RIGHT as long as its the opponent sign || board borders
			while (row - i >= 0 && col + i < board.getBoardSize() && board.getAllPointsList()[row - i][col + i].get_sign() == current_point.get_sign()) {
				i++;
			}
			//if this point is outside the boards, return
			if (!(row - i >= 0 && col + i < board.getBoardSize())){
				return;
			}
			if (board.getAllPointsList()[row - i][col + i].get_sign() == board.getBlank()) {
				if (!allreadyInList(row - i, col + i)){
					options.add(board.getAllPointsList()[row - i][col + i]);
				}

			}
		}
	}
}

	/**
	 * for all the check_direction functions - assume they get the opponent point as current point,
	 * and check around it for possible moves.
	 * adds the moves found to 'options'
	 * @param board - the game board
	 * @param current_point - the opponent point to check around.
	 * @param this_playr_sign- char
	 * @param options- a list of options to add to.
	 */
	private void checkUpLeftDiagonal(Board board, Point current_point,
		char this_playr_sign, ArrayList<Point> options) {
	if (current_point.get_sign() != this_playr_sign && current_point.get_sign() != board.getBlank()) {
		int row = current_point.get_row();
		int col = current_point.get_col();
		int i = 1;
		//if the DOWN-RIGHT point from this current point is this player sign - continue to check UP-LEFT
		if (row + 1 < board.getBoardSize() && col + 1 < board.getBoardSize()
				&& board.getAllPointsList()[row + 1][col + 1].get_sign() == this_playr_sign) {
			//keep moving UP-LEFT as long as its the opponent sign || board borders
			while (row - i >= 0 && col - i >= 0 && board.getAllPointsList()[row - i][col - i].get_sign() == current_point.get_sign()) {
				i++;
			}
			//if this point is outside the boards, return
			if (!(row - i >= 0 && col - i >= 0)){
				return;
			}
			if (board.getAllPointsList()[row - i][col - i].get_sign() == board.getBlank()) {
				if (!allreadyInList(row - i, col - i)){
					options.add(board.getAllPointsList()[row - i][col - i]);
				}
			}
		}
	}
}

	/**
	 * for all the check_direction functions - assume they get the opponent point as current point,
	 * and check around it for possible moves.
	 * adds the moves found to 'options'
	 * @param board - the game board
	 * @param current_point - the opponent point to check around.
	 * @param this_playr_sign- char
	 * @param options- a list of options to add to.
	 */
	private void checkDownRightDiagonal(Board board, Point current_point,
		char this_playr_sign, ArrayList<Point> options) {
	if (current_point.get_sign() != this_playr_sign && current_point.get_sign() != board.getBlank()) {
		int row = current_point.get_row();
		int col = current_point.get_col();
		int i = 1;
		//if the UP-LEFT point from this current point is this player sign - continue to check DOWN-RIGHT
		if (row - 1 >= 0 && col - 1 >= 0 && board.getAllPointsList()[row - 1][col - 1].get_sign() == this_playr_sign) {
			//keep moving DOWN-RIGHT as long as its the opponent sign || board borders
			while (row + i < board.getBoardSize() && col + i < board.getBoardSize() && board.getAllPointsList()[row + i][col + i].get_sign() == current_point.get_sign()) {
				i++;
			}
			//if this point is outside the boards, return
			if (!(row + i < board.getBoardSize() && col + i < board.getBoardSize() )){
				return;
			}
			if (board.getAllPointsList()[row + i][col + i].get_sign() == board.getBlank()) {
				if (!allreadyInList(row + i, col + i)){
					options.add(board.getAllPointsList()[row + i][col + i]);
				}
			}
		}
	}
}


	/**
	 * for all the check_direction functions - assume they get the opponent point as current point,
	 * and check around it for possible moves.
	 * adds the moves found to 'options'
	 * @param board - the game board
	 * @param current_point - the opponent point to check around.
	 * @param this_playr_sign- char
	 * @param options- a list of options to add to.
	 */
	private void checkDownLeftDiagonal(Board board, Point current_point,
			char this_playr_sign, ArrayList<Point> options) {
		if (current_point.get_sign() != this_playr_sign && current_point.get_sign() != board.getBlank()) {
			int row = current_point.get_row();
			int col = current_point.get_col();
			int i = 1;
			//if the UP-RIGHT point from this current point is this player sign - continue to check DOWN-LEFT
			if (row - 1 >= 0 && col + 1 < board.getBoardSize() && board.getAllPointsList()[row - 1][col + 1].get_sign() == this_playr_sign) {
				//keep moving DOWN-LEFT as long as its the opponent sign || board borders
				while (row + i < board.getBoardSize() && col - i >= 0 && board.getAllPointsList()[row + i][col - i].get_sign() == current_point.get_sign()) {
					i++;
				}
				//if this point is outside the boards, return
				if (!(row + i < board.getBoardSize() && col - i >= 0)){
					return;
				}
				if (board.getAllPointsList()[row + i][col - i].get_sign() == board.getBlank()) {
					if (!allreadyInList(row + i, col - i)){
						options.add(board.getAllPointsList()[row + i][col - i]);
					}

				}
			}
		}

	}
	
}

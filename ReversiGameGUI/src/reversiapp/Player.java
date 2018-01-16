package reversiapp;

import java.util.ArrayList;


public class Player {
	private char sign;
	private boolean no_moves;
	private int num;

	/**
	 * Constructor.
	 * @param sign
	 */
	public Player(char sign) {
		this.sign = sign;
		this.no_moves = false;
		if (sign == 'X'){
			this.num = 1;
		}
		if (sign == 'O'){
			this.num = 2;
		}
	}
	/**
	 * return true if the player have no moves.
	 * @return
	 */
	public boolean getNomoves() {
		return this.no_moves;
	}
	/**
	 * get the list of the possible moves.
	 * @param board
	 * @param moves_calculator
	 * @return
	 */
	public ArrayList<Point> get_possible_moves(Board board,
			MovesCalculator moves_calculator) {
		this.no_moves = false;
		ArrayList<Point> options = moves_calculator.calc_moves(board, this.sign);
		if (options.size() == 0) {
			this.no_moves = true;
		}
		board.setPossibleMoves(options);
		return options;
	}

	/**
	 * get the sign of the player (X/O)
	 * @return
	 */
	public char get_sign() {
		return this.sign;
	}
	
	/**
	 * set the sign
	 * @param sign
	 */
	public void set_sign(char sign){
		this.sign = sign;
	}

	/**
	 * get the number of the player
	 * @return
	 */
	public int getNum() {
		return this.num;
	}

}
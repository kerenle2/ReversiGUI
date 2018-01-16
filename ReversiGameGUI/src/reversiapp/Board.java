package reversiapp;

import java.util.ArrayList;

public class Board {
	
	private PointsCounter counter;
	private int board_size;
	private ArrayList<Point> possible_moves;
	private Point[][] all_points_list;
	private static char black_player = 'X';
	private static char white_player = 'O';
	private static char blank = ' ';
	
	public Board() {
		
	}
	/**
	 * constructor.
	 * @param board_size
	 */
	public Board(int board_size) {
		this.board_size = board_size;
		this.possible_moves = new ArrayList<Point>();
		this.all_points_list = new Point[board_size][board_size];
		this.counter = new PointsCounter();
		
		//initialize all points to blank:
		for (int i = 0; i < this.board_size; i++) {
			for (int j = 0; j < this.board_size; j++) {
				all_points_list[i][j] = new Point(i, j, blank);
			}
		}
	}
	/**
	 * get the list of the players.
	 * @return
	 */
	public Point[][] getAllPointsList() {
		return this.all_points_list;
	}
	
	/**
	 * sets point on the board
	 * @param p
	 */
	public void setPoint(Point p){
		int row = p.get_row();
		int col = p.get_col();
		char sign = p.get_sign();
		this.all_points_list[row][col].set_sign(sign);
		//increase the counter.
		if (p.get_sign() == 'X' || p.get_sign() == 'O') {
			this.counter.add_one(sign);
		}
	}

	/**
	 * set the possible moves in the list.
	 * @param possible_moves
	 */
	public void setPossibleMoves(ArrayList<Point> possible_moves) {
		this.possible_moves.removeAll(this.possible_moves);
		this.possible_moves.addAll(possible_moves);
	}
	/**
	 * get the possible moves list.
	 * @return
	 */
	public ArrayList<Point> getPossibleMoves() {
		return this.possible_moves;
	}
	/**
	 * get the counter
	 * @return
	 */
	PointsCounter getCounter() {
		return counter;
	}
	
	/**
	 * get the size of the board
	 * @return
	 */
	public int getBoardSize() {
		return this.board_size;
	}
	/**
	 * return blank char.
	 * @return
	 */
	public char getBlank() {
		return this.blank;
	}
	/**
	 * return true if the board is full, otherwise false.
	 * @return
	 */
	public boolean isFull() {
		int capacity = board_size * board_size;
		return counter.getBlackCount() + counter.getWhiteCount() >= capacity;

	}
	
	
}

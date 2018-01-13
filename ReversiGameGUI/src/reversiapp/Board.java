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
	
	public Point[][] getAllPointsList() {
		return this.all_points_list;
	}
	

	public void setPoint(Point p){
		int row = p.get_row();
		int col = p.get_col();
		char sign = p.get_sign();
		this.all_points_list[row][col].set_sign(sign);
		if (p.get_sign() == 'X' || p.get_sign() == 'O') {
			this.counter.add_one(sign);
		}
	}


	public void setPossibleMoves(ArrayList<Point> possible_moves) {
		this.possible_moves.removeAll(this.possible_moves);
		this.possible_moves.addAll(possible_moves);
	}
	
	public ArrayList<Point> getPossibleMoves() {
		return this.possible_moves;
	}
	
	PointsCounter getCounter() {
		return counter;
	}
	
	
	public int getBoardSize() {
		return this.board_size;
	}
	
	public char getBlank() {
		return this.blank;
	}
	
	public boolean isFull() {
		int capacity = board_size * board_size;
		return counter.getBlackCount() + counter.getWhiteCount() >= capacity;

	}
	
	
}

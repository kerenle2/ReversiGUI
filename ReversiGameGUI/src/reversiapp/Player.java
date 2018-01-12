package reversiapp;

import java.util.ArrayList;

import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class Player {
	private GridPane grid;
	private int row;
	private int col;
	private ImageView iv;
	private char sign;
	private boolean no_moves;
	private MovesCalculator moves_calculator;
	
	public Player(GridPane grid, char sign) {
		this.grid = grid;
//		this.row = row;
//		this.col = col;
		this.sign = sign;
		this.moves_calculator = new MovesCalculator();
		this.no_moves = false;

		
		// Load the player's image
		iv = new
	ImageView(getClass().getResource("mandala.png").toExternalForm());
	}

	public boolean getNomoves() {
		return this.no_moves;
	}
	
//	public void moveUp() {
//		row--; // need to check that player doesn't hit a wall
//		redraw();
//		}
//		public void moveDown() {
//		row++;
//		redraw();
//		}
//		public void moveLeft() {
//		col--;
//		redraw();
//		}
//		public void moveRight() {
//		col++;
//		redraw();
//		}
//		private void redraw() {
//		grid.getChildren().remove(iv);
//		grid.add(iv, col, row);
//		}
		
	
	ArrayList<Point> get_possible_moves(ReversiBoardController board,
			MovesCalculator moves_calculator) {
		this.no_moves = false;
		ArrayList<Point> options = moves_calculator.calc_moves(board, this.sign);
		if (options.size() == 0) {
			this.no_moves = true;
		}
		board.setPossibleMoves(options);
		return options;
	}

	public Point play_one_turn(ReversiBoardController board, Fliper flip) {
		ArrayList<Point> options = get_possible_moves(board, this.moves_calculator);
		if (options.size() == 0 || (options.get(0).get_col() == -1 && options.get(0).get_row() == -1)) {
			return new Point(-1,-1, 'Y'); //no point
		}
		Point chosen_point = choose_best_move(options, flip, board);
		play_next_step(board, chosen_point);
		return chosen_point;
	}
	
	public Point choose_best_move(ArrayList<Point> options_list, Fliper flip, ReversiBoardController board) {
		//cout << "please choose your next step row,column" << endl;
//		char dummy;
//		int x;
//		int y;
//		int i = 0;
//		Point chosen_step;
//		while (true) {
//		x = 0;
//		y = 0;
//		cin >> x >> dummy >> y; //  x,y
//		chosen_step = Point(x - 1, y - 1, this->sign);
//		if (cin.fail()) {
//			cin.clear();
//			cin.ignore(256, '\n');
//			cout << "you have entered an inValid point." << endl << "please enter one of your options." << endl;
//				cout << sign <<", your possible moves are:" << endl;
//				for (i = 0; i < options_list.size(); i++) {
//					options_list[i].printValuesPlusOne();
//					cout <<"  ";
//				}
//				cout << endl;
//		}
//		else {
//			if(isAnOption(chosen_step, options_list)) {
//				return chosen_step;
//			}
//			else {
//				cout << "you have entered an inValid point. please enter one of your options." << endl;
//					cout << sign <<", your possible moves are:" << endl;
//					for (i = 0; i < options_list.size(); i++) {
//						options_list[i].printValuesPlusOne();
//						cout <<"  ";
//					}
//					cout << endl;
//			}
//		}
//		}
		
		return new Point(0,0,'d'); //DELETEEEEEEEEE later
	}


	public char get_sign() {
		return this.sign;
	}
	
	
	public void set_sign(char sign){
		this.sign = sign;
	}
	
	
	public void play_next_step(ReversiBoardController board, Point chosen_step) {
		board.setPoint(chosen_step);
		board.getCounter().add_one(chosen_step.get_sign());
	}

	
	private boolean isAnOption(Point p, ArrayList<Point> options) {
		for (int i = 0; i < options.size(); i++) {
			if (p.get_row() == options.get(i).get_row()
					&& p.get_col() == options.get(i).get_col()) {
				return true;
			}
		}
		return false;
	}
	


	public void draw(int cellWidth, int cellHeight, int row, int col) {
		grid.add(iv, col, row);
		iv.setFitWidth(cellWidth);
		iv.setFitHeight(cellHeight);
		grid.getChildren().remove(iv);
		grid.add(iv, col, row);
		}
}
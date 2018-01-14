package reversiapp;

import java.util.ArrayList;

import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class Player {
	private char sign;
	private boolean no_moves;
	
//	public Player(GridPane grid, char sign) {
//		this.grid = grid;
//		this.sign = sign;
//		this.moves_calculator = new MovesCalculator();
//		this.no_moves = false;
//
//		
//		// Load the player's image
//		iv = new
//	ImageView(getClass().getResource("mandala.png").toExternalForm());
//	}

	public Player(char sign) {
		this.sign = sign;
		this.no_moves = false;

	}
	
	
	public boolean getNomoves() {
		return this.no_moves;
	}

	
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

	
	public char get_sign() {
		return this.sign;
	}
	
	
	public void set_sign(char sign){
		this.sign = sign;
	}
	
	
//	private boolean isAnOption(Point p, ArrayList<Point> options) {
//		for (int i = 0; i < options.size(); i++) {
//			if (p.get_row() == options.get(i).get_row()
//					&& p.get_col() == options.get(i).get_col()) {
//				return true;
//			}
//		}
//		return false;
//	}
//	


//	public void draw(int cellWidth, int cellHeight, int row, int col) {
//		grid.add(iv, col, row);
//		iv.setFitWidth(cellWidth);
//		iv.setFitHeight(cellHeight);
//		grid.getChildren().remove(iv);
//		grid.add(iv, col, row);
//		}
}
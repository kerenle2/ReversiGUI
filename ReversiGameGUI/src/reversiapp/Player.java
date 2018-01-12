package reversiapp;

import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class Player {
	private GridPane grid;
	private int row;
	private int col;
	private ImageView iv;
	private char sign;
	
	public Player(GridPane grid, char sign) {
		this.grid = grid;
//		this.row = row;
//		this.col = col;
		this.sign = sign;
		// Load the player's image
		iv = new
	ImageView(getClass().getResource("mandala.png").toExternalForm());
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
		
	public void draw(int cellWidth, int cellHeight, int row, int col) {
		grid.add(iv, col, row);
		iv.setFitWidth(cellWidth);
		iv.setFitHeight(cellHeight);
		grid.getChildren().remove(iv);
		grid.add(iv, col, row);
		}
}
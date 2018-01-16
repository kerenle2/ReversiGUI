package reversiapp;

import javafx.scene.Group;

import javafx.scene.Node;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;



public class Point {
	private int row,col;
	private char sign; 
	public Point() {
		this.row = 0;
		this.col = 0;
		this.sign = 'N'; // not initiaized
	}
	/**
	 * constructor.
	 * @param row
	 * @param col
	 * @param sign
	 */
	public Point(int row, int col, char sign) {
		this.row = row;
		this.col = col;
		this.sign = sign;
	}

	/**
	 * get row number.
	 * @return
	 */
	public int get_row() {
		return this.row;
	}
	/**
	 * got column number
	 * @return
	 */
	public int get_col() {
		return this.col;		
	}
	/**
	 * get sign of the player in this point
	 * @return
	 */
	public char get_sign() {
		return this.sign;
	}
	/**
	 * check if its the same point
	 * @param other
	 * @return
	 */
	public boolean isSamePoint(Point other) { 
		if (other.get_row() == row && other.get_col() == col) {
			return true;
		}
		return false;
	}
	/**
	 * set the sign of the player in this point 
	 * @param sign
	 */
	public void set_sign(char sign) {
		this.sign = sign;
	}
	
	/**
	 * get the color of the player in this point
	 * @param color
	 * @return
	 */
	public Color getColor(String color){
//		 String str = color.toLowerCase();
		 Color color_tmp = Color.valueOf(color);
		// ColorPicker c = 
		 return color_tmp;
		
	}
	
	/**
	 * draw the player in this point
	 * @param cellWidth
	 * @param this_player_color
	 * @param opponent_color
	 * @return
	 */	
	public Node draw(int cellWidth, String this_player_color, String opponent_color) {
		Color upper_color = getColor(this_player_color);
		Color lower_color = getColor(opponent_color);	
		
		Group group = new Group();
		Circle lower = new Circle(cellWidth/2.5, lower_color);
		group.getChildren().add(lower);
		Circle upper = new Circle(cellWidth/2.5, upper_color);
		upper.setLayoutY(cellWidth/2.5 - cellWidth / 2.3);
		group.getChildren().add(upper);

		return group;

	}

}

package reversiapp;

import javafx.scene.Group;
import java.lang.reflect.Field;

import javafx.scene.Node;
import javafx.scene.paint.Paint;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;



public class Point {
	private int row,col;
	private char sign; 
	private String color1;
	private String color2;
	public Point() {
		this.row = 0;
		this.col = 0;
		this.sign = 'N'; // not initiaized
//		this.color1 = 0;
//		this.color2 = 0;
	}
	
	public Point(int row, int col, char sign) {
		this.row = row;
		this.col = col;
		this.sign = sign;
	}


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

	
	public Node draw(int cellWidth) {
		if (this.sign == 'X') {
			Group group = new Group();
			
			Circle upper = new Circle(cellWidth/2.5, Color.WHITE);
			upper.setLayoutY(cellWidth/2.5 - 17);
			group.getChildren().add(upper);
			Circle lower = new Circle(cellWidth/2.5, Color.BLACK);
			group.getChildren().add(lower);
			return group;


		}
		else if (this.sign == 'O') {
			Group group = new Group();
			Circle upper = new Circle(cellWidth/2.5, Color.BLACK);
			upper.setLayoutY(cellWidth/2.5 - 17);
			group.getChildren().add(upper);
			Circle lower = new Circle(cellWidth/2.5, Color.WHITE);
			group.getChildren().add(lower);
			return group;
		}
		
		else return null;
	}
	
	public Color getColor(String color){
		 String str = color.toLowerCase();
		 Color color_tmp = Color.valueOf(str);
		 return color_tmp;
	}
	
	public Node draw(int cellWidth, String color) {
		Color color_tmp = getColor(color);
		Circle player = new Circle(cellWidth/2.5, color_tmp);
		return player;
	}
	
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

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
}

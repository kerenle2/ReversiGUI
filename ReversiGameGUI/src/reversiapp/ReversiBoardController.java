package reversiapp;

import java.io.IOException;

import javax.management.RuntimeErrorException;

//import com.sun.javafx.geom.Rectangle;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import reversiapp.Point;
public class ReversiBoardController extends GridPane{
	private int[][] board;
	private static final int FREE = 0;

	private Point[][] all_players_list;
	private int board_size;
	private Text row_col_num;
	public ReversiBoardController(int[][] board){
		this.board = board;
		this.board_size = board.length;
		this.all_players_list = new Point[board_size][board_size];
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ReversiBoard.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);

		for (int i = 0; i < this.board_size; i++) {
			for (int j = 0; j < this.board_size; j++) {
				all_players_list[i][j] = new Point(i, j, ' ');
			}
		}

//		try{
//			fxmlLoader.load();
//			this.setOnKeyPressed(event -> {
//				switch (event.getCode()) {
//				case DOWN:
//				black_player.moveDown();
//				break;
//				case UP:
//				black_player.moveUp();
//				break;
//				case LEFT:
//					black_player.moveLeft();
//				break;
//				case RIGHT:
//					black_player.moveRight();
//				break;
//				}
//				event.consume();
//			});
//			
//		} catch (IOException e) {
//			throw new RuntimeException(e);
//		}
	}
	
	public void setPoint(Point p){
		int row = p.get_row();
		int col = p.get_col();
		char sign = p.get_sign();
		this.all_players_list[row][col].set_sign(sign);


	}
	public void draw(){
		this.getChildren().clear();
		 
		int height = (int)this.getPrefHeight();
		int width = (int)this.getPrefWidth();
		
		int cellHeight = height / board.length;
		int cellWidth = width / board[0].length;
		
		//draw board 
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				
				if (board[i][j] == FREE){
					Rectangle rec = new Rectangle(cellWidth, cellHeight, Color.WHITE);
					rec.setStroke(Color.BLACK);
					this.add(rec, j, i);
				}
				if (i == 0){
					if (j == 0) continue;
					String str = Integer.toString(j);
					this.row_col_num = new Text("   " + str);
					this.row_col_num.setFont(Font.font("Arial Black", cellWidth/3));
					this.add(row_col_num, j, i);
				}
				if (j == 0){
					if (i == 0) continue;
					String str = Integer.toString(i);
					this.row_col_num = new Text("   " + str);
					this.row_col_num.setFont(Font.font("Arial Black", cellWidth/3));
					this.add(row_col_num, j, i);
				}
			}
		}
		//draw all players
		for (int i = 0; i < board_size; i++) {
			for (int j = 0; j < board_size; j++) {
				if (all_players_list[i][j].get_sign() == 'X'){
					Circle player = new Circle(cellWidth/2.5, Color.BLACK);
					this.add(player, j, i);
				}
				
				else if(all_players_list[i][j].get_sign() == 'O'){
					Circle player = new Circle(cellWidth/2.5, Color.RED);
					this.add(player, j, i);
				}
				else continue;
			}
		}
	}
		 
}

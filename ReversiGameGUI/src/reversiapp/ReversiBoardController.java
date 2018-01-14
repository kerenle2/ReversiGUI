package reversiapp;

import java.awt.event.ActionEvent;
import java.awt.print.Printable;
import java.io.IOException;
import java.util.ArrayList;

import javax.management.RuntimeErrorException;

import javafx.event.EventHandler;
//import com.sun.javafx.geom.Rectangle;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import reversiapp.Point;


public class ReversiBoardController extends GridPane{
	Board board;
//	private int[][] board;
	TurnBase turn_base;
	private FXMLLoader fxmlLoader;
	private static final int FREE = 0;
	private int board_size;
	private Text row_col_num;
	
	public ReversiBoardController(int board_size){
		//this.board = board;
	//	this.board_size = board.length;
		
		this.board_size = board_size;
		this.board = new Board(board_size);
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ReversiBoard.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		this.fxmlLoader = fxmlLoader;
		
		
//moves this part to board
//		for (int i = 0; i < this.board_size; i++) {
//			for (int j = 0; j < this.board_size; j++) {
//				all_players_list[i][j] = new Point(i, j, ' ');
//			}
//		}

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
	int width = (int)this.getPrefWidth();
	

	

	public int getBoardSize() {
		return this.board_size;
	}
	
	
	private void drawPossibleMoves(ArrayList<Point> possible_moves, int cellWidth, int cellHeight) {
		//IMPLEMENT HERE AND CALL FROM DRAW

		}
	
	
	public void draw(){
		this.getChildren().clear();
		 
		int height = (int)this.getPrefHeight();
		int width = (int)this.getPrefWidth();
		
		int cellHeight = height / board_size;
		int cellWidth = width / board_size;
		
		//draw board 
		for (int i = 0; i < board_size; i++) {
			for (int j = 0; j < board_size; j++) {
				
				//if (board.getAllPointsList()[i][j].get_sign() == " "){
					Rectangle rec = new Rectangle(cellWidth, cellHeight, Color.ANTIQUEWHITE);
					rec.setStroke(Color.BLACK);
					this.add(rec, j, i);
			//	}
				
//				if (i == 0){
//					if (j == 0) continue;
//					String str = Integer.toString(j);
//					this.row_col_num = new Text("   " + str);
//					this.row_col_num.setFont(Font.font("Arial Black", cellWidth/3));
//					this.add(row_col_num, j, i);
//				}
//				if (j == 0){
//					if (i == 0) continue;
//					String str = Integer.toString(i);
//					this.row_col_num = new Text("   " + str);
//					this.row_col_num.setFont(Font.font("Arial Black", cellWidth/3));
//					this.add(row_col_num, j, i);
//				}
			}
		}
		

		//draw all players
		for (int i = 0; i < board_size; i++) {
			for (int j = 0; j < board_size; j++) {
				if (board.getAllPointsList()[i][j].get_sign() == 'X'){
					//Circle player = new Circle(cellWidth/2.5, Color.BLACK);
					Node n = board.getAllPointsList()[i][j].draw(cellWidth);
					this.add(n, j, i);
				}
				
				else if(board.getAllPointsList()[i][j].get_sign() == 'O'){
				//	Circle player = new Circle(cellWidth/2.5, Color.WHITE);
					Node n = board.getAllPointsList()[i][j].draw(cellWidth);
					this.add(n, j, i);
				}
				else continue;
			}
		}

		//make buttons for the possible moves:
		for (int i = 0; i < board.getPossibleMoves().size(); i++) {
			Button move = new Button();
			move.setPrefWidth(cellWidth);
			move.setPrefHeight(cellWidth);

			String s ="<ColorInput" + "{paint=}" + "#eb25e4" + "/>"; //it doesn't work yet!!! trying to add colors to button to each player moves
			move.setStyle(s);
			this.add(move, board.getPossibleMoves().get(i).get_col(), board.getPossibleMoves().get(i).get_row());
			String row = String.valueOf(board.getPossibleMoves().get(i).get_row());
			row = row.concat(",");
			String col = String.valueOf(board.getPossibleMoves().get(i).get_col());
			String id = row.concat(col);
			move.setId(id);
			move.setOnAction(event-> {
				ReversiGameController.handlePointClick(move);
			});
		}

	}
		 
	public void setPoint(Point p){
		this.board.setPoint(p);
	}
	
	public Board getBoard() {
		return this.board;
	}
	public boolean isFull() {
		return this.board.isFull();

	}
	
}

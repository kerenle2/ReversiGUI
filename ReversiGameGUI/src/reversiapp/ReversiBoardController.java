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
import javafx.scene.shape.Rectangle;

import reversiapp.Point;


public class ReversiBoardController extends GridPane{
	Board board;
	TurnBase turn_base;
	private FXMLLoader fxmlLoader;
	private int board_size;
	private boolean game_ended;
	private Settings game_settings;
	
	public ReversiBoardController(int board_size, Settings game_settings){
		this.game_settings = game_settings;
		this.game_ended = false;
		this.board_size = board_size;
		this.board = new Board(board_size);
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ReversiBoard.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		this.fxmlLoader = fxmlLoader;
}
	
	public int getBoardSize() {
		return this.board_size;
	}
	
	
//	private void drawPossibleMoves(ArrayList<Point> possible_moves, int cellWidth, int cellHeight) {
//		//IMPLEMENT HERE AND CALL FROM DRAW
//
//		}
	
	public String checkColor2(){
		if (this.game_settings.getFirstPlayer() == this.game_settings.getColor1()){
			return this.game_settings.getColor2();
		}
		else if (this.game_settings.getFirstPlayer() == this.game_settings.getColor2()){
			return this.game_settings.getColor1();
		}
		else return null; //there is an error
	}
	public void draw(){
		this.getChildren().clear();
		int height = (int)this.getPrefHeight();
		int width = (int)this.getPrefWidth();
		
		int cellHeight = height / board_size;
		int cellWidth = width / board_size;
		
		for (int i = 0; i < board_size; i++) {
			for (int j = 0; j < board_size; j++) {
					Rectangle rec = new Rectangle(cellWidth, cellHeight, Color.ANTIQUEWHITE);
					rec.setStroke(Color.BLACK);
					this.add(rec, j, i);
			}
		}
		

		//draw all players
		for (int i = 0; i < board_size; i++) {
			for (int j = 0; j < board_size; j++) {
				if (board.getAllPointsList()[i][j].get_sign() == 'X'){
					Node n = board.getAllPointsList()[i][j].draw(cellWidth, game_settings.getFirstPlayer());
					this.add(n, j, i);
				}
				String color2 = checkColor2();
				if ( board.getAllPointsList()[i][j].get_sign() == 'O'){
					Node n = board.getAllPointsList()[i][j].draw(cellWidth, color2);
					this.add(n, j, i);
				}

//				else continue;
			}
		}

		//make buttons for the possible moves:
		if (!game_ended) {
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

	} //end of draw funtion
		 
	public void setPoint(Point p){
		this.board.setPoint(p);
	}
	
	public Board getBoard() {
		return this.board;
	}
	
	public void setGameEnded(boolean TorF) {
		this.game_ended = TorF;
		this.board.getPossibleMoves().removeAll(this.board.getPossibleMoves());
	}
	
	public boolean isFull() {
		return this.board.isFull();

	}
	
}

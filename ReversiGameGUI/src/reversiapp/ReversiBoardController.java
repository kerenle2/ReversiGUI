package reversiapp;

import java.io.IOException;

import javax.management.RuntimeErrorException;

//import com.sun.javafx.geom.Rectangle;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class ReversiBoardController extends GridPane{
	private int[][] board;
	private static final int FREE = 0;
//	private static final int WALL = 1;
	private Player player;
	
	public ReversiBoardController(int[][] board){
		this.board = board;
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ReversiBoard.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		this.player = new Player(this, 0, 0);
		try{
			fxmlLoader.load();
			this.setOnKeyPressed(event -> {
				switch (event.getCode()) {
				case DOWN:
				player.moveDown();
				break;
				case UP:
				player.moveUp();
				break;
				case LEFT:
				player.moveLeft();
				break;
				case RIGHT:
				player.moveRight();
				break;
				}
				event.consume();
			});
			
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	public void draw(){
		this.getChildren().clear();
		 
		int height = (int)this.getPrefHeight();
		int width = (int)this.getPrefWidth();
		
		int cellHeight = height / board.length;
		int cellWidth = width / board[0].length;
		

		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (board[i][j] == FREE){
					Rectangle rec = new Rectangle(cellWidth, cellHeight, Color.WHITE);
					rec.setStroke(Color.BLACK);
					this.add(rec, j, i);
				}
				else
					this.add(new Rectangle(cellWidth, cellHeight, Color.BLACK), j, i);
			}
		}
		player.draw(cellWidth, cellHeight);
	}
		 
}

package GUIRelated;

import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import reversiapp.Board;
import reversiapp.Point;
import reversiapp.Settings;


public class ReversiBoardController extends GridPane {
	private Board board;
	private int board_size;
	private boolean game_ended;
	private Settings game_settings;
	
	/**
	 * constructor.
	 * @param board_size
	 * @param game_settings
	 */
	public ReversiBoardController(int board_size, Settings game_settings){
		this.game_settings = game_settings;
		this.game_ended = false;
		this.board_size = board_size;
		this.board = new Board(board_size);
	}

	/**
	 * get board size
	 * @return
	 */
	public int getBoardSize() {
		return this.board_size;
	}
	
	/**
	 * check what is the color of the second player
	 * @return
	 */
	public String checkColor2(){
		if (this.game_settings.getFirstPlayer() == this.game_settings.getColor1()){
			return this.game_settings.getColor2();
		}
		else if (this.game_settings.getFirstPlayer() == this.game_settings.getColor2()){
			return this.game_settings.getColor1();
		}
		else return null; //if there is an error return null
	}
	
	/**
	 * draw the board
	 */
	public void draw(){

		this.getChildren().clear();
		int height = (int)this.getPrefHeight();
		int width = (int)this.getPrefWidth();
		
		int cellHeight = height / board_size;
		int cellWidth = width / board_size;
		
		//draw all cells
		for (int i = 0; i < board_size; i++) {
			for (int j = 0; j < board_size; j++) {
					Rectangle rec = new Rectangle(cellWidth, cellHeight, Paint.valueOf("#2D739A"));
					rec.setStroke(Color.BLACK);
					this.add(rec, j, i);
			}
		}
		//draw all players
		String first_player_color = game_settings.getFirstPlayer();
		String second_player_color= checkColor2();
		
		for (int i = 0; i < board_size; i++) {
			for (int j = 0; j < board_size; j++) {
				if (board.getAllPointsList()[i][j].get_sign() == 'X'){
					Node n = board.getAllPointsList()[i][j].draw(cellWidth, first_player_color, second_player_color);
					GridPane.setHalignment(n, HPos.CENTER);
					GridPane.setValignment(n, VPos.CENTER);
					this.add(n, j, i);
				}
				if ( board.getAllPointsList()[i][j].get_sign() == 'O'){
					Node n = board.getAllPointsList()[i][j].draw(cellWidth, second_player_color, first_player_color);
					GridPane.setHalignment(n, HPos.CENTER);
					GridPane.setValignment(n, VPos.CENTER);
					this.add(n, j, i);
				}
			}
		}

		//make buttons for the possible moves:
		if (!game_ended) {
		for (int i = 0; i < board.getPossibleMoves().size(); i++) {
			Button move = new Button();
			move.setPrefWidth(cellWidth);
			move.setPrefHeight(cellWidth);
			this.add(move, board.getPossibleMoves().get(i).get_col(), board.getPossibleMoves().get(i).get_row());
			String row = String.valueOf(board.getPossibleMoves().get(i).get_row());
			row = row.concat(",");
			String col = String.valueOf(board.getPossibleMoves().get(i).get_col());
			String id = row.concat(col);
			move.setId(id);
			move.setOnAction(event-> {
				ReversiGameController.handlePointClick(move);
				if (game_ended){
					Main.primaryStage.setScene(Main.menu_scene);

				}
			});
			
		}
		

		} 
	} //end of draw function
	
	/**
	 * set the move on the board
	 * @param p
	 */
	public void setPoint(Point p){
		this.board.setPoint(p);
	}
	/**
	 * get the board
	 * @return
	 */
	public Board getBoard() {
		return this.board;
	}
	/**
	 * return true if the game ended
	 * @param TorF
	 */
	public void setGameEnded(boolean TorF) {
		this.game_ended = TorF;
		this.board.getPossibleMoves().removeAll(this.board.getPossibleMoves());
	}
	/**
	 * return true if the board is full
	 * @return
	 */
	public boolean isFull() {
		return this.board.isFull();

	}
}

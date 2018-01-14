package reversiapp;


import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.event.ActionEvent;

public class ReversiGameController implements Initializable{
	
	public ReversiGameController() {
		// TODO Auto-generated constructor stub
	}
	
	@FXML
	private AnchorPane root;
	@FXML
	private Button start = new Button();
	private int board_size;	 
	private int[][]board;
	//private Player[] players = new Player[2];
	private ArrayList<Player> players;
	private static TurnBase turn_base;

	@Override	
	public void initialize(URL location, ResourceBundle resources) {

		this.board_size = 3;
	this.players = new ArrayList<Player>();

	}
	@FXML
	public void start(ActionEvent event){


		
		ReversiBoardController reversiBoard = new ReversiBoardController(board_size);
	//	root.setOnKeyPressed(reversiBoard.getOnKeyPressed());
		
		
		reversiBoard.setPoint(new Point(board_size/2 -1, board_size/2 - 1, 'O'));
		reversiBoard.setPoint(new Point(board_size/2 + 1 - 1, board_size/2 + 1 - 1, 'O'));
		reversiBoard.setPoint(new Point(board_size/2 - 1, board_size/2 + 1 - 1, 'X'));
		reversiBoard.setPoint(new Point(board_size/2 + 1 - 1, board_size/2 - 1, 'X'));
		
		reversiBoard.setPrefWidth(400);
		reversiBoard.setPrefHeight(400);
		root.getChildren().add(reversiBoard);
		
		//to handle window resize
		root.widthProperty().addListener((observable, oldValue, newValue) -> {
			double boardNewWidth = newValue.doubleValue();
			reversiBoard.setPrefWidth(boardNewWidth);
			reversiBoard.draw();
			});
			root.heightProperty().addListener((observable, oldValue, newValue) -> {
				reversiBoard.setPrefHeight(newValue.doubleValue());
				reversiBoard.draw();
			});
			
			Player black = new Player('X');
			Player white = new Player('O');
			players.add(black);
			players.add(white);
			
			this.turn_base = new TurnBase(reversiBoard, players);

			//initialize first turn possible moves:
			black.get_possible_moves(reversiBoard.getBoard(), turn_base.getMovesCalculator());
			
			reversiBoard.draw();
	}

	
	public static void handlePointClick(Button move) {
		String[] rowAndCol = move.getId().split(",");
		int row = Integer.parseInt(rowAndCol[0]);	
		int col = Integer.parseInt(rowAndCol[1]);
		Point chosen_point = new Point(row, col,' ');
		turn_base.play_game(chosen_point);
	}
	


}

package reversiapp;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;

public class ReversiGameController implements Initializable{
	@FXML
	private HBox root;
	private int board_size;	 
	private int[][]board;		 
	private Player[] players;

	@Override	
	public void initialize(URL location, ResourceBundle resources) {
		this.board_size = 6;
		//initialize the board
		this.board = new int[board_size + 1][board_size + 1];
		for (int i = 0; i <= board_size; i++){
			for (int j = 0; j <= board_size; j++){
				this.board[i][j] = 0;
			}
		}
		ReversiBoardController reversiBoard = new ReversiBoardController(board);
		root.setOnKeyPressed(reversiBoard.getOnKeyPressed());
		reversiBoard.setPoint(new Point(board.length/2 , board.length/2 , 'O'));
		reversiBoard.setPoint(new Point(board.length/2 +1 , board.length/2 + 1 , 'O'));
		reversiBoard.setPoint(new Point(board.length/2 , board.length/2 +1 , 'X'));
		reversiBoard.setPoint(new Point(board.length/2 +1, board.length/2 , 'X'));

		reversiBoard.setPrefWidth(400);
		reversiBoard.setPrefHeight(400);
		
		root.getChildren().add(0, reversiBoard);
		reversiBoard.draw();
		//to handle window resize
		root.widthProperty().addListener((observable, oldValue, newValue) -> {
			double boardNewWidth = newValue.doubleValue() - 120;
			reversiBoard.setPrefWidth(boardNewWidth);
			reversiBoard.draw();
			});
			root.heightProperty().addListener((observable, oldValue, newValue) -> {
				reversiBoard.setPrefHeight(newValue.doubleValue());
				reversiBoard.draw();
			});
	}
	
}

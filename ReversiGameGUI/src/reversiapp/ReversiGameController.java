package reversiapp;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.event.ActionEvent;

public class ReversiGameController implements Initializable{
	@FXML
	private AnchorPane root;
	@FXML
	private Button start;
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
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ReversiGame.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		root.setOnMouseClicked(event-> {this.start(event);});

//		root.setOnKeyPressed(reversiBoard.getOnKeyPressed());
	
		

	}
	@FXML
	public void start(MouseEvent event){
		ReversiBoardController reversiBoard = new ReversiBoardController(board);
		reversiBoard.setPoint(new Point(board.length/2 , board.length/2 , 'O'));
		reversiBoard.setPoint(new Point(board.length/2 +1 , board.length/2 + 1 , 'O'));
		reversiBoard.setPoint(new Point(board.length/2 , board.length/2 +1 , 'X'));
		reversiBoard.setPoint(new Point(board.length/2 +1, board.length/2 , 'X'));

		reversiBoard.setPrefWidth(400);
		reversiBoard.setPrefHeight(400);
		root.getChildren().add( reversiBoard);
		reversiBoard.draw();
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
	}
}

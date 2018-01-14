package reversiapp;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.event.ActionEvent;

public class ReversiGameController implements Initializable{
	@FXML
	private AnchorPane root;
	@FXML
	private Button start = new Button();
	@FXML
	private Button exit = new Button();
	@FXML 
	private Button settings = new Button();
	@FXML
	private ImageView background;
	private int board_size;	 
	private int[][]board;		 
	private Player[] players;
	private int window_width, window_height;
	

	@Override	
	public void initialize(URL location, ResourceBundle resources) {
		this.board_size = 6;
		this.window_width = 600;
		this.window_height = 400;
		//initialize the board
		this.board = new int[board_size + 1][board_size + 1];
		for (int i = 0; i <= board_size; i++){
			for (int j = 0; j <= board_size; j++){
				this.board[i][j] = 0;
			}
		}
//		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ReversiGame.fxml"));
//		fxmlLoader.setRoot(this);
//		fxmlLoader.setController(this);
		
	}
	@FXML
	public void exit(ActionEvent event){
		System.exit(-1);
	}
	@FXML
	public void start(ActionEvent event){
		this.root.getChildren().clear();
//		 iv = new ImageView();
		ImageView iv = new
				ImageView(getClass().getResource("wood.jpeg").toExternalForm());
		iv.setFitHeight(this.window_height);
		iv.setFitWidth(this.window_width);
		this.root.getChildren().add(iv);
		ReversiBoardController reversiBoard = new ReversiBoardController(board);
		ReversiSpritesController sprites = new ReversiSpritesController(reversiBoard.getCounter(),"black", "white");
		reversiBoard.setPoint(new Point(board.length/2 , board.length/2 , 'O'));
		reversiBoard.setPoint(new Point(board.length/2 +1 , board.length/2 + 1 , 'O'));
		reversiBoard.setPoint(new Point(board.length/2 , board.length/2 +1 , 'X'));
		reversiBoard.setPoint(new Point(board.length/2 +1, board.length/2 , 'X'));

		reversiBoard.setPrefWidth(this.window_width / 2);
		reversiBoard.setPrefHeight(this.window_width / 2);
		reversiBoard.setPadding(new Insets(40));
		sprites.setPadding(new Insets(50));
		sprites.setPrefWidth(this.window_width / 3);
		sprites.setPrefHeight(this.window_width / 2);
		root.getChildren().add( sprites);
		root.getChildren().add(reversiBoard);
		
		sprites.setLayoutX(this.window_width / 2);
		
		reversiBoard.draw();
		sprites.draw();
		//to handle window resize
		root.widthProperty().addListener((observable, oldValue, newValue) -> {
			double spritesNewWidth = newValue.doubleValue();
			sprites.setPrefWidth(spritesNewWidth);
			sprites.draw();
			double boardNewWidth = newValue.doubleValue();
			reversiBoard.setPrefWidth(boardNewWidth);
			reversiBoard.draw();
			});
		root.heightProperty().addListener((observable, oldValue, newValue) -> {
			sprites.setPrefHeight(newValue.doubleValue());
			sprites.draw();
			reversiBoard.setPrefHeight(newValue.doubleValue());
			reversiBoard.draw();
		});
	}	
	@FXML
	public void settings(ActionEvent event){
		this.root.getChildren().clear();
		ImageView iv = new
				ImageView(getClass().getResource("wood.jpeg").toExternalForm());
		iv.setFitHeight(this.window_height);
		iv.setFitWidth(this.window_width);
		this.root.getChildren().add(iv);
		Settings settings = new Settings();
		settings.setPadding(new Insets(50));

		root.getChildren().add(settings);
		settings.draw();
		
		
	}
	
}

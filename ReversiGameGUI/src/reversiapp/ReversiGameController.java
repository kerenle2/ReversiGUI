package reversiapp;


import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
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
	@FXML
	private Button exit = new Button();
	@FXML 
	private Button settings = new Button();
	@FXML
	private ImageView background;
	private int board_size;	 
//	private int[][]board;		 
//	private Player[] players;
	private int window_width, window_height;

	private ArrayList<Player> players;
	private static TurnBase turn_base;


	@Override	
	public void initialize(URL location, ResourceBundle resources) {
//		this.board_size = 3;
		this.players = new ArrayList<Player>();
		this.board_size = 6;
		this.window_width = 600;
		this.window_height = 400;
		//initialize the board
//		this.board = new int[board_size + 1][board_size + 1];
//		for (int i = 0; i <= board_size; i++){
//			for (int j = 0; j <= board_size; j++){
//				this.board[i][j] = 0;
//			}
//		}
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
		ImageView iv = new
				ImageView(getClass().getResource("wood.jpeg").toExternalForm());
		iv.setFitHeight(this.window_height);
		iv.setFitWidth(this.window_width);
		this.root.getChildren().add(iv);
		
		ReversiBoardController reversiBoard = new ReversiBoardController(board_size);
		ReversiSpritesController sprites = new ReversiSpritesController(reversiBoard.getBoard().getCounter(),"black", "white");
		reversiBoard.setPoint(new Point(board_size/2 -1, board_size/2 - 1, 'O'));
		reversiBoard.setPoint(new Point(board_size/2 + 1 - 1, board_size/2 + 1 - 1, 'O'));
		reversiBoard.setPoint(new Point(board_size/2 - 1, board_size/2 + 1 - 1, 'X'));
		reversiBoard.setPoint(new Point(board_size/2 + 1 - 1, board_size/2 - 1, 'X'));
		

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
		Player black = new Player('X');
		Player white = new Player('O');
		players.add(black);
		players.add(white);
		
		this.turn_base = new TurnBase(reversiBoard, players);

		//initialize first turn possible moves:
		black.get_possible_moves(reversiBoard.getBoard(), turn_base.getMovesCalculator());
		
		reversiBoard.draw();
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
	
	public static void handlePointClick(Button move) {
		String[] rowAndCol = move.getId().split(",");
		int row = Integer.parseInt(rowAndCol[0]);	
		int col = Integer.parseInt(rowAndCol[1]);
		Point chosen_point = new Point(row, col,' ');
		turn_base.play_game(chosen_point);
	}
	}

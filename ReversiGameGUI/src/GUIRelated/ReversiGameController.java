package GUIRelated;


import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import reversiapp.Player;
import reversiapp.Point;
import reversiapp.Settings;
import reversiapp.TurnBase;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.event.ActionEvent;

public class ReversiGameController implements Initializable{
	
	public ReversiGameController() {
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
	private int window_width, window_height;
	private Settings game_settings;
	private ArrayList<Player> players;
	private static TurnBase turn_base;


	@Override	
	public void initialize(URL location, ResourceBundle resources) {

		this.game_settings = new Settings();
		this.players = new ArrayList<Player>();
		this.board_size = this.game_settings.getBoardSize();
		this.window_width = 600;
		this.window_height = 400;
		
	}

	@FXML
	public void exit(ActionEvent event){
		System.exit(0);
	}
	/**
	 * get the settings
	 * @return
	 */
	public Settings getSettings(){
		return this.game_settings;
	}
	
	/**
	 * check the color of the second player
	 * @return
	 */
	public String checkColor2(){
		if (this.game_settings.getFirstPlayer() == this.game_settings.getColor1()){
			return this.game_settings.getColor2();
		}
		else if (this.game_settings.getFirstPlayer() == this.game_settings.getColor2()){
			return this.game_settings.getColor1();
		}
		else return null; //there is an error
	}
	
	/**
	 * start the game.
	 * @param event
	 */
	@FXML
	public void start(ActionEvent event){
		Player first = new Player('X');
		Player second = new Player('O');
		players.add(first);
		players.add(second);
		this.board_size = this.game_settings.getBoardSize();
		
		//make a new scene for playing
		AnchorPane anc = new AnchorPane();
		this.root = anc;
		Scene startScene = new Scene(this.root,600,400);
		Main.primaryStage.setScene(startScene);
		
		//load the background picture
		ImageView iv = new
				ImageView(getClass().getResource("backgroundgame.jpeg").toExternalForm());
		iv.setFitHeight(this.window_height);
		iv.setFitWidth(this.window_width);
		this.root.getChildren().add(iv);
		
		ReversiBoardController reversiBoard = new ReversiBoardController(this.board_size, game_settings);
		ReversiSpritesController sprites = new ReversiSpritesController(reversiBoard.getBoard().getCounter(),
				this.game_settings.getFirstPlayer(), checkColor2());
		ReversiGameController.turn_base = new TurnBase(reversiBoard, sprites, players);
		
		//initialize the board
		reversiBoard.setPoint(new Point(board_size/2 -1, board_size/2 - 1, 'O'));
		reversiBoard.setPoint(new Point(board_size/2 + 1 - 1, board_size/2 + 1 - 1, 'O'));
		reversiBoard.setPoint(new Point(board_size/2 - 1, board_size/2 + 1 - 1, 'X'));
		reversiBoard.setPoint(new Point(board_size/2 + 1 - 1, board_size/2 - 1, 'X'));
		reversiBoard.setPrefWidth(this.window_width / 2);
		reversiBoard.setPrefHeight(this.window_width / 2);
		reversiBoard.setPadding(new Insets(40));
		
		//initialize the sprites
		sprites.setPadding(new Insets(50));
		sprites.setPrefWidth(this.window_width / 3);
		sprites.setPrefHeight(this.window_width / 2);
		sprites.setLayoutX(this.window_width / 2);

		root.getChildren().add(sprites);
		root.getChildren().add(reversiBoard);

		//the first draw
		reversiBoard.draw();

		//initialize first turn possible moves:
		players.get(0).get_possible_moves(reversiBoard.getBoard(), turn_base.getMovesCalculator());
		sprites.draw('X');
		reversiBoard.draw();
		

}
	/**
	 * go to the settings window
	 * @param event
	 */
	@FXML
	public void settings(ActionEvent event){
		
		//make a new scene for the settings window
		AnchorPane anc = new AnchorPane();
		this.root = anc;
		Scene settingsScene = new Scene(this.root,600,400);
		Main.primaryStage.setScene(settingsScene);
		
		//load the background picture
		ImageView iv = new
				ImageView(getClass().getResource("settingsbackground.jpeg").toExternalForm());
		iv.setFitHeight(this.window_height);
		iv.setFitWidth(this.window_width);
		this.root.getChildren().add(iv);
		
		this.game_settings.setPadding(new Insets(50));
		root.getChildren().add(this.game_settings);
		this.game_settings.draw();
	}
	/**
	 * handle with point click on the board.
	 * @param move
	 */
	public static void handlePointClick(Button move) {
		//get the row and col of the chosen point
		String[] rowAndCol = move.getId().split(",");
		int row = Integer.parseInt(rowAndCol[0]);	
		int col = Integer.parseInt(rowAndCol[1]);
		Point chosen_point = new Point(row, col,' ');
		//play with the chosen point
		turn_base.play_game(chosen_point);
		
	}	
}

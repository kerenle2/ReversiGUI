package reversiapp;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class ReversiGameController implements Initializable{
	
	public ReversiGameController() {
		// TODO Auto-generated constructor stub
	}
	
	@FXML
	private HBox root;
	private int board_size;	 
	private int[][]board;
	//private Player[] players = new Player[2];
	private ArrayList<Player> players;
	private static TurnBase turn_base;

	@Override	
	public void initialize(URL location, ResourceBundle resources) {
		this.board_size = 8;
		this.players = new ArrayList<Player>();
//		//initialize the board
//		this.board = new int[board_size + 1][board_size + 1];
//		for (int i = 0; i <= board_size; i++){
//			for (int j = 0; j <= board_size; j++){
//				this.board[i][j] = 0;
//			}
//		}

		
		ReversiBoardController reversiBoard = new ReversiBoardController(board_size);
		root.setOnKeyPressed(reversiBoard.getOnKeyPressed());
		
		
		reversiBoard.setPoint(new Point(board_size/2 -1, board_size/2 - 1, 'O'));
		reversiBoard.setPoint(new Point(board_size/2 + 1 - 1, board_size/2 + 1 - 1, 'O'));
		reversiBoard.setPoint(new Point(board_size/2 - 1, board_size/2 + 1 - 1, 'X'));
		reversiBoard.setPoint(new Point(board_size/2 + 1 - 1, board_size/2 - 1, 'X'));
		
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
			
			Player black = new Player('X');
			Player white = new Player('O');
			players.add(black);
			players.add(white);
			
			this.turn_base = new TurnBase(reversiBoard, players);

			//initialize first turn possible moves:
			black.get_possible_moves(reversiBoard.getBoard(), turn_base.getMovesCalculator());
			
		
	}
	
	public static void handlePointClick(Button move) {
		System.out.print(move.getId()); //delete later
		String[] rowAndCol = move.getId().split(",");
	int row = Integer.parseInt(rowAndCol[0]);	
	int col = Integer.parseInt(rowAndCol[1]);
	Point chosen_point = new Point(row, col,' ');
	turn_base.run(chosen_point);
	}
	
//	public void run() {
//		turn_base.play_game();
//	//	cout << "GAME IS OVER!" << endl << "THE WINNER IS: ";
//
//		char winner = findWinner(turn_base);
//		if (winner == 'T') {
//		//	cout << "Tie! X & O have the same number of points";
//		}
//		else {
//		//	cout << winner << endl;
//		}
//	}
//	
//	
//	
//	
//	private char findWinner(TurnBase turn_base) {
//		if (turn_base.getBoard().getCounter().getBlackCount() > turn_base.getBoard().getCounter().getWhiteCount()) {
//			return 'X';
//		}
//		if (turn_base.getBoard().getCounter().getBlackCount() < turn_base.getBoard().getCounter().getWhiteCount()) {
//			return 'O';
//		}
//		return 'T';
//	}
}

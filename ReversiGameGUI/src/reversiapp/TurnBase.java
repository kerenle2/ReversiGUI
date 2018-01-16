package reversiapp;
import java.util.ArrayList;

import GUIRelated.ReversiBoardController;
import GUIRelated.ReversiSpritesController;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class TurnBase {

	//class members
	ReversiBoardController board_controller;
	ReversiSpritesController spriets;
	ArrayList<Player> players = new ArrayList<Player>();
	char current_turn_player;
	Fliper fliper;
	MovesCalculator moves_calculator;
	boolean end_game = false;

	
	/**
	 * constructor
	 */



	public TurnBase(ReversiBoardController board,ReversiSpritesController spriets, ArrayList<Player> players) {
		this.board_controller = board;
		this.spriets = spriets;
		this.players = players;
		this.fliper = new Fliper();
		this.current_turn_player = 'X';
		this.moves_calculator = new MovesCalculator();
	}

	public void play_game(Point chosen_point) {

		chosen_point.set_sign(current_turn_player);

		if (!board_controller.isFull() && (!(players.get(0).getNomoves() && players.get(1).getNomoves()))) {
			// current_player(this.current_turn_player).play_one_turn(board_controller, chosen_point);
			this.play_next_step(this.board_controller, chosen_point); 
			
			//if theres no move, return
			if (board_controller.getBoard().getCounter().getBlackCount() == 0 || board_controller.getBoard().getCounter().getWhiteCount() == 0){
				return ;
			}
			fliper.flip(board_controller.getBoard(), chosen_point, chosen_point.get_sign());
			this.change_player();
			//calc moves:
			current_player(this.current_turn_player).get_possible_moves(board_controller.getBoard(), this.moves_calculator);
			//if there is no move - notify and hange player
			if(current_player(this.current_turn_player).getNomoves()) {
				notifyNoMoves();
				change_player();
				current_player(this.current_turn_player).get_possible_moves(board_controller.getBoard(), this.moves_calculator);
			//	spriets.draw(this.current_turn_player);
				board_controller.draw();	
			}
			
			board_controller.draw();
			spriets.draw(this.current_turn_player);
			if (((players.get(0).getNomoves() && players.get(1).getNomoves()) || board_controller.isFull())) {
			//	board_controller.setGameEnded(true);
				printWinner();
				board_controller.setGameEnded(true);
				this.end_game = true;
//				SceneManager.changeScene("menu");
			}

		}		

	}

	public void notifyNoMoves() {
		Text text = new Text("Player" + current_player(current_turn_player).getNum() + " has no moves!");
		text.setFont(Font.font(16));
		text.setFill(Color.DARKVIOLET);
		this.spriets.addNode(text);
	}
	
	private void printWinner(){
		int winner = findWinner();
		if (winner == 0) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Winner");
			alert.setHeaderText(null);
			alert.setContentText("Tie!");
			alert.showAndWait();
		}
		else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information Dialog");
			alert.setHeaderText(null);
			alert.setContentText("Player " + winner + " WON!");
			alert.showAndWait();
		}
	}
	

	private  Player current_player(char sign) {
		if (sign == 'X') {
				return this.players.get(0);
		}
		else {
			return this.players.get(1);
		}
	}

	private void change_player() {
		if (this.current_turn_player == 'X') {
			this.current_turn_player = 'O';
		}
		else {
			this.current_turn_player = 'X';
		}
	}
	public Board getBoard() {
		return this.board_controller.getBoard();
	}
	

	
	public void play_next_step(ReversiBoardController board, Point chosen_step) {
		board.setPoint(chosen_step);

	}
	
	public MovesCalculator getMovesCalculator() {
		return this.moves_calculator;
	}
	

	private int findWinner() {
		if (this.board_controller.getBoard().getCounter().getBlackCount() > this.board_controller.getBoard().getCounter().getWhiteCount()) {
			return this.spriets.getFirstPlayer();
		}
		if (this.board_controller.getBoard().getCounter().getBlackCount() < this.board_controller.getBoard().getCounter().getWhiteCount()) {
			return this.spriets.getSecondPlayer();
		}
		return 0;
	}
}

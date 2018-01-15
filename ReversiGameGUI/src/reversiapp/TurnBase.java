package reversiapp;
import java.util.ArrayList;

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
				System.out.println(current_turn_player + " has no possible moves.");
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
			}
			
		}		

	}

	
	
	public void notifyNoMoves() {
		Text text = new Text(current_player(current_turn_player).getColor() + " has no possible moves");
		text.setFont(Font.font(15));
		text.setFill(Color.DARKVIOLET);
		this.spriets.addNode(text);
	}
	
	private void printWinner(){
		String winner = findWinner();
		if (winner == "TIE") {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Winner");
			alert.setHeaderText(null);
			alert.setContentText("Tie! " + this.spriets.getFirstPlayer() + " player and " + this.spriets.getSecondPlayer() + "player  have the same number of points" );

			alert.showAndWait();
			Text tie = new Text("Tie! X & O have the same number of points");
			tie.setFont(Font.font ("Verdana", 20));
			tie.setFill(Color.RED);
//			board_controller.getChildren().add(al)
			board_controller.add(tie, board_controller.getBoardSize() / 2, board_controller.getBoardSize() / 2);
			System.out.println("Tie! X & O have the same number of points");
		}
		else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information Dialog");
			alert.setHeaderText(null);
			alert.setContentText("the winner is the " + winner + " player");
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
	

	private String findWinner() {
		if (this.board_controller.getBoard().getCounter().getBlackCount() > this.board_controller.getBoard().getCounter().getWhiteCount()) {
			return this.spriets.getFirstPlayer();
		}
		if (this.board_controller.getBoard().getCounter().getBlackCount() < this.board_controller.getBoard().getCounter().getWhiteCount()) {
			return this.spriets.getSecondPlayer();
		}
		return "TIE";
	}
}

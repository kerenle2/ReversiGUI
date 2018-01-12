package reversiapp;
import java.util.ArrayList;

public class TurnBase {
	//class members
	ReversiBoardController board;
	ArrayList<Player> players;
	Fliper fliper;
	
	/**
	 * constructor
	 */
	public TurnBase() {
		this.fliper = new Fliper();
	}


	public TurnBase(ReversiBoardController board, ArrayList<Player> players) throws Exception {
		this.board = board;
		this.players = players;
		if (players.size() != 2) {
			throw new Exception("invalid number of players. expected 2 players");
		}
		this.fliper = new Fliper();
	}

	public void play_game() {
		int i = 0;
		int j = 1;

		while(!board.isFull() && (!(players.get(0).getNomoves() && players.get(1).getNomoves()))) {
			Point chosen_point = players.get(i).play_one_turn(board, fliper);

			//if theres no move, return
			if (board.getCounter().getBlackCount() == 0 || board.getCounter().getWhiteCount() == 0){
				return ;
			}
			
			fliper.flip(board, chosen_point, chosen_point.get_sign());
			//this->console.printBoard(board);
			//this->console.printCounter(board.getCounter());

			i = i > 0 ? 0 : 1;
			j = j > 0 ? 0 : 1;
		}

	}


	
}

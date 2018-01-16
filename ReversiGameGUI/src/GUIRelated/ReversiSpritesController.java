package GUIRelated;

import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import reversiapp.PointsCounter;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import java.lang.String;
import java.util.*;

public class ReversiSpritesController extends GridPane {
		private VBox vbox =  new VBox(20);;
		private ArrayList<Node> nodesToShow = new ArrayList<Node>();
		private PointsCounter counter;
		private int first_player = 1;
		private int second_player = 2;
		private int current_player;
		
		/**
		 * constructor.
		 * @param counter
		 * @param first_player
		 * @param second_player
		 */
		public ReversiSpritesController(PointsCounter counter,String first_player, String second_player){
			this.counter = counter;
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ReversiSprites.fxml"));
			fxmlLoader.setRoot(this);
			fxmlLoader.setController(this);
		}
		/**
		 * get the number of the first player
		 * @return
		 */
		public int getFirstPlayer(){
			return this.first_player;
		}
		/**
		 * get the number of the second player
		 * @return
		 */
		public int getSecondPlayer(){
			return this.second_player;
		}
		/**
		 * draw the sprites
		 * @param current_sign
		 */
		public void draw(char current_sign){
			this.getChildren().clear();
			
			//make a vbox
			this.vbox.getChildren().clear();
			this.vbox.setPadding(new Insets(60));
			this.getChildren().add(vbox);
			
			//update the current player
			if (current_sign == 'X'){
				this.current_player = this.first_player;
			}
			else if (current_sign == 'O'){
				this.current_player = this.second_player;
			}
			
			//add the text to the sprites window
			Text current = new Text("Turn: Player" + this.current_player);
			Text counterPlayer1 = new Text("player1: " + String.valueOf(this.counter.getBlackCount()));
			Text counterPlayer2 = new Text("Player2: " + String.valueOf(this.counter.getWhiteCount()));
			counterPlayer1.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
			counterPlayer2.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
			current.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
			addNode(current);
			addNode(counterPlayer1);
			addNode(counterPlayer2);
			
			for (Node n : nodesToShow) {
				vbox.getChildren().add(n);
			}
			nodesToShow.clear();

		}
		/**
		 * add all nodes
		 * @param n
		 */
		public void addNode(Node n) {
			this.nodesToShow.add(n);
		}
}

package reversiapp;

import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import java.lang.String;
import java.util.*;

public class ReversiSpritesController extends GridPane {
		private ArrayList spritesList;
		private VBox vbox =  new VBox(20);;
		private ArrayList<Node> nodesToShow = new ArrayList<Node>();
		private PointsCounter counter;
		private int first_player = 1;
		private int second_player = 2;
		private int current_player;
		private Rectangle rec;
		
		
		public ReversiSpritesController(PointsCounter counter,String first_player, String second_player){
			this.counter = counter;
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ReversiSprites.fxml"));
			fxmlLoader.setRoot(this);
			fxmlLoader.setController(this);
		}

		public int getFirstPlayer(){
			return this.first_player;
		}
		public int getSecondPlayer(){
			return this.second_player;
		}
		public void draw(char current_sign){
			this.getChildren().clear();
			this.vbox.getChildren().clear();
			int h = (int)this.getPrefHeight();
			int w = (int)this.getPrefWidth();
			//this.vbox = new VBox(20);
			this.vbox.setPadding(new Insets(60));
			this.getChildren().add(vbox);
			if (current_sign == 'X'){
				this.current_player = this.first_player;
			}
			else if (current_sign == 'O'){
				this.current_player = this.second_player;
			}
			
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
		
		public void addNode(Node n) {
			this.nodesToShow.add(n);
		}
}

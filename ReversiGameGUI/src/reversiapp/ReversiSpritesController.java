package reversiapp;

import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import java.lang.String;
import java.util.*;

public class ReversiSpritesController extends GridPane {
		private PointsCounter counter;
		private String first_player;
		private String second_player;
		private String current_player;
		private Rectangle rec;
		public ReversiSpritesController(PointsCounter counter,String first_player, String second_player){
			this.counter = counter;
			this.first_player = first_player;
			this.second_player = second_player;
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ReversiSprites.fxml"));
			fxmlLoader.setRoot(this);
			fxmlLoader.setController(this);
		}
		
		public void draw(char current_sign){
			this.getChildren().clear();
			
			int h = (int)this.getPrefHeight();
			int w = (int)this.getPrefWidth();

			VBox vbox = new VBox(20);
			this.getChildren().add(vbox);
			if (current_sign == 'X'){
				this.current_player = this.first_player;

			}
			else if (current_sign == 'O'){
				this.current_player = this.second_player;
			}
			Text current = new Text("Current Player: " + this.current_player);
			Text counterPlayer1 = new Text(this.first_player + " player score: " + String.valueOf(this.counter.getBlackCount()));
			Text counterPlayer2 = new Text(this.second_player + " player score: " + String.valueOf(this.counter.getWhiteCount()));

			counterPlayer1.setFont(Font.font(18));
			counterPlayer2.setFont(Font.font(18));
			current.setFont(Font.font(18));

			vbox.getChildren().add(current);
			vbox.getChildren().add(counterPlayer1);
			vbox.getChildren().add(counterPlayer2);

		}
}

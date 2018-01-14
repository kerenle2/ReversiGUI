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
		private String current_player;
		private String opp_player;

		private Rectangle rec;
		public ReversiSpritesController(PointsCounter counter,String current_player, String opp_player){
			this.counter = counter;
			this.current_player = current_player;
			this.opp_player = opp_player;
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ReversiSprites.fxml"));
			fxmlLoader.setRoot(this);
			fxmlLoader.setController(this);
		}
		public void draw(){
			this.getChildren().clear();
			
			int h = (int)this.getPrefHeight();
			int w = (int)this.getPrefWidth();
//			this.rec = new  Rectangle(w, h);
//			rec.setFill(Color.WHITE);
//			this.add(rec, 0, 0);
			VBox vbox = new VBox();
			this.getChildren().add(vbox);
			Text current = new Text("Current Player: " + this.current_player);
			Text counterPlayer1 = new Text(current_player + " player score: " + String.valueOf(2));
			Text counterPlayer2 = new Text(opp_player + " player score: " + String.valueOf(4));
			
//			String first_counter =  ;
//			String second_counter = ;

//			counterPlayer1.setText(first_counter);
			counterPlayer1.setFont(Font.font(18));
//			counterPlayer2.setText(second_counter);
			counterPlayer2.setFont(Font.font(18));
			current.setFont(Font.font(18));

//			counterPlayer1.setTextAlignment(TextAlignment.JUSTIFY);
			vbox.getChildren().add(current);
			vbox.getChildren().add(counterPlayer1);
			vbox.getChildren().add(counterPlayer2);

		}
}

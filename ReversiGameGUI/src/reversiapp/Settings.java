package reversiapp;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javafx.geometry.Insets;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Settings extends GridPane {

	public void draw() {
		this.getChildren().clear();
		
		VBox vbox = new VBox();
		this.getChildren().add(vbox);
		Text settings = new Text("Settings");
		settings.setFont(new Font("System Bold", 30));
		vbox.getChildren().add(settings);
		TextField first_player = new TextField("First Player: ");
		first_player.setBackground(new Background(new BackgroundFill(Color.ANTIQUEWHITE, new CornerRadii(1),
		         new Insets(0.0,0.0,0.0,0.0))));
		vbox.getChildren().add(first_player);
		
	}


//	void readFromFile() throws IOException, IOException{
//		try(BufferedReader br = new BufferedReader(new FileReader("file.txt"))) {
//		    StringBuilder sb = new StringBuilder();
//		    String line;
//			try {
//				line = br.readLine();
//			} catch (IOException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//
//		    while (line != null) {
//		        sb.append(line);
//		        sb.append(System.lineSeparator());
//		        try {
//					line = br.readLine();
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//		    }
//		    String everything = sb.toString();
//		}
//	}
}

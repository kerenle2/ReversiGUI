package reversiapp;
	
import java.awt.event.MouseEvent;
import java.io.IOException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;


public class Main extends Application {
	static Stage primaryStage;
	AnchorPane root;
	static Scene menu_scene;

	private int window_width = 600, window_height = 400;


	@Override
	public void start(Stage primaryStage1) {
		try {
			primaryStage = primaryStage1;	
			//set the root from the fxml
			root = (AnchorPane)FXMLLoader.load(getClass().getResource("ReversiGame.fxml"));
			menu_scene = new Scene(root,this.window_width,this.window_height);
			menu_scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setTitle("Reversi Game");
			primaryStage.setScene(menu_scene);
			primaryStage.show();

			
		} catch(Exception e) {
			
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		launch(args);

	}
	
	

	
}

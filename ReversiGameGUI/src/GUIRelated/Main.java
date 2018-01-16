package GUIRelated;
	
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
	public static Stage primaryStage;
	AnchorPane root;
	public static Scene mene_scene;

	private int window_width, window_height;


	@Override
	public void start(Stage primaryStage1) {
		try {
			primaryStage = primaryStage1;			
			root = (AnchorPane)FXMLLoader.load(getClass().getResource("ReversiGame.fxml"));
			mene_scene = new Scene(root,600,400);
			mene_scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setTitle("Reversi Game");
			primaryStage.setScene(mene_scene);
			primaryStage.show();

			
		} catch(Exception e) {
			
			e.printStackTrace();
		}
	}
//	public Scene getMenuScene()
	public static void main(String[] args) {
		launch(args);

	}
	
	

	
}

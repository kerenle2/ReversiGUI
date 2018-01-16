package reversiapp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import GUIRelated.Main;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class Settings extends GridPane {
	private String color1_string, color2_string;
	private int board_size;
	private Color color1, color2;

	/**
	 * constructor
	 */
	public Settings() {
		this.board_size = 8;
		// if the file exist, read from it
		File f = new File("settings.txt");
		if(f.exists() && !f.isDirectory()) { 
			this.readFromTextFile();
		}
		//else, make a new file and then read from it
		else{
			writeToTextFile("4", Color.WHEAT, Color.BLACK);
			readFromTextFile();
		}
		this.color1 = Color.valueOf(color1_string);
		this.color2 = Color.valueOf(color2_string);


	}
	/**
	 * draw the settings window
	 */
	public void draw() {
		//read all settings from the file
		this.readFromTextFile();
		this.getChildren().clear();
		
		//make a vbox
		VBox vbox = new VBox(15);
		this.getChildren().add(vbox);

		Text board_size_text = new Text("Board Size (4-20)");
		board_size_text.setFont(Font.font("Verdana", FontWeight.BOLD, 13));
		vbox.getChildren().add(board_size_text);

		TextField board_size_field = new TextField();
		board_size_field.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, new CornerRadii(1),
		         new Insets(0.0,0.0,0.0,0.0))));
		vbox.getChildren().add(board_size_field);
		
		Text first_color_text = new Text("Player1 color");
		first_color_text.setFont(Font.font("Verdana", FontWeight.BOLD, 13));
		vbox.getChildren().add(first_color_text);

		ColorPicker color1 = new ColorPicker(this.color1);
		vbox.getChildren().add(color1);

		Text second_color_text = new Text("Player2 color");
		second_color_text.setFont(Font.font("Verdana", FontWeight.BOLD, 13));
		vbox.getChildren().add(second_color_text);

		ColorPicker color2 = new ColorPicker(this.color2);
		vbox.getChildren().add(color2);	
		
		Button continue_btn = new Button("continue");
		continue_btn.setOnAction(e-> getSettingsFromUser(board_size_field, color1, color2));
		vbox.getChildren().add(continue_btn);
	}
	
	/**
	 * add a continue button (return to menu)
	 * @param size
	 * @param first
	 * @param second
	 */
	public void continueButton(TextField size, ColorPicker first, ColorPicker second) {
		getSettingsFromUser(size, first, second);
	}
	
	/**
	 * get the settings from the user.
	 * @param size
	 * @param color1
	 * @param color2
	 */
	public void getSettingsFromUser(TextField size, ColorPicker color1, ColorPicker color2) {
		String board_size = size.getText();
		if (board_size.equals("")) {
			board_size = Integer.toString(this.board_size);
		}
		int sizeInt = Integer.parseInt(board_size);
		if (sizeInt < 4 || sizeInt > 20) {
			Alert alert = new Alert(AlertType.ERROR, "invalid board size. please type again", ButtonType.OK);
			alert.showAndWait();
		}
		Color first_color = color1.getValue();
		Color second_color = color2.getValue();

		if (second_color.equals(first_color)) {
			Alert alert = new Alert(AlertType.ERROR, "two colors are the same! please type again", ButtonType.OK);
			alert.showAndWait();
		}
		//write all the settings to the file.
		writeToTextFile(board_size, first_color, second_color);
		readFromTextFile();
		Main.primaryStage.setScene(Main.menu_scene);
	}
	/**
	 * get the color of the first player
	 * @return
	 */
	public String getFirstPlayer(){
		return this.color1_string;
	}
	
	/**
	 * get the color of the first player
	 * @return
	 */
	public String getColor1(){
		return this.color1_string;
	}

	/**
	 * get the color of the second player
	 * @return
	 */
	public String getColor2(){
		return this.color2_string;
	}
	
	/**
	 * get the board size
	 * @return
	 */
	public int getBoardSize(){
		return this.board_size;
	}

	/**
	 * write the setting from the user to the file
	 * @param size
	 * @param first_color
	 * @param second_color
	 */
	public void writeToTextFile(String size, Color first_color, Color second_color) {
	        try {
	            File statText = new File("settings.txt");
	            FileOutputStream is = new FileOutputStream(statText);
	            OutputStreamWriter osw = new OutputStreamWriter(is);    
	            Writer w = new BufferedWriter(osw);
	            
	            this.color1 = first_color;
	            this.color2 = second_color;
	            w.write(size);
	            w.write("\n");
	            
	            w.write(first_color.toString());
	            
	            w.write("\n");
	            w.write(second_color.toString());
	            
	            w.close();
	        } catch (IOException e) {
	            System.err.println("Problem writing to the file statsTest.txt");
	        }	    
	}
	
	/**
	 * read the settings from the file
	 */
	public void readFromTextFile() {
		
		try(BufferedReader br = new BufferedReader(new FileReader("settings.txt"))) {
//		    StringBuilder sb = new StringBuilder();
		    String line;
			try {
				line = br.readLine();
				this.board_size = Integer.parseInt(line);
				line = br.readLine();
				this.color1_string = line;
				line = br.readLine();
				this.color2_string = line;

				
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
		
		
	}

		
}
	

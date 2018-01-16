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
import javafx.scene.text.Text;

public class Settings extends GridPane {
	private String color1_string, color2_string;
	private int board_size;
	private Color color1, color2;

	
	public Settings() {

		this.board_size = 8;
		this.readFromTextFile();
		this.color1 = Color.valueOf(color1_string);
		this.color2 = Color.valueOf(color2_string);


	}
	
	public void draw() {
		this.readFromTextFile();
		this.getChildren().clear();
		VBox vbox = new VBox(15);
		this.getChildren().add(vbox);
		Text settings = new Text("Settings");
		settings.setFont(new Font("System Bold", 30));
		vbox.getChildren().add(settings);

		Text board_size_text = new Text("insert a board size between 4 to 20");
		settings.setFont(new Font("System Bold", 30));
		vbox.getChildren().add(board_size_text);

		TextField board_size_field = new TextField();
		board_size_field.setBackground(new Background(new BackgroundFill(Color.ANTIQUEWHITE, new CornerRadii(1),
		         new Insets(0.0,0.0,0.0,0.0))));
		vbox.getChildren().add(board_size_field);
		
	
		
		Text first_color_text = new Text("insert a color for the first player:");
		settings.setFont(new Font("System Bold", 30));
		vbox.getChildren().add(first_color_text);

		
		ColorPicker color1 = new ColorPicker(this.color1);
		vbox.getChildren().add(color1);
		
//		TextField first_color_field = new TextField();
//		first_color_field.setBackground(new Background(new BackgroundFill(Color.ANTIQUEWHITE, new CornerRadii(1),
//		         new Insets(0.0,0.0,0.0,0.0))));
//		vbox.getChildren().add(first_color_field);
		
		Text second_color_text = new Text("insert a color for the second player:");
		settings.setFont(new Font("System Bold", 30));
		vbox.getChildren().add(second_color_text);

		ColorPicker color2 = new ColorPicker(this.color2);
		vbox.getChildren().add(color2);
//		TextField second_color_field = new TextField();
//		second_color_field.setBackground(new Background(new BackgroundFill(Color.ANTIQUEWHITE, new CornerRadii(1),
//		         new Insets(0.0,0.0,0.0,0.0))));
//		vbox.getChildren().add(second_color_field);
//		
		
		Button continue_btn = new Button("continue");
		continue_btn.setOnAction(e-> getSettingsFromUser(board_size_field, color1, color2));
		vbox.getChildren().add(continue_btn);
	}
	
	
	public void continueButton(TextField size, ColorPicker first, ColorPicker second) {
		getSettingsFromUser(size, first, second);
//		SceneManager.changeScene("menu");
	}
	

	
	
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
		
	//	String color1_string = color1.toString();
		Color second_color = color2.getValue();
	//	String color2_string = color2.toString();

//		String first_color = color1.getText();
//		first_color = first_color.toLowerCase();
//		if (first_color.equals("")) {
//			first_color = "black";
//		}
//		String second_color = color2.getText();
//		second_color = second_color.toLowerCase();
//		if(second_color.equals("")) {
//			second_color = "white";
//		}
		if (second_color.equals(first_color)) {
			Alert alert = new Alert(AlertType.ERROR, "two colors are the same! please type again", ButtonType.OK);
			alert.showAndWait();
		}
		writeToTextFile(board_size, first_color, second_color);
		readFromTextFile();
		Main.primaryStage.setScene(Main.mene_scene);
	}

	public String getFirstPlayer(){
		return this.color1_string;
	}
	
	
	public String getColor1(){
		return this.color1_string;
	}

	
	public String getColor2(){
		return this.color2_string;
	}
	
	
	public int getBoardSize(){
		return this.board_size;
	}

	
	public void writeToTextFile(String size, Color first_color, Color second_color) {
	        try {
	            File statText = new File("settings.txt");
	            FileOutputStream is = new FileOutputStream(statText);
	            OutputStreamWriter osw = new OutputStreamWriter(is);    
	            Writer w = new BufferedWriter(osw);
//	            String sizeP = size.concat(" ");						this part is befor chcnging to color picker
//	            String str1P = str1.concat(" ");
//	            String all = sizeP.concat(str1P).concat(str2);

	          //  System.out.println(all);//deleteeeee
	          //  w.write(all);
	            
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
	
	public void readFromTextFile() {
		try(BufferedReader br = new BufferedReader(new FileReader("settings.txt"))) {
		    StringBuilder sb = new StringBuilder();
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
	

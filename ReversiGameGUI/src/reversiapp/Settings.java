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
	private String first_color, second_color;
	private int board_size;
	

	
	public Settings() {
		this.writeToTextFile("3", "black", "white");
		this.readFromTextFile();
	}
	
	public void draw() {
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

		TextField first_color_field = new TextField();
		first_color_field.setBackground(new Background(new BackgroundFill(Color.ANTIQUEWHITE, new CornerRadii(1),
		         new Insets(0.0,0.0,0.0,0.0))));
		vbox.getChildren().add(first_color_field);
		
		Text second_color_text = new Text("insert a color for the second player:");
		settings.setFont(new Font("System Bold", 30));
		vbox.getChildren().add(second_color_text);

		TextField second_color_field = new TextField();
		second_color_field.setBackground(new Background(new BackgroundFill(Color.ANTIQUEWHITE, new CornerRadii(1),
		         new Insets(0.0,0.0,0.0,0.0))));
		vbox.getChildren().add(second_color_field);
		
		
		Button continue_btn = new Button("continue");
		continue_btn.setOnAction(e-> getSettingsFromUser(board_size_field, first_color_field, second_color_field));
		vbox.getChildren().add(continue_btn);
	}
	
	
	public void continueButton(TextField size, TextField first, TextField second) {
		getSettingsFromUser(size, first, second);
		return;
	}
	

	
	
	public void getSettingsFromUser(TextField size, TextField first, TextField second) {
		String board_size = size.getText();
		if (board_size.equals("")) {
			board_size = "8";
		}
		int sizeInt = Integer.parseInt(board_size);
		if (sizeInt < 4 || sizeInt > 20) {
			Alert alert = new Alert(AlertType.ERROR, "invalid board size. please type again", ButtonType.OK);
			alert.showAndWait();
		}
		String first_color = first.getText();
		first_color = first_color.toLowerCase();
		if (first_color.equals("")) {
			first_color = "black";
		}
		String second_color = second.getText();
		second_color = second_color.toLowerCase();
		if(second_color.equals("")) {
			second_color = "white";
		}
		if (second_color.equals(first_color)) {
			Alert alert = new Alert(AlertType.ERROR, "two colors are the same! please type again", ButtonType.OK);
			alert.showAndWait();
		}
		writeToTextFile(board_size, first_color, second_color);
		readFromTextFile();
	}

	public String getFirstPlayer(){
		return this.first_color;
	}
	
	
	public String getColor1(){
		return this.first_color;
	}

	
	public String getColor2(){
		return this.second_color;
	}
	
	
	public int getBoardSize(){
		return this.board_size;
	}

	
	public void writeToTextFile(String size, String str1, String str2) {
	        try {
	            File statText = new File("settings.txt");
	            FileOutputStream is = new FileOutputStream(statText);
	            OutputStreamWriter osw = new OutputStreamWriter(is);    
	            Writer w = new BufferedWriter(osw);
	            String sizeP = size.concat(" ");
	            String str1P = str1.concat(" ");
	            String all = sizeP.concat(str1P).concat(str2);
	            System.out.println(all);//deleteeeee
	            w.write(all);
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
				System.out.println(line);//deleteeeee
				String arr[] = line.split(" ");
				this.board_size = Integer.parseInt(arr[0]);
				this.first_color = arr[1];
				this.second_color = arr[2];
				System.out.println(this.board_size);//deleteeeee
				System.out.println(this.first_color);//deleteeeee
				System.out.println(this.second_color); //deleteeeee
				
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
	

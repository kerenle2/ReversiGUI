package reversiapp;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;

public class ReversiGameController implements Initializable{
	@FXML
	private HBox root;
	private int[][]board = { {0,0,0,0,0,0,0,0,0,0,0},
							{0,0,0,0,0,0,0,0,0,0,0},
							{0,0,0,0,0,0,0,0,0,0,0},
							{0,0,0,0,0,0,0,0,0,0,0},
							{0,0,0,0,0,0,0,0,0,0,0},
							{0,0,0,0,0,0,0,0,0,0,0},
							{0,0,0,0,0,0,0,0,0,0,0}};

//	}
		     

//	private int[][]board = { {0,0,1,0,0,0,0,1,0,0,0},
//						     {0,1,0,1,1,1,0,1,0,1,0},
//						     {1,0,0,0,1,1,0,1,0,1,0},
//						     {0,1,0,1,0,1,0,1,0,1,0},
//						     {0,0,1,0,1,1,0,1,0,1,0},
//						     {0,1,0,1,1,0,1,1,0,1,0},
//						     {0,1,0,1,1,1,0,1,0,1,0}};
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		ReversiBoardController reversiBoard = new ReversiBoardController(board);
		root.setOnKeyPressed(reversiBoard.getOnKeyPressed());

		reversiBoard.setPrefWidth(400);
		reversiBoard.setPrefHeight(400);
		root.getChildren().add(0, reversiBoard);
		reversiBoard.draw();
		//to handle window resize
		root.widthProperty().addListener((observable, oldValue, newValue) -> {
			double boardNewWidth = newValue.doubleValue() - 120;
			reversiBoard.setPrefWidth(boardNewWidth);
			reversiBoard.draw();
			});
			root.heightProperty().addListener((observable, oldValue, newValue) -> {
				reversiBoard.setPrefHeight(newValue.doubleValue());
				reversiBoard.draw();
			});
	}
	
}

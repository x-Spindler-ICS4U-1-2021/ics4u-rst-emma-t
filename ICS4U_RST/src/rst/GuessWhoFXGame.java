package rst;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class GuessWhoFXGame extends Application {
	
	private GWPlayer player;
	
	private String[][] fish;
	
	//Values needed for spacing and fonts that never change
    final int GAP = 15;
    final int LARGE_FONT = 25;
    final int SMALL_FONT = 14;
    
    ListView<String> lstClues;
	
	public void start(Stage myStage) throws Exception {
		
		player = new GWPlayer();
    	
    	//Grid pane FX layout for this program
    	GridPane root = new GridPane();
    	
    	//Defines spacing
   		root.setHgap(GAP);
   		root.setVgap(GAP);
   		root.setPadding(new Insets(GAP, GAP, GAP, GAP));
   		
   		Label lblTitle = new Label ("Tang's Guess Who Game!");
    	lblTitle.setFont(Font.font(LARGE_FONT));
   		root.add(lblTitle, 0, 0);
   		
   		fish = player.display();
		for (int row = 0; row <= 4; row++) {
			for (int col = 0; col <= 4; col++) {
				Label lblFish = new Label (fish[row][col]);
				root.add(lblFish, col, row);
			}
		}
   		
   		lstClues = new ListView<String>();
    	root.add(lstClues, 4, 0, 2, 5);
    	lstClues.setPrefWidth(100);
    	lstClues.setPrefHeight(250);
    	
    	Button btnClue = new Button("Get Clue");
   		root.add(btnClue, 4, 5);
   		btnClue.setPrefWidth(80);
   		btnClue.setOnAction(event -> getClue());
   		
   		Button btnSort = new Button("Sort");
   		btnSort.setPrefWidth(80);
   		root.add(btnSort, 5, 5);
   		btnClue.setOnAction(event -> sort());
   		
   		Button btnGuess = new Button("Make Guess");
   		btnGuess.setPrefWidth(80);
   		root.add(btnGuess, 4, 6);
   		btnClue.setOnAction(event -> guess());
   		
   		Button btnEnd = new Button("End");
   		btnEnd.setPrefWidth(80);
   		root.add(btnEnd, 5, 6);
   		btnClue.setOnAction(event -> end());
   		
   		//Displays scene
     	Scene scene = new Scene(root);
        myStage.setTitle("Guess Who Game");
      	myStage.setScene(scene);
      	myStage.show();
      	
      	
      	
		
	}
	
	private void playGame() {
		
	}
	
	private void getClue() {
		
	}
	
	private void sort() {
		
	}
	
	private void guess() {
		
	}
	
	private void end() {
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}

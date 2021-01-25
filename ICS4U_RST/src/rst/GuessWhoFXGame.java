package rst;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class GuessWhoFXGame extends Application {
	
	private GWPlayer player;
	
	//Grid pane FX layout for this program
	private GridPane root = new GridPane();
	
	private Label lblFish[] = new Label [12];
	private Button btnFish [] = new Button [12];
	private ImageView imgFish [] = new ImageView [12];
	
	private String fish [][] = new String [4][3];
	
	//Values needed for spacing and fonts that never change
    final int GAP = 15;
    final int LARGE_FONT = 25;
    final int SMALL_FONT = 14;
    
    ListView<String> lstClues;
	
	public void start(Stage myStage) throws Exception {
		
		player = new GWPlayer();
		
		//Label lblFish[] = new Label [12];
    	
    	//Grid pane FX layout for this program
    	//GridPane root = new GridPane();
    	
    	//Defines spacing
   		root.setHgap(GAP);
   		root.setVgap(GAP);
   		root.setPadding(new Insets(GAP, GAP, GAP, GAP));
   		
   		Label lblTitle = new Label ("Tang's Guess Who \nGame!");
    	lblTitle.setFont(Font.font(LARGE_FONT));
   		root.add(lblTitle, 0, 0, 6, 1);
   		
   		lstClues = new ListView<String>();
    	root.add(lstClues, 4, 0, 2, 6);
    	lstClues.setPrefWidth(100);
    	lstClues.setPrefHeight(250);
    	
    	Button btnClue = new Button("Get Clue");
   		root.add(btnClue, 4, 6);
   		btnClue.setPrefWidth(80);
   		btnClue.setOnAction(event -> getClue());
   		
   		Button btnSort = new Button("Sort");
   		btnSort.setPrefWidth(80);
   		root.add(btnSort, 5, 6);
   		btnClue.setOnAction(event -> sort());
   		
   		Button btnGuess = new Button("Make Guess");
   		btnGuess.setPrefWidth(80);
   		root.add(btnGuess, 4, 7);
   		btnClue.setOnAction(event -> guess());
   		
   		Button btnEnd = new Button("End");
   		btnEnd.setPrefWidth(80);
   		root.add(btnEnd, 5, 7);
   		btnClue.setOnAction(event -> end());
   		
   		charactersSetup();
   		
   		//Displays scene
     	Scene scene = new Scene(root);
        myStage.setTitle("Guess Who Game");
      	myStage.setScene(scene);
      	myStage.show();      	
		
	}
	
	private void playGame() {
		
	}
	
	private void charactersSetup() {
		
		fish = player.display();
   		for(int i = 0; i < lblFish.length; i++) {
   			for (int row = 0; row < 4; row++) {
   				for (int col = 0; col < 3; col++) {
   					lblFish[i] = new Label(fish[row][col]);
   					imgFish[i] = new ImageView(getClass().getResource("/fish/" + fish[row][col] + ".png").toString());
   					btnFish[i] = new Button();
   					imgFish[i].setFitWidth(75);
   					imgFish[i].setPreserveRatio(true);
   					btnFish[i].setGraphic(imgFish[i]);
   					
   					root.add(lblFish[i], col+1, row+1);
   					root.add(btnFish[i], col, row+1);
   				}
   			}
   		}
   		
   		
   		
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
package rst;

import java.util.Optional;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;
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
	
	private String[] fishImagesDisplay = new String [12];
	private String fishCharacteristicsDisplay;
	
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
    	root.add(lstClues, 6, 0, 2, 7);
    	lstClues.setPrefWidth(150);
    	lstClues.setPrefHeight(500);
    	
    	Button btnClue = new Button("Get Clue");
   		root.add(btnClue, 6, 7, 1, 1);
   		btnClue.setPrefWidth(80);
   		btnClue.setOnAction(event -> getClue());
   		
   		Button btnSort = new Button("Sort");
   		btnSort.setPrefWidth(80);
   		root.add(btnSort, 7, 7, 1, 1);
   		btnClue.setOnAction(event -> sort());
   		
   		Button btnGuess = new Button("Make Guess");
   		btnGuess.setPrefWidth(80);
   		root.add(btnGuess, 6, 8, 1, 1);
   		btnClue.setOnAction(event -> guess());
   		
   		Button btnEnd = new Button("End");
   		btnEnd.setPrefWidth(80);
   		root.add(btnEnd, 7, 8, 1, 1);
   		btnClue.setOnAction(event -> end());
   		
   		charactersSetup();
   		
   		//Displays scene
     	Scene scene = new Scene(root);
        myStage.setTitle("Guess Who Game");
      	myStage.setScene(scene);
      	myStage.setMaxHeight(550);
      	myStage.show();      	
		
	}
	
	private void playGame() {
		
	}
	
	private void charactersSetup() {
		
		int x = 0;
		
		fishImagesDisplay = player.display();
		
		for(int i = 0; i < lblFish.length; i++) {
   			imgFish[i] = new ImageView(getClass().getResource("/fish/" + fishImagesDisplay[i] + ".png").toString());
		}
		
		for (int row = 0; row < 8; row+=2) {
			for (int col = 0; col < 6; col+=2) {
				
				TextInputDialog dialog = new TextInputDialog();
				dialog.setTitle("Text Input Dialog");
				dialog.setHeaderText("What is the name of this character?");
				dialog.setContentText("Please enter name:");
				dialog.setGraphic(imgFish[x]);

				Optional<String> result = dialog.showAndWait();
				if (result.isPresent()){
				    player.createCharactertistics(result.get());
				}
				
				fishCharacteristicsDisplay = player.characteristicsDisplay(x);
					
				lblFish[x] = new Label(fishCharacteristicsDisplay);
				btnFish[x] = new Button();
				imgFish[x].setFitWidth(75);
				imgFish[x].setPreserveRatio(true);
				btnFish[x].setGraphic(imgFish[x]);
				
				root.add(lblFish[x], col+1, row+1, 1, 2);
				root.add(btnFish[x], col, row+1, 1, 2);
				x++;
					
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
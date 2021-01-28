package rst;

import java.io.FileNotFoundException;
import java.util.Optional;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import simpleIO.Console;
import simpleIO.FXDialog;

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
    
    private ListView<String> lstClues;
    private int clue = 0;
	
    private static final Image imgX = new Image(GuessWhoFXGame.class.getResource("/X.png").toString());
    
    private boolean isRemoving = false;
    private boolean isGuessing = false;
    private boolean isCorrect = false;
    
	public void start(Stage myStage) throws Exception {
		
		player = new GWPlayer();
		
		//Label lblFish[] = new Label [12];
    	
    	//Grid pane FX layout for this program
    	//GridPane root = new GridPane();
    	
    	//Defines spacing
   		root.setHgap(GAP);
   		root.setVgap(GAP);
   		root.setPadding(new Insets(GAP, GAP, GAP, GAP));
   		
   		Label lblTitle = new Label ("Tang's Guess Who Game!");
    	lblTitle.setFont(Font.font(LARGE_FONT));
   		root.add(lblTitle, 0, 0, 6, 1);
   		
   		lstClues = new ListView<String>();
    	root.add(lstClues, 6, 0, 2, 7);
    	lstClues.setPrefWidth(150);
    	lstClues.setPrefHeight(500);
    	
    	fishImagesDisplay = player.display();
    	for(int i = 0; i < 12; i++) {
   			imgFish[i] = new ImageView(getClass().getResource("/fish/" + fishImagesDisplay[i] + ".png").toString());
		}
    	charactersSetup();
    	
    	Button btnClue = new Button("Get Clue");
   		root.add(btnClue, 6, 7, 1, 1);
   		btnClue.setPrefWidth(80);
   		EventHandler<ActionEvent> buttonClue = new EventHandler<ActionEvent>() { 
            public void handle(ActionEvent e) 
            { 
            	getClue();
            } 
        }; 
        btnClue.setOnAction(buttonClue);
        
   		
   		Button btnSort = new Button("Sort");
   		btnSort.setPrefWidth(80);
   		root.add(btnSort, 7, 7, 1, 1);
   		EventHandler<ActionEvent> buttonSort = new EventHandler<ActionEvent>() { 
            public void handle(ActionEvent e) 
            { 
            	sort();
            } 
        }; 
        btnSort.setOnAction(buttonSort);
   		
   		Button btnGuess = new Button("Make Guess");
   		btnGuess.setPrefWidth(80);
   		root.add(btnGuess, 6, 8, 1, 1);
   		EventHandler<ActionEvent> buttonGuess = new EventHandler<ActionEvent>() { 
            public void handle(ActionEvent e) 
            { 
            	if(isCorrect == false) {
            	isGuessing = true;
            	FXDialog.print("Click a fish to guess");
            	}
            } 
        }; 
        btnGuess.setOnAction(buttonGuess);
   		
   		Button btnEnd = new Button("End");
   		btnEnd.setPrefWidth(80);
   		root.add(btnEnd, 7, 8, 1, 1);
   		EventHandler<ActionEvent> buttonEnd = new EventHandler<ActionEvent>() { 
            public void handle(ActionEvent e) 
            { 
            	end();
            } 
        }; 
        btnEnd.setOnAction(buttonEnd);
   		
   		//Displays scene
     	Scene scene = new Scene(root);
        myStage.setTitle("Guess Who Game");
      	myStage.setScene(scene);
      	myStage.setMaxHeight(550);
      	myStage.show();      	
		
	}
	
	private void charactersSetup() throws FileNotFoundException {
		
		int x = 0;
		
//		fishImagesDisplay = player.display();
		
//		for(int i = 0; i < lblFish.length; i++) {
//   			imgFish[i] = new ImageView(getClass().getResource("/fish/" + fishImagesDisplay[i] + ".png").toString());
//		}
		
		for (int row = 0; row < 8; row+=2) {
			for (int col = 0; col < 6; col+=2) {
				
				TextInputDialog dialog = new TextInputDialog();
				dialog.setTitle("Text Input Dialog");
				dialog.setHeaderText("What is the name of this character?");
				dialog.setContentText("Please enter name:");
				dialog.setGraphic(imgFish[x]);

				Optional<String> result = dialog.showAndWait();
				if (result.isPresent()){
				    player.createCharactertistics(x, result.get());
				}
				
				fishCharacteristicsDisplay = player.characteristicsDisplay(x);
					
				lblFish[x] = new Label(fishCharacteristicsDisplay);
				btnFish[x] = new Button();
				imgFish[x].setFitWidth(75);
				imgFish[x].setPreserveRatio(true);
				btnFish[x].setGraphic(imgFish[x]);
				
				int temp = x;

				btnFish[x].setOnAction(event -> {
					try {
						fishSelected(event, temp);
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				});
				
				
				root.add(lblFish[x], col+1, row+1, 1, 2);
				root.add(btnFish[x], col, row+1, 1, 2);
				x++;
					
			}
		}
		
		int value = player.correctFish();
		ImageView imgCorrectFish = new ImageView(getClass().getResource("/fish/" + fishImagesDisplay[value] + ".png").toString());
		imgCorrectFish.setFitWidth(75);
		imgCorrectFish.setPreserveRatio(true);
		root.add(imgCorrectFish, 3, 0);
		
		player.fileInput();
   		
	}
	
	private void fishSelected(ActionEvent event, int x) throws FileNotFoundException {
		
		if(isGuessing == false) {
		player.removed(x);
		
		if(isRemoving == true) {
			btnFish[x] = (Button) event.getSource();
		
			ImageView temp = new ImageView(imgX);
			temp.setFitWidth(75);
			temp.setPreserveRatio(true);
		
			btnFish[x].setGraphic(temp);
			
			imgFish[x] = temp;
		}
		}
		else {
			isCorrect = player.compareGuess(x);
			guess();
		}
		

	}
	
	private void getClue() {
		
		if(isCorrect == false) {
		Alert clueType = new Alert(AlertType.CONFIRMATION);
		clueType.setTitle("Choose Clue Type Dialog");
		clueType.setHeaderText("Please choose a characteristic to get a clue about");

		ButtonType btnColour = new ButtonType("Colour");
		ButtonType btnHat = new ButtonType("Hat");
		ButtonType btnSize = new ButtonType("Size");
		ButtonType btnMarkings = new ButtonType("Markings");
		ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);

		clueType.getButtonTypes().setAll(btnColour, btnHat, btnSize, btnMarkings, buttonTypeCancel);

		Optional<ButtonType> result = clueType.showAndWait();
		if (result.get() == btnColour){
			lstClues.getItems().add(player.clueColour());
			isRemoving = true;
		} else if (result.get() == btnHat) {
			lstClues.getItems().add(player.clueHat());
			isRemoving = true;
		} else if (result.get() == btnSize) {
			lstClues.getItems().add(player.clueSize());
			isRemoving = true;
		} else if (result.get() == btnMarkings) {
			lstClues.getItems().add(player.clueMarkings(clue));
			clue++;
			isRemoving = true;
		} else {
			Alert error = new Alert(AlertType.ERROR);
			error.setTitle("Error Dialog");
			error.setHeaderText("No clue type selected");

			error.showAndWait();
		}
		}
		
		

	}
	
	private void sort() {
		
		int imgOrder [] = player.sort();
		
		int x = 0;
		
		for (int row = 0; row < 8; row+=2) {
			for (int col = 0; col < 6; col+=2) {
				
				fishCharacteristicsDisplay = player.characteristicsDisplay(x);
				
				lblFish[x].setText(fishCharacteristicsDisplay);
				btnFish[x].setGraphic(imgFish[imgOrder[x]]);

				x++;
					
			}
		}
	}
	
	private void guess() {
		
		if(isCorrect == false) {
			FXDialog.print("That guess was incorrect. Get more clues or exit the game.");
		}
		else {
			FXDialog.print("Congratulations! You guessed correctly! End the game to reset or to exit.");
		}
		
		isGuessing = false;
		isRemoving = false;
		
		
	}
	
	private void end() {
		//TODO option to save game in file
		Platform.exit();
		player.end();
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
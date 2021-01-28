package rst;

import java.io.FileNotFoundException;
import java.io.IOException;
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
    
    private ImageView imgCorrectFish = new ImageView();
    private ImageView questionMark = new ImageView();
    
    private int clueMessage = 0;
    private int removingCharacters = 0;
    
	public void start(Stage myStage) throws Exception {
		
		FXDialog.print("Welcome to Tang's Guess Who Game! In this version, there will be 12 fish with various characteristics. Name these"
      			+ " 12 fish to continue");
		
		player = new GWPlayer();
		charactersSetup();
		
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
    	
    	Label lblCorrect = new Label("<-- This is what the correct \nfish looks like");
    	lblCorrect.setFont(Font.font(SMALL_FONT));
   		root.add(lblCorrect, 4, 0, 2, 1);
    	
    	int value = player.correctFish();
		imgCorrectFish = new ImageView(getClass().getResource("/fish/" + fishImagesDisplay[value] + ".png").toString());
    	
		imgCorrectFish.setFitWidth(75);
		imgCorrectFish.setPreserveRatio(true);
		imgCorrectFish.setVisible(false);
		root.add(imgCorrectFish, 3, 0);
		
		questionMark = new ImageView(getClass().getResource("/questionMark.png").toString());
		questionMark.setFitWidth(75);
		questionMark.setPreserveRatio(true);
		root.add(questionMark, 3, 0);
		
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
            	if(isCorrect == false && removingCharacters < 12) {
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
            	try {
					end();
 			    } catch (IOException error) {
 			    	FXDialog.print("Exception: " + error);
 			    	System.exit(0);
 			    } catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            } 
        }; 
        btnEnd.setOnAction(buttonEnd);
       
   		//Displays scene
     	Scene scene = new Scene(root);
        myStage.setTitle("Guess Who Game");
      	myStage.setScene(scene);
      	myStage.setMaxHeight(550);
      	myStage.show();
      	
      	FXDialog.print("Good job! To begin the game, get a clue!");
		
	}
	
	private void charactersSetup() throws FileNotFoundException {
		
		fishImagesDisplay = player.display();
    	for(int i = 0; i < 12; i++) {
   			imgFish[i] = new ImageView(getClass().getResource("/fish/" + fishImagesDisplay[i] + ".png").toString());
		}
		
		int x = 0;
		
		for (int row = 0; row < 8; row+=2) {
			for (int col = 0; col < 6; col+=2) {
				
				TextInputDialog dialog = new TextInputDialog();
				dialog.setTitle("Text Input Dialog");
				dialog.setHeaderText("What would you like to name this character?");
				dialog.setContentText("Please enter name:");
				dialog.setGraphic(imgFish[x]);

				Optional<String> result = dialog.showAndWait();
				if (result.isPresent()){
				    player.createCharactertistics(x, result.get());
				}
				else {
					System.exit(0);
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
						FXDialog.print("Exception: " + e);
						System.exit(0);
					}
				});
				
				
				root.add(lblFish[x], col+1, row+1, 1, 2);
				root.add(btnFish[x], col, row+1, 1, 2);
				x++;
					
			}
		}
		
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
			removingCharacters++;
		}
		}
		else {
			isCorrect = player.compareGuess(x);
			guess();
		}
		
		if(removingCharacters == 11) {
			FXDialog.print("You are now guessing the last fish");
			isGuessing = true;
			removingCharacters++;
		}

	}
	
	private void getClue() {
		
		if(isCorrect == false && removingCharacters < 12) {
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
		
		if(clueMessage == 0) {
			FXDialog.print("Click as many characters as you'd like to remove and then get another clue or make a guess! If it's helpful,"
					+ " sort the fish alphabetically");
			clueMessage++;
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
		
		FXDialog.print("All fish have been sorted alphabetically");
	}
	
	private void guess() {
		
		if(isCorrect == false && removingCharacters == 12) {
			FXDialog.print("That guess was incorrect. You lost!");
			imgCorrectFish.setVisible(true);
			questionMark.setVisible(false);
		}
		else if(isCorrect == false) {
			FXDialog.print("That guess was incorrect. Get more clues or exit the game.");
		}
		else {
			FXDialog.print("Congratulations! You guessed correctly! End the game to reset or to exit.");
			imgCorrectFish.setVisible(true);
			questionMark.setVisible(false);
		}
		
		isGuessing = false;
		isRemoving = false;
		
		
	}
	
	private void end() throws Exception {
		player.end();
		
		if(isCorrect == false) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information Dialog");
			alert.setHeaderText(null);
			alert.setContentText("This was the correct fish");
			imgCorrectFish.setVisible(true);
			alert.setGraphic(imgCorrectFish);

			alert.showAndWait();
		}
		
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("Text Input Dialog");
		dialog.setHeaderText("Enter a name and click OK to save game to your text file. \nThe game will automatically be saved to GuessWho.txt");
		dialog.setContentText("Please enter file name to save to:");

		Optional<String> result = dialog.showAndWait();
		if (result.isPresent() && result.get() != "GuessWho"){
			player.userFile(result.get());  
			FXDialog.print("Game save outputted to " + result.get());
		}
		else{
			FXDialog.print("Game save outputted to GuessWho");
		}
		
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmation Dialog");
		alert.setHeaderText(null);
		alert.setContentText("Would you like to play again?");
		
		ButtonType btnYes = new ButtonType("Yes");
		ButtonType btnNo = new ButtonType("No", ButtonData.CANCEL_CLOSE);

		alert.getButtonTypes().setAll(btnYes, btnNo); 
				
		Optional<ButtonType> result1 = alert.showAndWait();
		if (result1.get() == btnYes){
			imgCorrectFish.setVisible(false);
			
			for(int i = 0; i < 12; i++) {
				lblFish[i].setText("");
				btnFish[i].setGraphic(null);
				imgFish[i].setImage(null);
				
				//String[] fishImagesDisplay = new String [12];
			}
			
			clue = 0;
			clueMessage = 0;
		    removingCharacters = 0;
		    isCorrect = false;
		    lstClues.getItems().clear();
			questionMark.setVisible(true);
			
			player = new GWPlayer();
			charactersSetup();
			
			int value = player.correctFish();
			imgCorrectFish = new ImageView(getClass().getResource("/fish/" + fishImagesDisplay[value] + ".png").toString());
			imgCorrectFish.setFitWidth(75);
			imgCorrectFish.setPreserveRatio(true);
			imgCorrectFish.setVisible(false);
			root.add(imgCorrectFish, 3, 0);
			
		} else {
			Platform.exit();
		}
		
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
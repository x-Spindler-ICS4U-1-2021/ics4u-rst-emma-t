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

/**
 * Guess Who FX Game program
 * The GuessWhoFXGame program allows the user to play a version of the Guess Who? Game in a JavaFX layout.
 * 
 * @author s427549
 * @since 2021-1-18
 */
public class GuessWhoFXGame extends Application {
	
	//Uses data directly from the GWPlayer class
	private GWPlayer player;
	
	//Grid pane FX layout for this program
	private GridPane root = new GridPane();
	
	//Arrays of Nodes needed for the FX layout
	private Label lblFish[] = new Label [12];
	private Button btnFish [] = new Button [12];
	private ImageView imgFish [] = new ImageView [12];
	
	//Array of numbers for the order of the character images
	private String[] fishImagesDisplay = new String [12];
	private String fishCharacteristicsDisplay;
	
	//Values needed for spacing and fonts that never change
    final int GAP = 15;
    final int LARGE_FONT = 25;
    final int SMALL_FONT = 14;
    
    //ListView that outputs the past clues
    private ListView<String> lstClues;
    private int clue = 0;
	
    //Images needed for removing characters and displaying the correct character
    private static final Image imgX = new Image(GuessWhoFXGame.class.getResource("/X.png").toString());
    private ImageView imgCorrectFish = new ImageView();
    private ImageView questionMark = new ImageView();
    
    //Variables needed for checking when to remove, guess, and check if the guess is correct
    private boolean isRemoving = false;
    private boolean isGuessing = false;
    private boolean isCorrect = false;
    
    //Variables needed so that certain messages only appear once
    private int clueMessage = 0;
    private int removingCharacters = 0;
    
    /**
     * This is the start method
	 * @param myStage This is the first parameter to start method
	 * @exception Exception On input error
	 * @see Exception
     */
	public void start(Stage myStage) throws Exception {
		
		//FXDialog instructions
		FXDialog.print("Welcome to Tang's Guess Who Game! In this version, there will be 12 fish with various characteristics. Name these"
      			+ " 12 fish to continue");
		
		//Set up the characters through the GWPlayer class and the charactersSetup method
		player = new GWPlayer();
		charactersSetup();
		
    	//Defines spacing
   		root.setHgap(GAP);
   		root.setVgap(GAP);
   		root.setPadding(new Insets(GAP, GAP, GAP, GAP));
   		
   		//Label for displaying the game title
   		Label lblTitle = new Label ("Tang's Guess Who Game!");
    	lblTitle.setFont(Font.font(LARGE_FONT));
   		root.add(lblTitle, 0, 0, 6, 1);
   		
   		//ListView for displaying clues
   		lstClues = new ListView<String>();
    	root.add(lstClues, 6, 0, 2, 7);
    	lstClues.setPrefWidth(150);
    	lstClues.setPrefHeight(500);
    	
    	//Label for displaying the correct fish
    	Label lblCorrect = new Label("This is what the correct \nfish looks like");
    	lblCorrect.setFont(Font.font(SMALL_FONT));
   		root.add(lblCorrect, 4, 0, 2, 1);
    	
   		//Image of correct fish is put on scene but not visible
    	int value = player.correctFish();
		imgCorrectFish = new ImageView(getClass().getResource("/fish/" + fishImagesDisplay[value] + ".png").toString());
		imgCorrectFish.setFitWidth(75);
		imgCorrectFish.setPreserveRatio(true);
		imgCorrectFish.setVisible(false);
		root.add(imgCorrectFish, 3, 0);
		
		//Image of question mark is in place of correct fish
		questionMark = new ImageView(getClass().getResource("/questionMark.png").toString());
		questionMark.setFitWidth(75);
		questionMark.setPreserveRatio(true);
		root.add(questionMark, 3, 0);
		
		//Button for user to get a clue
    	Button btnClue = new Button("Get Clue");
   		root.add(btnClue, 6, 7, 1, 1);
   		btnClue.setPrefWidth(80);
   		EventHandler<ActionEvent> buttonClue = new EventHandler<ActionEvent>() { 
            //Button goes to getClue method
   			public void handle(ActionEvent e) 
            { 
            	getClue();
            } 
        }; 
        btnClue.setOnAction(buttonClue);
   		
        //Button for user to sort characters alphabetically
   		Button btnSort = new Button("Sort");
   		btnSort.setPrefWidth(80);
   		root.add(btnSort, 7, 7, 1, 1);
   		EventHandler<ActionEvent> buttonSort = new EventHandler<ActionEvent>() { 
            //Button goes to sort method
   			public void handle(ActionEvent e) 
            { 
            	sort();
            } 
        }; 
        btnSort.setOnAction(buttonSort);
   		
        //Button for user to make a guess
   		Button btnGuess = new Button("Make Guess");
   		btnGuess.setPrefWidth(80);
   		root.add(btnGuess, 6, 8, 1, 1);
   		EventHandler<ActionEvent> buttonGuess = new EventHandler<ActionEvent>() { 
            //Changes isGuessing so that next character clicked is guessed
   			public void handle(ActionEvent e) 
            { 
            	if (isCorrect == false && removingCharacters < 12) {
            		isGuessing = true;
            		FXDialog.print("Click a fish to guess");
            	}
            } 
        }; 
        btnGuess.setOnAction(buttonGuess);
   		
        //Button to end the current game
   		Button btnEnd = new Button("End");
   		btnEnd.setPrefWidth(80);
   		root.add(btnEnd, 7, 8, 1, 1);
   		EventHandler<ActionEvent> buttonEnd = new EventHandler<ActionEvent>() { 
            //Button goes to end method which changes scene
   			public void handle(ActionEvent e) 
            { 
            	try {
					end();
 			    } catch (IOException error) {
 			    	FXDialog.print("Exception: " + error);
 			    	System.exit(0);
 			    } catch (Exception e1) {
					FXDialog.print("Exception: " + e1);
 			    	System.exit(0);
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
      	
      	//Dialog that prompts user to get a clue
      	FXDialog.print("Good job! To begin the game, get a clue!");
	}
	
	/**
	 * The charactersSetup method outputs all images and labels of the 12 characters. It also allows for the
	 * characters to be clicked 
	 * @throws FileNotFoundException Unable to open pathname
	 * @see java.io.FileNotFoundException
	 */
	private void charactersSetup() throws FileNotFoundException {
		
		//All image views of the characters are created
		fishImagesDisplay = player.display();
    	for (int i = 0; i < 12; i++) {
   			imgFish[i] = new ImageView(getClass().getResource("/fish/" + fishImagesDisplay[i] + ".png").toString());
		}
		
    	//Variable that determines which character is being set up
		int x = 0;
		
		//All characters (images and labels) are ouputted to the scene in 3 columns and 4 rows
		for (int row = 0; row < 8; row+=2) {
			for (int col = 0; col < 6; col+=2) {
				
				//Get user input for the names of the characters
				TextInputDialog dialog = new TextInputDialog();
				dialog.setTitle("Text Input Dialog");
				dialog.setHeaderText("What would you like to name this character?");
				dialog.setContentText("Please enter name:");
				dialog.setGraphic(imgFish[x]);

				Optional<String> result = dialog.showAndWait();
				//Add name to character information or CANCEL and exit entire program
				if (result.isPresent()){
				    player.createCharactertistics(x, result.get());
				} else {
					System.exit(0);
				}
				
				//Get output label information
				fishCharacteristicsDisplay = player.characteristicsDisplay(x);
				
				//Create new labels and buttons for each character and set to correct information
				lblFish[x] = new Label(fishCharacteristicsDisplay);
				btnFish[x] = new Button();
				imgFish[x].setFitWidth(75);
				imgFish[x].setPreserveRatio(true);
				btnFish[x].setGraphic(imgFish[x]);
				
				//Variable needed for fishSelected method
				int temp = x;
				
				//Go to fishSelected method when button is clicked
				btnFish[x].setOnAction(event -> {
					try {
						fishSelected(event, temp);
					} catch (FileNotFoundException e) {
						FXDialog.print("Exception: " + e);
						System.exit(0);
					}
				});
				
				//Add labels and buttons to the scene
				root.add(lblFish[x], col+1, row+1, 1, 2);
				root.add(btnFish[x], col, row+1, 1, 2);
				
				//Continue to next character
				x++;		
			}
		}
		
		//Input characters into a text file in GWPlayer method
		player.fileInput();
	}
	
	/**
	 * The fishSelected method determines if a character that has been clicked is being guessed or removed and 
	 * continues appropriately
	 * @param event ActionEvent that is needed to see with character was clicked
	 * @param x int is the character in the btnFish and imgFish arrays that was clicked
	 * @throws FileNotFoundExceptionUnable to open pathname
	 * @see java.io.FileNotFoundException
	 */
	private void fishSelected(ActionEvent event, int x) throws FileNotFoundException {
		
		//Change image if the character is not guessing
		if (isGuessing == false) {
			
			//Changes data in GWPlayer
			player.removed(x);
			if (isRemoving == true) {
				
				//Get information on which button was clicked
				btnFish[x] = (Button) event.getSource();
		
				//Set clicked character to X image
				ImageView temp = new ImageView(imgX);
				temp.setFitWidth(75);
				temp.setPreserveRatio(true);
				btnFish[x].setGraphic(temp);
				imgFish[x] = temp;
				
				//Increase number of characters removed
				removingCharacters++;
			}
		} else {
			//Check if correct character is selected
			isCorrect = player.compareGuess(x);
			
			//Continue to guess method
			guess();
		}
		
		//User is forced to guess if there is only 1 character left
		if (removingCharacters == 11) {
			FXDialog.print("You are now guessing the last fish");
			isGuessing = true;
			removingCharacters++;
		}
	}
	
	/**
	 * The getClue method prompts user to select a type of clue and outputs clue to the listView
	 */
	private void getClue() {
		
		//Make sure that game is still being played
		if (isCorrect == false && removingCharacters < 12) {
			
			//Alert dialog that asks user for type of clue to output
			Alert clueType = new Alert(AlertType.CONFIRMATION);
			clueType.setTitle("Choose Clue Type Dialog");
			clueType.setHeaderText("Please choose a characteristic to get a clue about");

			//Custom buttons and button names for the different types of clues for alert dialog
			ButtonType btnColour = new ButtonType("Colour");
			ButtonType btnHat = new ButtonType("Hat");
			ButtonType btnSize = new ButtonType("Size");
			ButtonType btnMarkings = new ButtonType("Markings");
			ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
			clueType.getButtonTypes().setAll(btnColour, btnHat, btnSize, btnMarkings, buttonTypeCancel);

			//User selects type and is now able to remove characters
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
				//User did not click a type of clue and cannt remove any characters yet
				Alert error = new Alert(AlertType.ERROR);
				error.setTitle("Error Dialog");
				error.setHeaderText("No clue type selected");
				error.showAndWait();
			}
		}
		
		//Dialog instruction message only outputted after the first clue
		if (clueMessage == 0) {
			FXDialog.print("Click as many characters as you'd like to remove and then get another clue or make a guess! If it's helpful,"
					+ " sort the fish alphabetically");
			clueMessage++;
		}
	}
	
	/**
	 * The sort method sorts all the character images and labels alphabetically from A to Z
	 */
	private void sort() {
		
		//Array information about the order of the characters from sort method in GWPlayer
		int imgOrder [] = player.sort();
		
		//Variable needed for character number
		int x = 0;
		
		//Output images and labels in the same 3 column and 4 row format
		for (int row = 0; row < 8; row+=2) {
			for (int col = 0; col < 6; col+=2) {
				
				//Change character order
				fishCharacteristicsDisplay = player.characteristicsDisplay(x);
				lblFish[x].setText(fishCharacteristicsDisplay);
				btnFish[x].setGraphic(imgFish[imgOrder[x]]);

				//Continue to next character
				x++;
			}
		}
		
		//Dialog confirming sorting
		FXDialog.print("All fish have been sorted alphabetically");
	}
	
	/**
	 * The guess method compares the selected character to the correct character and outputs message
	 */
	private void guess() {
		
		//User loses if the selected character is the last character and incorrect
		if (isCorrect == false && removingCharacters == 12) {
			FXDialog.print("That guess was incorrect. You lost!");
			imgCorrectFish.setVisible(true);
			questionMark.setVisible(false);
		} else if (isCorrect == false) {
			//User guesses incorrectly but can continue if there is more than one character left
			FXDialog.print("That guess was incorrect. Get more clues or exit the game.");
		} else {
			//User wins if the selected character is correct
			FXDialog.print("Congratulations! You won! End the game to reset or to exit.");
			imgCorrectFish.setVisible(true);
			questionMark.setVisible(false);
		}
		
		//Resets values to default (false)
		isGuessing = false;
		isRemoving = false;
	}
	
	/**
	 * The end method is the button to exit the game, save the game log, and / or reset the game to play again
	 * @throws Exception On file input error
	 * @see Exception
	 */
	private void end() throws Exception {
		
		//Data is changes in end method in GWPlayer
		player.end();
		
		//User that loses / gives up is displayed correct fish for closure
		if (isCorrect == false) {
			
			//Alert dialog displays image of correct fish
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information Dialog");
			alert.setHeaderText(null);
			alert.setContentText("This was the correct fish");
			imgCorrectFish.setVisible(true);
			alert.setGraphic(imgCorrectFish);
			
			alert.showAndWait();
		}
		
		//Prompts user to save game log to specific file and is automatically saved to GuessWho text file either way
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("Text Input Dialog");
		dialog.setHeaderText("Enter a name and click OK to save game to your text file. \nThe game will automatically be saved to GuessWho.txt");
		dialog.setContentText("Please enter file name to save to:");

		Optional<String> result = dialog.showAndWait();
		//Saves game log to file of choice
		if (result.isPresent() && result.get() != "GuessWho"){
			player.userFile(result.get());  
			FXDialog.print("Game save outputted to " + result.get());
		} else{
			//Saves game log to just GuessWho file
			FXDialog.print("Game save outputted to GuessWho");
		}
		
		//Asks user to play again
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmation Dialog");
		alert.setHeaderText(null);
		alert.setContentText("Would you like to play again?");
		
		//Custom buttons instead of OK and CANCEL
		ButtonType btnYes = new ButtonType("Yes");
		ButtonType btnNo = new ButtonType("No", ButtonData.CANCEL_CLOSE);
		alert.getButtonTypes().setAll(btnYes, btnNo); 
				
		Optional<ButtonType> result1 = alert.showAndWait();
		//Resets data to play again
		if (result1.get() == btnYes){
			
			//Hides correct fish image
			imgCorrectFish.setVisible(false);
			
			//Resets all character labels, buttons, and images
			for (int i = 0; i < 12; i++) {
				lblFish[i].setText("");
				btnFish[i].setGraphic(null);
				imgFish[i].setImage(null);
				
			}
			
			//Resets variable data
			clue = 0;
			clueMessage = 0;
		    removingCharacters = 0;
		    isCorrect = false;
		    lstClues.getItems().clear();
		    
		    //Shows question mark in spot of correct fish again
			questionMark.setVisible(true);
			
			//Creates new GWPlayer and charactersSetup to set up new random characters
			player = new GWPlayer();
			charactersSetup();
			
			//New correct fish is chosen
			int value = player.correctFish();
			imgCorrectFish = new ImageView(getClass().getResource("/fish/" + fishImagesDisplay[value] + ".png").toString());
			imgCorrectFish.setFitWidth(75);
			imgCorrectFish.setPreserveRatio(true);
			imgCorrectFish.setVisible(false);
			root.add(imgCorrectFish, 3, 0);
		} else {
			//User does not play again and program is exited
			Platform.exit();
		}
	}
	
	/**
	 * This is the method that launches the program and makes use of the start method
	 * @param args Unused
	 * @return Nothing
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
package rst;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Guess Who Player class
 * The GWPlayer program uses all the information from the GuessWhoFXGame to actually play the game
 * (creating characteristics, choose a correct character, return which character image and label is 
 * displayed on the scene, input the game log in a file / files, return clues, compare guess, and end the 
 * game).
 * 
 * @author s427549
 * @since 2021-1-18
 */
public class GWPlayer {

	//Array of all the characteristics combinations
	private final String characteristics [] = {"RNSL", "RNSS", "RNML", "RNMS", "RNLL", "RNLS", "RFSL", "RFSS", "RFML", 
			"RFMS", "RFLL", "RFLS", "RBSL", "RBSS", "RBML", "RBMS", "RBLL", "RBLS", "YNSL", "YNSS", "YNML", "YNMS",
			"YNLL", "YNLS", "YFSL", "YFSS", "YFML", "YFMS", "YFLL", "YFLS", "YBSL", "YBSS", "YBML", "YBMS", "YBLL",
			"YBLS", "BNSL", "BNSS", "BNML", "BNMS", "BNLL", "BNLS", "BFSL", "BFSS", "BFML", "BFMS", "BFLL", "BFLS",
			"BBSL", "BBSS", "BBML", "BBMS", "BBLL", "BBLS"};
	
	//Array that holds 12 of the characteristic combinations
	private String characters[] = new String [12];
	//ArrayList that has characteristic combination but removes the ones that are already chosen
	private ArrayList<String> arrayList = new ArrayList<String>();
	//Array that determines which of the 12 characters have been removed 
	private String removed []  = new String [12];
	
	//Array of characters created in Character class
	private Character charactersCharacteristics[] = new Character [12];
	//Character that is the correct fish
	private Character correctFish;
	
	//ArrayList of characteristics for returning clues
	private ArrayList<String> arrayColours = new ArrayList<String>();
	private ArrayList<String> arrayHat = new ArrayList<String>();
	private ArrayList<String> arraySize = new ArrayList<String>();
	
	//PrintWriter that writes game log in GuessWho text file
	private PrintWriter file = new PrintWriter(new PrintWriter("data/GuessWho.txt"));
	
	//Array of sorted characters created in Character class 
	private Character sorted [] = new Character [12];
	//Array of numbers representing characters that will be sorted
	private int fishImageOrder [] = new int []{0,1,2,3,4,5,6,7,8,9,10,11};
	
	/**
	 * GWPlayer randomly chooses 12 characters that will be played in the game
	 * @throws FileNotFoundException Unable to open pathname
	 * @see java.io.FileNotFoundException
	 */
	public GWPlayer() throws FileNotFoundException {
		
		//Copies all characteristic combos from array to arrayList
		for (int z = 0; z < 54; z++) {
			arrayList.add(characteristics[z]);
		}
		
		//Randomly chooses 12 characteristic combination
		int value;
		for (int i = 0; i < 12; i++) {
			value = (int)(Math.random()*arrayList.size());
			
			//Sets characteristic combo to characters array
			characters[i] = arrayList.get(value);
			
			//Removes characteristic combo from arrayList to avoid duplications
			arrayList.remove(value);
		}
		
		//Moves all characters to removed array
		for (int i = 0; i < characters.length; i++) {
			removed[i] = characters[i];
		}
		
		//Fills arrays needed for clues
		arrayColours.addAll(Arrays.asList("red", "blue", "yellow"));
		arrayHat.addAll(Arrays.asList("none", "fedora", "baseball cap"));
		arraySize.addAll(Arrays.asList("small", "medium", "large"));
	}
	
	/**
	 * The createCharacteristics method creates 12 characters in the Character class
	 * @param i int The first parameter of the createCharactertistics method that is the character number
	 * @param name String The second parameter of the createCharactertistics method that is the character name
	 */
	public void createCharactertistics(int i, String name) {
		//New Character created and set to charactersCharacteristics array
		charactersCharacteristics[i] = new Character(characters[i], name);
	}
	
	/**
	 * The correctFish method randomly chooses a fish from the 12 characters
	 * @return int This is the character number that was chosen
	 */
	public int correctFish() {
		
		//Randomly chooose character
		int value;
		value = (int)(Math.random()*12);
		
		//Set character as correctFish
		correctFish = charactersCharacteristics[value];
		
		//Remove the correct fish characteristics from the clue arrays
		arrayColours.remove(correctFish.getColour());
		arrayHat.remove(correctFish.getHat());
		arraySize.remove(correctFish.getSize());
		
		//Return the character number of the correct character
		return value;
	}
	
	/**
	 * The characteristicsDisplay method returns a String of a character that will be displayed as a label
	 * @param i int This is the first parameter of the characteristicsDisplay method and it is the character number
	 * @return String Return what will be the text of the label of the character in the scene
	 */
	public String characteristicsDisplay(int i) {
		
		//Initialize the variable
		String output = "";
		
		//Output all characteristics in a readable format
		output = "Name: " + charactersCharacteristics[i].getName() + "\nColour: " + charactersCharacteristics[i].getColour() +
				"\nHat: " + charactersCharacteristics[i].getHat() + "\nSize : " + charactersCharacteristics[i].getSize() +
				"\nMarkings: " + charactersCharacteristics[i].getMarkings();
			
		//Return String
		return output;
	}
	
	/**
	 * The display method returns the array full of characteristic combinations of the 12 fish to the GuessWhoFXGame
	 * @return String[] Returns the array of characteristic combinations
	 */
	public String[] display() {
		return characters;
	}	
	
	/**
	 * The fileInput method inputs all the of 12 characters to the text file
	 * @throws FileNotFoundException Unable to open pathname
	 * @see java.io.FileNotFoundException
	 */
	public void fileInput() throws FileNotFoundException {
		
		//Describing what is being inputted
		file.println("Current characters");
		
		//Prints entire array in a readable format
		for (int i = 0; i < charactersCharacteristics.length; i++) {
			file.println("\nName: " + charactersCharacteristics[i].getName() + "\nColour: " + charactersCharacteristics[i].getColour() +
					"\nHat: " + charactersCharacteristics[i].getHat() + "\nSize : " + charactersCharacteristics[i].getSize() +
					"\nMarkings: " + charactersCharacteristics[i].getMarkings());
		}
		
		//Formatting
		file.println("--------------------------------------");
	}
	
	/**
	 * The clueColour method returns a clue about the colour of the correct fish
	 * @return String Returns clue about the colour of the correct fish
	 */
	public String clueColour() {
		//Initializes variable
		String output = "";

		//Output colour clue if they are still needed
		if (arrayColours.size() != 0) {
			//Randomly choose which colour
			int value = (int)(Math.random()*arrayColours.size());
			output = "The fish is not " + arrayColours.get(value);
		
			//Remove that colour from arrayList of colours for no duplicate clues
			arrayColours.remove(value);
		} else {
			//Output no more colour clues are needed
			output = "No more colour clues needed";
		}
		
		//Print into file that user got a colour clue
		clueInput(output);
		
		//Return clue
		return output;
	}
	
	/**
	 * The clueHat method returns a clue about the hat of the correct fish
	 * @return String Returns clue about the hat of the correct fish
	 */
	public String clueHat() {
		//Initializes variable
		String output = "";
		
		//Output hat clue if they are still needed
		if (arrayHat.size() != 0) {
			//Randomly choose which hat
			int value = (int)(Math.random()*arrayHat.size());
		
			if (arrayHat.get(value) == "none") {
				output = "The fish has a hat";
			} else {
				output = "The fish does not have a " + arrayHat.get(value);
			}
			
			//Remove that hat from the arrayList of hats for no duplicate clues
			arrayHat.remove(value);
		} else {
			//Output no more hat clues are needed
			output = "No more hat clues needed";
		}
		
		//Prints into file that user got a hat clue
		clueInput(output);
		
		//Returns clue
		return output;
	}
	
	/**
	 * The clueSize method returns a clue about the size of the correct fish
	 * @return String Returns clue about the size of the correct fish
	 */
	public String clueSize() {
		//Initializes variable
		String output = "";
		
		//Randomly chooses what size
		if (arraySize.size() != 0) {
			int value = (int)(Math.random()*arraySize.size());
		
			output = "The fish is not " + arraySize.get(value);
		
			//Remove that size from the arrayList of sizes for no duplicate clues
			arraySize.remove(value);
		} else {
			//Output no more size clues are needed
			output = "No more size clues needed";
		}
		
		//Prints into file that user got a size clue
		clueInput(output);
		
		//Returns clue
		return output;
		
	}
	
	/**
	 * The clueMarkings method returns a clue about the markings of the correct fish
	 * @param clue int Determines if marking clue has already been returned
	 * @return String Returns clue about the markings of the correct fish
	 */
	public String clueMarkings(int clue) {
		//Initializes variable
		String output = "";
		
		//Outputs the marking clue if it is the first marking clue
		if (clue == 0) {
			output = "The fish has " + correctFish.getMarkings();
		} else {
			//Outputs no more markings clues are needed
			output = "No more markings clues needed";
		}
		
		//Prints into file that user got a marking clue
		clueInput(output);
		
		//Returns clue
		return output;
		
	}
	
	/**
	 * The clueInput method inputs that a clue was given into the text file
	 * @param clue String This is the first parameter of the clueInput method that states what the clue was
	 */
	private void clueInput(String clue) {
		file.println("Clue: " + clue);
		file.println("--------------------------------------");
	}
	
	/**
	 * The sort method sorts the characters alphabetically from A to Z. Alphabetical sorting uses the bubble sort algorithm
	 * @return int [] Returns array of character numbers after being sorted 
	 */
	public int [] sort() {
		
		//Copy charactersCharacteristics array to sorted
		for (int i = 0; i < 12; i++) {
			sorted[i] = charactersCharacteristics[i];
		}
		
		//Variables needed for sorting
		boolean hadSwap = true;
		int bottom = sorted.length-1;
		
		do {
			hadSwap = false;
			
			//Goes through entire array
			for (int x = 0; x < bottom; x++) {
				//Compares names while ignoring case
				if (sorted[x].getName().compareToIgnoreCase(sorted[x+1].getName())>0){
					hadSwap = true;
					
					//Swaps character to correct order
					Character temp = sorted[x];
					sorted[x] = sorted[x+1];
					sorted[x+1] = temp;
					
					//Swaps character number to correct order 
					int temp2 = fishImageOrder[x];
					fishImageOrder[x] = fishImageOrder[x+1];
					fishImageOrder[x+1] = temp2;
				}
			}
			bottom = bottom - 1;
		} while (hadSwap == true);
		
		//Set charactersCharacteristics to the sorted array
		for (int i = 0; i < 12; i++) {
			charactersCharacteristics[i] = sorted[i];
		}
		
		//Prints into file that user sorted the list
		file.println("Sorted characters alphabetically");
		file.println("--------------------------------------");
		
		//Return array of character numbers
		return fishImageOrder;
	}
	
	/**
	 * The removed method sets the character's characteristics combination in the removed array to "null"
	 * @param x int This is the first parameter of the removed method and it is the number of the character being removed
	 * @throws FileNotFoundException Unable to open pathname
	 * @see java.io.FileNotFoundException
	 */
	public void removed(int x) throws FileNotFoundException {
		
		//Sets characteristics of character in array removed to "null"
		removed[x] = "null";
		
		//Prints in the text file that the user removed this specific character
		file.println("Removed : \nName: " + charactersCharacteristics[x].getName() + "\nColour: " + charactersCharacteristics[x].getColour() +
				"\nHat: " + charactersCharacteristics[x].getHat() + "\nSize : " + charactersCharacteristics[x].getSize() +
				"\nMarkings: " + charactersCharacteristics[x].getMarkings());
		file.println("--------------------------------------");
	}

	/**
	 * The compareGuess method compares the guess of the user to the correct character
	 * @param x int The user guessed this character
	 * @return boolean Return if the user guessed correctly
	 */
	public boolean compareGuess(int x) {

		//Print in file whether the guess of the character was correct or incorrect
		if (charactersCharacteristics[x] == correctFish) {
			file.println("Guessed correct character (" + charactersCharacteristics[x].getName() + ")");
			file.println("--------------------------------------");
			return true;
		} else {
			file.println("Guessed incorrect character (" + charactersCharacteristics[x].getName() + ")");
			file.println("--------------------------------------");
			return false;
		}
	}
	
	/**
	 * The userFile method reads the GuessWho text file data and prints the lines into the user's file
	 * @param file String This is the first parameter of the userFile method and it is the name of the user's file
	 * @throws IOException Error in input or output operation
	 * @see java.io.IOException
	 */
	public void userFile(String file) throws IOException {
		
		//Program creates new BufferedReader and PrintWriter
		BufferedReader readFile = new BufferedReader(new FileReader("data/GuessWho.txt"));
		PrintWriter fileOut = new PrintWriter(new BufferedWriter(new FileWriter("data/" + file + ".txt")));
		
		//Variable needed for copying lines from the file
		String line;
		
		//Continue reading file until null
		while ((line = readFile.readLine()) != null) {
        	fileOut.println(line);
        }
		
		//Close bufferedReader
        readFile.close();
        
        //Print into new file and close PrintWriter
        fileOut.println(line);
        fileOut.close();
	}
	
	/**
	 * The end method prints that the user closed the game and closes the GuessWho text file 
	 */
	public void end() {
		
		//Prints message into the text file
		file.println("Game ended. The correct fish was " + correctFish.getName());
		
		//Closes PrintWriter
		file.close();
	}
	
}
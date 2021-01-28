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

public class GWPlayer {
	
	//private String characterDisplay [] = new String [12];
	//TODO static or private
	private final String characteristics [] = {"RNSL", "RNSS", "RNML", "RNMS", "RNLL", "RNLS", "RFSL", "RFSS", "RFML", 
			"RFMS", "RFLL", "RFLS", "RBSL", "RBSS", "RBML", "RBMS", "RBLL", "RBLS", "YNSL", "YNSS", "YNML", "YNMS",
			"YNLL", "YNLS", "YFSL", "YFSS", "YFML", "YFMS", "YFLL", "YFLS", "YBSL", "YBSS", "YBML", "YBMS", "YBLL",
			"YBLS", "BNSL", "BNSS", "BNML", "BNMS", "BNLL", "BNLS", "BFSL", "BFSS", "BFML", "BFMS", "BFLL", "BFLS",
			"BBSL", "BBSS", "BBML", "BBMS", "BBLL", "BBLS"};
	private String characters[] = new String [12];
	
	//private ArrayList arrayList = new ArrayList(); 
	private ArrayList<String> arrayList = new ArrayList<String>();
	private String removed []  = new String [12];
	
	private Character charactersCharacteristics[] = new Character [12];
	
	private Character correctFish;
	
	private ArrayList<String> arrayColours = new ArrayList<String>();
	private ArrayList<String> arrayHat = new ArrayList<String>();
	private ArrayList<String> arraySize = new ArrayList<String>();
	
	private PrintWriter file = new PrintWriter(new PrintWriter("data/GuessWho.txt"));
	
	private Character sorted [] = new Character [12];
	
	private int fishImageOrder [] = new int []{0,1,2,3,4,5,6,7,8,9,10,11};
	
	public GWPlayer() throws FileNotFoundException {
		
		for(int z = 0; z < 54; z++) {
			arrayList.add(characteristics[z]);
		}
//		arrayList.add("RNSL", "RNSS", "RNML", "RNMS", "RNLL", "RNLS", "RFSL", "RFSS", "RFML", 
//				"RFMS", "RFLL", "RFLS", "RBSL", "RBSS", "RBML", "RBMS", "RBLL", "RBLS", "YNSL", "YNSS", "YNML", "YNMS",
//				"YNLL", "YNLS", "YFSL", "YFSS", "YFML", "YFMS", "YFLL", "YFLS", "YBSL", "YBSS", "YBML", "YBMS", "YBLL",
//				"YBLS", "BNSL", "BNSS", "BNML", "BNMS", "BNLL", "BNLS", "BFSL", "BFSS", "BFML", "BFMS", "BFLL", "BFLS",
//				"BBSL", "BBSS", "BBML", "BBMS", "BBLL", "BBLS");
		
		int value;
		for(int i = 0; i < 12; i++) {
			value = (int)(Math.random()*arrayList.size());
			
			characters[i] = arrayList.get(value);
			
			arrayList.remove(value);
		}
		
		for(int i = 0; i < characters.length; i++) {
			removed[i] = characters[i];
		}
		
		
		arrayColours.addAll(Arrays.asList("red", "blue", "yellow"));
		
		arrayHat.addAll(Arrays.asList("none", "fedora", "baseball cap"));
		
		arraySize.addAll(Arrays.asList("small", "medium", "large"));
		
	}
	
	public int correctFish() {
		int value;
		value = (int)(Math.random()*12);
		
		correctFish = charactersCharacteristics[value];
		
		arrayColours.remove(correctFish.getColour());
		arrayHat.remove(correctFish.getHat());
		arraySize.remove(correctFish.getSize());
		
		return value;
	}
	
	public void fileInput() throws FileNotFoundException {
		
		file.println("Current characters");
		
		for(int i = 0; i < charactersCharacteristics.length; i++) {
			//Prints entire array
			//TODO print int charactersCharacteristics name and characteristics
			file.println("\nName: " + charactersCharacteristics[i].getName() + "\nColour: " + charactersCharacteristics[i].getColour() +
					"\nHat: " + charactersCharacteristics[i].getHat() + "\nSize : " + charactersCharacteristics[i].getSize() +
					"\nMarkings: " + charactersCharacteristics[i].getMarkings());
		}
		
		file.println("--------------------------------------");
		
	}
	
	public void removed(int x) throws FileNotFoundException {
		
		removed[x] = "null";
		
		file.println("Removed : \nName: " + charactersCharacteristics[x].getName() + "\nColour: " + charactersCharacteristics[x].getColour() +
				"\nHat: " + charactersCharacteristics[x].getHat() + "\nSize : " + charactersCharacteristics[x].getSize() +
				"\nMarkings: " + charactersCharacteristics[x].getMarkings());
		
		file.println("--------------------------------------");
		
	}
	
	
	public void createCharactertistics(int i, String name) {
			
		charactersCharacteristics[i] = new Character(characters[i], name);
		
			
		
	}
	
	public String clueColour() {
		
		String output = "";

		if(arrayColours.size() != 0) {
			int value = (int)(Math.random()*arrayColours.size());
		
			output = "The fish is not " + arrayColours.get(value);
		
			arrayColours.remove(value);
		}
		else {
			output = "No more colour clues needed";
		}
		
		file.println("Clue: " + output);
		file.println("--------------------------------------");
		
		
		return output;
		
	}
	
	public String clueHat() {
		String output = "";
		
		if(arrayHat.size() != 0) {
			int value = (int)(Math.random()*arrayHat.size());
		
			if(arrayHat.get(value) == "none") {
				output = "The fish has a hat";
			}
			else {
				output = "The fish does not have a " + arrayHat.get(value);
			}
			
			arrayHat.remove(value);
		}
		else {
			output = "No more hat clues needed";
		}
		
		file.println("Clue: " + output);
		file.println("--------------------------------------");
		
		return output;
		
	}
	
	public String clueSize() {
		String output = "";
		
		if(arraySize.size() != 0) {
			int value = (int)(Math.random()*arraySize.size());
		
			output = "The fish is not " + arraySize.get(value);
		
			arraySize.remove(value);
		}
		else {
			output = "No more size clues needed";
		}
		
		file.println("Clue: " + output);
		file.println("--------------------------------------");
		
		return output;
		
	}
	
	public String clueMarkings(int clue) {
		String output = "";
		
		if(clue == 0) {
			output = "The fish has " + correctFish.getMarkings();
		}
		else {
			output = "No more markings clues needed";
		}
		
		file.println("Clue: " + output);
		file.println("--------------------------------------");
		
		return output;
		
	}
	
	public int [] sort() {
		
		for(int i = 0; i < 12; i++) {
			sorted[i] = charactersCharacteristics[i];
		}
		
		boolean hadSwap = true;
		int bottom = sorted.length-1;
		
		do {
			hadSwap = false;
		
			for(int x = 0; x < bottom; x++) {

				if(sorted[x].getName().compareToIgnoreCase(sorted[x+1].getName())>0){
					hadSwap = true;
					
					Character temp = sorted[x];
					sorted[x] = sorted[x+1];
					sorted[x+1] = temp;
					
					int temp2 = fishImageOrder[x];
					fishImageOrder[x] = fishImageOrder[x+1];
					fishImageOrder[x+1] = temp2;
					
				}
			}
			bottom = bottom - 1;
		} while (hadSwap == true);
		
		for(int i = 0; i < 12; i++) {
			charactersCharacteristics[i] = sorted[i];
		}
		
		file.println("Sorted characters alphabetically");
		file.println("--------------------------------------");
		
		return fishImageOrder;
	}
	
	
	public boolean compareGuess(int x) {
		//if(removed[x] != "null" && charactersCharacteristics[x] == correctFish) {
		if(
			charactersCharacteristics[x] == correctFish) {
			file.println("Guessed correct character (" + charactersCharacteristics[x].getName() + ")");
			file.println("--------------------------------------");
			return true;
		}
		else {
			file.println("Guessed incorrect character (" + charactersCharacteristics[x].getName() + ")");
			file.println("--------------------------------------");
			return false;
		}
	}
	
	public String[] display() {
		
		return characters;
		
	}	
	
	public String characteristicsDisplay(int i) {
		
		//TODO add Age
		
		String output = "";
		
		//for(int i = 0; i < 12; i++) {
			
			output = "Name: " + charactersCharacteristics[i].getName() + "\nColour: " + charactersCharacteristics[i].getColour() +
					"\nHat: " + charactersCharacteristics[i].getHat() + "\nSize : " + charactersCharacteristics[i].getSize() +
					"\nMarkings: " + charactersCharacteristics[i].getMarkings();
			
		//}
		
		return output;
	}
	
	public void userFile(String file) throws IOException {
		
		BufferedReader readFile = new BufferedReader(new FileReader("data/GuessWho.txt"));
		PrintWriter fileOut = new PrintWriter(new BufferedWriter(new FileWriter("data/" + file + ".txt")));
		 String line;

	        while ((line = readFile.readLine()) != null) {
	        	fileOut.println(line);
	        }
	        readFile.close();
	        
	        fileOut.println(line);
	        
		fileOut.close();
	}
	
	public void end() {
		file.println("Game ended. The correct fish was " + correctFish.getName());
		file.close();
	}
	
}
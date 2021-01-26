package rst;

import java.util.ArrayList;

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
	
	private Character charactersCharacteristics[] = new Character [12];
	private boolean isCorrectGuess = false;
	
	private Character correctFish;
	
	public GWPlayer() {
		
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
		
	}
	
	public int correctFish() {
		int value;
		value = (int)(Math.random()*12);
		
		correctFish = charactersCharacteristics[value];
		
		return value;
	}
	
	public void fileInput() {
		
	}
	
	public void createCharactertistics(int i, String name) {
			
		charactersCharacteristics[i] = new Character(characters[i], name);
			
		
	}
	
	public String getNewClue(int clue) {
		String output = "";
		
		if(clue == 0) {
			output = "The fish's colour is " + correctFish.getColour();
		}
		else if(clue == 1) {
			output = "The fish's hat is " + correctFish.getHat();
		}
		else if(clue == 2) {
			output = "The fish's size is " + correctFish.getSize();
		}
		else if(clue == 3) {
			output = "The fish's marks are " + correctFish.getMarkings();
		}
		else {
			output = "No more clues needed";
		}
		
		return output;
		
	}
	
	public void sortAlpha() {
		
		
	}
	
	public void sortAge() {
		
	}
	
	public boolean compareGuess() {
		return false;
		
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
}
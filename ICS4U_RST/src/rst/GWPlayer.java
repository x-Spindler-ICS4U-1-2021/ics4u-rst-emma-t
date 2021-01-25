package rst;

public class GWPlayer {
	
	private String characterDisplay [][] = new String [4][3];
	//TODO static or private
	private final String characteristics [] = {"RNSL", "RNSS", "RNML", "RNMS", "RNLL", "RNLS", "RFSL", "RFSS", "RFML", 
			"RFMS", "RFLL", "RFLS", "RBSL", "RBSS", "RBML", "RBMS", "RBLL", "RBLS", "YNSL", "YNSS", "YNML", "YNMS",
			"YNLL", "YNLS", "YFSL", "YFSS", "YFML", "YFMS", "YFLL", "YFLS", "YBSL", "YBSS", "YBML", "YBMS", "YBLL",
			"YBLS", "BNSL", "BNSS", "BNML", "BNMS", "BNLL", "BNLS", "BFSL", "BFSS", "BFML", "BFMS", "BFLL", "BFLS",
			"BBSL", "BBSS", "BBML", "BBMS", "BBLL", "BBLS"};
	private String characters[] = new String [12];
	private Character charactersCharacteristics[];
	private boolean isCorrectGuess = false;
	
	public GWPlayer() {
		String characteristicsCopy [] = new String [54];
	
		for(int z = 0; z < 54; z++) {
			characteristicsCopy[z] = characteristics[z];
		}
		
		int value;
		for(int i = 0; i < 12; i++) {
			value = (int)(Math.random()*characteristicsCopy.length);
			
			//charactersCharacteristics[i] = new Character(characteristicsCopy[value]);
			characters[i] = characteristicsCopy[value];
			
			String temp [] = new String [characteristicsCopy.length-1];
			
			for (int x = 0, y = 0; x < characteristicsCopy.length; x++) { 
				
				if (x == value) { 
					continue; 
				} 
				
				temp[y++] = characteristicsCopy[x]; 	
			} 
			
			
		}
		
	}
	
	public void createCharactertistics() {
//		String characteristicsCopy [] = new String [54];
//		Arrays.fill(characteristicsCopy, characteristics);
//	
//		int value;
//		for(int i = 0; i < 12; i++) {
//			value = (int)(Math.random()*characteristicsCopy.length);
//			
//			charactersCharacteristics[i] = new Character(characteristicsCopy[value]);
//			characters[i] = characteristicsCopy[value];
//			
//			String temp [] = new String [characteristicsCopy.length-1];
//			
//			for (int x = 0, y = 0; x < characteristicsCopy.length; x++) { 
//				
//				if (x == value) { 
//					continue; 
//				} 
//				
//				temp[y++] = characteristicsCopy[x]; 	
//			} 
//			
//			
//		}
	}
	
	public String getNewClue() {
		return null;
		
	}
	
	public void sortAlpha() {
		
		
	}
	
	public void sortAge() {
		
	}
	
	public boolean compareGuess() {
		return false;
		
	}
	
	public String[][] display() {
		
		int i = 0;
		
		for (int row = 0; row < 4; row++) {
			for (int col = 0; col < 3; col++) {
				characterDisplay[row][col] = characters[i];
				i++;
			}
		}
		
		return characterDisplay;
		
	}	
}
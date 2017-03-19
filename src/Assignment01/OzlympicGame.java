package Assignment01;

import java.util.Scanner;

/* 
 * OzlympicGame create a Driver object
 * for implement the game controller.
 * Addd by LosoLai 19/03/17
 */
public class OzlympicGame {
	public static final int SELECT_GAME           = 1;
	public static final int PREDIT_WINNER         = 2;
	public static final int START_GAME            = 3;
	public static final int DISPLAY_FINALRESULT   = 4;
	public static final int DISPLAY_ATHLETEPOINTS = 5;
	public static final int EXIT_GAME             = 6;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner reader = new Scanner(System.in);
		int userInput = 0;
		String sInput = "";
		
		boolean bException = false;
		boolean bValidate = false;
		boolean bEndGame = false;
		
		do{
			//display the main menu
			OzlympicGame.displayMenu();
			sInput = reader.next();
			try
			{
				//get user input
				userInput = Integer.parseInt(sInput);
			}
			catch(Exception e){
				bException = showUserChoiceWarning(sInput);
			}
			
			if(bException)
				continue;
			
			bValidate = inputValidation(sInput, userInput);
			if(!bValidate)
				continue;
			
			if((userInput == EXIT_GAME))
				break;
			
			Driver gameDriver = new Driver();
			bEndGame = !(gameDriver.processByUserInput(userInput));
		}
		while((userInput != EXIT_GAME) && (!bEndGame));
		
		OzlympicGame.gameOver();
	}

	public static void displayMenu()
	{
		System.out.print("Ozlympic Game\n" +
						 "===================================\n" + 
						 "1. Select a game to run\n" +
						 "2. Predict the winner of the game" + 
						 "3. Start the game\n" +
						 "4. Display the final results of all games\n" +
						 "5. Display the points of all athletes\n" +
						 "6. Exit\n\n" +
						 "Enter an option:");
	}
	public static void gameOver()
	{
		System.out.println("Game Over!!");
	}
	
	public static boolean showUserChoiceWarning(String input)
	{
		if(input.length() >= 32)
		{
			System.out.println("Input overflow, please enter valid range (1-6)\n");
			return true;
		}
		
		System.out.println("Input Invalid, please make sure can only enter number (1-6)\n");
		return true;
	}
	public static boolean inputValidation(String sInput, int input)
	{
		if(input < 0 || input > 6)
		{
			System.out.println("Please enter valid range (1-6)\n");
			return false;
		}
		return true;
	}
}

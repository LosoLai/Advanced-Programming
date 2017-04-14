package Assignment01;
import java.util.Scanner;

/**Author: Loso
 * Functions:
 * 1. Display main menus for getting user input
 * 2. OzlympicGame creates a Driver object
 *    for implementation of the game controller part.
 * 3. Provide static methods for showing error/validation messages   
 */
public class OzlympicGame {
	//define main menu options
	public static final int SELECT_GAME = 1;
	public static final int PREDICT_WINNER = 2;
	public static final int START_GAME = 3;
	public static final int DISPLAY_FINALRESULT = 4;
	public static final int DISPLAY_ATHLETEPOINTS = 5;
	public static final int EXIT_GAME = 6;
	
	//define game status
	public static final int GAME_DEFAULT = 0;
	public static final int GAME_INITIATED = 1;
	public static final int GAME_EXECUTED = 2;
	public static int gameStatus = GAME_DEFAULT;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner reader = new Scanner(System.in);
		Driver gameDriver = new Driver();
		
		int userInput = 0;
		String sInput = "";
		
		boolean bException = false;
		boolean bValidate = false;
		boolean bEndGame = false;
		
		do{
			//display the main menu
			DisplayMenuAndErrorMsg.displayMenu();
			sInput = reader.next();
			try
			{
				//get user input
				userInput = Integer.parseInt(sInput);
			}
			catch (ArithmeticException e)
			{
				bException = DisplayMenuAndErrorMsg.showOverflowWarning();
			}
			catch(Exception e)
			{
				bException = DisplayMenuAndErrorMsg.showUserChoiceWarning(sInput);
			}
			
			if(bException)
				continue;
			
			bValidate = DisplayMenuAndErrorMsg.inputValidation_Main(userInput);
			if(!bValidate)
				continue;
			
			if((userInput == EXIT_GAME))
				break;
			
			bEndGame = !(gameDriver.processByUserInput(userInput));
		}
		while((userInput != EXIT_GAME) && (!bEndGame));
		
		DisplayMenuAndErrorMsg.gameOver();
	}
}

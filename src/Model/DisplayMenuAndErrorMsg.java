package Model;

/**Author: Loso
 * DisplayMenuAndErrorMsg class's responsibility is 
 * display all the menus which the whole game needs
 */
public class DisplayMenuAndErrorMsg {
		//display menu, error messages & validation checking----------------------------
		public static void displayMenu()
		{
			System.out.print("\nOzlympic Game\n" +
							 "===================================\n" + 
							 "1. Select a game to run\n" +
							 "2. Predict the winner of the game\n" + 
							 "3. Start the game\n" +
							 "4. Display the final results of all games\n" +
							 "5. Display the points of all athletes\n" +
							 "6. Exit\n\n" +
							 "Enter an option:");
		}
		public static void displayGameTypeMenu()
		{
			System.out.print("\nGame Type Option:\n" +
							 "===================================\n" + 
							 "1. Swimming\n" +
							 "2. Cycling\n" + 
							 "3. Running\n" +
							 "4. Back to main menu\n\n" +
							 "Enter an option:");
		}
		/*REDUNDANT
		 * 
		 * public static boolean showOverflowWarning()
		{
			System.out.println("Input overflow, please enter valid range\n");
			return true;
		}
		public static boolean showUserChoiceWarning(String input)
		{		
			System.out.println("Input Invalid, please make sure you only enter a number\n");
			return true;
		}
		public static boolean inputValidation_Main(int input)
		{
			if(input < 0 || input > 6)
			{
				System.out.println("Please enter valid range.\n");
				return false;
			}
			return true;
		}
		// check valid input under the 1.select game type
		public static boolean inputValidation_Sub(int input)
		{
			if(input < 0 || input > 4)
			{
				System.out.println("Please enter valid range.\n");
				return false;
			}
			if(input == 4)
				return false;
			return true;
		}
		// check valid input under the 2.predict the winner
		public static boolean inputValidation_Sub(int input, int listSize)
		{
			if(input < 0 || input > listSize)
			{
				System.out.println("Please enter valid range.\n");
				return false;
			}
			return true;
		}*/
		// display error message when game object is uninitialized
		public static boolean errorMsg_GameUninitialized()
		{
			System.out.println("Please select game type first.");
			return true;
		}
		// display error message when game object is unexecuted
			/*	
			 * MADE INTO GameUnexecutedException
			 * public static boolean errorMsg_GameUnexecuted()
				{
					System.out.println("Please start game to get result.");
					return true;
				}*/
		public static void errorMsg_InvalidCandidateList()
		{
			System.out.println("Candidate number is invalid, game is cancelled.\n" +
							   "Please select game type again.");
		}
		public static void gameOver()
		{
			System.out.println("Game Over!!");
		}
		//------------------------------------------------------------------------------
}

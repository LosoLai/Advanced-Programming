package Controller;

import Model.*;
import View.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**Author: Loso
 * The role of the Driver is to control the games
 * which means it controls all the logic to execute each game round
 * Functions: processByUserInput()
 * - implement the specific methods by user input
 * 1. initialParticipantList()
 * 2. createGameByInput()
 * 3. displayCandidateList()
 * 4. displayAllGameResult()
 * 5. displayAllAthletePoints()
 * 6. findFile() <Add by Arion 01/04/17>
 */

public class Driver {
	
	Data data = new Data();
	public static Game currentGame;
	public static Driver gameDriver = new Driver();
	private ArrayList<Game> gameList = new ArrayList<Game>();
	//Modified by Loso 10/05/17----------------------------------
	public static ArrayList<Official> officialList;
	public static ArrayList<Athlete> athleteList;
	//private ArrayList<Participant> swimmerList;
	//private ArrayList<Participant> cyclistList;
	//private ArrayList<Participant> sprinterList;
	//private ArrayList<Participant> superAthList;
	//private ArrayList<Participant> officialList;
	//-----------------------------------------------------------
	
	public static Driver getInstance()
	{
		return gameDriver;
	}
	public static HashMap<String, Participant> getParticipantList()
	{
		return Data.participant;
	}
	public ArrayList<Game> getGameList()
	{
		return gameList;
	}
	
	public Driver()
	{
		//Modified by Loso 10/05/17--------------------------------------------
		//Comment the connect DB feature for testing the file reading part
		//To retrieve participant lists 
		/*boolean bProcessResult1 = data.ozlympicDB();
		
		//failure in hsql database
		if(!bProcessResult1) {
			System.out.print("Failed to connect to database. Proceeding to read text file.\n\n");
			
			//Reading data from file
			boolean bProcessResult2 = data.initialParticipantList();
			if (!bProcessResult2)
				System.out.println("Failed to read participant list from text file.\n\n");
			else 
				System.out.println("Participant list read successfully from text file!!!\n\n");
		}
		else 
			System.out.print("Participant list read successfully from database!!\n\n");*/
		
		//Reading data from file
		boolean bProcessResult2 = data.initialParticipantList();
		if (!bProcessResult2)
			System.out.println("Failed to read participant list from text file.\n\n");
		else
			System.out.println("Participant list read successfully from text file!!!\n\n");
		
		
		officialList = data.getOfficialList();
		athleteList = data.getAthletelList();
		//---------------------------------------------------------------------
	}
	
	//Modified by Loso---------------------------------------------------------
	//SELECT_GAME: deal with user click a game type for creating a game
	public boolean selectGameTypeForCreateAGame(String gameType)
	{
		//check game status
		if(OzlympicGameView.gameStatus == OzlympicGameView.GAME_DEFAULT ||
		   currentGame == null)
			return DisplayMenuAndErrorMsg.errorMsg_GameUninitialized();	
		

		//check game status to prevent initialization without executing
		if(OzlympicGameView.gameStatus == OzlympicGameView.GAME_INITIATED)
			return DisplayMenuAndErrorMsg.errorMsg_GameUnexecuted();
						
		//create a Game object by game type
		boolean bCreated = createGameByInput(gameType);
						
		OzlympicGameView.gameStatus = OzlympicGameView.GAME_INITIATED;

		return bCreated;
	}
	private boolean createGameByInput(String gameType)
	{
		currentGame = new Game(gameType);
		gameList.add(currentGame);
		
		//test - display gameID 
		System.out.println("\n" + "Game selected: " + currentGame.getGameID());
		return true;
	}
	//-------------------------------------------------------------------------
	/*public boolean processByUserInput(int userInput, String gameType)
	{
		if(userInput != OzlympicGameView.SELECT_GAME)
		{
			//check game status
			if(OzlympicGameView.gameStatus == OzlympicGameView.GAME_DEFAULT ||
					   currentGame == null)
				return DisplayMenuAndErrorMsg.errorMsg_GameUninitialized();	
		}
		
		//implement game control
		switch(userInput)
		{
			case OzlympicGameView.SELECT_GAME:
			{
				//check game status to prevent initialization without executing
				if(OzlympicGameView.gameStatus == OzlympicGameView.GAME_INITIATED)
					return DisplayMenuAndErrorMsg.errorMsg_GameUnexecuted();
				
				// create a Game object by userInput
				createGameByInput(gameType);
				
				OzlympicGameView.gameStatus = OzlympicGameView.GAME_INITIATED;
			}
			break;
			case OzlympicGameView.SELECT_PARTICIPANT:
			{
				boolean bFuncWork = displayCandidateList();
				if(!bFuncWork)
					return true; //back to main menu
			}
			break;
			case OzlympicGameView.START_GAME:
			{				
				//re-run game checking
				if(OzlympicGameView.gameStatus == OzlympicGameView.GAME_EXECUTED) {
					boolean bFuncWork = displayCandidateList();
					if(!bFuncWork)
						return true; //back to main menu
				currentGame.executeGame();
				}
				if(OzlympicGameView.gameStatus == OzlympicGameView.GAME_INITIATED) {
					boolean bExecuted = currentGame.executeGame();
					if(bExecuted)
						OzlympicGameView.gameStatus = OzlympicGameView.GAME_EXECUTED;
					}
			}
			break;
			case OzlympicGameView.DISPLAY_FINALRESULT:
			{
				//display game result
				displayAllGameResults();
			}
				break;
			case OzlympicGameView.DISPLAY_ATHLETEPOINTS:
			{
				displayAllAthletePoints();
			}
			break;
			default:
				return false;
		}
		return true;
	}
	
	
	private boolean createGameByInput(String gameType)
	{
		currentGame = new Game(gameType);
		gameList.add(currentGame);
		
		//test - display gameID 
		System.out.println("\n" + "Game selected: " + currentGame.getGameID());
		return true;
	}
	private boolean displayCandidateList()
	{
		ArrayList<Athlete> candList = currentGame.getCandidate();
		if(candList != null)
		{
			System.out.print("\nPlease choose the athlete you think is going to win the game.\n\n" +
		                     "Candidate List\n" + "===================================\n");
			String candListMenu = "";
			for(int i=0 ; i<candList.size() ; i++)
			{
				Athlete candInfo = candList.get(i);
				if(candInfo != null)//+ candInfo.getName() + "\n"; showing name probably is better
					candListMenu += Integer.toString(i+1) + ". " 
									+ candInfo.getName() + "\n"; 
			}
			System.out.print(candListMenu + "\nEnter an option:" );
			
			Scanner reader = new Scanner(System.in);
			String sInput = reader.next();
			int predict = 0;
			try
			{
				predict = Integer.parseInt(sInput);
			}
			catch (ArithmeticException e)
			{
				DisplayMenuAndErrorMsg.showOverflowWarning();
			}
			catch(Exception e)
			{
				DisplayMenuAndErrorMsg.showUserChoiceWarning(sInput);
			}
			
			boolean bValidate = DisplayMenuAndErrorMsg.inputValidation_Sub(predict, candList.size());
			if(!bValidate)
				return false;
		}
		return true;
	}
	private void displayAllGameResults()
	{
		String result = "";
		for(int i=0 ; i<gameList.size() ; i++)
		{
			Game gameInfo = gameList.get(i);
			if(gameInfo != null)
				result += gameInfo.getGameResult();
		}
		System.out.println(result);
	}
	private void displayAllAthletePoints()
	{
		//need to remove all superAthletes from each list first
		swimmerList.removeAll(superAthList);
		String swimmersResult = "===== Swimmers result =====\n";
		for(int i=0 ; i<swimmerList.size() ; i++)
		{
			Participant swimmer = swimmerList.get(i);
			if(swimmer != null && swimmer instanceof Swimmer)
			{
				int point = ((Swimmer)swimmer).getPoints();
				swimmersResult += (swimmer.getName() + 
								   " -> " + Integer.toString(point) + "\n");
			}
		}
		System.out.println(swimmersResult);
		
		String cyclistsResult = "===== Cyclists result =====\n";
		cyclistList.removeAll(superAthList);
		for(int i=0 ; i<cyclistList.size() ; i++)
		{
			Participant cyclist = cyclistList.get(i);
			if(cyclist != null && cyclist instanceof Cyclist)
			{
				int point = ((Cyclist)cyclist).getPoints();
				cyclistsResult += (cyclist.getName() + 
								   " -> " + Integer.toString(point) + "\n");
			}
		}
		System.out.println(cyclistsResult);
		
		String sprintersResult = "===== Sprinters result =====\n";
		sprinterList.removeAll(superAthList);
		for(int i=0 ; i<sprinterList.size() ; i++)
		{
			Participant sprinter = sprinterList.get(i);
			if(sprinter != null && sprinter instanceof Sprinter)
			{
				int point = ((Sprinter)sprinter).getPoints();
				sprintersResult += (sprinter.getName() + 
									" -> " + Integer.toString(point) + "\n");
			}
		}
		System.out.println(sprintersResult);
		
		String superAthResult = "===== SuperAthlete result =====\n";
		for(int i=0 ; i<superAthList.size() ; i++)
		{
			Participant superAth = superAthList.get(i);
			if(superAth != null && superAth instanceof SuperAthlete)
			{
				int point = ((SuperAthlete)superAth).getPoints();
				superAthResult += (superAth.getName() + 
								   " -> " + Integer.toString(point) + "\n");
			}
		}
		System.out.println(superAthResult);
	}*/
}

package Controller;

import Assignment02.GameUnexecutedException;
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
	//define game status
	public static final int GAME_DEFAULT 	= 0;
	public static final int GAME_INITIATED 	= 1;
	public static final int GAME_EXECUTED 	= 2;
	public static int gameStatus 			= GAME_DEFAULT;
	
	Data data = new Data();
	public static Game currentGame;
	public static Driver gameDriver = new Driver();
	private ArrayList<Game> gameList = new ArrayList<Game>();
	//Modified by Loso 10/05/17----------------------------------
	public static ArrayList<Official> officialList;
	public static ArrayList<Athlete> athleteList;
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
		boolean bProcessResult1 = Data.ozlympicDB();
		
		//failure in hsql database
		if(!bProcessResult1) {
			System.out.print("Failed to connect to database. Proceeding to read text file.\n\n");
			
			//Reading data from file
			boolean bProcessResult2 = data.initialParticipantList();
			if (!bProcessResult2)
				System.out.println("Failed to read participant list from text file.\n\n");
			else 
				System.out.println("Participant list read successfully from text file!!!\n\n");
			officialList = data.getOfficialList();
			athleteList = data.getAthletelList();
		}
		else 
			System.out.print("Participant list read successfully from database!!\n\n");
		officialList = data.getOfficialList();
		athleteList = data.getAthletelList();
		
	}
	
	//Modified by Loso---------------------------------------------------------
	//SELECT_GAME: deal with user click a game type for creating a game
	public boolean selectGameTypeForCreateAGame(String gameType) throws GameUnexecutedException
	{	
		//check game status to prevent initialization without executing
		if(gameStatus == GAME_INITIATED)
			throw new GameUnexecutedException("Please start game to get result.");
						
		//create a Game object by game type
		boolean bCreated = createGameByInput(gameType);
						
		gameStatus = GAME_INITIATED;

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
	
	//SET CANDIDATE & REFEREE
	public boolean setRefereeAndCandidate(String refereeID, ArrayList<String> athleteIDList) throws GameUnexecutedException
	{
		if(currentGame == null)
			return false;
		
		Participant referee = Data.participant.get(refereeID);
		if(referee == null)
			return false;
		currentGame.setReferee(referee);
		for(int i=0 ; i<athleteIDList.size() ; i++)
		{
			String id = athleteIDList.get(i);
			Athlete cadidate = (Athlete)Data.participant.get(id);
			if(cadidate == null)
				continue;
			currentGame.addCandidate(cadidate);
		}
		int cadidateNum = currentGame.getCandidate().size();
		if(cadidateNum < Game.CANDIDATELIMIT_MIN || cadidateNum > Game.CANDIDATELIMIT_MAX)
			return false;
		
		//execute the game
		boolean bExecute = executeCurrentGame();
		return bExecute;
	}
	private boolean executeCurrentGame() throws GameUnexecutedException
	{
		boolean bExecuted = false;
		//re-run game checking
		if(gameStatus == GAME_EXECUTED) {
			throw new GameUnexecutedException("Re-run game exception");
		}
		if(gameStatus == GAME_INITIATED) {
			bExecuted = currentGame.executeGame();
			if(bExecuted)
				gameStatus = GAME_EXECUTED;
		}
		return bExecuted;
	}
	//-------------------------------------------------------------------------
}

package Controller;
import Assignment01.Cyclist;
import Assignment01.Official;
import Assignment01.Participant;
import Assignment01.Sprinter;
import Assignment01.SuperAthlete;
import Assignment01.Swimmer;
import Model.*;
import View.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

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
	
	public static HashMap<String, ArrayList<Participant>> participantList = new HashMap<String, ArrayList<Participant>>();
	
	public static Game currentGame;
	public static Driver gameDriver = new Driver();
	private ArrayList<Game> gameList = new ArrayList<Game>();	
	private ArrayList<String[]> fileList = new ArrayList<String[]>();
	private ArrayList<Participant> swimmerList = new ArrayList<Participant>();
	private ArrayList<Participant> cyclistList = new ArrayList<Participant>();
	private ArrayList<Participant> sprinterList = new ArrayList<Participant>();
	private ArrayList<Participant> superAthList = new ArrayList<Participant>();
	private ArrayList<Participant> officialList = new ArrayList<Participant>();
	
	
	public static Driver getInstance()
	{
		return gameDriver;
	}
	public Driver()
	{
		boolean bProcessResult = initialParticipantList();
		
		//data initialization fail
		if(!bProcessResult)    
			System.out.print("Failed to read file!!\n\n");
		else 
			System.out.print("Participant list read successfully!!\n\n");
	}
	
	public boolean processByUserInput(int userInput, String gameType)
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
			/*case OzlympicGameView.SELECT_PARTICIPANT:
			{
				boolean bFuncWork = displayCandidateList();
				if(!bFuncWork)
					return true; //back to main menu
			}
			break;*/
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
	
	private boolean initialParticipantList()
	{
		//fileNotFoundRecovery();
		
		//Modified by Arion--------------------------
				Participant temp;	
				final int ID_INDEX = 0, TYPE_INDEX = 1, NAME_INDEX = 2, AGE_INDEX = 3, STATE_INDEX = 4;
				//Modified by Loso
				String rootPath = this.getClass().getResource("Participants.csv").getFile();
				//File fileToBeFound = findFile(rootPath, "Participants.csv");
				File fileToBeFound = new File(rootPath);
				if(fileToBeFound == null)
					return fileNotFoundRecovery();
				//-------------------------------------------
			    BufferedReader br = null;
			    String line = "";
			    String cvsSplitBy = ",";
			    
				try {

		            br = new BufferedReader(new FileReader(fileToBeFound.getAbsolutePath()));
		            while ((line = br.readLine()) != null) {

		                // use comma as separator
		                fileList.add(line.split(cvsSplitBy));
		            }
		            
		            Set<String> unique_id = new HashSet<String>();
		            
		            for (int i=0; i<fileList.size(); i++){
		            	if (fileList.get(i).length != 5) {
		            		fileList.remove(i--);
		            	}
		            	else if (!unique_id.add(fileList.get(i)[ID_INDEX])) {
		            		fileList.remove(i--);
		            	}
		            	else if (fileList.get(i)[ID_INDEX].isEmpty() || fileList.get(i)[TYPE_INDEX].isEmpty() || fileList.get(i)[NAME_INDEX].isEmpty() || fileList.get(i)[AGE_INDEX].isEmpty() || fileList.get(i)[STATE_INDEX].isEmpty()) {
		            		fileList.remove(i--);
		            	}
		            }
		            
		            for (int i=0; i<fileList.size(); i++) {
		       
		            	if (fileList.get(i)[TYPE_INDEX].equalsIgnoreCase("swimmer")) {
		            		temp = new Swimmer(fileList.get(i)[ID_INDEX], fileList.get(i)[NAME_INDEX],Integer.parseInt(fileList.get(i)[AGE_INDEX]),fileList.get(i)[STATE_INDEX]);
		           			swimmerList.add(temp);
		           		}
		           		else if (fileList.get(i)[TYPE_INDEX].equalsIgnoreCase("sprinter")) {
		           			temp = new Sprinter(fileList.get(i)[ID_INDEX], fileList.get(i)[NAME_INDEX],Integer.parseInt(fileList.get(i)[AGE_INDEX]),fileList.get(i)[STATE_INDEX]);
		           			sprinterList.add(temp);
		           		}
		           		else if (fileList.get(i)[TYPE_INDEX].equalsIgnoreCase("cyclist")) {
		           			temp = new Cyclist(fileList.get(i)[ID_INDEX], fileList.get(i)[NAME_INDEX],Integer.parseInt(fileList.get(i)[AGE_INDEX]),fileList.get(i)[STATE_INDEX]);
		           			cyclistList.add(temp);
		           		}
		           		else if (fileList.get(i)[TYPE_INDEX].equalsIgnoreCase("official")) {
		           			temp = new Official(fileList.get(i)[ID_INDEX], fileList.get(i)[NAME_INDEX],Integer.parseInt(fileList.get(i)[AGE_INDEX]),fileList.get(i)[STATE_INDEX]);       
		           			officialList.add(temp);
		            	}
		            	else if (fileList.get(i)[TYPE_INDEX].equalsIgnoreCase("superathlete")) {
		            		temp = new SuperAthlete(fileList.get(i)[ID_INDEX], fileList.get(i)[NAME_INDEX],Integer.parseInt(fileList.get(i)[AGE_INDEX]),fileList.get(i)[STATE_INDEX]);
		           			superAthList.add(temp);
		           			swimmerList.add(temp);
		           			sprinterList.add(temp);
		           			cyclistList.add(temp);
		           		}
		           	}
		            
		            //-------------------------------------------
		            
		    		participantList.put(Participant.SWIMMER, swimmerList);
		    		participantList.put(Participant.CYCLIST, cyclistList);
		    		participantList.put(Participant.SPRINTER, sprinterList);
		    		participantList.put(Participant.OFFICIAL, officialList);		

		        } catch (FileNotFoundException e) {
		        	fileNotFoundRecovery();
		            e.printStackTrace();
		        } catch (IOException e) {
		            e.printStackTrace();
		        } finally {
		            if (br != null) {
		                try {
		                    br.close();
		                } catch (IOException e) {
		                    e.printStackTrace();
		                }
		            }
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
	}
	//Add by Arion
	private static final File findFile(final String rootFilePath, final String fileToBeFound) {

	    File rootFile = new File(rootFilePath);
	    File[] subFiles = rootFile.listFiles();
	    for (File file : subFiles != null ? subFiles : new File[] {}) {
	        if (file.getAbsolutePath().endsWith(fileToBeFound)) {
	            return file;
	        } else if (file.isDirectory()) {
	            File f = findFile(file.getAbsolutePath(), fileToBeFound);
	            if (f != null) {
	                return f;
	            }
	        }
	    }

	    return null; // null returned in case file is not found

	}
	private boolean fileNotFoundRecovery()
	{
		//setting dummy data here for testing	
		for(int i=0 ; i<6 ; i++)
		{
			//setting athlete
			//name format using (personType + id)
			String name = "ATH-" + Swimmer.SWIMMER 
							     + Integer.toString(i);
			int j = 0;
			int age = 20 + i;
			String id = "Oz000" + Integer.toString(j++);
			Participant swimmer = new Swimmer(id, name, age, "VIC");
			swimmerList.add(swimmer);
		
			id = "Oz000" + Integer.toString(j++);		
			name = "ATH-" + Cyclist.CYCLIST
				          + Integer.toString(i);
			Participant cyclist = new Cyclist(id, name, age, "VIC");
			cyclistList.add(cyclist);
			
			id = "Oz000" + Integer.toString(j++);		
			name = "ATH-" + Sprinter.SPRINTER
			              + Integer.toString(i);
			Participant sprinter = new Sprinter(id, name, age, "VIC");
			sprinterList.add(sprinter);
			
			id = "Oz000" + Integer.toString(j++);
			name = "ATH-" + SuperAthlete.SUPERATHLETE 
		                  + Integer.toString(i);
			Participant superAthlete = new SuperAthlete(id, name, age, "VIC");
			superAthList.add(superAthlete);
					
			//for implement candidate list
			//adding superAth into each list
			swimmerList.add(superAthlete);
			cyclistList.add(superAthlete);
			sprinterList.add(superAthlete);
					
			//setting offical
			id = "Oz000" + Integer.toString(j++);
			name = "OFF-" + Integer.toString(i);
			Participant offical = new Official(id, name, age, "VIC");
			officialList.add(offical);
		}
				
		participantList.put(Participant.SWIMMER, swimmerList);
		participantList.put(Participant.CYCLIST, cyclistList);
		participantList.put(Participant.SPRINTER, sprinterList);
		participantList.put(Participant.OFFICIAL, officialList);
		return true;
	}
}

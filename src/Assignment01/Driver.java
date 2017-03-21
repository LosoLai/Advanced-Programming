package Assignment01;

import java.util.*;

/* 
 * OzlympicGame create a Driver object
 * for implement the game controller.
 * Addd by LosoLai 19/03/17
 */

public class Driver {
	private ArrayList<Game> gameList = new ArrayList<Game>();
	private ArrayList<Participant> participantList;
	
	public boolean processByUserInput(int userInput)
	{
		boolean bProcessResult = false;
		
		bProcessResult = initialParticipantList();
		if(!bProcessResult)    //initial data fail 
			return false;
		
		switch(userInput)
		{
			case OzlympicGame.SELECT_GAME:
			{
				// create a Game obj randomly
				Random rand = new Random();
				Game currentGame;
				int randomNum = rand.nextInt(Game.GAMETYPE_NUMBER);
				if(randomNum == Game.GAME_RUNNING)
					currentGame = new Running();
				else if(randomNum == Game.GAME_SWIMMING)
					currentGame = new Swimming();
				else
					currentGame = new Cycling();
				gameList.add(currentGame);
				
				//test - display gameID 
				//System.out.println(currentGame.getGameID());
			}
				break;
			case OzlympicGame.PREDIT_WINNER:
				break;
			case OzlympicGame.START_GAME:
				break;
			case OzlympicGame.DISPLAY_FINALRESULT:
				break;
			case OzlympicGame.DISPLAY_ATHLETEPOINTS:
				break;
			case OzlympicGame.EXIT_GAME:
			default:
				return false;
		}
		return true;
	}
	
	private boolean initialParticipantList()
	{
		//setting dummy data here for testing ()
		//need to use read file to set info.
		
		for(int i=0 ; i<6 ; i++)
		{
			//setting athlete
			String name = "ATH-" + Integer.toString(Athlete.SWIMMER) 
					             + Integer.toString(i);
			int age = 20 + i;
			Participant swimmer = new Athlete(name, age, "VIC", Athlete.SWIMMER);
			participantList.add(swimmer);
			
			name = "ATH-" + Integer.toString(Athlete.CYCLIST) 
		                  + Integer.toString(i);
			Participant cyclist = new Athlete(name, age, "VIC", Athlete.CYCLIST);
			participantList.add(cyclist);
			
			name = "ATH-" + Integer.toString(Athlete.SPRINTER) 
	                      + Integer.toString(i);
			Participant sprinter = new Athlete(name, age, "VIC", Athlete.SPRINTER);
			participantList.add(sprinter);
			
			name = "ATH-" + Integer.toString(Athlete.SUPERATHLETE) 
                          + Integer.toString(i);
			Participant superAthlete = new Athlete(name, age, "VIC", Athlete.SUPERATHLETE);
			participantList.add(superAthlete);
			
			//setting offical
			name = "OFF-" + Integer.toString(i);
			Participant offical = new Offical(name, age, "VIC");
			participantList.add(offical);
		}
		
		return true;
	}
}

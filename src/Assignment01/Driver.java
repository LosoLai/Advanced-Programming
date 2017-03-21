package Assignment01;

import java.util.*;

/* 
 * OzlympicGame create a Driver object
 * for implement the game controller.
 * Addd by LosoLai 19/03/17
 */

public class Driver {
	private ArrayList<Game> gameList = new ArrayList<Game>();
	//private ArrayList<Participant> participantList;
	
	public boolean processByUserInput(int userInput)
	{
		boolean bProcessResult = false;
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
}

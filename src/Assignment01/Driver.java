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

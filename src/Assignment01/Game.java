package Assignment01;

import java.util.ArrayList;

public abstract class Game {
	public static final int GAME_SWIMMING   = 0;
	public static final int GAME_CYCLING    = 1;
	public static final int GAME_RUNNING    = 2;
	public static final int GAMETYPE_NUMBER = 3; //total 3 types of game
	public static int gameRoundNum = 0;
	
	private String gameType;
	private String gameID;
	
	private Offical            referee;
	private ArrayList<Athlete> candidate;
	
	public Game()
	{
		this.gameType = "";
		this.gameRoundNum = 0;
	}
	
	public Game(String gameType)
	{
		this.gameRoundNum++;
		setGameID(gameType);
		
		//setting offical and canidate info.
	}
	
	public void setGameID(String gameType)
	{
		this.gameType = gameType;
		this.gameID = gameType + Integer.toString(gameRoundNum);
	}
	
	public String getGameID()
	{
		return this.gameID;
	}
	
	abstract public int generateTime();

}

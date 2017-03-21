package Assignment01;

public abstract class Game {
	public static final int GAME_RUNNING    = 0;
	public static final int GAME_SWIMMING   = 1;
	public static final int GAME_CYCLING    = 2;
	public static final int GAMETYPE_NUMBER = 3; //total 3 types of game
	public static int gameRoundNum = 0;
	
	private String gameType;
	private String gameID;
	
	public Game()
	{
		this.gameType = "";
		this.gameRoundNum = 0;
	}
	
	public Game(String gameType)
	{
		this.gameRoundNum++;
		setGameID(gameType);
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

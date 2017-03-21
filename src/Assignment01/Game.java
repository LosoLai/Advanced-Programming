package Assignment01;

public abstract class Game {
	public static int gameRoundNum = 0;
	private String gameType;
	
	public Game()
	{
		this.gameType = "";
		this.gameRoundNum = 0;
	}
	
	public Game(String gameType)
	{
		this.gameType = gameType;
		this.gameRoundNum++;
	}
	
	abstract public int generateTime();

}

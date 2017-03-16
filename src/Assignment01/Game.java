package Assignment01;

public abstract class Game {
	private String gameType;
	
	public Game()
	{
		this.gameType = "";
	}
	
	public Game(String gameType)
	{
		this.gameType = gameType;
	}
	
	abstract public int generateTime();

}

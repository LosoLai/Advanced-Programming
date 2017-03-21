package Assignment01;

public class Offical extends Participant {
	public static final String OFFICAL = "O";
	private String gameResult;
	
	public Offical(String name, int age, String state)
	{
		super(name, age, state, OFFICAL);
		setGameResult("");
	}

	public String getGameResult() {
		return gameResult;
	}

	public void setGameResult(String gameResult) {
		this.gameResult = gameResult;
	}
}

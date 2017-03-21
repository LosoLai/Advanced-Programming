package Assignment01;

import java.util.Random;

public class Running extends Game {
	private final int TIMELIMITE_MIN        = 10;
	private final int TIMELIMITE_MAX        = 20;
	
	public static final String GAMETYPE_RUN = "R";
	
	public Running()
	{
		super(GAMETYPE_RUN);
	}

	public int generateTime()
	{
		Random rand = new Random();
		int randomNum = rand.nextInt((TIMELIMITE_MAX - TIMELIMITE_MIN) + 1) + TIMELIMITE_MIN;
		return randomNum;
	}
}

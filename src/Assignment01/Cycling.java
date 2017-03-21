package Assignment01;

import java.util.Random;

public class Cycling extends Game {
	private final int TIMELIMITE_MIN          = 500;
	private final int TIMELIMITE_MAX          = 800;
	
	public static final String GAMETYPE_CYCLE = "C";

	public Cycling()
	{
		super(GAMETYPE_CYCLE);
	}

	public int generateTime()
	{
		Random rand = new Random();
		int randomNum = rand.nextInt((TIMELIMITE_MAX - TIMELIMITE_MIN) + 1) + TIMELIMITE_MIN;
		return randomNum;
	}
}

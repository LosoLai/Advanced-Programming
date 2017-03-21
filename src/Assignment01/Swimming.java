package Assignment01;

import java.util.Random;

public class Swimming extends Game {
	private final int TIMELIMITE_MIN         = 100;
	private final int TIMELIMITE_MAX         = 200;
	
	public static final String GAMETYPE_SWIM = "S";
	
	public Swimming()
	{
		super(GAMETYPE_SWIM);
	}

	public int generateTime()
	{
		Random rand = new Random();
		int randomNum = rand.nextInt((TIMELIMITE_MAX - TIMELIMITE_MIN) + 1) + TIMELIMITE_MIN;
		return randomNum;
	}
}

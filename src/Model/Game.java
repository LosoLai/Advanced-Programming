package Model;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;

/**Author: Loso
 * Game is a Abstract class 
 * 1. provide the constructor for initial variables
 *    and getter/setter for accessing variables
 * 2. Having a protected static variable for counting
 *    the game round number  
 * 3. Three abstract methods:
 *  - public int generateTime() 
 *  subclasses implement its own time limitation
 * @return int - random a number between its own limitation
 *  - public boolean pickCandidate(int candidateLimit)
 * @param int candidateLimit
 * @return boolean : to verify the behavior is complete or not
 *  subclasses implement its own candidate list
 *  - public boolean executeGame()   <--------should be extract common section
 *  subclasses implement its own game
 * @return boolean : to verify the behavior is complete or not
 */
public class Game {
	public static final String GAME_SWIMMING   = "Swimming";
	public static final String GAME_CYCLING    = "Cycling";
	public static final String GAME_RUNNING    = "Running";
	public static final int GAMETYPE_NUMBER = 3; //total 3 types of games
	public static final int REFEREELIMIT = 1;
	public static final int CANDIDATELIMIT_MIN = 4;
	public static final int CANDIDATELIMIT_MAX = 8;
	public final int S_TIMELIMIT_MIN   = 100;
	public final int S_TIMELIMIT_MAX   = 200;
	public final int C_TIMELIMIT_MIN   = 500;
	public final int C_TIMELIMIT_MAX   = 800;
	public final int R_TIMELIMIT_MIN   = 10;
	public final int R_TIMELIMIT_MAX   = 20;
	
	//protected static variable for counting the game round number
	private static int gameRoundNum = 0;
	
	private String gameType;
	private String gameID;
	
	private Participant referee;
	private String gameResult;
	private ArrayList<Athlete> candidate;
	
	//Constructor for initial values
	public Game(String gameType)
	{
		this.gameRoundNum++;
		setGameID(gameType);
	}
	
	public void setGameID(String gameType)
	{
		this.gameType = gameType;
		this.gameID = gameType.substring(0, 1) + String.format("%02d", gameRoundNum);
	}
	public String getGameID()
	{
		return this.gameID;
	}
	public ArrayList<Athlete> getCandidate() {
		return this.candidate;
	}
	public void addCandidate(Athlete candidate) {
		if(this.candidate == null)
			this.candidate = new ArrayList<Athlete>();
		this.candidate.add(candidate);
	}
	public Participant getReferee() {
		return referee;
	}
	public void setReferee(Participant referee) {
		this.referee = referee;
	}
	public String getGameResult() {
		return gameResult;
	}
	public void setGameResult(String gameResult) {
		this.gameResult = gameResult;
	}
	
	//Runs the game and returns results
	public boolean executeGame()
	{
		if(getCandidate() == null || getReferee() == null)
			return false;
		
		ArrayList<Athlete> athList = getCandidate();
		
		//execute athlete compete()
		for(int i=0 ; i<athList.size() ; i++)
		{
			Athlete candidate = athList.get(i);
			if(candidate == null)
				continue;
			
			//use setExecuteTime to store data
			double competeSec = candidate.Compete();
			candidate.setExecuteTime(competeSec);
		}
		
		Collections.sort(athList);
		
		//setting info into referee
		Official referee;
		if(getReferee() instanceof Official)
		{
			referee = ((Official)getReferee());
			
			try {
			setGameResult(referee.setResultTopList(this.getGameID(), athList));
			} catch (SQLException e2) {
				e2.printStackTrace();
			} catch (ClassNotFoundException e2) {
				e2.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			//display game result
			System.out.println("\n" + getGameResult() + "\n\n");
			
			//reset referee's game result
			referee.resetGameResult();
		}
		return true;
	}
	
	//Generates and returns random time according to game limits
	public double generateTime()
	{
		double randomNum;
		int min = 0;
		int max = 0;
		
		if(gameType == GAME_SWIMMING)
		{
			min = S_TIMELIMIT_MIN;
			max = S_TIMELIMIT_MAX;
		}
		if(gameType == GAME_CYCLING)
		{
			min = C_TIMELIMIT_MIN;
			max = C_TIMELIMIT_MAX;
		}
		if(gameType == GAME_RUNNING)
		{
			min = R_TIMELIMIT_MIN;
			max = R_TIMELIMIT_MAX;
		}
		
		//Returns random time with 3 decimal places
		randomNum = ThreadLocalRandom.current().nextDouble(min, max);
		return randomNum;
	}
}

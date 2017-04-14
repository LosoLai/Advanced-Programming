package Assignment01;
import java.util.ArrayList;
import java.util.Collections;

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
public abstract class Game {
	public static final int GAME_SWIMMING   = 1;
	public static final int GAME_CYCLING    = 2;
	public static final int GAME_RUNNING    = 3;
	public static final int GAMETYPE_NUMBER = 3; //total 3 types of games
	public static final int CANDIDATELIMIT_MIN = 4;
	public static final int CANDIDATELIMIT_MAX = 8;
	
	//protected static variable for counting the game round number
	private static int gameRoundNum = 0;
	
	private String gameType;
	private String gameID;
	
	private Participant referee;
	private String gameResult;
	private ArrayList<Athlete> candidate;
	private Athlete predictWinner;
	
	//Constructor for initial values
	public Game(String gameType)
	{
		this.gameRoundNum++;
		setGameID(gameType);
	}
	
	public void setGameID(String gameType)
	{
		this.gameType = gameType;
		this.gameID = gameType + String.format("%02d", gameRoundNum);
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
	public Athlete getPredictWinner() {
		return predictWinner;
	}
	public void setPredictWinner(Athlete predictWinner) {
		this.predictWinner = predictWinner;
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
		
		// showing result here <need to store in referee>
		System.out.println("\n" + this.getGameID() + " result:\n" +
						   "-------------------------------");
		
		for(int i=0; i<athList.size(); i++) {
			System.out.println(i+1 + ". " + athList.get(i).getName() + " -> " 
		                       + athList.get(i).getExecuteTime());
		}
		
		//setting info into referee
		Official referee;
		if(getReferee() instanceof Official)
		{
			referee = ((Official)getReferee());
			setGameResult(referee.setResultTopList(this.getGameID(), athList));
			
			//display game result
			System.out.println("\n" + getGameResult() + "\n\n");
			
			//reset referee's game result
			referee.resetGameResult();
		}
		
		// Display winner & prediction info
		if(getPredictWinner() != null) {
			System.out.println("The Winner: " + athList.get(0).toString() + "\n\n" +
							   "Your Prediction: " + getPredictWinner().toString() +
							   "\n-------------------------------\n");
			}
			else {
				System.out.println("The Winner: " + athList.get(0).toString() + "\n\n" + 
			                       "Ooops! You forgot to make a prediction!" +
			                       "\n-------------------------------\n");
				
			}
		//checking prediction winner
		if(getPredictWinner() == athList.get(0))
			System.out.println("Congratulation: The athlete you predicted won the game!!");
		return true;
	}
	
	//Generates and returns random time according to game limits
	public abstract double generateTime();
	
	/*Picks candidates for game
	 * Candidate limit as parameter
	 * Returns true if action is complete
	 */
	public abstract boolean pickCandidate(int candidateLimit);
}

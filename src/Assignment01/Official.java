package Assignment01;
import java.util.ArrayList;

/**Author: Arion
 * Official inheritance from Participant class 
 */
public class Official extends Participant {
	public static final int RESULT_TOP3 = 3;
	private final int INDEX_1ST = 0;
	private final int INDEX_2ND = 1;
	private final int INDEX_3RD = 2;
	private final int POINT_1ST = 5;
	private final int POINT_2ND = 2;
	private final int POINT_3RD = 1;
	
	//Extra variables for recording ranking list and game result
	private Athlete[] resultTop3;
	private String gameResult;
	
	public Official(String id, String name, int age, String state)
	{
		super(id, OFFICIAL, name, age, state);
		this.resultTop3 = new Athlete[RESULT_TOP3];
		this.gameResult = "";
	}

	public String getGameResult() {
		return gameResult;
	}
	public void resetGameResult()
	{
		this.gameResult = "";
	}

	public Athlete[] getResultTopList() {
		return resultTop3;
	}
	
	//Generates game result and stores points in Athlete objects
	public String setResultTopList(String gameID, ArrayList<Athlete> sortedList) 
	{
		//display gameID first
		this.gameResult = "\n========= " + gameID + " Top 3 ==========\n";
		if(sortedList != null)
		{
			int index = 0;
			while(index < RESULT_TOP3)
			{
				Athlete record = sortedList.get(index);
				if(index == INDEX_1ST)
					record.setPoints(POINT_1ST);
				if(index == INDEX_2ND)
					record.setPoints(POINT_2ND);
				if(index == INDEX_3RD)
					record.setPoints(POINT_3RD);
					
				resultTop3[index] = record;
				this.gameResult += record.toString();
				index++;
			}
			this.gameResult += "\n\nReferee: " + this.getName() + "\n";
		}
		return this.gameResult;
	}
}

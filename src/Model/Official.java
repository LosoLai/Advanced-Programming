package Model;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


/**Author: Arion
 * Official inheritance from Participant class 
 */
public class Official extends Participant {
	public static final int RESULT_TOP3 = 3;
	private final int INDEX_1ST = 0;
	private final int INDEX_2ND = 1;
	private final int INDEX_3RD = 2;
	public static final int POINT_1ST = 5;
	public static final int POINT_2ND = 2;
	public static final int POINT_3RD = 1;
	public static final int NO_POINTS = 0;
	
	//Extra variables for recording ranking list and game result
	private Athlete[] resultTop3;
	private String gameResult;
	
	public Official(String id, String name, int age, String state)
	{
		//assume that the offical can not be athlete 
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
	
	@Override
    public String toString() 
	{
		return String.format("\n" + super.getName() +
				 ", " + this.getGameResult());
    }
	
	//Generates game result and stores points in Athlete objects
	public String setResultTopList(String gameID, ArrayList<Athlete> sortedList) 
	{
		//Timestamp for gameResults file
		String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SS").format(new Date());
		
		//Modified by Loso 13/05/17----------------------------------------
		//remove write file and DB 
		//reason: should do write file / DB when user close the application
		//because write file / DB is time expensive, shouldn't execute each time after setResult
		//display gameID first
		this.gameResult +=  gameID + ", " + this.getPersonID() + ", " + timeStamp;
		
		if(sortedList != null)
		{
			int index = 0;
			while(index < sortedList.size())
			{
				Athlete record = sortedList.get(index);
				if(index == INDEX_1ST){
					record.setPoints(POINT_1ST);
					this.gameResult += record.toString() + POINT_1ST;
					resultTop3[index] = record;
				}
				else if(index == INDEX_2ND) {
					record.setPoints(POINT_2ND);
					this.gameResult += record.toString() + POINT_2ND;
					resultTop3[index] = record;
				}
				else if(index == INDEX_3RD) {
					record.setPoints(POINT_3RD);
					this.gameResult += record.toString() + POINT_3RD;
					resultTop3[index] = record;
				}
				else {
					this.gameResult += record.toString() + NO_POINTS;
				}
					
				index++;
			}
		}
		return this.gameResult;
		//-----------------------------------------------------------------
	}
}

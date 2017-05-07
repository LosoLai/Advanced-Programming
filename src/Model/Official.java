package Model;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import Controller.Data;



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
	private final int NO_POINTS = 0;
	
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
	public String setResultTopList(String gameID, ArrayList<Athlete> sortedList) throws Exception
	{
		//Timestamp for gameResults file
		String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SS").format(new Date());
		Data data = new Data();
		
		//display gameID first
		this.gameResult = "\n========= " + gameID + " Results ==========\n";
		
		//Write to file
		data.writeToFile(timeStamp);
		data.writeToFile(gameResult);
		
		if(sortedList != null)
		{
			int index = 0;
			while(index < sortedList.size())
			{
				Athlete record = sortedList.get(index);
				if(index == INDEX_1ST){
					record.setPoints(POINT_1ST);
					resultTop3[index] = record;
					data.connection.prepareStatement("insert into results values ('" + gameID +"', '" + this.getPersonID() + "', '" + sortedList.get(index).getPersonID() + "', '" + sortedList.get(index).getExecuteTime() + "', '" + POINT_1ST + "');");
					data.writeToFile(record.toString() + ", " + POINT_1ST + "\n");
					this.gameResult += record.toString() + ", " + POINT_1ST + "\n";
				}
				else if(index == INDEX_2ND){
					record.setPoints(POINT_2ND);
					resultTop3[index] = record;
					data.connection.prepareStatement("insert into results values ('" + gameID +"', '" + this.getPersonID() + "', '" + sortedList.get(index).getPersonID() + "', '" + sortedList.get(index).getExecuteTime() + "', '" + POINT_2ND + "');");
					data.writeToFile(record.toString() + ", " + POINT_2ND + "\n");
					this.gameResult += record.toString() + ", " + POINT_2ND + "\n";
				}
				else if(index == INDEX_3RD){
					record.setPoints(POINT_3RD);
					resultTop3[index] = record;
					data.connection.prepareStatement("insert into results values ('" + gameID +"', '" + this.getPersonID() + "', '" + sortedList.get(index).getPersonID() + "', '" + sortedList.get(index).getExecuteTime() + "', '" + POINT_3RD + "');");
					data.writeToFile(record.toString() + ", " + POINT_3RD + "\n");
					this.gameResult += record.toString() + ", " + POINT_3RD + "\n";
				}
				else {	
					data.connection.prepareStatement("insert into results values ('" + gameID +"', '" + this.getPersonID() + "', '" + sortedList.get(index).getPersonID() + "', '" + sortedList.get(index).getExecuteTime() + "', '" + NO_POINTS + "');");
					data.writeToFile(record.toString() + ", " + NO_POINTS + "\n");
					this.gameResult += record.toString() + ", " + NO_POINTS + "\n";
				}
				index++;
			}
			this.gameResult += "\n\nReferee: " + this.getName();
		}
		data.writeToFile("\n");
		return this.gameResult;
	}
}

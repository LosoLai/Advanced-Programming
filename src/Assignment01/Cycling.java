package Assignment01;
import java.util.ArrayList;
import java.util.Collections;


/**Author: Loso
 * inheritance from Game
 * initial the basic information through superclass constructor
 */
public class Cycling extends Game {
	private final int TIMELIMIT_MIN   = 500;
	private final int TIMELIMIT_MAX   = 800;
	private static String GAMETYPE_CYCLE = "C";

	public Cycling()
	{
		super(GAMETYPE_CYCLE);
		
		//setting official and candidate info
		boolean bValidate = pickCandidate(Game.CANDIDATELIMIT_MAX);
		if(!bValidate)
			DisplayMenuAndErrorMsg.errorMsg_InvalidCandidateList();
	}

	@Override
	public double generateTime()
	{
        double randomNum;
		
		//Returns random time with 3 decimal places
		randomNum = Math.round((TIMELIMIT_MIN + Math.random()*(TIMELIMIT_MAX - TIMELIMIT_MIN))*1000);
		return randomNum/1000;
	}
	
	@Override
	public boolean pickCandidate(int candidateLimit)
	{
		ArrayList<Participant> cyclistList = Driver.participantList.get(Participant.CYCLIST);
		ArrayList<Participant> officalList = Driver.participantList.get(Participant.OFFICIAL);
		
		//randomize list data
		Collections.shuffle(cyclistList);
		Collections.shuffle(officalList);
		
		Participant person;
		
		int candidateNum = candidateLimit;
		if(cyclistList.size() < candidateNum)
			candidateNum = cyclistList.size();
		
		//checking candidate limitation
		if(candidateNum < Game.CANDIDATELIMIT_MIN)
			return false;
			
		for(int i=0 ; i<candidateNum ; i++)
		{
			person = cyclistList.get(i);
			if(person == null)
				continue;
			if(person instanceof Cyclist)
				super.addCandidate((Cyclist)person);
			if(person instanceof SuperAthlete)
				super.addCandidate((SuperAthlete)person);
		}
		
		//always set index to 0 as a game referee
		person = officalList.get(0);
		
		if(person instanceof Official && person != null)
			super.setReferee(person);
		return true;
	}
}

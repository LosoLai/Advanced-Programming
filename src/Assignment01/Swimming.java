package Assignment01;
import java.util.ArrayList;
import java.util.Collections;

/**Author: Loso
 * inheritance from Game
 * initial the basic information through superclass constructor
 */
public class Swimming extends Game {
	private final int TIMELIMIT_MIN   = 100;
	private final int TIMELIMIT_MAX   = 200;
	private static String GAMETYPE_SWIM = "S";
	
	public Swimming()
	{
		super(GAMETYPE_SWIM);
		
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
		ArrayList<Participant> swimmerList = Driver.participantList.get(Participant.SWIMMER);
		ArrayList<Participant> officialList = Driver.participantList.get(Participant.OFFICIAL);
		
		//randomize list data
		Collections.shuffle(swimmerList);
		Collections.shuffle(officialList);
				
		Participant person;
		
		int candidateNum = candidateLimit;
		if(swimmerList.size() < candidateNum)
			candidateNum = swimmerList.size();
		
		//checking candidate limitation
		if(candidateNum < Game.CANDIDATELIMIT_MIN)
			return false;
			
		for(int i=0 ; i<candidateNum ; i++)
		{
			person = swimmerList.get(i);
			if(person == null)
				continue;
			if(person instanceof Swimmer)
				super.addCandidate((Swimmer)person);
			if(person instanceof SuperAthlete)
				super.addCandidate((SuperAthlete)person);
		}
		
		//always set index to 0 as a game referee
		person = officialList.get(0);
		if(person instanceof Official && person != null)
			super.setReferee(person);
		return true;
	}
}

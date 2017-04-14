package Assignment01;
import java.util.ArrayList;
import java.util.Collections;


/**Author: Loso
 * inheritance from Game
 * initializing the basic information through superclass constructor
 */
public class Running extends Game {
	private final int TIMELIMIT_MIN   = 10;
	private final int TIMELIMIT_MAX   = 20;
	private static String GAMETYPE_RUN = "R";
	
	public Running()
	{
		super(GAMETYPE_RUN);
		
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
		ArrayList<Participant> runnerList = Driver.participantList.get(Participant.SPRINTER);
		ArrayList<Participant> officalList = Driver.participantList.get(Participant.OFFICIAL);
		
		//randomize list data
		Collections.shuffle(runnerList);
		Collections.shuffle(officalList);
				
		Participant person;
		
		int candidateNum = candidateLimit;
		if(runnerList.size() < candidateNum)
			candidateNum = runnerList.size();
		
		//checking candidate number limitation
		if(candidateNum < Game.CANDIDATELIMIT_MIN)
			return false;
			
		for(int i=0 ; i<candidateNum ; i++)
		{
			person = runnerList.get(i);
			if(person == null)
				continue;
			if(person instanceof Sprinter)
				super.addCandidate((Sprinter)person);
			if(person instanceof SuperAthlete)
				super.addCandidate((SuperAthlete)person);
		}
		
		//always set index to 0 as swimming game referee
		person = officalList.get(0);
		if(person instanceof Official && person != null)
			super.setReferee(person);
		return true;
	}
}

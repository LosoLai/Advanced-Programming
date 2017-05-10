package View;

import java.util.ArrayList;

import Controller.Driver;
import Model.Athlete;
import Model.Official;
import Model.Participant;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class AthletePointsButtonHandler implements EventHandler<ActionEvent> {
	@Override
	public void handle(ActionEvent e) {
		OzlympicGameView.getDisplayContent().getChildren().remove(1);
		ArrayList<Participant> allAthlete = new ArrayList<Participant>();
		allAthlete = Driver.getParticipantList().get(Participant.SWIMMER);
		allAthlete.addAll(Driver.getParticipantList().get(Participant.SWIMMER));
		allAthlete.addAll(Driver.getParticipantList().get(Participant.CYCLIST));
		allAthlete.addAll(Driver.getParticipantList().get(Participant.SPRINTER));
		allAthlete.addAll(Driver.getParticipantList().get(Participant.SUPERATHLETE));
		ArrayList<Athlete> converted = new ArrayList<Athlete>();
		convertToAthleteTypeOfArrayList(allAthlete, converted);
		System.out.println(converted.size());
		OzlympicGameView.gameView.createTableView_AthletePoints(converted);
		//test
		System.out.println("Athlete points clicked");
	}
	
	private void convertToAthleteTypeOfArrayList(ArrayList<Participant> participant, ArrayList<Athlete> converted)
	{
		for(int i=0 ; i<participant.size() ; i++)
		{
			Participant p = participant.get(i);
			if(p instanceof Official)
				continue;
			Athlete a = (Athlete)p;
			converted.add(a);
		}
	}
}

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
		OzlympicGameView.gameView.createTableView_AthletePoints(Driver.athleteList);
		//test
		System.out.println("Athlete points clicked");
	}
}

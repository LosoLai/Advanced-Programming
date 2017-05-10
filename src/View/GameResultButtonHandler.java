package View;

import java.util.ArrayList;

import Controller.Driver;
import Model.Game;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class GameResultButtonHandler implements EventHandler<ActionEvent> {
	@Override
	public void handle(ActionEvent e) {
		OzlympicGameView.getDisplayContent().getChildren().remove(1);
		ArrayList<Game> gameList = Driver.getInstance().getGameList();
		OzlympicGameView.gameView.createTableView_GameResults(gameList);
		//test
		System.out.println("Game result history clicked");
	}
}


package View;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class InstractionsPane extends StackPane{
	public InstractionsPane()
	{
		super();
		VBox vbox = new VBox();
		vbox.setPadding(new Insets(10));
		vbox.setSpacing(8);
		Label title = new Label("Display Area:");		
		String str = "Instructions:\n" +
					 "1. Select a game type from navigation menu.\n" +
				 	 "2. Choose athlete and referee to participate the game.\n" +
				 	 "3. Click the 'Confirm' button to set the candidate.\n" +
				 	 "4. Select Athlete Points to list the scores for each athlete.\n" +
				 	 "5. Select Game Result History to show the all game result.";
		TextArea content = new TextArea(str);
		content.setPrefHeight(OzlympicGameView.getRoot().getHeight());
		content.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
		vbox.getChildren().addAll(title, content);
		this.getChildren().add(vbox);
	}
}

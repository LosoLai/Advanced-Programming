package View;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
		Label title = new Label("Instractions:");
		title.setFont(Font.font("Verdana", FontWeight.BOLD, 24));
		String str = "1. Select a game type from \nnavigation menu.\n\n" +
				 	 "2. Choose athlete and referee \nto participate the game.\n\n" +
				 	 "3. Click the 'Confirm' button \nto set the candidate.\n\n" +
				 	 "4. Select Athlete Points to \nlist the scores for each athlete.\n\n" +
				 	 "5. Select Game Result History \nto show the all game result.";
		TextArea content = new TextArea(str);
		content.setPrefHeight(OzlympicGameView.getRoot().getHeight());
		content.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
		vbox.getChildren().addAll(title, content);
		this.getChildren().add(vbox);
	}
}

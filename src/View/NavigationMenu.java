package View;

import Model.Game;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;

public class NavigationMenu extends VBox{
	private Button swimming;
	private Button cycling;
	private Button running;
	private Button athletePoints;
	private Button gameResult;
	
	public Button getSwimmingButton()
	{
		return this.swimming;
	}
	public Button getCyclingButton()
	{
		return this.cycling;
	}
	public Button getRunningButton()
	{
		return this.running;
	}
	public Button getAthletePointsButton()
	{
		return this.athletePoints;
	}
	public Button getGameResultButton()
	{
		return this.gameResult;
	}
	
	public NavigationMenu()
	{
		super();
		this.setPadding(new Insets(10));
		this.setSpacing(8);
	    //create game type options
		TitledPane gameType = new TitledPane();
		/*gameType.setStyle("-fx-padding: 10;" + 
                		  "-fx-border-style: solid inside;" + 
                		  "-fx-border-width: 2;" +
                		  "-fx-border-insets: 5;" + 
                		  "-fx-border-radius: 5;" + 
               			  "-fx-background-color: #DFB951" +
               			  "-fx-border-color: blue;");*/
		
		gameType.setText("1. Game Type");
		GridPane typeOptions = new GridPane();
		typeOptions.setVgap(4);
		typeOptions.setPadding(new Insets(5, 5, 5, 5));
		//create swimming button
		swimming = new Button(Game.GAME_SWIMMING);
		typeOptions.add(swimming, 0, 0);
		//create cycling button
		cycling = new Button(Game.GAME_CYCLING);
		typeOptions.add(cycling, 0, 1);
		//create running button
		running = new Button(Game.GAME_RUNNING);
		typeOptions.add(running, 0, 2);
		gameType.setContent(typeOptions);
		this.getChildren().add(gameType);
		
		//create candidate info. 
		TitledPane participantInfo = new TitledPane();
		participantInfo.setText("2. Participant Info.");
		GridPane participant = new GridPane();
		participant.setVgap(4);
		participant.setPadding(new Insets(5, 5, 5, 5));
		participant.add(new Label("Referee :\n" + 
								  "One official only"), 0, 0);
		participant.add(new Label("Athlete List :\n" + 
								  "Minimum: 4 athletes\n" + 
								  "Maximum: 8 athletes"), 0, 1);
		participantInfo.setContent(participant);
		this.getChildren().add(participantInfo);
		
		//create display result options
		TitledPane displayResult = new TitledPane();
		displayResult.setText("3. Display Results");
		GridPane resultOptions = new GridPane();
		resultOptions.setVgap(4);
		resultOptions.setPadding(new Insets(5, 5, 5, 5));
		athletePoints = new Button("Athlete Points");
		resultOptions.add(athletePoints, 0, 0);
		gameResult = new Button("Game Result History");
		resultOptions.add(gameResult, 0, 1);
		displayResult.setContent(resultOptions);
		this.getChildren().add(displayResult);
		
		//Modified by Arion 14/05/17-------------------------------------
		//create participant colour legend
		TitledPane legend = new TitledPane();
		legend.setText("4. Participant Legend");
		GridPane legend_display = new GridPane();
		legend_display.setHgap(4);
		legend_display.setPadding(new Insets(5, 5, 5, 5));
								
		Rectangle swLeg = new Rectangle(10,10);
		swLeg.setStyle("-fx-fill:#8fb1e8;");
		Rectangle cyLeg = new Rectangle(10,10);
		cyLeg.setStyle("-fx-fill:#7bfca2;");
		Rectangle spLeg = new Rectangle(10,10);
		spLeg.setStyle("-fx-fill:#fcfc7b;");
		Rectangle supLeg = new Rectangle(10,10);
		supLeg.setStyle("-fx-fill:#fc9d7b;");
		Rectangle ofLeg = new Rectangle(10,10);
		ofLeg.setStyle("-fx-fill:#d3c2d6;");
								
		legend_display.add(swLeg, 0, 0);
		legend_display.add(new Label("Swimmers"), 1, 0);
		legend_display.add(cyLeg, 0, 1);
		legend_display.add(new Label("Cyclists"), 1, 1);
		legend_display.add(spLeg, 0, 2);
		legend_display.add(new Label("Sprinters"), 1, 2);
		legend_display.add(supLeg, 0, 3);
		legend_display.add(new Label("Superathletes"), 1, 3);
		legend_display.add(ofLeg, 0, 4);
		legend_display.add(new Label("Referees"), 1, 4);
								
		legend.setContent(legend_display);
		this.getChildren().add(legend);
	}
}

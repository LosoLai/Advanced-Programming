/**@Author Loso
 * OzlympicMainFrame is the main window view of the game
 * 
 */

package View;
import Controller.Driver;
import Model.Athlete;
import Model.Game;
import Model.Official;
import Model.Participant;

import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import Assignment02.GameUnexecutedException;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.ParallelTransition;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.input.DataFormat;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextBoundsType;

public class OzlympicGameView extends Application {
	public static OzlympicGameView gameView;
	//private Stage primaryStage;
	private static Driver gameDriver;	//the role is game controller
	private static BorderPane root;
	//private static VBox displayContent;
	
	private final DataFormat buttonFormat = new DataFormat("com.example.myapp.formats.button");
	private Button draggingButton ;
	
	public static BorderPane getRoot()
	{
		return root;
	}
	
	@Override
	public void start(Stage primaryStage) {
		gameView = this;
		//this.primaryStage = primaryStage;
		
		try {
			root = new BorderPane();
			Scene scene = new Scene(root, 800, 550);
			//create menu bar on the top of view
			createMenuBar();
			//create navigation menu
			createNavigationMenu();
			//create display area
			displayContentPane();
			
			//window bind with the root prefer size
			primaryStage.minWidthProperty().bind(root.prefWidthProperty());
			primaryStage.setScene(scene);
			primaryStage.setTitle("OzlympicGame");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		} 
	}
	
	public static void main(String[] args) {
		gameDriver = Driver.getInstance();
		launch(args);
	}
	
	private void createMenuBar()
	{
		String[] menu = {"Save", "Help"};
		String[] menuItem = {"Game Result Saving", 
							 "Rules Explaination"};
		MenuBar menuBar = new MenuBar();
        // --- Menu Save
        Menu menuSave = new Menu(menu[0]);
        MenuItem menuItem_0 = new MenuItem(menuItem[0]);
        menuItem_0.setOnAction((ActionEvent e) -> {
                System.out.println(menuItem[0]);
        });
        menuSave.getItems().add(menuItem_0);
        // --- Menu Help
        Menu menuHelp = new Menu(menu[1]);
        MenuItem menuItem_1 = new MenuItem(menuItem[1]);
        menuItem_1.setOnAction((e) -> {
                System.out.println(menuItem[1]);
        });
        menuHelp.getItems().add(menuItem_1);
        menuBar.getMenus().addAll(menuSave, menuHelp);
        root.setTop(menuBar);
	}
	private void createNavigationMenu()
	{
		VBox optionMenu = new VBox();
		optionMenu.setPadding(new Insets(10));
		optionMenu.setSpacing(8);
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
		Button swimming = new Button(Game.GAME_SWIMMING);
		swimming.setOnAction((ActionEvent e) -> {
			gameTypeButtonHandler(Game.GAME_SWIMMING);
		});
		typeOptions.add(swimming, 0, 0);
		//create cycling button
		Button cycling = new Button(Game.GAME_CYCLING);
		cycling.setOnAction((e) -> {
			gameTypeButtonHandler(Game.GAME_CYCLING);
		});
		typeOptions.add(cycling, 0, 1);
		//create running button
		Button running = new Button(Game.GAME_RUNNING);
		running.setOnAction((e) -> {
			gameTypeButtonHandler(Game.GAME_RUNNING);
		});
		typeOptions.add(running, 0, 2);
		gameType.setContent(typeOptions);
		optionMenu.getChildren().add(gameType);
		
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
		optionMenu.getChildren().add(participantInfo);
		
		//create display result options
		TitledPane displayResult = new TitledPane();
		displayResult.setText("3. Display Results");
		GridPane resultOptions = new GridPane();
		resultOptions.setVgap(4);
		resultOptions.setPadding(new Insets(5, 5, 5, 5));
		Button athletePoints = new Button("Athlete Points");
		//AthletePointsButtonHandler apHandler = new AthletePointsButtonHandler();
		athletePoints.setOnAction((e) -> {
			displayAthletePoint();
		});
		resultOptions.add(athletePoints, 0, 0);
		Button gameResult = new Button("Game Result History");
		gameResult.setOnAction((e) -> {
			displayGameResultHistory();
		});
		resultOptions.add(gameResult, 0, 1);
		displayResult.setContent(resultOptions);
		optionMenu.getChildren().add(displayResult);
		
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
				optionMenu.getChildren().add(legend);
				root.setLeft(optionMenu);
				//----------------------------------------------------------------

	}
	//create display area for showing the information required
	private void displayContentPane()
	{
		StackPane display = new StackPane();
		root.setPrefWidth(900);
		display.setPrefHeight(root.getHeight());
		
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
		content.setPrefHeight(root.getHeight());
		content.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
		vbox.getChildren().addAll(title, content);
		display.getChildren().add(vbox);
		root.setCenter(display);
	}
	
	private void createListView_SelectParticipants()
	{
		root.setPrefWidth(1000);
		
		GridPane selectParticipant = new GridPane();
		selectParticipant.setVgap(4);
		selectParticipant.setPadding(new Insets(5, 5, 5, 5));
		selectParticipant.add(new Label("Choose Referee:"), 0, 0);
		selectParticipant.add(new Label("Choose Athlete:"), 1, 0);
		
		//create splitePane for referee 
		FlowPane pane1 = new FlowPane();
        FlowPane pane2 = new FlowPane();
        pane1.setVgap(2);
		pane1.setHgap(2);
		pane2.setVgap(2);
		pane2.setHgap(2);
        
        ArrayList<Official> official = gameDriver.officialList;
        pane1.getChildren().add(new Label(Participant.OFFICIAL + "List :"));
        pane2.getChildren().add(new Label(Participant.OFFICIAL + "Selected :"));
        for(int i=0 ; i<official.size() ; i++)
        {
        	pane1.getChildren().add(createButton(official.get(i).getPersonID()));
        }
	    
        addDropHandling(pane1);
        addDropHandling_Validation_Referee(pane2);

        SplitPane splitPane_Ref = new SplitPane(pane1, pane2);
        splitPane_Ref.setStyle("-fx-border-color: #f26704;");
        splitPane_Ref.setOrientation(Orientation.VERTICAL);
        splitPane_Ref.setDividerPositions(0.7f, 0.3f);
        splitPane_Ref.setPrefSize(400, 400);
        selectParticipant.add(splitPane_Ref, 0, 1);

        //create splitePane for athlete
        FlowPane pane3 = new FlowPane();
        FlowPane pane4 = new FlowPane();
        pane3.setVgap(2);
        pane3.setHgap(2);
        pane4.setVgap(2);
        pane4.setHgap(2);
        
        ArrayList<Athlete> athlete = gameDriver.athleteList;
        pane3.getChildren().add(new Label(Participant.ATHLETE + "List :"));
        pane4.getChildren().add(new Label(Participant.ATHLETE + "Selected :"));
        for(int i=0 ; i<athlete.size() ; i++)
        {
        	pane3.getChildren().add(createButton(athlete.get(i).getPersonID(), 
        										 athlete.get(i).getPersonType()));
        }
	    
        addDropHandling(pane3);
        addDropHandling_Validation_Athlete(pane4);

        SplitPane splitPane_Ath = new SplitPane(pane3, pane4);
        splitPane_Ath.setStyle("-fx-border-color: #206bd6;");
        splitPane_Ath.setOrientation(Orientation.VERTICAL);
        splitPane_Ath.setDividerPositions(0.7f, 0.3f);
        splitPane_Ath.setPrefSize(400, 400);
        selectParticipant.add(splitPane_Ath, 1, 1);
        
        root.setCenter(selectParticipant);
        
        setConfirmButton(pane2, pane4);
	}
	private void setConfirmButton(FlowPane referee, FlowPane athlete)
	{
		Button confirm = new Button("Confirm");
		confirm.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
		confirm.prefWidthProperty().bind(root.widthProperty());
		confirm.setMinHeight(40);
		confirm.setOnAction((ActionEvent e) -> {
			setCandidateList(referee, athlete);
		});
		root.setBottom(confirm);
	}
	private void createTableView_AthletePoints(ArrayList<Athlete> allAthlete)
	{
		Set<Athlete> set = new HashSet<Athlete>(allAthlete);
		List<Athlete> list = set.stream().collect(Collectors.toList());
		
		TableView table = new TableView();
		table.setEditable(false);
		ObservableList<Athlete> data =
	            FXCollections.observableArrayList(list);
		
		TableColumn name = new TableColumn("Name");
		name.setMinWidth(200);
		name.setCellValueFactory(new PropertyValueFactory<>("name"));
		TableColumn type = new TableColumn("Type");
		type.setMinWidth(100);
		type.setCellValueFactory(new PropertyValueFactory<>("personType"));
		TableColumn score = new TableColumn("Score");
		score.setMinWidth(60);
		score.setCellValueFactory(new PropertyValueFactory<>("points"));
		
		table.setItems(data);
		table.getColumns().addAll(name, type, score);
		
		final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(table);
		
        root.setCenter(vbox);
	    
		//test
		Iterator itr = set.iterator();
		while(itr.hasNext()) {
		    System.out.println(itr.next());
		}
		System.out.println(allAthlete.size() + "\t" + set.size());
	}
	private void createTableView_GameResults(ArrayList<Game> gameList)
	{
		TableView table = new TableView();
		table.setEditable(false);
		ObservableList<Game> data =
	            FXCollections.observableArrayList(gameList);
		
		TableColumn type = new TableColumn("Game Type");
		type.setMinWidth(100);
		type.setCellValueFactory(new PropertyValueFactory<>("gameType"));
		TableColumn id = new TableColumn("ID");
		id.setMinWidth(50);
		id.setCellValueFactory(new PropertyValueFactory<>("gameID"));
		TableColumn result = new TableColumn("Game Result");
		result.setMinWidth(200);
		result.setCellValueFactory(new PropertyValueFactory<>("gameResult"));
		
		table.setItems(data);
		table.getColumns().addAll(type, id, result);
		
		final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(table);
		
	    root.setCenter(vbox);
	    
		//test
		System.out.println(gameList.size());
		Iterator itr = gameList.iterator();
		while(itr.hasNext()) {
		    System.out.println(itr.next());
		}
	}
	//handling buttons' action
	private void gameTypeButtonHandler(String gameType)
	{
		//remove the display content node first
		boolean bResult = false;
		try{
			bResult = gameDriver.selectGameTypeForCreateAGame(gameType);
		} catch (GameUnexecutedException e) {
			e.printStackTrace();
		}
		//test
		System.out.println("result :" + bResult);
		
		//create lists showing all the participants
		createListView_SelectParticipants();
	}
	private void displayAthletePoint()
	{
		createTableView_AthletePoints(Driver.athleteList);
		//test
		System.out.println("Athlete points clicked");
	}
	private void displayGameResult(ArrayList<String> athleteIDList)
	{
		//remove button at the bottom
		root.setBottom(null);
		
		GridPane vbox = new GridPane();
		vbox.setVgap(30);
		vbox.setHgap(30);
		vbox.setPadding(new Insets(30, 10, 10, 20));
		
		int listSize = athleteIDList.size();
		final double ONEMINUTE = 60;
		final double theMaxSec = gameDriver.currentGame.getCandidate().get(listSize-1).getExecuteTime();
		IntegerProperty seconds = new SimpleIntegerProperty();
		for(int i=0 ; i<listSize ; i++)
		{
			String athleteID = athleteIDList.get(i);
			Athlete candidate = (Athlete)gameDriver.getParticipantList().get(athleteID);
			String textName = athleteID + " - " +candidate.getName() + " : ";
			Label athleteName = new Label(textName);
			ProgressBar progress = new ProgressBar();
	        progress.setMinWidth(300);
	        double v = ((candidate.getExecuteTime() / theMaxSec) * ONEMINUTE);
	        System.out.println("divide by " + v);
	        progress.progressProperty().bind(seconds.divide(v));
	        vbox.add(athleteName, 0, i);
	        vbox.add(progress, 1, i);
			//Test
			System.out.println(candidate.getName() + " :" + candidate.getExecuteTime());
		}
		
		Text currTimeText = new Text("Current time: 0 secs" );
		currTimeText.setBoundsType(TextBoundsType.VISUAL);
		vbox.add(currTimeText, 0, listSize);
		root.setCenter(vbox);
		
		Timeline timeline = new Timeline(
	        new KeyFrame(Duration.ZERO, new KeyValue(seconds, 0)),
	        new KeyFrame(Duration.minutes(1), e-> {
	            // do anything you need here on completion...
	            System.out.println("game end");
	        }, new KeyValue(seconds, 60))   
	    );
		timeline.currentTimeProperty().addListener(new InvalidationListener() {
			
			public void invalidated(Observable ov) {
			
				int time = (int) timeline.getCurrentTime().toSeconds();
				int exect = (int)((theMaxSec / ONEMINUTE) * time);
				currTimeText.setText("Current time: " + time + " secs\n" +
									 "Exect time : " + exect);
			}
		});
	    timeline.setCycleCount(1);
	    timeline.play();
	}
	private void displayGameResultHistory()
	{
		ArrayList<Game> gameList = gameDriver.getGameList();
		createTableView_GameResults(gameList);
		//test
		System.out.println("Game result history clicked");
	}
	private void setCandidateList(FlowPane referee, FlowPane athlete)
	{
		boolean bSet = false;
		//get referee ID
		String refereeID = "";
		int nodeNum = referee.getChildren().size();
		for(int i=0 ; i<nodeNum ; i++)
		{
			Node item = referee.getChildren().get(i);
			if(item instanceof Button)
				refereeID = ((Button) item).getText();
		}
		//set athleteIDList
		ArrayList<String> athleteIDList = new ArrayList<String>();
		nodeNum = athlete.getChildren().size();
		for(int j=0 ; j<nodeNum ; j++)
		{
			Node item = athlete.getChildren().get(j);
			if(item instanceof Button)
				athleteIDList.add(((Button) item).getText());
		}
		
		try {
			bSet = gameDriver.setRefereeAndCandidate(refereeID, athleteIDList);
		} catch (GameUnexecutedException e){
			e.printStackTrace();
		}
		//display the game result
		if(bSet)
			displayGameResult(athleteIDList);
	}
	//-----------------------------------------------------------
	//create draggable buttons
	private Button createButton(String text) {
        Button button = new Button(text);
        button.setOnDragDetected(e -> {
            Dragboard db = button.startDragAndDrop(TransferMode.MOVE);
            db.setDragView(button.snapshot(null, null));
            ClipboardContent cc = new ClipboardContent();
            cc.put(buttonFormat, "button");
            db.setContent(cc);
            draggingButton = button ;
        });
        button.setOnDragDone(e -> draggingButton = null);
        button.setStyle("-fx-background-color: #d3c2d6;" + 
        				"-fx-border-color: black;");
        return button ;
    }
	private Button createButton(String ID, String athleteType) {
        Button button = createButton(ID);
        if(athleteType.equals(Participant.SWIMMER)) // blue
        	button.setStyle("-fx-background-color: #8fb1e8;" +
        					"-fx-border-color: black;");
        if(athleteType.equals(Participant.CYCLIST)) // green
        	button.setStyle("-fx-background-color: #7bfca2;" +
							"-fx-border-color: black;");
        if(athleteType.equals(Participant.SPRINTER)) // yellow
        	button.setStyle("-fx-background-color: #fcfc7b;" +
							"-fx-border-color: black;");
        if(athleteType.equals(Participant.SUPERATHLETE)) //orange
        	button.setStyle("-fx-background-color: #fc9d7b;" +
							"-fx-border-color: black;");
        return button;
    }
	//dealing with drag buttons----------------------------------
	private void addDropHandling(Pane pane) {
        pane.setOnDragOver(e -> {
            Dragboard db = e.getDragboard();
            if (db.hasContent(buttonFormat) 
                    && draggingButton != null 
                    && draggingButton.getParent() != pane) {
                e.acceptTransferModes(TransferMode.MOVE);
            }
        });

        pane.setOnDragDropped(e -> {
            Dragboard db = e.getDragboard();
            if (db.hasContent(buttonFormat)) {
                ((Pane)draggingButton.getParent()).getChildren().remove(draggingButton);
                pane.getChildren().add(draggingButton);
                e.setDropCompleted(true);
            }           
        });
    }
	private void addDropHandling_Validation_Referee(Pane pane) {
        pane.setOnDragOver(e -> {
            Dragboard db = e.getDragboard();
            if (db.hasContent(buttonFormat) 
                    && draggingButton != null 
                    && draggingButton.getParent() != pane) {
                e.acceptTransferModes(TransferMode.MOVE);
            }
        });

        pane.setOnDragDropped(e -> {
            Dragboard db = e.getDragboard();
            if (db.hasContent(buttonFormat)) {
            	//test
            	System.out.println("Official selected:" + pane.getChildren().size());
            	if(pane.getChildren().size() > Game.REFEREELIMIT)
            		System.err.println("Too many referee"); //need to show error message(throw exception)
            	else
            	{
            		((Pane)draggingButton.getParent()).getChildren().remove(draggingButton);
            		pane.getChildren().add(draggingButton);
            		e.setDropCompleted(true); 
            	}
            }           
        });
    }
	private void addDropHandling_Validation_Athlete(Pane pane) {
        pane.setOnDragOver(e -> {
            Dragboard db = e.getDragboard();
            if (db.hasContent(buttonFormat) 
                    && draggingButton != null 
                    && draggingButton.getParent() != pane) {
                e.acceptTransferModes(TransferMode.MOVE);
            }
        });

        pane.setOnDragDropped(e -> {
            Dragboard db = e.getDragboard();
            if (db.hasContent(buttonFormat)) {
            	//test
            	System.out.println("Athlete selected:" + pane.getChildren().size());
            	if(pane.getChildren().size() > Game.CANDIDATELIMIT_MAX)
            		System.err.println("Too many athletes"); //need to show error message(throw exception)
            	else
            	{
            		((Pane)draggingButton.getParent()).getChildren().remove(draggingButton);
            		pane.getChildren().add(draggingButton);
            		e.setDropCompleted(true); 
            	}
            }           
        });
    }
	//-----------------------------------------------------------
}

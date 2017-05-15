/**@Author Loso
 * OzlympicMainFrame is the main window view of the game
 * 
 */

package View;
import Controller.Driver;
import Controller.Data;
import Model.Athlete;
import Model.Game;
import Model.Official;
import Model.Participant;

import java.util.*;
import java.util.stream.Collectors;

import Assignment02.*;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.*;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.input.DataFormat;
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
	Data data = new Data();
	
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
			Scene scene = new Scene(root, 600, 550);
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
		} /*catch(GameFullException e) {
			e.printStackTrace();
		} catch(NoRefereeException e) {
			e.printStackTrace();
		} catch(TooFewAthleteException e) {
			e.printStackTrace();
		} catch(WrongTypeException e) {
			e.printStackTrace();
		} */catch(Exception e) {
			e.printStackTrace();
		} 
	}
	
	public static void main(String[] args){
		gameDriver = Driver.getInstance();
		launch(args);
	}
	
	private void createMenuBar()
	{
		String[] menu = {"Save", "Help"};
		String[] menuItem = {"Game Result Saving", 
							 "Rules Explanation"};
		MenuBar menuBar = new MenuBar();
        // --- Menu Save
        Menu menuSave = new Menu(menu[0]);
        MenuItem menuItem_0 = new MenuItem(menuItem[0]);
        menuItem_0.setOnAction((ActionEvent e) -> {
                System.out.println(menuItem[0]);
                //Save to File and DB------------------------------------------------
                data.writeToDB(gameDriver.getGameList());
                data.writeToFile(gameDriver.getGameList());
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
		NavigationMenu optionMenu = new NavigationMenu();
		optionMenu.getSwimmingButton().setOnAction((ActionEvent e) -> {
			gameTypeButtonHandler(Game.GAME_SWIMMING);
		});
		optionMenu.getCyclingButton().setOnAction((e) -> {
			gameTypeButtonHandler(Game.GAME_CYCLING);
		});
		optionMenu.getRunningButton().setOnAction((e) -> {
			gameTypeButtonHandler(Game.GAME_RUNNING);
		});
		optionMenu.getAthletePointsButton().setOnAction((e) -> {
			displayAthletePoint();
		});
		optionMenu.getGameResultButton().setOnAction((e) -> {
			displayGameResultHistory();
		});
		root.setLeft(optionMenu);
	}
	//create display area for showing the information required
	private void displayContentPane()
	{
		InstractionsPane display = new InstractionsPane();
		root.setPrefWidth(600);
		display.setPrefHeight(root.getHeight());
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
        // added try/catch for TooManyRefereeException---------------------------------------
      
        try {
        addDropHandling_Validation_Referee(pane2);
        } catch (TooManyRefereeException e) {
        	Alert alert = new Alert(AlertType.WARNING);
        	alert.setTitle("TooManyRefereeException Dialog");
			alert.setHeaderText("Warning Dialog : Too many referees");
			alert.setContentText(e.getMessage());
			alert.showAndWait();
        	//e.printStackTrace();
        } 

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
        
        // added try/catch for GameFullException--------------------------------------------------
        try {
        addDropHandling_Validation_Athlete(pane4);
        } catch (GameFullException e) {
        	Alert alert = new Alert(AlertType.WARNING);
        	alert.setTitle("GameFullException Dialog");
			alert.setHeaderText("Warning Dialog : Game full");
			alert.setContentText(e.getMessage());
			alert.showAndWait();
        	//e.printStackTrace();
        } 

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
		root.setPrefWidth(500);
		Set<Athlete> set = new HashSet<Athlete>(allAthlete);
		List<Athlete> list = set.stream().collect(Collectors.toList());
		
		AthletePointsTable table = new AthletePointsTable(list);
		final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(table);
        root.setCenter(vbox);
	}
	private void createTableView_GameResults(ArrayList<Game> gameList)
	{
		GameResultHistoryTable table = new GameResultHistoryTable(gameList);
		
		final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(table);
		
	    root.setCenter(vbox);
	}
	//handling buttons' action
	private void gameTypeButtonHandler(String gameType)
	{
		//remove the display content node first
		boolean bResult = false;
		try{
			bResult = gameDriver.selectGameTypeForCreateAGame(gameType);
		} catch (GameUnexecutedException e) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("GameUnexecutedException Dialog");
			alert.setHeaderText("Warning Dialog : Unexecuted");
			alert.setContentText(e.getMessage());
			alert.showAndWait();
			//e.printStackTrace();
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
	private void displayGameResultAnimation(ArrayList<String> athleteIDList)
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
		//display the game result progress
		if(bSet)
			displayGameResultAnimation(athleteIDList);
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
	private void addDropHandling_Validation_Referee(Pane pane) throws TooManyRefereeException {
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
            	
            	//throw TooManyRefereeException-------------------------------------------------------------
            	if (pane.getChildren().size() > Game.REFEREELIMIT){
            		throw new TooManyRefereeException("Too many referees selected. Please select only one referee.");
            	}else
            	{
            		((Pane)draggingButton.getParent()).getChildren().remove(draggingButton);
            		pane.getChildren().add(draggingButton);
            		e.setDropCompleted(true); 
            	}
             	
            } 
        });
    }
	private void addDropHandling_Validation_Athlete(Pane pane) throws GameFullException{
        pane.setOnDragOver(e -> {
            Dragboard db = e.getDragboard();
            if (db.hasContent(buttonFormat) 
                    && draggingButton != null 
                    && draggingButton.getParent() != pane) {
                e.acceptTransferModes(TransferMode.MOVE);
            }
        });

        //throw GameFullException---------------------------------------------------------------------
        pane.setOnDragDropped(e -> {
            Dragboard db = e.getDragboard();
            if (db.hasContent(buttonFormat)) {
            	//test
            	System.out.println("Athlete selected:" + pane.getChildren().size());
            	if(pane.getChildren().size() > Game.CANDIDATELIMIT_MAX)
            		throw new GameFullException("Too many athletes selected. Only up to 8 athletes allowed to compete."); 
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

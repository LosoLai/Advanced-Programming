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

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import javafx.scene.Group;
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
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.input.DataFormat;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;

public class OzlympicGameView extends Application {
	//define main menu options
	public static final int SELECT_GAME 			= 1;
	public static final int SELECT_PARTICIPANT		= 2;
	public static final int START_GAME 				= 3;
	public static final int DISPLAY_FINALRESULT 	= 4;
	public static final int DISPLAY_ATHLETEPOINTS	= 5;
		
	//define game status
	public static final int GAME_DEFAULT 	= 0;
	public static final int GAME_INITIATED 	= 1;
	public static final int GAME_EXECUTED 	= 2;
	public static int gameStatus 			= GAME_DEFAULT;
	
	public static OzlympicGameView gameView;
	private Stage primaryStage;
	private static Driver gameDriver;	//the role is game controller
	private static BorderPane root;
	private static VBox displayContent;
	
	private final DataFormat buttonFormat = new DataFormat("com.example.myapp.formats.button");
	private Button draggingButton ;
	
	public static BorderPane getRoot()
	{
		return root;
	}
	public static VBox getDisplayContent()
	{
		return displayContent;
	}
	
	@Override
	public void start(Stage primaryStage) {
		gameView = this;
		this.primaryStage = primaryStage;
		
		try {
			root = new BorderPane();
			Scene scene = new Scene(root,800,600);
			//create menu bar on the top of view
			createMenuBar();
			//create navigation menu
			createNavigationMenu();
			//create display area
			createDisplayPane();
			
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
		participant.add(new Label("Referee :"), 0, 0);
		participant.add(new Label("Athlete List :"), 0, 1);
		participantInfo.setContent(participant);
		optionMenu.getChildren().add(participantInfo);
		
		//create display result options
		TitledPane displayResult = new TitledPane();
		displayResult.setText("3. Display Results");
		GridPane resultOptions = new GridPane();
		resultOptions.setVgap(4);
		resultOptions.setPadding(new Insets(5, 5, 5, 5));
		Button athletePoints = new Button("Athlete Points");
		AthletePointsButtonHandler apHandler = new AthletePointsButtonHandler();
		athletePoints.setOnAction(apHandler);
		resultOptions.add(athletePoints, 0, 0);
		Button gameResult = new Button("Game Result History");
		GameResultButtonHandler grHandler = new GameResultButtonHandler();
		gameResult.setOnAction(grHandler);
		resultOptions.add(gameResult, 0, 1);
		displayResult.setContent(resultOptions);
		optionMenu.getChildren().add(displayResult);
		
		root.setLeft(optionMenu);
	}
	private void createDisplayPane()
	{
		StackPane display = new StackPane();
		display.setPrefHeight(root.getHeight());
		
		displayContent = new VBox();
		displayContent.setPadding(new Insets(10));
		displayContent.setSpacing(8);
		Label title = new Label("Display Area:");		
		String str = "1. Select a game type from menu bar.\n" +
				 	 "2. Choose athlete and referee to participate the game.\n" +
				 	 "3. Click the 'Play' button to start the game";
		TextArea content = new TextArea(str);
		content.setPrefHeight(root.getHeight());
		displayContent.getChildren().addAll(title, content);
		display.getChildren().add(displayContent);
		root.setCenter(display);
	}
	private void createListView_SelectParticipants()
	{
		//get all participant lists
		HashMap<String, Participant> allParticipant = gameDriver.getParticipantList();
		//test
		Iterator entries = allParticipant.entrySet().iterator();
		while(entries.hasNext()) {
			Entry thisEntry = (Entry) entries.next();
		    System.out.println(thisEntry.getKey() + " - " + thisEntry.getValue());
		}
		
		GridPane selectParticipant = new GridPane();
		selectParticipant.setVgap(4);
		selectParticipant.setPadding(new Insets(5, 5, 5, 5));
		selectParticipant.add(new Label("Choose Referee:"), 0, 0);
		selectParticipant.add(new Label("Choose Athlete:"), 1, 0);
		
		//create splitePane for referee 
		FlowPane pane1 = new FlowPane();
        FlowPane pane2 = new FlowPane();
        
        ArrayList<Official> official = gameDriver.officialList;
        pane1.getChildren().add(new Label(Participant.OFFICIAL + "List :"));
        pane2.getChildren().add(new Label(Participant.OFFICIAL + "Selected :"));
        for(int i=0 ; i<official.size() ; i++)
        {
        	pane1.getChildren().add(createButton(official.get(i).getName()));
        }
	    
        addDropHandling(pane1);
        addDropHandling(pane2);

        SplitPane splitPane_Ref = new SplitPane(pane1, pane2);
        splitPane_Ref.setStyle("-fx-border-color: #f26704;");
        splitPane_Ref.setOrientation(Orientation.VERTICAL);
        splitPane_Ref.setDividerPositions(0.7f, 0.3f);
        splitPane_Ref.setPrefSize(400, 400);
        selectParticipant.add(splitPane_Ref, 0, 1);

        //create splitePane for athlete
        FlowPane pane3 = new FlowPane();
        FlowPane pane4 = new FlowPane();
        
        ArrayList<Athlete> athlete = gameDriver.athleteList;
        pane3.getChildren().add(new Label(Participant.ATHLETE + "List :"));
        pane4.getChildren().add(new Label(Participant.ATHLETE + "Selected :"));
        for(int i=0 ; i<athlete.size() ; i++)
        {
        	pane3.getChildren().add(createButton(athlete.get(i).getName()));
        }
	    
        addDropHandling(pane3);
        addDropHandling(pane4);

        SplitPane splitPane_Ath = new SplitPane(pane3, pane4);
        splitPane_Ath.setStyle("-fx-border-color: #206bd6;");
        splitPane_Ath.setOrientation(Orientation.VERTICAL);
        splitPane_Ath.setDividerPositions(0.7f, 0.3f);
        splitPane_Ath.setPrefSize(400, 400);
        selectParticipant.add(splitPane_Ath, 1, 1);
        
        displayContent.getChildren().add(selectParticipant);		
	}
	
	public void createTableView_AthletePoints(ArrayList<Athlete> allAthlete)
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
		
	    displayContent.getChildren().add(vbox);
	    
		//test
		Iterator itr = set.iterator();
		while(itr.hasNext()) {
		    System.out.println(itr.next());
		}
		System.out.println(allAthlete.size() + "\t" + set.size());
	}
	public void createTableView_GameResults(ArrayList<Game> gameList)
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
		
	    displayContent.getChildren().add(vbox);
	    
		//test
		System.out.println(gameList.size());
		Iterator itr = gameList.iterator();
		while(itr.hasNext()) {
		    System.out.println(itr.next());
		}
	}
	
	private void gameTypeButtonHandler(String gameType)
	{
		//remove the displaycontent node first
		displayContent.getChildren().remove(1);
		GameTypeButtonHandler buttonHandler = new GameTypeButtonHandler(gameType);
		boolean bResult = buttonHandler.handle();
		
		//create lists showing all the participants
		createListView_SelectParticipants();
	}
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
        return button ;
    }
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
}

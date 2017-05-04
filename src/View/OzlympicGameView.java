/**@Author Loso
 * OzlympicMainFrame is the main window view of the game
 * 
 */

package View;
import Controller.Driver;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;

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
	
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,600,400);
			//create menu bar on the top of view
			createMenuBar(root);
			//create navigation menu
			createNavigationMenu(root);
			//create display area
			createDisplayPane(root);
			
			primaryStage.setScene(scene);
			primaryStage.setTitle("OzlympicGame");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	private void createMenuBar(BorderPane root)
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
	private void createNavigationMenu(BorderPane root)
	{
		VBox optionMenu = new VBox();
		optionMenu.setPadding(new Insets(10));
		optionMenu.setSpacing(8);
	    //create game type options
		TitledPane gameType = new TitledPane();
		/*instractions.setStyle("-fx-padding: 10;" + 
                			  "-fx-border-style: solid inside;" + 
                			  "-fx-border-width: 2;" +
                			  "-fx-border-insets: 5;" + 
                			  "-fx-border-radius: 5;" + 
                			  "-fx-border-color: blue;");*/
		
		gameType.setText("Game Type");
		GridPane typeOptions = new GridPane();
		typeOptions.setVgap(4);
		typeOptions.setPadding(new Insets(5, 5, 5, 5));
		typeOptions.add(new Button("Swimming"), 0, 0);
		typeOptions.add(new Button("Cycling"), 0, 1);
		typeOptions.add(new Button("Running"), 0, 2);
		gameType.setContent(typeOptions);
		optionMenu.getChildren().add(gameType);
		
		//create display result options
		TitledPane displayResult = new TitledPane();
		displayResult.setText("Display Results");
		GridPane resultOptions = new GridPane();
		resultOptions.setVgap(4);
		resultOptions.setPadding(new Insets(5, 5, 5, 5));
		resultOptions.add(new Button("Athlete Points"), 0, 0);
		resultOptions.add(new Button("Game Result History"), 0, 1);
		displayResult.setContent(resultOptions);
		optionMenu.getChildren().add(displayResult);
		root.setLeft(optionMenu);
	}
	private void createDisplayPane(BorderPane root)
	{
		String str = "1. Select a game type from menu bar.\n" +
				 	 "2. Choose athlete and referee to participant the game.\n" +
				 	 "3. Click the 'Play' button to start the game";
		TextArea content = new TextArea(str);
		root.setCenter(content);
	}
}

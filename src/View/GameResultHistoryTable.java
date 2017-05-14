package View;

import java.util.ArrayList;

import Model.Game;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class GameResultHistoryTable extends TableView{
	public GameResultHistoryTable(ArrayList<Game> gameList)
	{
		super();
		this.setEditable(false);
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
		
		this.setItems(data);
		this.getColumns().addAll(type, id, result);
	}
}

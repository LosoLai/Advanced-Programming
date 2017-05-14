package View;

import java.util.List;

import Model.Athlete;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class AthletePointsTable extends TableView{
	public AthletePointsTable(List<Athlete> list)
	{
		super();
		
		this.setEditable(false);
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
		
		this.setItems(data);
		this.getColumns().addAll(name, type, score);
	}
}

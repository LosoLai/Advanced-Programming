package View;

import java.util.List;

import Model.Athlete;
import Model.Participant;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class AthletePointsTable extends TableView{
	private Button back;
	public Button getBackButton()
	{
		return this.back;
	}
	public AthletePointsTable(List<Athlete> list)
	{
		super();
		
		this.setEditable(false);
		ObservableList<Athlete> data =
	            FXCollections.observableArrayList(list);
		
		TableColumn name = new TableColumn("Name");
		name.setMinWidth(100);
		name.setCellValueFactory(new PropertyValueFactory<>("name"));
		TableColumn<Participant, String> type = new TableColumn<>("Type");
		type.setCellValueFactory(new PropertyValueFactory<Participant, String>("personType"));
		TableColumn extraType = new TableColumn("Extra Type");
		extraType.setCellValueFactory(new PropertyValueFactory<>("extraType"));
		TableColumn score = new TableColumn("Score");
		score.setMinWidth(60);
		score.setCellValueFactory(new PropertyValueFactory<>("points"));
		
		this.setItems(data);
		this.getColumns().addAll(name, type, extraType, score);
		
		setHomeButton();
	}
	private void setHomeButton()
	{
		back = new Button("Back");
		back.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
		back.setMinHeight(40);
	}
}

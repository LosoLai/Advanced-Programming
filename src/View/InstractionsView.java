package View;

import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class InstractionsView extends Stage{
	public InstractionsView()
	{
		super();
		this.setTitle("Instructions");
		
		Group root = new Group();
        Scene scene = new Scene(root, 700, 450, Color.WHITE);
        
        GridPane gridpane = new GridPane();
        gridpane.setPadding(new Insets(5));
        gridpane.setHgap(10);
        gridpane.setVgap(10);
        
        final ImageView imv = new ImageView();
        final Image image2 = new Image(InstractionsView.class.getResourceAsStream("Image.png"));
        imv.setImage(image2);

        final HBox pictureRegion = new HBox();
        
        pictureRegion.getChildren().add(imv);
        gridpane.add(pictureRegion, 1, 1);
        
        
        root.getChildren().add(gridpane);        
        this.setScene(scene);
        this.show();
	}
}

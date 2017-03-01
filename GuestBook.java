package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.*; 
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class GuestBook extends Application {
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Form.fxml"));
		Parent root = loader.load();
		
		Controller controls = loader.getController(); 
	
		primaryStage.setTitle("Guest Book");
		primaryStage.setScene(new Scene(root,1820,980));
		primaryStage.show();
		
		
		controls.setData(); 
		
		Scene scene = new Scene(new Group(), 1366, 900);

		// make input field
		TextField latlong = new TextField();

		// make browser
		Browser ViewPort = new Browser();

		// make button
		Button btn = new Button();
		btn.setText("Get Latlong");

		// Fill in the grid with the declared elements and labels for them.
		GridPane grid = new GridPane();
		grid.setVgap(4);
		grid.setHgap(10);
		grid.setPadding(new Insets(5, 5, 5, 5));
		grid.add(new Label("Your Lat Long is:"), 0, 0);
		grid.add(latlong, 1, 0);
		grid.add(btn, 15, 0);
		grid.add(ViewPort, 1, 15);

		Group root2 = (Group) scene.getRoot();
		root2.getChildren().add(grid);
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	
	public static void main(String[] args)
	{
		launch(args); 
	}
	
	

}

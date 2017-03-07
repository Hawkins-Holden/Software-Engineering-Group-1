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
	
	public static void main(String[] args)
	{
		launch(args); 
	}
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Browser.fxml"));
		Parent root = loader.load();
		
		Controller controls = loader.getController(); 
	
		primaryStage.setTitle("Guest Book");
		primaryStage.setScene(new Scene(root,800,480));
		primaryStage.show();
		
	}
	
	

}

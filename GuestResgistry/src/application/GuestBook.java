package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.stage.Stage;


@SuppressWarnings("restriction")
public class GuestBook extends Application {
	
	public static void main(String[] args)
	{
		launch(args); 
	}
	
	
	@SuppressWarnings("unused")
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

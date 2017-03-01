package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.*; 

public class GuestBook extends Application {
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Form.fxml"));
		Parent root = loader.load();
		
		Controller controls = loader.getController(); 
	
		primaryStage.setTitle("Guest Book");
		primaryStage.setScene(new Scene(root,1215,879));
		primaryStage.show();
		
		
		controls.setData(); 
		
	}
	
	public static void main(String[] args)
	{
		launch(args); 
	}
	
	

}

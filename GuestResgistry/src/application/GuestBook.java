package application; 

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

public class GuestBook extends Application {

   @Override
   public void start(Stage Stage) throws Exception {	
   	
      Parent root = FXMLLoader.load(getClass().getResource("GuestBook.fxml"));
      Stage.setTitle("Guest Book Registry");
      Stage.setScene(new Scene(root, 1680, 1200));
      Stage.show();
   
   }
	

   public static void main(String[] args) {
      launch(args);	
   }

	
}
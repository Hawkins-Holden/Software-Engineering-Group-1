package adminApplication; 

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

@SuppressWarnings("restriction")
public class Admin extends Application {

   @Override
   public void start(Stage Stage) throws Exception {	
   	
      Parent root = FXMLLoader.load(getClass().getResource("analytics.fxml"));
      Stage.setTitle("Admin Platform");
      Stage.setScene(new Scene(root, 1680, 1200));
      Stage.show();
   
   }
	

   public static void main(String[] args) {
      launch(args);	
   }	
}
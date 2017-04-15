package Admin;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class UpdateVisitor extends Application {
	
	public void start(Stage Stage) throws Exception {
		
	
		Parent root = FXMLLoader.load(getClass().getResource("AdminViewForm.fxml"));
		Stage.setTitle("Update Users");
		Stage.setScene(new Scene(root));
		Stage.setHeight(900);
		Stage.setWidth(1200);
		Stage.show();

	}

	public static void main(String[] args) {
		launch(args);
	} 

}


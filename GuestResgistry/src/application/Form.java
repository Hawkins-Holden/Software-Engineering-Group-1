
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Form extends Application {
	
	public void start(Stage Stage) throws Exception {
		
	
		Parent root = FXMLLoader.load(getClass().getResource("Map.fxml"));
		Stage.setTitle("Welcome to Monroe-West Monroe CVB!!!");
		Stage.setScene(new Scene(root));
		Stage.setHeight(900);
		Stage.setWidth(1200);
		Stage.show();

	}

	public static void main(String[] args) {
		launch(args);
	} 

}

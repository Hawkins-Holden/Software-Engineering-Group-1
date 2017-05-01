package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.stage.Stage;

public class Main extends Application {

	/** Intentionally start with map, this will be changed later. */
	private static String FxmlTarget = "Map.fxml";
	/**Is the form (as opposed to the map) to be loaded?*/

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

			FXMLLoader loader = new FXMLLoader(getClass().getResource(FxmlTarget));
			Parent root = loader.load();
			//MapController controls = loader.getController();
			primaryStage.setTitle("Map");
			primaryStage.setScene(new Scene(root, 1920, 1080));
			primaryStage.show();
		

	}
}
package Pindropper4;

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

public class Main extends Application {

	/** Intentionally start with map, this will be changed later. */
	private static String FxmlTarget = "MapViewer.fxml";
	/**Is the form (as opposed to the map) to be loaded?*/
	private static boolean isForm = false;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		//Check which fxml file is to be loaded.
		if (isForm = false) {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(FxmlTarget));
			Parent root = loader.load();
			//MapController controls = loader.getController();
			primaryStage.setTitle("Guest Book");
			primaryStage.setScene(new Scene(root, 1024, 680));
			primaryStage.show();
		}
		if (isForm = true) {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(FxmlTarget));
			Parent root = loader.load();
			//FormController controls = loader.getController();
			primaryStage.setTitle("Guest Form");
			primaryStage.setScene(new Scene(root, 1024, 680));
			primaryStage.show();
		}
		
		MapController.setMain(this);

	}

	/**
	 * Intentionally private
	 * 
	 * @return
	 */
	private String getTarget() {
		return FxmlTarget;
	}

	public void setTarget(String newTarg) throws Exception {
		//Make a new stage so start() can be called again.
		Stage stage = new Stage();
		
		//Set the target to the form.fxml or whatever is needed.
		FxmlTarget = newTarg;
		
		//Set the flag so the correct controlle (FormController.java) is loaded.
		isForm = true;
		start(stage);
	}

}

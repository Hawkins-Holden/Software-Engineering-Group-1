package adminApplication;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;

@SuppressWarnings("restriction")
public class ErrorAlert {
	
	public static void showError(Exception e){
		Alert errorAlert = new Alert(Alert.AlertType.CONFIRMATION);
		errorAlert.setTitle("Error");
		errorAlert.setHeaderText("A " + e.getClass().getName()
				+ " was thrown. Details are shown below. Please contact an administrator for assistance.");
		errorAlert.setContentText(e.getMessage());
		ButtonType cancelButton = new ButtonType("Close", ButtonBar.ButtonData.CANCEL_CLOSE);
		errorAlert.getButtonTypes().setAll(cancelButton);
		errorAlert.showAndWait();
	}
	
}

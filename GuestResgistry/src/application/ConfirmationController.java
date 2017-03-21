package application;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

public class ConfirmationController implements Initializable {

	
	
	public void switchScene(ActionEvent event) throws IOException
	{
		
		Parent setScene = FXMLLoader.load(getClass().getResource("MapViewer.fxml"));
		Stage new_Stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		new_Stage.setTitle("Map");
		new_Stage.setScene(new Scene(setScene, 1680, 1200));
		new_Stage.show();
		
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		
	}
	
}

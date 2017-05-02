/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class MiddleFormController implements Initializable {

	ScreensController myController;
	@FXML
	private RadioButton rbYes;
	@FXML
	private RadioButton rbNo;
	@FXML
	private ComboBox<String> Hear;
	@FXML
	private TextField TravelCity;

	@FXML
	private Label Destination_error;
	@FXML
	private Label empty_field;
	private Visitor visitor;
	private boolean error;
	
	@SuppressWarnings("restriction")
	ObservableList<String> slist = FXCollections.observableArrayList("Billboard", "Interstate Sign", "Other");

	/**
	 * Initializes the controller class.
	 */
	@SuppressWarnings("restriction")
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		visitor = VisitorContext.getInstance().currentVisitor();
		Hear.setItems(slist);
		
		if (visitor.getHeard() != null && !visitor.getHeard().equals("No Response")) {
			Hear.getSelectionModel().select(visitor.getHeard());
		}
		if (visitor.getDestination() != null && !visitor.getDestination().isEmpty()) {
			TravelCity.textProperty().setValue(visitor.getDestination());
		}

		if (visitor.getHotel() != null && !visitor.getHotel().isEmpty()) {
			if (visitor.getHotel().equals("Yes")) {
				rbYes.setSelected(true);
			} else if (visitor.getHotel().equals("No")) {
				rbNo.setSelected(true);
			}
		}

		System.out.println("Middle Form Lat: " + visitor.getLatitude());
		System.out.println("Middle Form Lat: " + visitor.getLongitude());
	}


	public void radioSelect(ActionEvent eve) {

		if (rbYes.isSelected()) {
		}

		else if (rbNo.isSelected()) {
		}
	}

	@SuppressWarnings("restriction")
	public void goBack(ActionEvent event) throws IOException {
		Parent newScene = FXMLLoader.load(getClass().getResource("BeginForm.fxml"));
		Stage new_Stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		new_Stage.setTitle("Your Information");
		new_Stage.setScene(new Scene(newScene, 1680, 1200));
		new_Stage.show();
	}

	@SuppressWarnings("restriction")
	@FXML
	private void goToScreen3(ActionEvent event) throws IOException 
	{
		destinationValidate(TravelCity, Destination_error);//checks if there's more than 128 chars in destination textbox
		
		System.out.println("Form2 visitor: " + visitor.getCity());
		visitor.setDestination(TravelCity.getText());

		/*********************************************************************
		 * Checks to see if the user selected from the "Heard" dropdown box.
		 * Sets this value to "No Response" if the user didn't select anything.
		 *********************************************************************/
		
		if (Hear.getSelectionModel().getSelectedItem() != null) {
			visitor.setHeard(Hear.getSelectionModel().getSelectedItem().toString());
		}
		else 
		{
			visitor.setHeard("No Response");
		}
		
		
		visitor.setDestination(TravelCity.getText());
		if (Hear.getSelectionModel().getSelectedItem() != null) {
			visitor.setHeard(Hear.getSelectionModel().getSelectedItem().toString());
		}
		else 
		{
			visitor.setHeard("No Response");
		}

		if (rbYes.isSelected()) {
			visitor.setHotel("Yes");
		} else {
			visitor.setHotel(null);
		}
		if (rbNo.isSelected()) {
			visitor.setHotel("No");
		} else {
			visitor.setHotel(null);
		}


		/****************************************************************************
		 * When the user inputs more than 128 characters in the "Destination" textbox,
		 * a warning label appears when said user tries to go to the next page. The
		 * user will be able to move on to the next page otherwise. 
		 ****************************************************************************/
		
		if (error == true)
		{
			Destination_error.setText("Please use no more than 128 characters");
		}
		
		if (error == false)
		{
		// myController.setScreen(SoftwareEngineering.screen3ID);
		Parent newScene = FXMLLoader.load(getClass().getResource("EndForm.fxml"));
		Stage new_Stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		new_Stage.setTitle("Your Information");
		new_Stage.setScene(new Scene(newScene, 1680, 1200));
		new_Stage.show();
		}
	}

	/****************************************************************************
	 * This method checks to see if the textbox entry is more than 128 characters.
	 ****************************************************************************/
	
	public void destinationValidate(TextField TravelCity, Label Destination_error) 
	{
		if (TravelCity.getText().length() > 128)
		  {
			 error = true;
		  }
		else
		{
			error = false;
			Destination_error.setText("");
		}
	}
	
	
	public void goHome(ActionEvent event) throws IOException{
		
		JDBC.addVisitor(visitor);
		
		Parent newScene = FXMLLoader.load(getClass().getResource("Map.fxml"));
		Stage new_Stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		new_Stage.setTitle("Your Information");
		new_Stage.setScene(new Scene(newScene, 1680, 1200));
		new_Stage.show();	
	}
}
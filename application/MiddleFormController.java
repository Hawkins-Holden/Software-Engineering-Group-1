/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class MiddleFormController implements Initializable, ControlledScreen {

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
	
	
	ObservableList<String> slist = FXCollections.observableArrayList("Billboard", "Interstate Sign", "Other");
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Hear.setItems(slist);

    }
    
    public void setScreenParent(ScreensController screenParent){
        myController = screenParent;
         
    }
    
    	public void destinationValidate(TextField TravelCity, Label Destination_error) {

		if (TravelCity.getText() != null && !TravelCity.getText().matches("[a-zA-Z ]+") && !TravelCity.getText().isEmpty()) {
			Destination_error.setText("Please enter a valid City!");
		} else {
			Destination_error.setText("");
		}

	}
	
	
	public void radioSelect(ActionEvent eve) {

		if (rbYes.isSelected()) {
		}

		else if (rbNo.isSelected()) {
		}
	}


    @FXML
    private void goToScreen1(ActionEvent event){
        
       myController.setScreen(SoftwareEngineering.screen1ID);
    }
    
    @FXML
    private void goToScreen3(ActionEvent event){
        destinationValidate(TravelCity, Destination_error);
		
		if (Destination_error.getText().isEmpty() && (!Hear.getSelectionModel().isEmpty() || 
				!TravelCity.getText().isEmpty() || rbYes.isSelected() || rbNo.isSelected())) {
			 myController.setScreen(SoftwareEngineering.screen3ID);
		}
                
                else {
                    if (Destination_error.getText().isEmpty()){
                    empty_field.setText("Your information will not be shared. Please enter your destination.");
                    }
                }
      
    }
}


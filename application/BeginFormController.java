/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;





/**
 *
 * @author admin
 */
public class BeginFormController implements Initializable, ControlledScreen {
    
   ScreensController myController;
   
        @FXML 
        private BorderPane mainBody; 
	@FXML
	private TextField Fname;
	@FXML
	private TextField Lname;
	@FXML
	private Label Fname_error;
	@FXML
	private Label Lname_error;
    @FXML
	private Label empty_field;
	@FXML
	private ComboBox<String> State;
	/*@FXML
	private Label message;*/

	private String firstName;
	private String lastName;
	ObservableList<String> state_list = FXCollections.observableArrayList("AL", "AK", "AZ", "AR", "CA", "CO", "CT",
			"DC", "DE", "FL", "GA", "HI", "ID", "IL", "IN", "IA", "KS", "KY", "LA", "ME", "MD", "MA", "MI", "MN", "MS",
			"MO", "MT", "NE", "NV", "NH", "NJ", "NM", "NY", "NC", "ND", "OH", "OK", "OR", "PA", "RI", "SC", "SD", "TN",
			"TX", "UT", "VT", "VA", "WA", "WV", "WI", "WY");


    /**
     * Initializes the controller class.
     */
   
   
    public void setScreenParent(ScreensController screenParent){
        myController = screenParent;
    }
    
     @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        State.setItems(state_list);    
        setWelcomeMessage();
    }

    @FXML
    private void goToScreen2(ActionEvent event){
        
                firstName = Fname.getText();
		lastName = Lname.getText();
		fnameValidate(Fname, Fname_error);
		lnameValidate(Lname, Lname_error);

		if ((Fname_error.getText().isEmpty() && Lname_error.getText().isEmpty())
				&& ((!Fname.getText().isEmpty() || !Lname.getText().isEmpty()))) {	
                myController.setScreen(SoftwareEngineering.screen2ID);
    }
                
                else {
                    if (Fname_error.getText().isEmpty() && Lname_error.getText().isEmpty()){
                    empty_field.setText("Your information will not be shared. Please enter your name.");
                    }
                }
                
                }
    
    
	public void fnameValidate(TextField Fname, Label Fname_error) {

		if (Fname.getText() != null && !Fname.getText().matches("[a-zA-Z]+") && !Fname.getText().isEmpty()) {
			Fname_error.setText("Please enter a valid First Name!");
		} else {
			Fname_error.setText("");
		}

	}
	
	private void setWelcomeMessage(){
		
		String[] locationInfo = new String[15];
		int i = 0;
		
		try {
			
			//Get visitor location to perform analytics on it
			System.out.println("file read attempted");
			Scanner scan = new Scanner(new File("LocationOfVisitor.txt"));
			while (scan.hasNext() && i < 15) {
				locationInfo[i] = (scan.nextLine());
				i++;
			}
			i = 0;
			scan.close();
			
			//Check if location is inside the US
			if(locationInfo[3].equals("USA")){
				
				//If so, perform analytics
				String city = locationInfo[1];
				String state = locationInfo[2];
				
				//String message = checkDataBase(city, state);//
				//messageLabel.setText(message);
				
				
			}
			
			
		} catch (Exception e) {
			// cry
			System.out.println("file n");
		}
		
	}

	public void lnameValidate(TextField Lname, Label Lname_error) {

		if (Lname.getText() != null && !Lname.getText().matches("[a-zA-Z]+") && !Lname.getText().isEmpty()) {
			Lname_error.setText("Please enter a valid Last name!");
		} else {
			Lname_error.setText("");
		}

	}
    
    @FXML
    private void goToScreen3(ActionEvent event){
       myController.setScreen(SoftwareEngineering.screen3ID);
    }
}

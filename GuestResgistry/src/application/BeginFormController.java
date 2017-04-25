/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import org.json.JSONException;
import java.io.BufferedWriter;


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
	private TextField State;
	@FXML
	private TextField CIty;  //This must be a TextField, not a Label, or it WILL NOT WORK
	@FXML
	private TextField Country; //This must be a TextField, not a Label, or it WILL NOT WORK
	@FXML
	private TextField ZipC; //This must be a TextField, not a Label, or it WILL NOT WORK
	/*@FXML
	private Label messageLabel;*/

	private String firstName;
	private String lastName;
	ObservableList<String> state_list = FXCollections.observableArrayList("AL", "AK", "AZ", "AR", "CA", "CO", "CT",
			"DC", "DE", "FL", "GA", "HI", "ID", "IL", "IN", "IA", "KS", "KY", "LA", "ME", "MD", "MA", "MI", "MN", "MS",
			"MO", "MT", "NE", "NV", "NH", "NJ", "NM", "NY", "NC", "ND", "OH", "OK", "OR", "PA", "RI", "SC", "SD", "TN",
			"TX", "UT", "VT", "VA", "WA", "WV", "WI", "WY");
	private String[] info = new String[20];


    /**
     * Initializes the controller class.
     */
   
   
    public void setScreenParent(ScreensController screenParent){
        myController = screenParent;
    }
    
     @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //State.setItems(state_list);    
        setWelcomeMessage();
    }

    @FXML
    private void goToScreen2(ActionEvent event) throws IOException{
        
        	//-----------------
			Parent newScene = FXMLLoader.load(getClass().getResource("MiddleForm.fxml"));
			Stage new_Stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			new_Stage.setTitle("Your Information");
			new_Stage.setScene(new Scene(newScene, 1680, 1200));
			new_Stage.show();
			//-----------------
        
        firstName = Fname.getText();
		lastName = Lname.getText();
		//fnameValidate(Fname, Fname_error);
		//lnameValidate(Lname, Lname_error);

		/*if ((Fname_error.getText().isEmpty() && Lname_error.getText().isEmpty())
				&& ((!Fname.getText().isEmpty() || !Lname.getText().isEmpty()))) {
                //myController.setScreen(SoftwareEngineering.screen2ID);
		*/	

			
    /*}
                
                else {
                    if (Fname_error.getText().isEmpty() && Lname_error.getText().isEmpty()){
                    empty_field.setText("Don't worry, we only keep track of this for our own department. Please fill out all fields.");
                    }
                }*/
                
                }
    
    
	public void fnameValidate(TextField Fname, Label Fname_error) {

		if (Fname.getText() != null && !Fname.getText().matches("[a-zA-Z]+") && !Fname.getText().isEmpty()) {
			Fname_error.setText("Please enter a valid First Name!");
		} else {
			Fname_error.setText("");
		}

	}
	
	private void setWelcomeMessage() {

		String[] locationInfo = new String[15];
		int i = 0;

		try {

			// Get visitor location to perform analytics on it
			System.out.println("file read attempted");
			Scanner scan = new Scanner(new File("LocationOfVisitor.txt"));
			while (scan.hasNext() && i < 15) {
				locationInfo[i] = (scan.nextLine());
				info[i] = locationInfo[i];
				System.out.println("These are the things: " + locationInfo[i]);
				i++;
			}
			
			System.out.println(locationInfo[0] + ",  " + locationInfo[1]);
			i = 0;
			scan.close();
			

			// Check if location is inside the US
			if (locationInfo[3].equals("USA")) {

				// If so, perform analytics
				//1 is the city, 2 the state, 3 the country (if US), 4 the type (not needed), 5 the zip code, and 6 is the metropolitan area.
				//7 and up are for other things.
				String city = locationInfo[1];
				String zip = locationInfo[5];
				String state = locationInfo[2];
				String metro = locationInfo[6];
				System.out.println("This is City: " + city + "\n" + "This is the state: " + state + "\n" + "This is the country: " + locationInfo[3] + "\n" + "This is [4]: " + locationInfo[4]
				+ "\nThis is [5]: " + locationInfo[5] + "\nThis is [6]: " + locationInfo[6] + "\nThis is [7]: " + locationInfo[7] );
				
				State.setText(state);
				CIty.setText(city);
				Country.setText(locationInfo[3]);
				ZipC.setText(zip);
				
				//State.setAccessibleText(state);
				// String message = checkDataBase(city, state);//
				// messageLabel.setText(message);
				
				PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("LocationOfVisitor.txt")));
				
			while(i < locationInfo.length && locationInfo[i] != null){
			System.out.println("This is a thing: " + locationInfo[i]);
			writer.println(locationInfo[i]);
			i++;
		}

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

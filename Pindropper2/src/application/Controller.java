package application;

import java.beans.EventHandler;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import org.json.JSONException;

import com.sun.media.jfxmediaimpl.platform.Platform;

//import application2.Main2;
//import application2.Browser.JavaApplication;
//import application.Browser.JavaApplication;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker.State;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.*;
import netscape.javascript.JSObject;

public class Controller implements Initializable {

	@FXML
	private WebView map;
	@FXML
	private Button submit;
	@FXML
	private TextField inputFname;
	@FXML
	private TextField inputMname;
	@FXML
	private TextField inputLname;
	@FXML
	private TextField inputEmail;
	@FXML
	private TextField inputDestination;
	@FXML
	private TextField inputParty;
	@FXML
	private Button closeButton;
	@FXML
	private ComboBox<String> reasonBox;
	@FXML
	private ComboBox<String> purposeBox;
	@FXML
	private RadioButton yesID;
	@FXML
	private RadioButton noID;
	@FXML
	private CheckBox inBox;
	private boolean choice;
	private boolean option;

	public void setData() {

		reasonBox.setStyle("-fx-font-weight:bold;");
		purposeBox.setStyle("-fx-font-weight:bold;");

		if (reasonBox.getValue() == null) {
			reasonBox.getItems().addAll("Billboard", "Interstate Sign", "Other");
		}

		if (reasonBox.getValue() == null && purposeBox.getValue() == null) {
			purposeBox.getItems().addAll("Business", "Pleasure", "Convention", "Other");
		}
	}

	public boolean choiceButton(ActionEvent e) {

		ToggleGroup group = new ToggleGroup();
		yesID.setToggleGroup(group);
		yesID.setSelected(true);
		noID.setToggleGroup(group);

		if (yesID.isSelected()) {
			choice = true;
		} else {
			choice = false;
		}

		return choice;
	}

	public boolean checkButton(ActionEvent m) {

		if (inBox.isSelected()) {
			option = true;
		} else {
			option = false;
		}

		return option;
	}

	public void SubmitButton(ActionEvent event) {
		String Fname = inputFname.getText();
		String Mname = inputMname.getText();
		String Lname = inputLname.getText();
		String Email = inputEmail.getText();
		String Destination = inputDestination.getText();
		String Party = inputParty.getText();
		int numPeople = Integer.parseInt(Party);
		String Reason = reasonBox.getSelectionModel().getSelectedItem().toString();
		String Purpose = purposeBox.getSelectionModel().getSelectedItem().toString();
		boolean Radio = choice;
		boolean Check = option;
	}

	public void openNext() {

		Stage window = (Stage) closeButton.getScene().getWindow();

	}

	public void exitButton() {

		Stage window = (Stage) closeButton.getScene().getWindow();
		window.close();
	}

	public void clearButton(ActionEvent event) {
		inputFname.clear();
		inputMname.clear();
		inputLname.clear();
		inputEmail.clear();
		inputDestination.clear();
		inputParty.clear();
	}

	public void changeScene(ActionEvent f) {

		// make a WebEngine instance for manipulation of the WebView "map".
		WebEngine webengine = makeEngine(false);
		
		//System.out.println("somewhere is got, we have");
		
		if (webengine != null) {
			//System.out.println("again somewhere is got, we have");
			webengine.executeScript("setLocations(-25.363, 131.044)");
		}
		

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		// initialize the map
		makeEngine(true);

		//populateMap(makeEngine(false));

	}

	private WebEngine makeEngine(boolean trigger) {

		// make a WebEngine instance for manipulation of the WebView "map".
		WebEngine webEngine = map.getEngine();

		if(trigger == true){
		webEngine.getLoadWorker().stateProperty().addListener(new ChangeListener<State>() {

			@Override
			public void changed(ObservableValue<? extends State> ov, State oldState, State newState) {
				if (newState == State.SUCCEEDED) {
					JSObject window = (JSObject) webEngine.executeScript("window");
					window.setMember("app", new JavaApplication());
				}
				if(newState != null){
					//working on it
				}
			}
			
		});

		JSObject window = (JSObject) webEngine.executeScript("window");
		window.setMember("app", new JavaApplication());
		

		// load appropriate file
		java.net.URL num = getClass().getResource("Map.html");
		webEngine.load(num.toString());
		}
		return webEngine;
	}
	
	/**
	 * Takes the latitude and longitude pairs stored in the database and uses them to place pins on the map
	 * @param web The WebEngine for the current page in use.
	 */
	private void populateMap(WebEngine web){
		
		ArrayList<String> latLongs = new ArrayList<String>();
		String temp = "";
		int temper = 0;
		
		//pull from "database"
		latLongs = getFromJDBC.getLatLongs();
		temp = latLongs.get(0);
		
		/*System.out.println("getting to the communication");
		
		while(temper < latLongs.size()){
			web.executeScript("addToArray(element);");
			temper++;
		}
		
		System.out.println("past the communication");*/
		
		
		
		//Tell JavaScript to place the pins
		
		System.out.println("getting to the communication");
		
		//web.executeScript("createMarker(test)");
		web.executeScript("callJavaFX();");
		
		System.out.println("past the communication");
			
	}
	
	private ArrayList<String> getLocations(){
		return getFromJDBC.getLatLongs();
	}

	public class JavaApplication {
		public double getString(int i){
			System.out.println("called getString()");
			ArrayList<String> latLongArray = new ArrayList<String>();
			latLongArray = getLocations();
			double number = 0.1;
			number = Double.parseDouble(latLongArray.get(i));
			System.out.println(number);
			return number;
		}
		public void callFromJavascript(String coords) throws JSONException {
			//System.out.println(Serializer.getAddress(GeoCode.reverseGeoCode(coords)));
			System.out.println(coords);
		}
		public void testCall(String sumpthang){
			System.out.println(sumpthang);
			//populateMap(makeEngine(false));
			//System.out.println("called populate Map");
		}
	}
}

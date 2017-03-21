package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import org.json.JSONException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker.State;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import netscape.javascript.JSObject;

public class MapController implements Initializable {

	@FXML
	private WebView map;
	@FXML
	private Button submit;
	
	/**Used to communicate with the main method that is running*/
	static Main main;
	
	private String address = "";
	
	/**
	 * Called from Main.java, this lets the controller know the instance of main to communicate with.
	 * @param newMain The instance of Main that will be communicating with this controller.
	 */
	public static void setMain(Main newMain){
		main = newMain;
}
	
	
	public void changeScene(ActionEvent event) throws IOException
	{
		
		Parent newScene = FXMLLoader.load(getClass().getResource("GuestBook.fxml"));
		Stage new_Stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		new_Stage.setTitle("Success!");
		new_Stage.setScene(new Scene(newScene, 1680, 1200));
		new_Stage.show();
		
	}
	
	public void initialize(URL location, ResourceBundle resources) {
		makeEngine(true);
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
	
	
	
	private ArrayList<String> getLocations(){
		return GetFromJDBC.getLatLongs();
	}
	
	/**
	 * Required to communicate with the javascript that runs the map, contains methods to populate the map
	 * and use the information sent back from the map (via javascript) to store lat-long pairs and addresses.
	 *
	 */
	public class JavaApplication {
		public int getSize(){
			
			ArrayList<String> latLongArray = new ArrayList<String>();
			latLongArray = getLocations();
			return latLongArray.size();
			
		}
		public double getString(int i){
			
			ArrayList<String> latLongArray = new ArrayList<String>();
			latLongArray = getLocations();
			double number = 0.1;
			number = Double.parseDouble(latLongArray.get(i));
			return number;
			
		}
		public void callFromJavascript(String coords) throws JSONException {
			String[] latLongPair = new String[2];
			latLongPair = GeoCoding.getLatLong(coords);
			//System.out.println(SerializeJson.getAddress(GeoCoding.reverseGeoCode(coords)));
			
		}
		public void testCall(String message1){
			System.out.println(message1);
		}
	}
}

/*
 * Takes the latitude and longitude pairs stored in the database and uses them to place pins on the map
 * @param web The WebEngine for the current page in use.
 
private void populateMap(WebEngine web){
	
	ArrayList<String> latLongs = new ArrayList<String>();
	String temp = "";
	int temper = 0;
	
	//pull from "database"
	latLongs = GetFromJDBC.getLatLongs();
	temp = latLongs.get(0);
	
	System.out.println("getting to the communication");
	
	while(temper < latLongs.size()){
		web.executeScript("addToArray(element);");
		temper++;
	}
	
	System.out.println("past the communication");
	
	
	
	//Tell JavaScript to place the pins
	
	System.out.println("getting to the communication");
	
	//web.executeScript("createMarker(test)");
	web.executeScript("callJavaFX();");
	
	System.out.println("past the communication");
		
}*/ //Kept as a reference
	

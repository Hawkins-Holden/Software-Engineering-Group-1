package Pindropper4;

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

public class MapController implements Initializable {

	@FXML
	private WebView map;
	@FXML
	private Button submit;
	
	static Main main;
	
	public static void setMain(Main newMain){
		main = newMain;
	}

	public void changeScene(ActionEvent f) throws Exception{

		
		/*
		// make a WebEngine instance for manipulation of the WebView "map".
		WebEngine webengine = makeEngine(false);
		
		//System.out.println("somewhere is got, we have");
		
		if (webengine != null) {
			//System.out.println("again somewhere is got, we have");
			webengine.executeScript("setLocations(-25.363, 131.044)");
		}
		*/
		
		main.setTarget("Form.fxml");

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
	
	
	
	private ArrayList<String> getLocations(){
		return GetFromJDBC.getLatLongs();
	}

	public class JavaApplication {
		public int getSize(){
			ArrayList<String> latLongArray = new ArrayList<String>();
			latLongArray = getLocations();
			return latLongArray.size();
		}
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
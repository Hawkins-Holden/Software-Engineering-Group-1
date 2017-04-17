package application;

import java.util.ArrayList;

import org.json.JSONException;

public class JavascriptComm {

	public int getSize(){
		
		ArrayList<String> latLongArray = new ArrayList<String>();
		latLongArray = getLocations();
		System.out.println("size retrieved");
		return latLongArray.size();
		
	}
	
	public double getString(int i){
		
		ArrayList<String> latLongArray = new ArrayList<String>();
		latLongArray = getLocations();
		double number = 0.1;
		number = Double.parseDouble(latLongArray.get(i));
		System.out.println("lat or long retrieved: " + number);
		return number;
		
	}
	
	public void callFromJavascript(String coords) throws JSONException  {
		
		System.out.println("Pin creation attempted");
		String[] latLongPair = new String[2];
		latLongPair = GeoCoding.getLatLong(coords);
		TestArray.sendLatLongs(latLongPair);
		LocationInfoExtractor.extractLocationInfo(SerializeJson.getAddress(GeoCoding.reverseGeoCode(coords)));
		
	}
	
	public void testCall(String message1){
		System.out.println(message1);
	}
	
	private ArrayList<String> getLocations(){
		return TestArray.getLatLongs();
	}
	
}

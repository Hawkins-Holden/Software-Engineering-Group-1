package application;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
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
	
	public void callFromJavascript(String coords) throws JSONException, IOException  {
		
		String[] addressElements = new String[15];
		int i = 0;
		
		//Clear out the test file this will be written to
		//FileWriter fw = new FileWriter(new File("LocationOfVisitor.txt"));
		//BufferedWriter writer = new BufferedWriter(fw);
		
		PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("LocationOfVisitor.txt")));
		
		//Convert the Lat Long pair into a usable address; and then write the address to a text file for later use
		System.out.println("Pin creation attempted: " + coords);
		String[] latLongPair = new String[2];
		latLongPair = GeoCoding.getLatLong(coords);
		TestArray.sendLatLongs(latLongPair);
		addressElements = LocationInfoExtractor.extractLocationInfo(SerializeJson.getAddress(GeoCoding.reverseGeoCode(coords)));
		
		while(i < addressElements.length && addressElements[i] != null){
			writer.println(addressElements[i]);
			i++;
		}
		
		writer.close();
	}
	
	public void testCall(String message1) throws JSONException, IOException{
		System.out.println(message1);
		callFromJavascript(message1);
	}
	
	public void testWrite(String message) throws IOException{
		

		File test = new File("LocationOfVisitor.txt");
		test.createNewFile();
		
		PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(test)));
		writer.println(message);
		writer.println(message + " 4");
		writer.println(message + " 4" + " 5");
		writer.close();

				
	}
	
	private ArrayList<String> getLocations(){
		return TestArray.getLatLongs();
	}
	
}

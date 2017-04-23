package adminApplication;

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
		
		ArrayList<String> latLongArray =  getLocations();
		double number = 0.1;
		number = Double.parseDouble(latLongArray.get(i));
		System.out.println("lat or long retrieved: " + number);
		return number;
		
	}
	
	private ArrayList<String> getLocations(){
		ArrayList<String> latlngs = new ArrayList<String>();
		for(String[] latlng: AnalyticsController.getLatLongData())
		{
			latlngs.add(latlng[0] + ", " + latlng[1]);
		}
		return latlngs;
	}
	
}

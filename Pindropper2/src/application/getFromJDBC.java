package application;

import java.util.ArrayList;

/**
 * A helper class used by the Controller.java class. Essentially takes in the latitude and longitude pairs from the database and returns them in an arraylist.
 * @author Aaron Cole
 *
 */
public class getFromJDBC {

	/**
	 * Takes in the latitude and longitude pairs from the database and returns them in an arraylist.
	 * @return latLongs An arraylist (of strings) containing the latitude and longitude pairs from the database.
	 */
	public static ArrayList<String> getLatLongs(){
		
		ArrayList<String> latLongs = new ArrayList<String>();
		
		/*This will be changed later, but for now it is just a static arraylist that I made up*/
		latLongs.add("41.9022770409637");
		latLongs.add("-93.515625");
		latLongs.add("13.89");
		latLongs.add("1");
		
		return latLongs;
		
	}
	
}

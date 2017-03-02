package src.application;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;

public class GeoCode {

	public static String[] setLatLong(String nums){
		
		String temper = "";
		String temp[] = new String[2];
		
		temper = nums.substring(1);
		temper = temper.substring(0, temper.length() - 1);
		
		Scanner scan = new Scanner(temper);
		scan.useDelimiter(", ");
		
		temp[0]=scan.next();
		temp[1]=scan.next();
		
		return temp;
	}
	
	public String getJSON(String nums) throws MalformedURLException, IOException {

		URLConnection tempest;
		String temp[] = new String[2];
		temp = setLatLong(nums);
		String a = "https://maps.googleapis.com/maps/api/geocode/json?latlng=" + temp[0] + "," + temp[1]
				+ "&key=AIzaSyBuT9VzPqnYSaB5Os0U4mkI8C7CiuFzwao";
		URL url = new URL(a);
		
		tempest = url.openConnection();

		return tempest.toString();
		
	}
	
}

package application;

import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;


public class SerializeJson {

	public static String[] getAddress(String jsonObject) throws JSONException{
		System.out.println(jsonObject);
		
		String[] returner = new String[2];
		String temp1 = "";
		String temp2 = "";
		String metro = "";
		int num1 = 0;
		int beginIndex = 0;
		int endIndex = 0;
		
		num1 = jsonObject.indexOf("Metropolitan");

		if (num1 != -1) {
			temp1 = jsonObject.substring(0, num1 + 1);
			temp2 = jsonObject.substring(num1);
			beginIndex = temp1.lastIndexOf('"') + 1;
			endIndex = temp2.indexOf('"') + beginIndex + 18;

			metro = jsonObject.substring(beginIndex, endIndex);
			System.out.println("This is Metro: " + metro);
			returner[0] = metro;
		} else {
			returner[0] = "";
		}
		
		JSONObject json = new JSONObject(jsonObject);
		HashMap<String, Object> jsonMap = new HashMap<String, Object>();
		int tempest = 0;
		
		jsonMap = JsonToMap.jsonToMap(json);
		
		tempest = jsonMap.get("results").toString().indexOf("geometry");
		System.out.println(tempest);
		
		
		returner[1] = jsonMap.get("results").toString().substring(20, tempest-1); //"formatted address_ starts at index 2		
		System.out.println("This is returner: " + returner);
		
		return returner;
		
	}
	
}
